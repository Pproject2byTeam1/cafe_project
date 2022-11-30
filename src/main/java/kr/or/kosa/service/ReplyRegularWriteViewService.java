package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.Regular_Board_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.Regular_Board;
import kr.or.kosa.dto.User;

public class ReplyRegularWriteViewService implements Action {

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
			
			//답글 쓰기
			
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
		    
		    String url="";
			
		
			if(user == null) {
			
				String board_msg = "로그인이 필요한 기능입니다.";
	            String board_url = "/WebCafe_Project/login_view.do";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
				
			} else {
				
				int b_code = Integer.parseInt(request.getParameter("b_code"));
				int refer = Integer.parseInt(request.getParameter("refer"));
				int depth = Integer.parseInt(request.getParameter("depth"));
				int step = Integer.parseInt(request.getParameter("step"));
				int idx = Integer.parseInt(request.getParameter("idx"));
				
				Regular_Board_Dao rdao = new Regular_Board_Dao();
				Regular_Board board = rdao.getRegular_BoardByIdx(idx);
				
				request.setAttribute("infolist", infolist);
				request.setAttribute("b_code", b_code);
				request.setAttribute("board", board);
				request.setAttribute("refer", refer);
				request.setAttribute("depth", depth);
				request.setAttribute("step", step);
				
				
				url="/WEB-INF/view/regularboard_reply.jsp";
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
