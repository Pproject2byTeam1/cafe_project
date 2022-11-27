package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.User;

public class UserDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String url = "";
		String msg="";
		
		try {
			 HttpSession session = request.getSession();
			 User user = (User) session.getAttribute("member");
			 String userId = user.getEmail_id();
			 UserDao dao = new UserDao();
			 
			 //삭제 실행
			 int row = dao.deleteUser(userId);
			 
			 if(row >0) {//삭제 성공
				 msg ="삭제 되었습니다.";
				 session.invalidate();
				 url = "index.jsp";
				 request.setAttribute("board_msg", msg);
				 request.setAttribute("board_url", url);
			 }else {//삭제 실패
				 msg ="삭제 실패";
				 url = "userinfo.do";
				 request.setAttribute("board_msg", msg);
				 request.setAttribute("board_url", url);
			 }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		forward = new ActionForward();
	  	forward.setRedirect(false);
	  	forward.setPath("/WEB-INF/view/redirect.jsp");
		
		return forward;
	}

}
