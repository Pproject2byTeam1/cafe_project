package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Img_Board_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Img_Board;

public class Img_Board_List_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	
		ActionForward forward = new ActionForward();
		
		try {
			
			Board_Dao dao = new Board_Dao(); 
			
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			
			//게시물 총 건수
			int totalboardcount = dao.totalBoardCountByB_code(b_code);
			
			//상세보기 >> 다시  LIST 넘어올때  >> 현재 페이지 설정
			String ps = request.getParameter("ps");
			String cp = request.getParameter("cp");
			
			if(ps == null || ps.trim().equals("")) {
				ps = "5"; //List 페이지 처음 호출 경우 -> 5개씩 
			}
			
			if(cp == null || cp.trim().equals("")) {
				cp = "1"; //default 값 설정 -> 1번째 페이지 보겠다 
			}
			
			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int pagecount = 0;
			
			//23건  % 5
			if(totalboardcount % pagesize == 0) {
				pagecount = totalboardcount / pagesize;
			}else {
				pagecount = (totalboardcount / pagesize) + 1; 
			}
			
			List<Board> list = dao.getBoardListByB_code(b_code, cpage, pagesize);
			
			Img_Board_Dao board_dao = new Img_Board_Dao();
			List<Img_Board> img_list = board_dao.getImg_BoadList(cpage, pagesize);
			
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("totalboardcount", totalboardcount);
			request.setAttribute("list", list);
			request.setAttribute("img_list", img_list);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("imgboard_list.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
