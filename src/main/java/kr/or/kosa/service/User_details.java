package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Market_Board_Dao;
import kr.or.kosa.dao.User_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Market_Board;
import kr.or.kosa.dto.User;

public class User_details implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			
			String id = request.getParameter("id");
			
			User_Dao dao = new User_Dao();
			
			User userlist = dao.idSearchUser(id);
			request.setAttribute("userlist", userlist);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/user_setting_edit.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	} 

}
