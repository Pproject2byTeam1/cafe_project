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

public class UpdatePwdService implements Action {

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
			String newpassword = (String) request.getParameter("newpassword");
			
			//update 실행
			if(!password.equals(newpassword)) {
				row = dao.updateUserPwd(userId, password);
			}
			
			if(row<0) {
				System.out.println("row 변경안됨");
			}else {
				System.out.println("성공");
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
