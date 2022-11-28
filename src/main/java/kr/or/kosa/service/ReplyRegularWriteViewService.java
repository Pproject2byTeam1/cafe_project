package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dto.Board_Info;

public class ReplyRegularWriteViewService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			int refer = Integer.parseInt(request.getParameter("refer"));
			int depth = Integer.parseInt(request.getParameter("depth"));
			int step = Integer.parseInt(request.getParameter("step"));
			
			//사이드 바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
			request.setAttribute("infolist", infolist);
			request.setAttribute("b_code", b_code);
			request.setAttribute("refer", refer);
			request.setAttribute("depth", depth);
			request.setAttribute("step", step);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/regularboard_reply.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
