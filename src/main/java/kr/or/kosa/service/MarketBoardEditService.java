package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.MarketBoard;
import kr.or.kosa.dto.User;

public class MarketBoardEditService implements Action {

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
			
 			HttpSession session = request.getSession();
 	        User user = (User) session.getAttribute("member");
 			
 			String url = "";
 			int b_code = Integer.parseInt(request.getParameter("b_code"));
 			int idx = Integer.parseInt(request.getParameter("idx"));
 			
 
 			
 			// 로그인 안할경우 로그인 페이지로
			if (user == null ) {

	            String board_msg = "권한이 없습니다.";
	            String board_url = "/WebCafe_Project/login_view.do";
	               
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	           
	         }else {
	        	 
	        	MarketBoardDao dao = new MarketBoardDao();
	        	Board_Dao bdao = new Board_Dao();
	        	MarketBoard marketboard = dao.readMarket(idx);
	        	int totalboardcount = bdao.totalBoardCountByB_code(b_code);
	        	int soldcount = dao.countSoldF(b_code);
	        	
				request.setAttribute("marketboard", marketboard);
				request.setAttribute("totalboardcount", totalboardcount);
				request.setAttribute("soldcount", soldcount);
				
				
				url="/WEB-INF/view/marketboard_edit.jsp";
	        	 
	         } 
			
 		
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
