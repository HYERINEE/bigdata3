package kr.mem.model;
import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {
	
	// dao�� �����ͺ��̽��� �޼ҵ带 ������ִ� ���� --> �ʿ�� �ϴ� ��ü�� 
	
	private Connection conn; 		// ���ᰴü --> conn
	private PreparedStatement psmt;	// sql ��ɹ� ���� --> psmt
	private ResultSet rs; 			// select �� ������ ��ü ���ڵ� �� ��Ƽ� ���� --> ����� ���� ó��
	
	// �����ε� --> �� �����ε��ϴ°�? �����Ҷ� Ŭ�������� �޸𸮸� �÷��ַ��� comfile ������ �ƴ϶�, ���⼭ �ٷ� �ϴ� ��
	// �ʱ�ȭ �� : static  --> �Ǹ��� �ѹ� ����
	static {
		try {						// DriverManager
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	// ���ᰴü  --> ���� url �߸����� ����!   --> finally���� �����ε� �ÿ��� �ʿ� ����
	public Connection getConnect() {
		String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user="hr";
		String password="hr";
		
		// DriverManager �ȿ� �ִ� �ֵ��� �� Static
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ���ϰ� �������ֱ�
		return conn;  
	}
	
	// ���� �������̽��� ����� �����ϴ� �۾��� �ʿ��ϴ�! ������ ���⼭�� �̰ź���!
	public int memberInsert( MemberVO vo ) {
		
		conn = getConnect();
		// sql ��ɹ��� �ٲٴ� �۾��� ���� ������ �۾� --> ������ ����� ����? 
		// ==> �������,  java���� �������� sql���������� ���� �����ؼ� ����ϴ� �����ӿ�ũ : MyBatis 
		String SQL = "insert into tblMem values (seq_num.nextval,?,?,?,?,?)";
		int cnt = -1;  // -1�� �ǹ̴� ����!
		
		try {
			// ���� �������� ��Ų�� --> �ӵ��� ������ �ϱ� ����, �׸��� �ι�° ����
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
	
	// ArrayList �����Ҷ� ��ĳ�����̳� �ٿ�ĳ������ ����� �Ѵ�.
	public ArrayList<MemberVO> memberAllList() {
		
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		conn = getConnect();
		String SQL = "select * from tblMem order by num desc";
		
		try {
			psmt = conn.prepareStatement(SQL);
			// ����ǥ ������ �ʿ� ����!! --> �Ķ���� ������ ����! "SQL"
			rs = psmt.executeQuery();
			// �� ���� �� ó��
			while(rs.next()) {
				// ---> rs �� �÷��� ����Ű�� �ֱ� ������, ������ True ������ False �ϱ� True�� ������ �;� �Ѵ�.
				int num =rs.getInt("num");
				String name =rs.getString("name");
				String phone =rs.getString("phone");
				String addr =rs.getString("addr");
				double lat =rs.getDouble("lat");
				double lng =rs.getDouble("lng");
				MemberVO vo = new MemberVO(num,name,phone,addr,lat,lng);  // ����
				list.add(vo);  // ���
				
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
			if(rs.next()) {  // ������ ������ �ͼ�, 
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
	
	// Close �޼���
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
