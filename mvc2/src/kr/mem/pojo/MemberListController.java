package kr.mem.pojo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;

public class MemberListController implements Controller{

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// 1. 전체리스트를 가져오기
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = dao.memberAllList();
		
		// 2. 객체바인딩
		request.setAttribute("list", list);
		
		// 3. VIEW 페이지로  	--> member/memberList.jsp
		//				  	--> 알바생한테 맡기지 않고, 포워딩은 프론트에서!
		//					--> 포워딩할 뷰페이지 이름 넘겨주기! 그거슨 Return!
		return "member/memberList.jsp";
	}


	
	

}
