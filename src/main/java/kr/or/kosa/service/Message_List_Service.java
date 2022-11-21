package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Message_Dao;
import kr.or.kosa.dto.Message;

public class Message_List_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();
		
		try {
			
			HttpSession session = request.getSession();
			Message_Dao dao = new Message_Dao();
			String userId = (String) session.getAttribute("userid");
			
			List<Message> messagelist = dao.getMessageByReceiveId("T2@naver.com"/* userId */);//테스트용
			
			request.setAttribute("messagelist", messagelist);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/memo_list.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}

}
