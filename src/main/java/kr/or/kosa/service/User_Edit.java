package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.User_Dao;
import kr.or.kosa.dto.User;

public class User_Edit implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String nick = request.getParameter("nick");
			int point = Integer.parseInt(request.getParameter("point"));
			
			User_Dao dao = new User_Dao();
			dao.editSettingUser(id, password, nick, point);
			
			
			request.setAttribute("id", id);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("user_details.do");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	} 

}

