package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.Board_Rank_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.Board_Rank;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.Comments;
import kr.or.kosa.dto.MarketBoard;
import kr.or.kosa.dto.User;
import kr.or.kosa.utils.ViewCountPrevent;

public class MarketBoardReadService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		try {
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);//top
			
			//사이드 바
			Board_Info_Dao infodao = new Board_Info_Dao();
		    List<Board_Info> infolist = infodao.getSideBoardList();
	        
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			int cp = Integer.parseInt(request.getParameter("cp"));
			
			Board_Info boardinfo = new Board_Info();

	        for(Board_Info info : infolist) {
	        	if(info.getB_code() == b_code) {
	        		boardinfo.setB_name(info.getB_name());
	        		boardinfo.setB_type(info.getB_type());
	        	}
	        }
			
			//DAO 불러오기
			MarketBoardDao dao = new MarketBoardDao();
			UserDao udao = new UserDao();
			Yes_Dao ydao = new Yes_Dao();
			CommentsDao cdao = new CommentsDao();

			//세션 불러오기
			HttpSession session = request.getSession();
			User user1 = (User) session.getAttribute("member");

			if (user1 != null) {
				Yes_Dao yesdao = new Yes_Dao();
				String yes = yesdao.getYesEmailByIdxEmail(idx, user1.getEmail_id());

				if (yes != null) {
					request.setAttribute("yes2", yes);
				} else {
					request.setAttribute("yes2", "no");
				}
			}
			
			//특정 게시글 조회, 댓글 조회
			MarketBoard list = dao.readMarket(idx);
			List<Comments> comments = cdao.getCommentListByIdx(idx);
			int commentscount = cdao.getCommentCountBy_idx(idx);
			
			//특정 유저 단순정보 조회
			User user = udao.selectUserById(list.getEmail_id());
			int yes = ydao.getYesCountBy_idx(idx);
			
			//조회수 증가
			ViewCountPrevent prevent = new ViewCountPrevent();
			prevent.viewCountPrevent(idx, request, response);
			
			request.setAttribute("boardinfo", boardinfo);
			request.setAttribute("infolist", infolist);
			request.setAttribute("list", list);
			request.setAttribute("b_code", b_code);
			request.setAttribute("cpage", cp);
			request.setAttribute("user", user);
			request.setAttribute("comments", comments);
			request.setAttribute("commentscount", commentscount);
			request.setAttribute("yes", yes);
			
			Board_Rank_Dao boardrankdao = new Board_Rank_Dao();
			Board_Rank boardrank = boardrankdao.getBoardRank(b_code);
			
			String url = "";
			
			if(user1 == null) {
				String board_msg = "로그인이 필요한 기능입니다.";
				String board_url = "/WebCafe_Project/login_view.do";
				
				request.setAttribute("board_msg", board_msg);
				request.setAttribute("board_url", board_url);
				
				url = "/WEB-INF/view/redirect.jsp";
			}else if(user1.getRank() >= boardrank.getW_rank() || user1.getIsAdmin().equals("M")) {
				
				request.setAttribute("b_code", b_code);
				url = "/WEB-INF/view/marketboard_read.jsp";
				
			}else {
				
				String board_msg = boardrank.getW_rank() + "등급부터 확인 가능합니다.";
				String board_url = "/WebCafe_Project/marketboard_list.do?b_code="+b_code;

				request.setAttribute("board_msg", board_msg);
				request.setAttribute("board_url", board_url);
				
				url = "/WEB-INF/view/redirect.jsp";
			}
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath(url);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
