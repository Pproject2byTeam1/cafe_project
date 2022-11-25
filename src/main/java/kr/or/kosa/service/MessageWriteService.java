package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dto.User;

public class MessageWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			
			HttpSession session = request.getSession();
			User user2 = (User) session.getAttribute("member");
			String userId = user2.getEmail_id();
			String responde_Id = (String) request.getParameter("sender_id");//받는사람 아이디
			
			System.out.println("dd: " + responde_Id);
			
			request.setAttribute("userId", userId);
			request.setAttribute("responde_Id", responde_Id);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/memo_write.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}

}
