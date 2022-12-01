package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dao.Regular_Board_Dao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.Comments;
import kr.or.kosa.dto.Regular_Board;
import kr.or.kosa.dto.User;
import kr.or.kosa.utils.ViewCountPrevent;

public class Regular_Board_Post_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		try {
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);//top
			
			//사이드 바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			
			// DAO 불러오기
			Regular_Board_Dao dao = new Regular_Board_Dao();
			UserDao udao = new UserDao();
			Yes_Dao ydao = new Yes_Dao();
			CommentsDao cdao = new CommentsDao();
			
			//세션 불러오기
			HttpSession session = request.getSession();
			User user1 = (User) session.getAttribute("member");

			
			//좋아요 기능
			if (user1 != null) {
				Yes_Dao yesdao = new Yes_Dao();
				String yes = yesdao.getYesEmailByIdxEmail(idx, user1.getEmail_id());

				if (yes != null) {
					request.setAttribute("yespark", yes);
				} else {
					request.setAttribute("yespark", "no");
				}
			}
			
			//특정 게시글 조회, 댓글 조회
			Regular_Board board = dao.getRegular_BoardByIdx(idx);
			List<Comments> comments = cdao.getCommentListByIdx(idx);
			
			//특정 유저 단순정보 조회
			User user = udao.selectUserById(board.getEmail_id());
			int yes = ydao.getYesCountBy_idx(idx);
			
			//조회수 증가
			ViewCountPrevent prevent = new ViewCountPrevent();
			prevent.viewCountPrevent(idx, request, response);
			
			request.setAttribute("infolist", infolist);
			request.setAttribute("board", board);
			request.setAttribute("comments", comments);
			request.setAttribute("idx", idx);
			request.setAttribute("user", user);
			request.setAttribute("yes", yes);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/regularboard_post.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
