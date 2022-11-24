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
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.Comments;
import kr.or.kosa.dto.DataBoard;
import kr.or.kosa.dto.Regular_Board;

public class DataBoardListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
	
		HttpSession session = request.getSession();
		try {
			DataBoardDao dao1= new DataBoardDao();
		     
					Board_Dao dao = new Board_Dao(); 
					Yes_Dao ydao = new Yes_Dao();
					CommentsDao cdao = new CommentsDao();
					int b_code = Integer.parseInt(request.getParameter("b_code"));
			//사이드바	
			   Board_Info_Dao infodao = new Board_Info_Dao();
		         List<Board_Info> infolist = infodao.getSideBoardList();
		         
		         request.setAttribute("infolist", infolist);
					
			
			int totalboardcount = dao1.totaldataBoard();
			
		
			//상세보기 >> 다시 리스트로 넘어 올때
			String ps = request.getParameter("ps");
			String cp = request.getParameter("cp");
		
		
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

			int pagecount = 0;
			if(totalboardcount % pagesize == 0) {
				pagecount = totalboardcount / pagesize;
			}else {
				pagecount = (totalboardcount / pagesize) + 1; 
			}
		
			//댓글
		
			
			List<DataBoard> list = dao.getdata_boardList(b_code, cpage, pagesize);
			
			List<Integer> countlist = new ArrayList<Integer>();
			List yescountlist = new ArrayList();
			List commentcountlist = new ArrayList();
			
			for(DataBoard re : list) {	
				int idx = re.getIdx();
				int yescount = ydao.getYesCountBy_idx(idx);
				int commentcount = cdao.getCommentCountBy_idx(idx);
						
				yescountlist.add(yescount);
				commentcountlist.add(commentcount);
			}
			
		
		//List<Comments> comlist1 =dao.getComment(code, pagecount, cpage, pagesize);
			
		
			
			
			request.setAttribute("infolist", infolist);
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("totalboardcount", totalboardcount);
			request.setAttribute("list", list);
			request.setAttribute("yes", yescountlist);
			request.setAttribute("comment", commentcountlist);
			
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("totalboardcount", totalboardcount);
			
		
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/databoard_list.jsp");
			
			
		} catch (NamingException e) {
				
			System.out.println(e.getMessage());
		}
				
				
		
		
		return forward;
	}

}
