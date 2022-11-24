package kr.or.kosa.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dto.MarketBoard;

public class MarketBoardWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String m_mode = request.getParameter("m_mode");
		String cate = request.getParameter("m.cate");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String img_name = request.getParameter("img_name");
		int price = Integer.parseInt(request.getParameter("price"));
		String nick = request.getParameter("nick");
		String email_id = request.getParameter("email_id");
		int b_code = Integer.parseInt(request.getParameter("b_code"));
		
		int result = 0;
		
		MarketBoard market = new MarketBoard();
		
		market.setM_mode(m_mode);
		market.setCate(cate);
		market.setTitle(title);
		market.setContent(content);
		market.setImg_name(img_name);
		market.setPrice(price);
		market.setNick(nick);
		market.setEmail_id(email_id);
		market.setB_code(b_code);
		
		try {
			MarketBoardDao dao = new MarketBoardDao();
			
			result = dao.writeMarket(market);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		String msg = "";
		String url = "";
		if (result > 0) {
			msg = "insert success";
			url = "MarketBoardRead.do";
		} else {
			msg = "insert fail";
			url = "MarketBoardWrite.do";
		}

		request.setAttribute("market_msg", msg);
		request.setAttribute("market_url", url);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/board/redirect.jsp");

		return forward;

	}

}
