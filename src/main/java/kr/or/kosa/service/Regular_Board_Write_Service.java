package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.User;

public class Regular_Board_Write_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("member");
		
		String url = "";
		String b_code = request.getParameter("b_code");
		
		try {
			
			if (user == null) {

	            String board_msg = "권한이 없습니다.";
	            String board_url = "/WebCafe_Project/regular_list.do?b_code="+b_code;
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	           
	         } else {
	        	 
	 			Board_Info_Dao infodao = new Board_Info_Dao();
	 			List<Board_Info> infolist = infodao.getSideBoardList();
	 		
	 			request.setAttribute("infolist", infolist);
	 			
	 			url="/WEB-INF/view/regular_write.jsp";
	 			
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
