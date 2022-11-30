package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.UserDetails;

public class idVerification implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
ActionForward forward = new ActionForward();
		
		
		try {
			UserDao dao = new UserDao();
			String email_id = (String) request.getParameter("email_id");
			
			//검증 실행
			String fn = dao.isCheckById(email_id);
			
			if(!fn.equals("false")) {
				request.setAttribute("verification", true);
			}else {
				request.setAttribute("verification", false);
			}
			
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/verification.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}

}
