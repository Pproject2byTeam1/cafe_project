package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MessageDao;
import kr.or.kosa.dto.Message;
import kr.or.kosa.dto.User;

public class MessageListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();
		
		try {
			
			HttpSession session = request.getSession();
			MessageDao dao = new MessageDao();
			User user2 = (User) session.getAttribute("member");
			String userId = user2.getEmail_id();
			
			List<Message> messagelist = dao.getMessageByReceiveId(userId );//테스트용
			
			request.setAttribute("messagelist", messagelist);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/memo_list.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}

}
