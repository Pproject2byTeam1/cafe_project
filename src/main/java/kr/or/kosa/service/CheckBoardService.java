package kr.or.kosa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dto.AttendanceBoad;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.User;

public class CheckBoardService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		List<AttendanceBoad> boardlist = null;
		int count = 0;
		
		//날짜계산 준비
		String inputdate = (String) request.getParameter("inputdate");
		System.out.println("inputdate: "+inputdate);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date nowdate = null;
		String nowday = "";
		if(inputdate == null) {//입력받은 날짜가 없다면
			nowdate= new Date();
			nowday = format.format(nowdate);
		}else {//있으면
			try {
				nowdate= format.parse(inputdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			nowday = format.format(nowdate);
		}
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
		int nowday2 = Integer.parseInt(sdf2.format(nowdate));
		request.setAttribute("nowday2", nowday2); // 일자
		Date date=null;
		try {
			date = format.parse(nowday);
			request.setAttribute("date5", format.format(date));//입력된 날짜 yyyy-MM-dd
			
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		//각 년 월, 일 구하기
		int year = cal.get(Calendar.YEAR);
		request.setAttribute("year", year);
		int month = cal.get(Calendar.MONTH)+1;
		request.setAttribute("month", month);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		request.setAttribute("days", days);
		
		cal.add(Calendar.DATE, 1); //날짜 +1일
		Date nexdate = new Date(cal.getTimeInMillis());
		String nextday = format.format(nexdate);

		
		
		try {
			//사이드바 정보
	        Board_Info_Dao infodao = new Board_Info_Dao();
	        List<Board_Info> infolist = infodao.getSideBoardList();
	        
	        request.setAttribute("infolist", infolist);
	        
	        //유저정보 가져가기
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("member");
			String userId = null;
			if(user != null) {
				userId = user.getEmail_id();
			}
			System.out.println(userId);
			request.setAttribute("userId", userId);

			Board_Dao dao = new Board_Dao();
			
			//총 숫자 가져가기
			count = dao.countAttendenceBoard(nowday, nextday);
			request.setAttribute("count", count);
			
			//상세보기 >> 다시  LIST 넘어올때  >> 현재 페이지 설정
			String ps = request.getParameter("ps");
			String cp = request.getParameter("cp");

			if(ps == null || ps.trim().equals("")) {
				ps = "10"; //List 페이지 처음 호출 경우 -> 10개씩 
			}
			
			if(cp == null || cp.trim().equals("")) {
				cp = "1"; //default 값 설정 -> 1번째 페이지 보겠다 
			}
			
			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int pagecount = 0;
			
			if(count % pagesize == 0) {
				pagecount = count / pagesize;
			}else {
				pagecount = (count / pagesize) + 1; 
			}
			
			int b_code = Integer.parseInt(request.getParameter("b_code")); 
			
			//작성목록 가져가기
			boardlist = dao.getBoardListAttendence(b_code, cpage, nowday, nextday);
			request.setAttribute("boardlist", boardlist);
			System.out.println("boardlist: "+boardlist);

			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			
		} catch (Exception e) {
			System.out.println("error: "+e.getMessage());
		}
		
		forward = new ActionForward();
	  	forward.setRedirect(false);
	  	forward.setPath("/WEB-INF/view/attendancecheck_board.jsp");
	  	
		return forward;
	}

}
