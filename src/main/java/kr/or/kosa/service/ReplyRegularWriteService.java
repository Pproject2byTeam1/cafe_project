package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.Regular_Board_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.Regular_Board;
import kr.or.kosa.dto.User;

public class ReplyRegularWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();
		
		int result = 0;
		
		try {
			
			//사이드 바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
			//답글 쓰기
			
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
		    
		    String msg = "";
			String url = "";
			
			if(user == null) {
				
				msg = "실패";
				url = "/WebCafe_Project/login_view.do";
				
			}else {
				
				int b_code = Integer.parseInt(request.getParameter("b_code"));
				int replyIdx = Integer.parseInt(request.getParameter("reply_idx"));
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				Regular_Board board = new Regular_Board();
				board.setRefer(replyIdx);
				board.setB_code(b_code);
				board.setTitle(title);
				board.setNick(user.getNick());
				board.setContent(content);
				board.setEmail_id(user.getEmail_id());
				
				Regular_Board_Dao dao = new Regular_Board_Dao();
				
				result = dao.insertRegualr_BoardReply(board);
				
				if (result > 0) {
					msg = "성공";
					url = "/WebCafe_Project/regular_list.do?b_code=" + b_code;
				} else {
					msg = "실패";
					url = "/WebCafe_Project/regular_list.do?b_code=" + b_code;
				}
				
				request.setAttribute("b_code", b_code);
				
			}
			
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			request.setAttribute("infolist", infolist);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/redirect.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
