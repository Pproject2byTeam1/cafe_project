package kr.or.kosa.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MarketBoardDao;

public class MarketBoardDeleteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String email_id = request.getParameter("email_id");
		int b_code = Integer.parseInt(request.getParameter("b_code"));
		
		String msg="";
		String url="";
		 
		MarketBoardDao dao;
		try {
			dao = new MarketBoardDao();
			
			int result = dao.delMarket(idx, email_id);
			
			if(result > 0){
				msg="삭제 성공";
				url="MarketBoardList.do?b_code=" + b_code;
			}else{
				msg="삭제 실패";
				url="MarKetBoardList.do?b_code" + b_code;
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("board_msg",msg);
		request.setAttribute("board_url",url);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false); // forward
		forward.setPath("/WEB-INF/views/board/redirect.jsp");

		return forward;
	}

}
