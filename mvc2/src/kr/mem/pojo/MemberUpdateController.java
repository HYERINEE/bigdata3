package kr.mem.pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;

public class MemberUpdateController implements Controller {

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ctxPath = request.getContextPath();
		int num = Integer.parseInt(request.getParameter("num"));
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");

		MemberVO vo = new MemberVO();
		// setter 세개로 묶어준댬! --> 풀어주고!!
		vo.setNum(num);
		vo.setPhone(phone);
		vo.setAddr(addr);

		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberUpdate(vo);
		
		String page = null;
		if (cnt > 0) {
			// 다시 리다이렉트로 성공하면 리스트로 돌아가기!
			page="redirect:"+ctxPath+"/list.do";
		} else {
			throw new ServletException("error");
		}

		return page;
	}

}
