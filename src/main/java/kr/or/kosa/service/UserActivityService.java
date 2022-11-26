package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardListGetDao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.BoardListGet;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CommentList;
import kr.or.kosa.dto.Comments;
import kr.or.kosa.dto.User;
import kr.or.kosa.dto.UserDetails;

public class UserActivityService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		try {
			//사이드바 정보
	        Board_Info_Dao infodao = new Board_Info_Dao();
	        List<Board_Info> infolist = infodao.getSideBoardList();
	        
	        request.setAttribute("infolist", infolist);
			
	        //유저정보 가져가기
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("member");
			String email_id = request.getParameter("email_id");
			String userId = user.getEmail_id();
			
			
			
			if (email_id != null) {
				userId = email_id;
			}
			
			System.out.println(userId);
			
			//쿼리문 실행준비
			BoardListGetDao dao = new BoardListGetDao();
			CommentsDao commentdao = new CommentsDao();
			//자신이 쓴 글 가져오기
			List<BoardListGet> writeboardlist = dao.getBoardListByMe(userId);
			
			//자신이 쓴 댓글 가져오기
			List<Comments> commnetlist = commentdao.getCommentListByMe(userId);
			
			//자신이 좋아요 한 글 가져오기
			List<BoardListGet> likeboardlist = dao.getBoardListByLike(userId);
			
			request.setAttribute("writeboardlist", writeboardlist);
			request.setAttribute("commnetlist", commnetlist);
			request.setAttribute("likeboardlist", likeboardlist);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);///WEB-INF/view/
		  	forward.setPath("user_activity_info_board.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}

}
