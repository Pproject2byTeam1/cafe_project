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
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.DataBoard;
import kr.or.kosa.dto.User;

public class Data_Board_Post_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		try {
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);//top
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("member");
			
			//Dao 불러오기
			Board_Info_Dao infodao = new Board_Info_Dao();
			UserDao udao = new UserDao();
			DataBoardDao dao = new DataBoardDao();
			Board_Dao bdao = new Board_Dao();
			Yes_Dao ydao = new Yes_Dao();
			
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			
			
			List<Board_Info> infolist = infodao.getSideBoardList();
			int idx = Integer.parseInt(request.getParameter("idx"));
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			String ori_name = request.getParameter("ori_name");

			bdao.updateHits(idx);
			DataBoard board = dao.getData_BoardByIdx(idx);
			
			User rank = udao.selectUserById(board.getEmail_id());
			
			//좋아요 기능 
			if (user != null) {
				Yes_Dao yesdao = new Yes_Dao();
				String yes = yesdao.getYesEmailByIdxEmail(idx, user.getEmail_id());

				if (yes != null) {
					request.setAttribute("yespark", yes);
				} else {
					request.setAttribute("yespark", "no");
				}
			}
			
			int yes = ydao.getYesCountBy_idx(idx);
			
			request.setAttribute("infolist", infolist);
			request.setAttribute("board", board);
			request.setAttribute("b_code", b_code);
			request.setAttribute("rank", rank);
			request.setAttribute("yes", yes);
			request.setAttribute("ori_name", ori_name);
			request.setAttribute("banner", banner);//top
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/view/data_contentview.jsp");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}
}
