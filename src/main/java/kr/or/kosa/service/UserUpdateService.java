package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.User;
import kr.or.kosa.dto.UserDetails;

public class UserUpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		int row = 0;
		int row2 = 0;
		
		try {
	        //유저정보 가져오기
			HttpSession session = request.getSession();
			User user2 = (User) session.getAttribute("member");
			String userId = user2.getEmail_id();
			
			UserDao dao = new UserDao();
			String nickname = (String) request.getParameter("nickname");
			String tel = (String) request.getParameter("tel");
			System.out.println(tel);
			
			String orinick = (String) request.getParameter("orinick");
			String oriphone = (String) request.getParameter("oriphone");
			
			//update 실행
			if(!orinick.equals(nickname)) {
				row2 = dao.updateUserNick(userId,nickname);
			}
			if(!oriphone.equals(tel)) {
				row = dao.updateUserTelnum(tel, userId);
			}
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("userinfo.do");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}

}
