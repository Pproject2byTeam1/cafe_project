package kr.or.kosa.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.Comments;

public class DataBoardListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		
		
		HttpSession session = request.getSession();
		try {
			DataBoardDao dao = new DataBoardDao();
			
			//사이드바	
			   Board_Info_Dao infodao = new Board_Info_Dao();
		         List<Board_Info> infolist = infodao.getSideBoardList();
		         
		         request.setAttribute("infolist", infolist);
			
			
			int totalboardcount = dao.totaldataBoard();
			
		
			//상세보기 >> 다시 리스트로 넘어 올때
			String ps = request.getParameter("ps");
			String cp = request.getParameter("cp");
			String b_code = request.getParameter("b_code");
		
			if (ps == null || ps.trim().equals("")) {
				// default 값 설정
				ps = "5"; // 5개씩
			}

			if (cp == null || cp.trim().equals("")) {
				// default 값 설정
				cp = "1"; // 1번째 페이지 보겠다
			}
		
			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int code = Integer.parseInt(b_code);
			int pagecount = 0;
			if(totalboardcount % pagesize == 0) {
				pagecount = totalboardcount / pagesize;
			}else {
				pagecount = (totalboardcount / pagesize) + 1; 
			}
			List<Board> datalist = dao.getAllDatalist(code, cpage, pagesize);
			//댓글
		List<Comments> comlist =dao.getComment(code, pagecount, cpage, pagesize);
			
			
			
			List<Integer> countlist = new ArrayList<Integer>();
			
			for(Board board : datalist) {
				int idx = board.getIdx();
				int count = dao.getYes(idx);
				
				countlist.add(count);
			}
			
			List<Integer> yeslist = new ArrayList<Integer>();
			for(Board board : datalist) {
				int idx1 = board.getIdx();
				int count1 = dao.getComments(idx1);
				
				yeslist.add(count1);
			}
		//List<Comments> comlist1 =dao.getComment(code, pagecount, cpage, pagesize);
			
			int cnt =countlist.size();
			int yes = yeslist.size();
			request.setAttribute("datalist",datalist);
			request.setAttribute("yeslist", yes);
			request.setAttribute("countlist", cnt);
			
			
			
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("totalboardcount", totalboardcount);
			
		
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/databoard_list.jsp");
			
			
		} catch (NamingException e) {
				
			System.out.println(e.getMessage());
		}
				
				
		
		
		return forward;
	}

}
