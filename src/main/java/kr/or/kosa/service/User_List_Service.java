package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Img_Board_Dao;
import kr.or.kosa.dao.User_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Img_Board;
import kr.or.kosa.dto.User_Details;

public class User_List_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		try {
			
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userid");
			
			User_Dao dao = new User_Dao();
			
			int totalusercount = dao.totalUserCount();
			
			//상세보기 >> 다시  LIST 넘어올때  >> 현재 페이지 설정
			String ps = request.getParameter("ps");
			String cp = request.getParameter("cp");
			
			//List 페이지 처음 호출 경우
			if(ps == null || ps.trim().equals("")) {
				ps = "5"; //5개씩 
			}
			
			if(cp == null || cp.trim().equals("")) {
				//default 값 설정
				cp = "1"; // 1번째 페이지 보겠다 
			}
			
			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int pagecount=0;
			
			if(totalusercount % pagesize == 0) {
				pagecount = totalusercount / pagesize;
			}else {
				pagecount = (totalusercount / pagesize) + 1; 
			}
			
			List<User_Details> alluser = dao.getUserListAll();
			
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("totalusercount", totalusercount);
			request.setAttribute("alluser", alluser);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/user_list.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
