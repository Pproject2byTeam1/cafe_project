package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.User;

public class Data_Board_Post_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	ActionForward forward = new ActionForward();
		
		try {
			HttpSession session = request.getSession();
	          User user = (User) session.getAttribute("member");
			Board_Info_Dao infodao = new Board_Info_Dao();
	        List<Board_Info> infolist = infodao.getSideBoardList();
	        int idx = Integer.parseInt(request.getParameter("idx"));
	        
	        UserDao udao = new UserDao();
			DataBoardDao dao = new DataBoardDao();
			Board_Dao bdao = new Board_Dao();

			bdao.updateHits(idx);
			Board board = dao.getData_BoardByIdx(idx);
			User rank = udao.selectUserById(board.getEmail_id());
		
			request.setAttribute("infolist", infolist);
			request.setAttribute("board", board);
			request.setAttribute("rank", rank);
			
			
	
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/data_contentview.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}
	}


