package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardListGetDao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.BoardListGet;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.Comments;
import kr.or.kosa.dto.User;
import kr.or.kosa.dto.UserDetails;

public class UserActivityService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);//top
			
			//사이드바 정보
	        Board_Info_Dao infodao = new Board_Info_Dao();
	        List<Board_Info> infolist = infodao.getSideBoardList();
	        
	        request.setAttribute("infolist", infolist);
			
	        //유저정보 가져가기
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("member");
			
			String url ="";
			
			if (user == null) {
	        	String board_msg = "권한이 없습니다.";
	            String board_url = "/WebCafe_Project/login_view.do";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
			}else {
				String email_id = request.getParameter("email_id");
				String userId = user.getEmail_id();
				if (email_id != null) {
					userId = email_id;
				}
				UserDao userdao = new UserDao();
				UserDetails userdetail = userdao.selectUserById(userId);
				
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
				request.setAttribute("userdetail", userdetail);
				
				url = "/WEB-INF/view/user_activity_info_board.jsp";
				
			}
			
			
			
			
			forward = new ActionForward();
		  	forward.setRedirect(false);///WEB-INF/view/
		  	forward.setPath(url);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}

}
