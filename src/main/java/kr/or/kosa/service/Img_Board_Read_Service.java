package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Img_Board_Dao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Img_Board;
import kr.or.kosa.dto.User;
import kr.or.kosa.dto.Yes;

public class Img_Board_Read_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		try {
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			
			Board_Dao bdao = new Board_Dao();
			bdao.updateHits(idx);
			
			Img_Board_Dao dao = new Img_Board_Dao();
			Img_Board imgboard = dao.getImg_BoardByIdx(idx);
			
			HttpSession session = request.getSession();
	        User user = (User) session.getAttribute("member");
			
	        if(user != null) {
	        	Yes_Dao yesdao = new Yes_Dao();
				Yes yes = yesdao.getYesByIdxEmail(idx , user.getEmail_id());
				
				System.out.println(yes.getEmail_id());
				
				request.setAttribute("yes", yes);
	        }
			request.setAttribute("imgboard", imgboard);
			request.setAttribute("b_code", b_code);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/imgboard_read.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return forward;
	}

}
