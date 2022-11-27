package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UserDao;

public class User_Edit implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String nick = request.getParameter("nick");
			String isAdmin = request.getParameter("isAdmin");
			int point = Integer.parseInt(request.getParameter("point"));
			
			UserDao dao = new UserDao();
			dao.editSettingUser(id, password, nick, isAdmin, point);
			
			
			request.setAttribute("id", id);
			
			
			
			String board_msg = "수정되었습니다.";
        	String board_url = "/WebCafe_Project/windowclose.do";
			
			request.setAttribute("board_msg", board_msg);
			request.setAttribute("board_url", board_url);
        	
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/redirect.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	} 

}

