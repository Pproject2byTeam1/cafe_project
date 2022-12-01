package kr.or.kosa.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;

public class getBoardList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			Board_Info_Dao infodao = new Board_Info_Dao();
			//저장된 보드 정보 가져오기
			List<Board_Info> info = infodao.getBoardindex();
			
			String date = request.getParameter("date");
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			int b = info.get(b_code-1).getMain_idx();
			//날짜계산 준비
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date nowdate =  new Date();
			String nowday = format.format(nowdate);
			
			Board_Dao dao = new Board_Dao(); 
			List<Board> board = dao.getBoardList(b_code,date,nowday);
			
		    request.setAttribute("board", board);
		    request.setAttribute("b", b);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		forward = new ActionForward();
	  	forward.setRedirect(false);
	  	forward.setPath("/WEB-INF/view/boardoutput.jsp");
	  	
		return forward;
		
	}

}
