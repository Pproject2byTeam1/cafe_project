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
 			
 			
 			System.out.println(b_code);
			System.out.println(email_idx);
			System.out.println(todo);
 			
 			// 로그인 안할경우 로그인 페이지로
			if (user == null) {

	            String board_msg = "권한이 없습니다.";
	            String board_url = "/WebCafe_Project/login_view.do";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	           
	         }
			
			// 수정하기화면 가기 (회원 id와 글 id가 같고 todo가 modify일경우)
			if (user.getEmail_id().equals(email_idx)){
				
				Regular_Board_Dao dao = new Regular_Board_Dao();
				Board board = dao.getRegular_BoardByIdx(idx);
				request.setAttribute("board", board);
				request.setAttribute("idx", idx);
				request.setAttribute("email_idx", email_idx);
				request.setAttribute("b_code", b_code);
	        	
				url="/WEB-INF/view/regularboard_edit.jsp";
	        	 
	         } else {
	        	 
	        	String board_msg = "작성자가 다릅니다.";
	            String board_url = "/WebCafe_Project/regular_list.do?b_code=1";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	         }
			
			// 수정확인버튼
			if (todo.equals("modify")) {
				
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				System.out.println(title);
				System.out.println(content);
				
				
				Board_Dao bdao = new Board_Dao();
				int editboard = bdao.updateUseIdxBoard(title,content,idx);
				
					if(editboard > 0){
						String board_msg = "수정 완료.";
			            String board_url = "/WebCafe_Project/regular_list.do?b_code=1";
			              
			            request.setAttribute("board_msg", board_msg);
			            request.setAttribute("board_url", board_url);
			              
			            url="/WEB-INF/view/redirect.jsp";
					}
				
				
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
