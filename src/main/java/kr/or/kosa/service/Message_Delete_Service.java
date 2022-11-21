package kr.or.kosa.service;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Message_Dao;

public class Message_Delete_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			
			Message_Dao dao = new Message_Dao();
			
			String[] m_idx = request.getParameterValues("m_idx");
			System.out.println(m_idx);
			String delCheckNo = (String)request.getParameter("delCheckNo");
			System.out.println(delCheckNo);
			String idx = Arrays.toString(m_idx).replace("[", "").replace("]", "");
			System.out.println(idx);

			/*
			 * int row = dao.deleteMessageByIdx(idx); if(row>0) {
			 * System.out.println("삭제 성공"); }else { System.out.println("삭제 실패"); }
			 */

			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/memoListAppand.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;
	}

}
