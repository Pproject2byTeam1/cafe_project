package kr.or.kosa.service;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MessageDao;
import kr.or.kosa.dto.Message;
import kr.or.kosa.dto.User;

public class MessageDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		    
		try {
			HttpSession session = request.getSession();
			MessageDao dao = new MessageDao();
			
			String[] m_idx = request.getParameterValues("idxarr[]");
			String delCheckNo = (String)request.getParameter("delCheckNo");
			String idx = Arrays.toString(m_idx).replace("[", "").replace("]", "");

			//진짜 삭제처리
			int row = dao.deleteMessageByIdx(idx); 
			
			
			//다시 리스트 뽑기
			User user2 = (User) session.getAttribute("member");
			String userId = user2.getEmail_id();
			List<Message> messagelist = dao.getMessageByReceiveId(userId );//테스트용
			request.setAttribute("messagelist", messagelist);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/memoListAppand.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;
	}

}
