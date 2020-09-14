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

		// ** Ŭ���̾�Ʈ�� ���� ��û�� �ߴ��� �˷��ִ� �۾�!!!!!!!!!!!

		// 1. � ��û���� �ľ��ϴ� �۾� --> ?.do
		// ��ü���
		String reqUrl = request.getRequestURI();
		System.out.println(reqUrl);

		// ������ ��θ� ���� --> context�� ã�� �� : ������ �� �ڵ����� ��������!!!!
		String ctxPath = request.getContextPath();
		System.out.println(ctxPath);

		// Ŭ���̾�Ʈ�� ��û�� ���
		// �������� ��! --> ������ġ ���� ����!
		// Ŭ���̾�Ʈ�� ���� ��û�� �ߴ��� �� �� �ִ��� ������ ��û!!!!!!!! * �߿��� � ��û!?
		String command = reqUrl.substring(ctxPath.length());

		// �������̽� �����
		Controller controller = null;
		String nextView = null;
		
		HandlerMapping mappings = new HandlerMapping();
		controller = mappings.getController(command);
		nextView = controller.requestHandle(request, response);

		// �� ��û�� ���� ó���ϱ�(�б��۾�)
		
		// �ڵ鷯 ���� --> HandlerMapping
		// *.do --> Member "*" Controller
		
		/*  --> ������ �ּ�!
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

		// View �������� �����ϴ� �κ�  --> �б��۾����� �ߺ��Ǵ� �κ��� ������ �������� ���� ����, �����̷�Ʈ.������Ʈ ����� ������!

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