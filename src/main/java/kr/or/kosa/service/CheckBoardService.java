package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;

public class CheckBoardService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		forward = new ActionForward();
	  	forward.setRedirect(false);
	  	forward.setPath("/WEB-INF/view/attendancecheck_board.jsp");
	  	
		return forward;
	}

}
