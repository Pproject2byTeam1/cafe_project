package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.User;

public class MessageWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);//top
			
			Board_Info_Dao infodao = new Board_Info_Dao();
 			List<Board_Info> infolist = infodao.getSideBoardList();
 			request.setAttribute("infolist", infolist);
			
			HttpSession session = request.getSession();
			User user2 = (User) session.getAttribute("member");
			String url = "";
			
			if (user2 == null) {
				
	        	String board_msg = "권한이 없습니다.";
	            String board_url = "/WebCafe_Project/windowclose.do";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	            
			} else {
				String userId = user2.getEmail_id();
				String responde_Id = (String) request.getParameter("sender_id");//받는사람 아이디

				url = "/WEB-INF/view/memo_write.jsp";
				
				request.setAttribute("userId", userId);
				request.setAttribute("member", user2);
				request.setAttribute("responde_Id", responde_Id);
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
