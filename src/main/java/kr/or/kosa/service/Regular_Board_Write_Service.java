package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;

public class Regular_Board_Write_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		String msg = "";
		String url = "";
		
		int result = 0;
		
		try {
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}

}
