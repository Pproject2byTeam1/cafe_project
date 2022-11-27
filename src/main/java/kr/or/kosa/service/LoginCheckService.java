package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.User;

public class LoginCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		String url = "";
		int row = 0;
		try {
			String email_id = request.getParameter("email_id");
			String name = request.getParameter("name");
			String nick = request.getParameter("nick");
			String date = request.getParameter("date");
			String phone = request.getParameter("phone");
			
			UserDao dao = new UserDao();
			HttpSession session = request.getSession();
			
			if(dao.isCheckById(email_id).equals("false")) {//없는놈이면 만들고
				row = dao.joinSnsUser(email_id, phone, name, nick, date);
				if(row == 2) {//삽입에 성공시 로그인
					User user = dao.selectUserById(email_id);
					session.setAttribute("member", user);
				}else {//실패시
					//알림창 띄우고 첫 화면으로
					session.invalidate();
				}
			}else {//있으면 넣고 바로 이동
				
				User user = dao.selectUserById(email_id);
				session.setAttribute("member", user);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("index.jsp");
		
		return forward;
	}
}

