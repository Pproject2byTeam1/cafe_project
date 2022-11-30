package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.User;

public class adminUpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		int row = 0;
		int row2 = 0;
		
		try {
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);//top
			
	        //유저정보 가져오기
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("member");
			String userId = user.getEmail_id();
			
			UserDao dao = new UserDao();
			String name = (String) request.getParameter("name");
			String nickname = (String) request.getParameter("nickname");
			String newpassword = (String) request.getParameter("newpassword");
			String date = (String) request.getParameter("date");
			String tel = (String) request.getParameter("tel");
			
			String orinick = (String) request.getParameter("orinick");
			String oriphone = (String) request.getParameter("oriphone");
			
			//update 실행
			if(orinick.equals(nickname)) {
				row = dao.updateAdmin(userId, newpassword, name, orinick, date);
			}else {
				row = dao.updateAdmin(userId, newpassword, name, nickname, date);
			}
			
			if(!oriphone.equals(tel)) {
				row2 = dao.updateUserTelnum(tel, userId);
			}else {
				row2 = dao.updateUserTelnum(oriphone, userId);
			}
			
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("cafemain.do");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}

}
