package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.UserDetails;

public class Login_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();
		
		try {
			
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			
			//사이드 바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
			String email_id = request.getParameter("email_id");
			String password = request.getParameter("password");
			
			UserDao dao = new UserDao();
			UserDetails user = dao.selectUserById(email_id);
			
			HttpSession session = request.getSession();
			String url = "";
			
			if(password.equals("") || email_id.equals("")) {
				String board_msg = "값을 정확히 넣어주세요.";
	            String board_url = "/WebCafe_Project/login_view.do";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
			}else if(dao.isCheckById(email_id).equals("false")) {
				String board_msg = "아이디를 다시 입력해주세요.";
	            String board_url = "/WebCafe_Project/login_view.do";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
			}else {
				if(user.getPassword().equals(password)) {
					session.setAttribute("member", user);
					url = "cafemain.do";
				}else {
					
					
					String board_msg = "비밀번호를 다시 입력해주세요.";
		            String board_url = "/WebCafe_Project/login_view.do";
		              
		            request.setAttribute("board_msg", board_msg);
		            request.setAttribute("board_url", board_url);
		              
		            url="/WEB-INF/view/redirect.jsp";
				}
			}
			
			//총 유저 명수 구하기
			UserDao udao = new UserDao();
			int totalUser = udao.totalUserCount();
			
			request.setAttribute("totalUser", totalUser);
			
			request.setAttribute("banner", banner);//top
			request.setAttribute("infolist", infolist);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath(url);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
