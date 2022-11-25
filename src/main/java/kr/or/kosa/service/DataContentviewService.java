package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.User;

public class DataContentviewService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		try {
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			String idx = request.getParameter("idx");
			// 사이드바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			Board_Dao dao = new Board_Dao();

			// 글내용보기 시작
			DataBoardDao datadao = new DataBoardDao();

			User user = (User) session.getAttribute("member");

			if (user != null) {
				Yes_Dao yesdao = new Yes_Dao();
				int idx_t = Integer.parseInt(idx);
				String yes = yesdao.getYesByIdxEmail(idx_t, user.getEmail_id());

				if (yes != null) {
					request.setAttribute("yes", yes);
				} else {
					request.setAttribute("yes", "no");
				}
			}

			String url = "";
			// 글번호를 안가져오는 경우
			if (idx == null || idx.trim().equals("")) {
				url = "/databoard_list.jsp";
			} else {
				idx = idx.trim();

				// 조회수 증가 dao
				// boolean isread = dao.getReadNum(idx);
				// if(isread)System.out.println("조회증가 : " + isread);
				// 데이터 하나 조회

				Board databoard = datadao.getBoard_data(Integer.parseInt(idx));

				request.setAttribute("idx", idx);
				request.setAttribute("b_code", b_code);
				request.setAttribute("databoard", databoard);
				url = "/WEB-INF/view/data_contentview.jsp";

			}

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath(url);

		} catch (Exception e) {
			e.getMessage();
		}

		return forward;
	}

}
