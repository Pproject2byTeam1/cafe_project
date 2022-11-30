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
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.User;

public class DeleteAttendanceService implements Action {

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
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("member");
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			String msg = "";
			String url = "";
			
			if(user == null) {
				msg = "로그인이 필요한 기능입니다.";
				url = "/WebCafe_Project/loginok.do";
			}else {
			
				Board_Dao dao = new Board_Dao();
				int result = dao.delete_Board(idx, user.getEmail_id());
				
				if(result > 0) {
					msg = "성공";
					url = "/WebCafe_Project/checkBoard.do?b_code=2";
				}else {
					msg = "실패";
					url = "/WebCafe_Project/checkBoard.do?b_code=2";
				}
				
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
