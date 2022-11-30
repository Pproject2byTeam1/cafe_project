package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dto.Board_Info;

public class DataDeleteOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		try {
			HttpSession session = request.getSession();
			// 로그인 한사람정보

			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			int idx = Integer.parseInt(request.getParameter("idx"));
			String email_id = request.getParameter("email_id");
			DataBoardDao databoard = new DataBoardDao();
			int b_code = Integer.parseInt(request.getParameter("b_code"));

			int row = databoard.deleteDataBoard(idx);

			String msg = "";
			String url = "";
			
			if (row < 0) {
				msg = "삭제에 실패하였습니다";
				url = "databoard_list.do?b_code"+ b_code;
			} else {
				msg = "성공적으로 삭제되었습니다";
				url = "databoard_list.do?b_code"+ b_code;
			}
			forward = new ActionForward();
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		forward.setRedirect(false);
		forward.setPath("/WEB-INF/view/redirect.jsp");
		return forward;
	}

}
