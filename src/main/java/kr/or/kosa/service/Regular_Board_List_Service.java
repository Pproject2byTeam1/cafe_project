package kr.or.kosa.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.Regular_Board;
import kr.or.kosa.dto.User;

public class Regular_Board_List_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		try {
			Board_Info_Dao infodao = new Board_Info_Dao();
	        List<Board_Info> infolist = infodao.getSideBoardList();

	        
			Board_Dao dao = new Board_Dao(); 
			Yes_Dao ydao = new Yes_Dao();
			CommentsDao cdao = new CommentsDao();
			UserDao udao = new UserDao();
			
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
			
			List<Regular_Board> list = dao.getRegular_boardList(b_code, cpage, pagesize);
			
			//yes, 댓글 수
			List yescountlist = new ArrayList();
			List commentcountlist = new ArrayList();
			List ranklist = new ArrayList();
			
			for(Regular_Board re : list) {	
				int idx = re.getIdx();
				String email_id = re.getEmail_id();
				int yescount = ydao.getYesCountBy_idx(idx);
				int commentcount = cdao.getCommentCountBy_idx(idx);
				
				User user = udao.selectUserById(email_id);
				int rank = user.getRank();
						
				yescountlist.add(yescount);
				commentcountlist.add(commentcount);
				ranklist.add(rank);
			}
			
		
			request.setAttribute("infolist", infolist);
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("totalboardcount", totalboardcount);
			request.setAttribute("list", list);
			request.setAttribute("yes", yescountlist);
			request.setAttribute("comment", commentcountlist);
			request.setAttribute("rank", ranklist);
			request.setAttribute("b_code", b_code);
			
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/regularboard_list.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
