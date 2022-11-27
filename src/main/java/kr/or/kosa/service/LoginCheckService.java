package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;

public class LoginCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("");
		
		return forward;
	}
}

