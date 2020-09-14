package kr.mem.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.pojo.Controller;
import kr.mem.pojo.MemberDeleteController;
import kr.mem.pojo.MemberInsertController;
import kr.mem.pojo.MemberInsertFormController;
import kr.mem.pojo.MemberListController;



public class MemberFrontController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("euc-kr");

		// ** 클라이언트가 무슨 요청을 했는지 알려주는 작업!!!!!!!!!!!

		// 1. 어떤 요청인지 파악하는 작업 --> ?.do
		// 전체경로
		String reqUrl = request.getRequestURI();
		System.out.println(reqUrl);

		// 선택한 경로만 나옴 --> context를 찾는 것 : 지정된 걸 자동으로 가져오는!!!!
		String ctxPath = request.getContextPath();
		System.out.println(ctxPath);

		// 클라이언트가 요청한 명령
		// 가져오는 것! --> 시작위치 직접 지정!
		// 클라이언트가 무슨 요청을 했는지 알 수 있는지 없는지 요청!!!!!!!! * 중에서 어떤 요청!?
		String command = reqUrl.substring(ctxPath.length());

		// 인터페이스 선언부
		Controller controller = null;
		String nextView = null;
		
		HandlerMapping mappings = new HandlerMapping();
		controller = mappings.getController(command);
		nextView = controller.requestHandle(request, response);

		// 각 요청에 따라 처리하기(분기작업)
		
		// 핸들러 매핑 --> HandlerMapping
		// *.do --> Member "*" Controller
		
		/*  --> 여러줄 주석!
		 * 
		MemberDAO dao = new MemberDAO();
		if (command.equals("/list.do")) {
			controller = new MemberListController();
			nextView = controller.requestHandle(request, response);

		} else if (command.equals("/insert.do")) {
			controller = new MemberInsertController();
			nextView = controller.requestHandle(request, response);

		} else if (command.equals("/insertForm.do")) {
			controller = new MemberInsertFormController();
			nextView = controller.requestHandle(request, response);

		} else if (command.equals("/delete.do")) {
			controller = new MemberDeleteController();
			nextView = controller.requestHandle(request, response);

		}
		*
		*/

		// ---------------------------------------------------------//

		// View 페이지로 연동하는 부분  --> 분기작업에서 중복되는 부분을 나눠줌 이쪽으로 따로 빼서, 리다이렉트.리퀘스트 선언부 나눠줌!

		if (nextView != null) {
			if (nextView.indexOf("redirect:") != -1) {
				String[] sp = nextView.split(":"); 	// sp[0]:sp[1]
				response.sendRedirect(sp[1]); 		// :0

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/"+nextView);
				rd.forward(request, response);

			}
		}
	}
}