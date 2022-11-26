package kr.or.kosa.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.DataBoard;
import kr.or.kosa.dto.User;

public class DataBoardListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		try {
			HttpSession session = request.getSession();

			DataBoardDao dao1 = new DataBoardDao();

			Board_Dao dao = new Board_Dao();
			Yes_Dao ydao = new Yes_Dao();
			CommentsDao cdao = new CommentsDao();
			UserDao udao = new UserDao();
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			// 관리자만 삭제가능하게
			User user = (User) session.getAttribute("member");
			request.setAttribute("member", user);
			// 사이드바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();

			request.setAttribute("infolist", infolist);

			// 게시물 총 개수 마켓보드dao 사용
			MarketBoardDao market_dao = new MarketBoardDao();
			int totalboardcount = market_dao.countMarket(b_code);

			// 상세보기 >> 다시 리스트로 넘어 올때
			String ps = request.getParameter("ps");
			String cp = request.getParameter("cp");

			if (ps == null || ps.trim().equals("")) {
				// default 값 설정
				ps = "5"; // 5개씩
			}

			if (cp == null || cp.trim().equals("")) {
				// default 값 설정
				cp = "1"; // 1번째 페이지 보겠다
			}

			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);

			int pagecount = 0;
			if (totalboardcount % pagesize == 0) {
				pagecount = totalboardcount / pagesize;
			} else {
				pagecount = (totalboardcount / pagesize) + 1;
			}


			List<DataBoard> list = dao.getdata_boardList(b_code, cpage, pagesize);

			List<Integer> countlist = new ArrayList<Integer>();// 덧글 카운트
			List<Integer> yescountlist = new ArrayList();
			List<Integer> commentcountlist = new ArrayList();
			List ranklist = new ArrayList();
			for (DataBoard re : list) {
				int idx = re.getIdx();
				String email_id = re.getEmail_id();
				int yescount = ydao.getYesCountBy_idx(idx);
				int commentcount = cdao.getCommentCountBy_idx(idx);
				User user1 = udao.selectUserById(email_id);
				int rank = user1.getRank();

				yescountlist.add(yescount);
				commentcountlist.add(commentcount);
				ranklist.add(rank);
			}

		
			request.setAttribute("infolist", infolist);
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("totalboardcount", totalboardcount);
			request.setAttribute("list", list);
			request.setAttribute("yes", yescountlist);
			request.setAttribute("comment", commentcountlist);
			request.setAttribute("rank", ranklist);
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("totalboardcount", totalboardcount);

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/view/databoard_list.jsp");

		} catch (NamingException e) {

			System.out.println(e.getMessage());
		}

		return forward;
	}

}
