package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.UserDetails;

public class Login_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();
		
		try {
			
			String email_id = request.getParameter("email_id");
			String password = request.getParameter("password");
			
			UserDao dao = new UserDao();
			UserDetails user = dao.selectUserById(email_id);
			
			HttpSession session = request.getSession();
			String url = "";
			
			if(password.equals("") || email_id.equals("")) {
				url = "/WEB-INF/view/login.jsp";
			}else if(dao.isCheckById(email_id).equals("false")) {
				url = "/WEB-INF/view/login.jsp";
			}else {
				if(user.getPassword().equals(password)) {
					session.setAttribute("member", user);
					url = "index.jsp";
				}else {
					url = "/WEB-INF/view/login.jsp";
				}
			}
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath(url);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
