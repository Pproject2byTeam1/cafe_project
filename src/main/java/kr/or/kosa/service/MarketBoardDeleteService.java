package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dto.Board_Info;

public class MarketBoardDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String msg="";
	    String url="";
		
	    ActionForward forward = new ActionForward();
		
		try {
	    	    
		//삭제글 처리 (글번호 받기)
		String idx = request.getParameter("idx");
		String cpage = request.getParameter("cp"); // current page
		String pagesize = request.getParameter("ps"); // pagesize
		String referer = (String)request.getHeader("Referer");
		
		forward = new ActionForward();
		
		if(idx == null || idx.trim().equals("")){
			msg ="글번호가 넘어오지 않았습니다";
			url = "BoardContent.do?idx=" + idx;
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/board/redirect.jsp");
			
		} else {
			request.setAttribute("idx", idx);
			request.setAttribute("cp", cpage);
			request.setAttribute("ps", pagesize);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/board/marketboard_delete.jsp");
		}
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	
	return forward;
	}
	
}
