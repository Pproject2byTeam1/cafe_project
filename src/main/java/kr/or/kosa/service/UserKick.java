package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;

public class UserKick implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		
		String url = "";
		
		
		try {
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);//top
			
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
	        UserDao udao = new UserDao();
	     	String id = request.getParameter("id");

	        int row = udao.modifyAdmin(id);
	        
	        
			
			if (row > 0) {
			
				String board_msg = "수정되었습니다.";
	        	String board_url = "/WebCafe_Project/user_list.do";
				
				request.setAttribute("board_msg", board_msg);
				request.setAttribute("board_url", board_url);
	        	
				url="/WEB-INF/view/redirect.jsp";
			
			} else {
			
				String board_msg = "실패했습니다..";
	        	String board_url = "/WebCafe_Project/user_list.do";
				
				request.setAttribute("board_msg", board_msg);
				request.setAttribute("board_url", board_url);
	        	
				url="/WEB-INF/view/redirect.jsp";
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
