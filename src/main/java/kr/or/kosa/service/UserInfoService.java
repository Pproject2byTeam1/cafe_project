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

public class UserInfoService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		try {
			//사이드바 정보
	        Board_Info_Dao infodao = new Board_Info_Dao();
	        List<Board_Info> infolist = infodao.getSideBoardList();
	        
	        request.setAttribute("infolist", infolist);
			
	        //유저정보 가져가기
			HttpSession session = request.getSession();
			User user = new User();
			UserDetails details = new UserDetails();
			User user2 = (User) session.getAttribute("member");
			String url = "";
			
			if (user2 == null) {
				
	        	String board_msg = "권한이 없습니다.";
	            String board_url = "/WebCafe_Project/login_view.do";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	            
			} else {
				
				String userId = user2.getEmail_id();
				
				UserDao dao = new UserDao();
				user = dao.selectUserById(userId);
				details = dao.selectUserDetailById(userId);
				
				String phone = details.getPhone();
				String number = phone.substring(0, 3) + " - " + phone.substring(3, 7) + " - " + phone.substring(7, 11);
				
				String birth = user.getBirth();
				String day = birth.substring(0,4) + "년 " + birth.substring(4,6) + "월 " + birth.substring(6,8) + "일";
				
				String join = details.getJoin_date();
				String date = join.substring(0,4) + "년 " + join.substring(4,6) + "월 " + join.substring(6,8) + "일";
				
				url="/WEB-INF/view/user_info_change_board.jsp";
				
				request.setAttribute("user", user);
				request.setAttribute("details", details);
				request.setAttribute("phone", number);
				request.setAttribute("birthday", day);
				request.setAttribute("joindate", date);
				
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
