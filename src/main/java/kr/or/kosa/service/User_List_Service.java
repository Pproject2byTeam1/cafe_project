package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Img_Board_Dao;
import kr.or.kosa.dao.User_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Img_Board;
import kr.or.kosa.dto.User_Details;

public class User_List_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		try {
			
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userid");
			
			User_Dao dao = new User_Dao();
			
			List<User_Details> alluser = dao.getUserListAll();
			
			
			request.setAttribute("alluser", alluser);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/user_list.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
