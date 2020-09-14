package kr.mem.model;
import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {
	
	// dao는 데이터베이스에 메소드를 만들어주는 역할 --> 필요로 하는 객체들 
	
	private Connection conn; 		// 연결객체 --> conn
	private PreparedStatement psmt;	// sql 명령문 전송 --> psmt
	private ResultSet rs; 			// select 를 날리면 전체 레코드 값 담아서 저장 --> 결과의 집합 처리
	
	// 동적로딩 --> 왜 동적로딩하는가? 실행할때 클래스에다 메모리를 올려주려고 comfile 시점이 아니라, 여기서 바로 하는 것
	// 초기화 블럭 : static  --> 맨먼저 한번 실행
	static {
		try {						// DriverManager
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	// 연결객체  --> 접속 url 잘못쓰지 말것!   --> finally구문 동적로딩 시에는 필요 없음
	public Connection getConnect() {
		String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user="hr";
		String password="hr";
		
		// DriverManager 안에 있는 애들은 다 Static
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 리턴값 지정해주기
		return conn;  
	}
	
	// 먼저 인터페이스를 만들고 구현하는 작업이 필요하다! 하지만 여기서는 이거부터!
	public int memberInsert( MemberVO vo ) {
		
		conn = getConnect();
		// sql 명령문을 바꾸는 작업이 가장 귀찮은 작업 --> 별도의 방법은 무엇? 
		// ==> 예를들면,  java에서 빠져나온 sql쿼리구문을 빼서 연결해서 사용하는 프레임워크 : MyBatis 
		String SQL = "insert into tblMem values (seq_num.nextval,?,?,?,?,?)";
		int cnt = -1;  // -1의 의미는 실패!
		
		try {
			// 먼저 컴파일을 시킨다 --> 속도를 빠르게 하기 위함, 그리고 두번째 설정
			psmt = conn.prepareStatement(SQL); 
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getPhone());
			psmt.setString(3, vo.getAddr());
			psmt.setDouble(4, vo.getLat());
			psmt.setDouble(5, vo.getLng());
			
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	
	// ArrayList 저장할때 업캐스팅이나 다운캐스팅을 해줘야 한다.
	public ArrayList<MemberVO> memberAllList() {
		
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		conn = getConnect();
		String SQL = "select * from tblMem order by num desc";
		
		try {
			psmt = conn.prepareStatement(SQL);
			// 물음표 설정이 필요 없음!! --> 파라미터 설정값 없음! "SQL"
			rs = psmt.executeQuery();
			// 선 조건 후 처리
			while(rs.next()) {
				// ---> rs 는 컬럼을 가리키고 있기 때문에, 있으면 True 없으면 False 니까 True면 가지고 와야 한다.
				int num =rs.getInt("num");
				String name =rs.getString("name");
				String phone =rs.getString("phone");
				String addr =rs.getString("addr");
				double lat =rs.getDouble("lat");
				double lng =rs.getDouble("lng");
				MemberVO vo = new MemberVO(num,name,phone,addr,lat,lng);  // 묶고
				list.add(vo);  // 담고
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	
	public int memberDelete(int num) {
		conn = getConnect();
		String SQL = "delete from tblMem where num=?";
		int cnt = -1;
		try {
			psmt=conn.prepareStatement(SQL);
			psmt.setInt(1, num);
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}
	
	public MemberVO memberContent(int num) {
		MemberVO vo=null;
		conn=getConnect();
		String SQL = "select * from tblMem where num=?";
		try {
			psmt = conn.prepareStatement(SQL);
			psmt.setInt(1, num);
			rs=psmt.executeQuery();
			if(rs.next()) {  // 개별로 가지고 와서, 
				num =rs.getInt("num");
				String name =rs.getString("name");
				String phone =rs.getString("phone");
				String addr =rs.getString("addr");
				double lat =rs.getDouble("lat");
				double lng =rs.getDouble("lng");
				vo = new MemberVO(num,name,phone,addr,lat,lng);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return vo;
	}
	
	public int memberUpdate(MemberVO vo) {
		conn=getConnect();
		String SQL = "update tblMem set phone=?, addr=? where num=?";
		int cnt=-1;
		try {
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, vo.getPhone());
			psmt.setString(2, vo.getAddr());
			psmt.setInt(3, vo.getNum());
			cnt=psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return cnt;
		
	}
	
	// Close 메서드
	public void dbClose() {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(psmt!=null) {
				psmt.close();
			}
			if(conn!=null) {
				conn.close();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
