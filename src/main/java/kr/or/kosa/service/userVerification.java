package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.User;

public class userVerification implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		int row = 0;
		
		try {
	        //유저정보 가져오기
			HttpSession session = request.getSession();
			User user2 = (User) session.getAttribute("member");
			String userId = user2.getEmail_id();
			 
			UserDao dao = new UserDao();
			String password = (String) request.getParameter("password");
			//검증 실행
			row = dao.verificationUser2(userId, password);
			
			if(row>0) {
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
