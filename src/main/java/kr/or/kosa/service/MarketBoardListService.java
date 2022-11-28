package kr.or.kosa.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.MarketBoard;
import kr.or.kosa.dto.User;
import kr.or.kosa.utils.ThePager;

public class MarketBoardListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	
		ActionForward forward = new ActionForward();
		
		try {

			//사이드 바
			Board_Info_Dao infodao = new Board_Info_Dao();
		    List<Board_Info> infolist = infodao.getSideBoardList();

			MarketBoardDao market_dao = new MarketBoardDao(); 
			Yes_Dao ydao = new Yes_Dao();
			CommentsDao cdao = new CommentsDao();
			UserDao udao = new UserDao();
			
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			
			//게시물 총 건수
			int totalboardcount = market_dao.countMarket(b_code);			
			
			//판매중 개수
			int soldcount = market_dao.countSoldF(b_code);
			
			//상세보기 >> 다시  LIST 넘어올때  >> 현재 페이지 설정
			String ps = request.getParameter("ps");
			String cp = request.getParameter("cp");

			if(ps == null || ps.trim().equals("")) {
				ps = "8"; //List 페이지 처음 호출 경우 -> 8개씩 
			}
			
			if(cp == null || cp.trim().equals("")) {
				cp = "1"; //default 값 설정 -> 1번째 페이지 보겠다 
			}
			
			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int pagecount = 0;
			
			if(totalboardcount % pagesize == 0) {
				pagecount = totalboardcount / pagesize;
			}else {
				pagecount = (totalboardcount / pagesize) + 1; 
			}
			
			System.out.println("몇번페이지 : " + cpage);
			System.out.println("pagesize : " + pagesize);
			List<MarketBoard> list = market_dao.listMarket(b_code, cpage, pagesize);
			
			//yes, 댓글수
			List yescountlist = new ArrayList();
			List commentcountlist = new ArrayList();
			List ranklist = new ArrayList();
			
			for (MarketBoard mb : list) {
				int idx = mb.getIdx();
				String email_id = mb.getEmail_id();
				int yescount = ydao.getYesCountBy_idx(idx);
				int commentcount = cdao.getCommentCountBy_idx(idx);
				
				User user = udao.selectUserById(email_id);
				int rank = user.getRank();
				yescountlist.add(yescount);
				commentcountlist.add(commentcount);
				ranklist.add(rank);
			
			}
		
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("totalboardcount", totalboardcount);
			request.setAttribute("list", list);
			request.setAttribute("b_code", b_code);
			request.setAttribute("soldcount", soldcount);
			request.setAttribute("infolist", infolist);
			request.setAttribute("yes", yescountlist);
			request.setAttribute("comments", commentcountlist);
			request.setAttribute("rank", ranklist);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/marketboard_list.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
