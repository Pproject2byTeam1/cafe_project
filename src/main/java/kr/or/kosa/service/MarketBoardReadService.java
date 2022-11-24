package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dto.MarketBoard;

public class MarketBoardReadService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		try {
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			MarketBoardDao dao = new MarketBoardDao();
			
			List<MarketBoard> marketboard = dao.readMarket(idx);
			
			request.setAttribute("marketboard", marketboard);
			
				
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/MarketBoard_read.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return forward;
	}

}
