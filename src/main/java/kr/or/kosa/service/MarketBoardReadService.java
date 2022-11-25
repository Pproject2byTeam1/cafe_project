package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.MarketBoard;
import kr.or.kosa.dto.User;

public class MarketBoardReadService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		try {
			
			//사이드 바
			Board_Info_Dao infodao = new Board_Info_Dao();
		    List<Board_Info> infolist = infodao.getSideBoardList();
	        request.setAttribute("infolist", infolist);
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			
			
			MarketBoardDao dao = new MarketBoardDao();
			
			MarketBoard list = dao.readMarket(idx);
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("member");

			if (user != null) {
				Yes_Dao yesdao = new Yes_Dao();
				String yes = yesdao.getYesEmailByIdxEmail(idx, user.getEmail_id());

				if (yes != null) {
					request.setAttribute("yes", yes);
				} else {
					request.setAttribute("yes", "no");
				}
			}
			
			request.setAttribute("list", list);
			request.setAttribute("b_code", b_code);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/marketboard_read.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
