package kr.mem.model;

public class MemberVO {

	// 회원이라는 모델의 상태정보를 입력! --> database를 보고 알수 있음
	// "sql의 table 구조"와 "일맥상통"함 --> VO도 이와 같이 만듦!

	private int num;
	private String name;
	private String phone;
	private String addr;
	private double lat;
	private double lng;

	// 디폴트 생성자
	public MemberVO() {
	}
	

	public MemberVO(int num, String name, String phone, String addr, double lat, double lng) {
		super();
		this.num = num;
		this.name = name;
		this.phone = phone;
		this.addr = addr;
		this.lat = lat;
		this.lng = lng;
	}


	public MemberVO(String name, String phone, String addr) {
		super();
		this.name = name;
		this.phone = phone;
		this.addr = addr;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	// 만든이유는? --> 디버깅 : 바구니안에 데이터가 잘 들어있는지 안들어있는지 확인하기 위해서
	// --> 모든 값을 한번에 리턴해주니까! 있으면 좋아요!
	@Override
	public String toString() {
		return "MemberVO [num=" + num + ", name=" + name + ", phone=" + phone + ", addr=" + addr + ", lat=" + lat
				+ ", lng=" + lng + "]";
	}

}
