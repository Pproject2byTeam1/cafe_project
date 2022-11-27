package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.User;

public class SnsCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		String url = "";
		
		try {
			String email = request.getParameter("hiddentext");
			System.out.println(email);
			UserDao dao = new UserDao();
			User user = dao.selectUserById(email);
			if(user != null) {//이미 가입된 경우 로그인 처리 후 메인화면 ㄱㄱ
				HttpSession session = request.getSession();
				session.setAttribute("member", user);
				url = "/index.jsp";
			}else {//아니면 등록 페이지 ㄱㄱ
				url = "/snsRegister.jsp";
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
        forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		return forward;
	}

}
