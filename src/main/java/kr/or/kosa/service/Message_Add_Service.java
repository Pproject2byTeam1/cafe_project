package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MessageDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.Message;

public class Message_Add_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		Message message = null;
		int row = 0;
		
		try {
			
			String writer = (String) request.getParameter("writer");//내 아이디
			String reader = (String) request.getParameter("reader");//받는사람 아이디
			String content = request.getParameter("content");
			UserDao user_Dao = new UserDao();
			String writerNick = user_Dao.selectUserById(writer).getNick();
			String readerNick = user_Dao.selectUserById(reader).getNick();
			
			//삽입
			MessageDao dao = new MessageDao();
			message = new Message();
			message.setSend_id(writer);
			message.setReceive_id(reader);
			message.setM_content(content);
			message.setSend_nick(writerNick);
			message.setReceive_nick(readerNick);
			
			row = dao.insertMessage(message);
			
		} catch (Exception e) {
			if(row > 0) {
				System.out.println("삽입 성공");
			}else {
				System.out.println("삽입 실패");
			}
			System.out.println(e.getMessage());
		}
		
		forward = new ActionForward();
	  	forward.setRedirect(false);
	  	forward.setPath("memo_list.do");

		return forward;
	}

}
