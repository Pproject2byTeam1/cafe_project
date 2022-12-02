package kr.or.kosa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
			int index = Integer.parseInt(request.getParameter("index"));
			
			//날짜계산 준비
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date nowdate =  new Date();
			String nowday = format.format(nowdate);
			Date date3=null;
			try {
				date3 = format.parse(nowday);
				request.setAttribute("date3", format.format(date3));//입력된 날짜  yyyy-MM-dd
				
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date3);
			cal1.add(Calendar.DATE, +1); //날짜 +1일
			Date aftdate = new Date(cal1.getTimeInMillis());
			String aftereday = format.format(aftdate);
			Board_Dao dao = new Board_Dao(); 
			List<Board> board = dao.getBoardList(b_code,date,aftereday);
			
		    request.setAttribute("board", board);
		    request.setAttribute("b", index);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		forward = new ActionForward();
	  	forward.setRedirect(false);
	  	forward.setPath("/WEB-INF/view/boardoutput.jsp");
	  	
		return forward;
		
	}

}
