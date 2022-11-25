package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dto.Board_Info;

public class DeleteOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			ActionForward forward = new ActionForward();
			
		try {
			HttpSession session = request.getSession();
			//로그인 한사람정보
		
			
	      
			Board_Info_Dao infodao = new Board_Info_Dao();
	        List<Board_Info> infolist = infodao.getSideBoardList();
	        int idx = Integer.parseInt(request.getParameter("idx"));
	        String email_id = request.getParameter("email_id");
	       DataBoardDao databoard = new DataBoardDao();
	    
	
			int row =databoard.deleteDataBoard(idx);
			
			String msg = "";
			
			if(row < 0) {
				msg = "실패";
			}else {
				msg = "확인";
			}
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/index.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
