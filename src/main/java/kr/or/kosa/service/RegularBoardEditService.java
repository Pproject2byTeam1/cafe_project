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

public class RegularBoardEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);//top
			
			Board_Info_Dao infodao = new Board_Info_Dao();
 			List<Board_Info> infolist = infodao.getSideBoardList();
			
 			HttpSession session = request.getSession();
 	        User user = (User) session.getAttribute("member");
 			
 			String url = "";
 			int b_code = Integer.parseInt(request.getParameter("b_code"));
 			int idx = Integer.parseInt(request.getParameter("idx"));
 			String email_idx = request.getParameter("id");

 	
 			System.out.println(user);
 			
 			// 로그인 안할경우 로그인 페이지로
			if (user == null ) {

	            String board_msg = "권한이 없습니다.";
	            String board_url = "/WebCafe_Project/login_view.do";
	               
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	           
	         }
			
			// 수정하기화면 가기 (세션 id와 글 id가 같을경우)
			if (user.getEmail_id().equals(email_idx)){
				
				Regular_Board_Dao dao = new Regular_Board_Dao();
				Regular_Board board = dao.getRegular_BoardByIdx(idx);
				request.setAttribute("board", board);
				request.setAttribute("idx", idx);
				request.setAttribute("email_idx", email_idx);
				request.setAttribute("b_code", b_code);
	        	
				url="/WEB-INF/view/regularboard_edit.jsp";
	        	 
	         } else {
	        	 
	        	String board_msg = "작성자가 다릅니다.";
	            String board_url = "/WebCafe_Project/regular_post.do?b_code="+ b_code + "&idx=" + idx;
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	         }
			
 		
 			request.setAttribute("infolist", infolist);
			
			forward = new ActionForward();
 		  	forward.setRedirect(false);
 		  	forward.setPath(url);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
