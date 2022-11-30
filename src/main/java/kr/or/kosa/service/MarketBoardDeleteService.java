package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.User;

public class MarketBoardDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String msg="";
	    String url="";
		
	    ActionForward forward = new ActionForward();
		
		try {
	    		
		Board_Info_Dao infodao = new Board_Info_Dao();
	    List<Board_Info> infolist = infodao.getSideBoardList();
	    request.setAttribute("infolist", infolist);
		
		//삭제글 처리 (글번호, 글쓴ID, 로그인된 ID 값 받기)
	    HttpSession session = request.getSession();
        User user = (User) session.getAttribute("member");
	    
		int idx = Integer.parseInt(request.getParameter("idx"));
		String email_id = user.getEmail_id();
		int b_code = Integer.parseInt(request.getParameter("b_code"));
		String idx1 = request.getParameter("idx");
		
		if(idx1 == null || idx1.trim().equals("")){
			msg ="글번호가 넘어오지 않았습니다";
			url = "marketboard_read.do?b_code=" + b_code + "&idx=" + idx;
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/view/redirect.jsp");
						
		} else {
			//삭제 로직
			MarketBoardDao dao = new MarketBoardDao();
			int result = dao.delMarket(idx, email_id);
			DataBoardDao databoard = new DataBoardDao();
			
			if(result > 0 ) {
				msg ="삭제 되었습니다.";
				url = "marketboard_list.do?b_code=" + b_code;
				
				request.setAttribute("cp", request.getParameter("cp"));
				request.setAttribute("ps", request.getParameter("ps"));
				request.setAttribute("board_msg", msg);
				request.setAttribute("board_url", url);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/view/redirect.jsp");
				
			}else {
				msg ="삭제 할 수 없습니다.";
				url = "marketboard_read.do?b_code=" + b_code + "&idx=" + idx;
				
				request.setAttribute("board_msg", msg);
				request.setAttribute("board_url", url);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/view/redirect.jsp");
				
			}
			

		
		}
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	
	return forward;
	}
	
}
