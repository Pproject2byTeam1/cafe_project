package kr.or.kosa.service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.AdminDao;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;

public class RapportListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		ActionForward forward = new ActionForward();
		
		
		try {
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userid");
			
			AdminDao dao = new AdminDao();
			int totalreportcount = dao.totalreportCount();
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
			
			if(totalreportcount % pagesize == 0) {
				pagecount = totalreportcount / pagesize;
			}else {
				pagecount = (totalreportcount / pagesize) + 1; 
			}
			
			
			List<Board> reportlist = dao.reportlist(cpage, pagesize);
		
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("totalreportcount",totalreportcount);
			request.setAttribute("reportlist", reportlist);
			
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/rapport_list.jsp");
			
			
			/*
			 * List<String> countlist = new ArrayList<String>();
			 * 
			 * for(Board baord :reportlist) { int b_code = baord.getB_code(); Board_Info
			 * count=dao.getBoardInfo(b_code);
			 * 
			 * countlist.addAll(count);
			 * 
			 * 
			 * 
			 * 
			 * }
			 * 
			 */
			
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		

		return forward;
	}

}
