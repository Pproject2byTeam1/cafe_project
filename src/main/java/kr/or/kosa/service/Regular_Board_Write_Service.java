package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.Regular_Board_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.User;

public class Regular_Board_Write_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		try {
			
			Board_Info_Dao infodao = new Board_Info_Dao();
 			List<Board_Info> infolist = infodao.getSideBoardList();
			
 			HttpSession session = request.getSession();
 	        User user = (User) session.getAttribute("member");
 			
 			String url = "";
 			int b_code = Integer.parseInt(request.getParameter("b_code"));
 			int idx = Integer.parseInt(request.getParameter("idx"));
 			String email_idx = request.getParameter("id");
 			String todo = request.getParameter("todo");
 			

 			
 			// 로그인 안할경우 로그인 페이지로
			if (user == null) {

	            String board_msg = "로그인을 해주세요.";
	            String board_url = "/WebCafe_Project/login_view.do";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	           
	         }
			

			url="/WEB-INF/view/regularboard_write?.jsp";
	 			
	   
			
			
 		
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
