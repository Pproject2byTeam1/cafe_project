package kr.or.kosa.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.AdminDao;
import kr.or.kosa.dto.AttendanceBoad;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.User;

public class RapportListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		ActionForward forward = new ActionForward();
		
		
		try {
			Board_Info_Dao infodao = new Board_Info_Dao();
	        List<Board_Info> infolist = infodao.getSideBoardList();
			
			HttpSession session = request.getSession();
	          User user = (User) session.getAttribute("member");
			String userId = (String) session.getAttribute("userid");
			
			AdminDao dao = new AdminDao();
			int totalreportcount = dao.totalreportCount();
			String ps = request.getParameter("ps");
			String cp = request.getParameter("cp");
			//게시물 총건수 


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
			
			
			List<AttendanceBoad> reportlist = dao.reportlist(cpage, pagesize);
			List<AttendanceBoad> reportlist2 = dao.reportlist(cpage, pagesize);
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("totalreportcount",totalreportcount);
			request.setAttribute("reportlist", reportlist);
			request.setAttribute("reportlist2", reportlist2);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/rapport_list.jsp");
			
		  	List boardlist = new ArrayList();
		for(AttendanceBoad re :reportlist) {
			int idx=re.getIdx();
			Board_Info boardnum = dao.getBoardInfo(idx);
			boardlist.add(boardnum);
		}
		
		
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("cpage", cpage);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("totalboardcount", totalreportcount);
		request.setAttribute("boardnum", boardlist);
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		

		return forward;
	}

}
