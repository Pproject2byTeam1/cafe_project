package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.Board_Rank_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.Board_Rank;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.User;

public class DataBoardWriteViewService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();

		try {
			// top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();

			// 사이드 바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();

			int b_code = Integer.parseInt(request.getParameter("b_code"));

			request.setAttribute("infolist", infolist);
			request.setAttribute("b_code", b_code);
			request.setAttribute("banner", banner);// top

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("member");

			Board_Rank_Dao boardrankdao = new Board_Rank_Dao();
			Board_Rank boardrank = boardrankdao.getBoardRank(b_code);

			String url = "";

			// 로그인 안했으면 로그인 페이지로
			if (user == null) {

				String board_msg = "로그인을 해주세요.";
				String board_url = "/WebCafe_Project/login_view.do";

				request.setAttribute("board_msg", board_msg);
				request.setAttribute("board_url", board_url);

				url = "/WEB-INF/view/redirect.jsp";

			} else if (user.getRank() >= boardrank.getW_rank() || user.getIsAdmin().equals("M")) {

				request.setAttribute("b_code", b_code);
				url = "/WEB-INF/view/databoard_write.jsp";

			} else {
				
				String board_msg = boardrank.getW_rank() + "등급부터 작성가능합니다.";
				String board_url = "/WebCafe_Project/databoard_list.do?b_code=" + b_code;

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
