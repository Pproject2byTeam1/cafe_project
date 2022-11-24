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
			//사이드바 정보
	        Board_Info_Dao infodao = new Board_Info_Dao();
	        List<Board_Info> infolist = infodao.getSideBoardList();
	        
	        request.setAttribute("infolist", infolist);
			
	        //유저정보 가져오기
			HttpSession session = request.getSession();
			String userId = "T1@naver.com";//(String) session.getAttribute("userid");
			
			UserDao dao = new UserDao();
			String nickname = (String) request.getParameter("nickname");
			String tel = (String) request.getParameter("tel");
			System.out.println(tel);
			
			//update 실행
			row = dao.updateUserTelnum(tel, userId);
			row2 = dao.updateUserNick(userId,nickname);
			
			
			if(row<0) {
				System.out.println("row 변경안됨");
			}else if(row2<0) {
				System.out.println("row2 변경안됨");
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
