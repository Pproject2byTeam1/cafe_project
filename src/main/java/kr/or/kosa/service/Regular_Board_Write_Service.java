package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.Regular_Board_Dao;
import kr.or.kosa.dto.Board;
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
		int idx = Integer.parseInt(request.getParameter("idx"));
		String email_idx = request.getParameter("id");
		String todo = request.getParameter("todo");
		
		
		try {
			
			Board_Info_Dao infodao = new Board_Info_Dao();
 			List<Board_Info> infolist = infodao.getSideBoardList();
			
			if (user == null) {

	            String board_msg = "권한이 없습니다.";
	            String board_url = "/WebCafe_Project/login_view.do";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	           
	         }
			
			// 수정하기 (회원 id와 글 id가 같고 todo가 수정일경우)
			if (user.getEmail_id().equals(email_idx) && todo.equals("modify")){
				
				Regular_Board_Dao dao = new Regular_Board_Dao();
				Board board = dao.getRegular_BoardByIdx(idx);
				request.setAttribute("board", board);
	        	
				url="/WEB-INF/view/regularboard_write.jsp";
	        	 
	         } else {
	        	 
	        	String board_msg = "작성자가 다릅니다.";
	            String board_url = "/WebCafe_Project/regular_list.do?b_code=1";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	         }
			
			// 글쓰기 (todo가 'write'일경우)
			if (todo.equals("write")) {
	 			
	 			url="/WEB-INF/view/regularboard_write.jsp";
	 			
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
