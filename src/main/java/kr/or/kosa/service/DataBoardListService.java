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
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.DataBoard;
import kr.or.kosa.dto.User;

public class DataBoardListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		try {
			HttpSession session = request.getSession();
			Board_Dao boarddao = new Board_Dao();
			DataBoardDao dao = new DataBoardDao();
			Yes_Dao ydao = new Yes_Dao();
			CommentsDao cdao = new CommentsDao();
			UserDao udao = new UserDao();
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			
			// 관리자만 삭제가능하게
			User user = (User) session.getAttribute("member");
			request.setAttribute("member", user);
			
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);//top
			
			// 사이드바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();

			Board_Info boardinfo = new Board_Info();

	        for(Board_Info info : infolist) {
	        	if(info.getB_code() == b_code) {
	        		boardinfo.setB_name(info.getB_name());
	        		boardinfo.setB_type(info.getB_type());
	        	}
	        }
			
	        request.setAttribute("boardinfo", boardinfo);
			request.setAttribute("infolist", infolist);

			int totalboardcount = boarddao.totalBoardCountByB_code(b_code);

			// 상세보기 >> 다시 리스트로 넘어 올때
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
			
			if (totalboardcount % pagesize == 0) {
				pagecount = totalboardcount / pagesize;
			} else {
				pagecount = (totalboardcount / pagesize) + 1;
			}

			// 댓글
			
			//리스트 뿌리기
			List<DataBoard> list = dao.getdata_boardList(b_code, cpage, pagesize);

			List<Integer> yescountlist = new ArrayList<Integer>();
			List<Integer> commentcountlist = new ArrayList<Integer>();
			List<Integer> ranklist = new ArrayList<Integer>();
			
			for (DataBoard re : list) {
				int idx = re.getIdx();
				String email_id = re.getEmail_id();
				int yescount = ydao.getYesCountBy_idx(idx);
				int commentcount = cdao.getCommentCountBy_idx(idx);
				User user1 = udao.selectUserById(email_id);
				int rank = user1.getRank();

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
			request.setAttribute("ps", pagesize);
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/view/databoard_list.jsp");

		} catch (NamingException e) {

			System.out.println(e.getMessage());
		}

		return forward;
	}

}
