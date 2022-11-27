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
import kr.or.kosa.dto.Regular_Board;
import kr.or.kosa.dto.User;

public class RegularBoardWriteOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		try {
			
			Board_Info_Dao infodao = new Board_Info_Dao();
 			List<Board_Info> infolist = infodao.getSideBoardList();
			
 			HttpSession session = request.getSession();
 	        User user = (User) session.getAttribute("member");
 	        
 	        int b_code = Integer.parseInt(request.getParameter("b_code"));
 	        
 			String url = "";
 			int row = 0;
 			
 			//제목, 내용 받기
 			String title = request.getParameter("title");
 			String content = request.getParameter("content");
 			

 			// 로그인 안하거나 풀렸으면 로그인 페이지로
			if (user == null) {

	            String board_msg = "세션이 만료되었습니다.";
	            String board_url = "/WebCafe_Project/login_view.do";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	           
	         }else {
	        	 
	        	Regular_Board_Dao rdao = new Regular_Board_Dao();
	        	Regular_Board dto = new Regular_Board();
	        	
	        	dto.setTitle(title);
	        	dto.setContent(content);
	        	dto.setB_code(b_code);
	        	dto.setEmail_id(user.getEmail_id());
				dto.setNick(user.getNick());
	        	
	        	row = rdao.writeRegualr_Board(dto);
	        	
	        	if (row > 0) {
	        		String board_msg = "글작성이 완료되었습니다.";
		        	String board_url = "/WebCafe_Project/regular_list.do?b_code="+ b_code;
		        	request.setAttribute("board_msg", board_msg);
		            request.setAttribute("board_url", board_url);
		        	url="/WEB-INF/view/redirect.jsp";
				}
	        	
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
