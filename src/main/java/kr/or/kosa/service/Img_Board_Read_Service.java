package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.Board_Rank_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dao.Img_Board_Dao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.Board_Rank;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.Comments;
import kr.or.kosa.dto.Img_Board;
import kr.or.kosa.dto.User;
import kr.or.kosa.utils.ViewCountPrevent;

public class Img_Board_Read_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();

		try {
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			
			// top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);// top

			// 사이드 바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();

			Board_Info boardinfo = new Board_Info();

	        for(Board_Info info : infolist) {
	        	if(info.getB_code() == b_code) {
	        		boardinfo.setB_name(info.getB_name());
	        		boardinfo.setB_type(info.getB_type());
	        	}
	        }
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			// 조회수 증가
			ViewCountPrevent prevent = new ViewCountPrevent();
			prevent.viewCountPrevent(idx, request, response);

			Img_Board_Dao dao = new Img_Board_Dao();
			Img_Board imgboard = dao.getImg_BoardByIdx(idx);

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("member");

			// 특정 유저 단순정보 조회
			UserDao udao = new UserDao();

			User user1 = udao.selectUserById(imgboard.getEmail_id());
			if (user != null) {
				Yes_Dao yesdao = new Yes_Dao();
				String yes = yesdao.getYesEmailByIdxEmail(idx, user.getEmail_id());

				if (yes != null) {
					request.setAttribute("yes", yes);
				} else {
					request.setAttribute("yes", "no");
				}
			}

			// 댓글 조회
			CommentsDao codao = new CommentsDao();
			List<Comments> comments = codao.getCommentListByIdx(idx);

			request.setAttribute("boardinfo", boardinfo);
			request.setAttribute("comments", comments);
			request.setAttribute("imgboard", imgboard);
			request.setAttribute("b_code", b_code);
			request.setAttribute("infolist", infolist);
			request.setAttribute("user1", user1);

			Board_Rank_Dao boardrankdao = new Board_Rank_Dao();
			Board_Rank boardrank = boardrankdao.getBoardRank(b_code);

			String url = "";

			if (user == null) {
				String board_msg = "로그인이 필요한 기능입니다.";
				String board_url = "/WebCafe_Project/login_view.do";

				request.setAttribute("board_msg", board_msg);
				request.setAttribute("board_url", board_url);

				url = "/WEB-INF/view/redirect.jsp";
			} else if (user.getRank() >= boardrank.getW_rank() || user.getIsAdmin().equals("M")) {

				request.setAttribute("b_code", b_code);
				url = "/WEB-INF/view/imgboard_read.jsp";

			} else {

				String board_msg = boardrank.getW_rank() + "등급부터 확인 가능합니다.";
				String board_url = "/WebCafe_Project/img_board_list.do?b_code=" + b_code;

				request.setAttribute("board_msg", board_msg);
				request.setAttribute("board_url", board_url);

				url = "/WEB-INF/view/redirect.jsp";
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
