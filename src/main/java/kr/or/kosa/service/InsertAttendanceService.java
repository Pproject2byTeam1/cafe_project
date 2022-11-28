package kr.or.kosa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dto.AttendanceBoad;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.User;

public class InsertAttendanceService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		List<AttendanceBoad> boardlist = null;
		int row = 0;
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		int size = 1024 * 1024 * 10;
		
		//날짜계산 준비
				String inputdate = (String) request.getParameter("inputdate");
				System.out.println("inputdate: "+inputdate);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date nowdate = new Date();
				String nowday = format.format(nowdate);
				
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
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("member");
			
			Board_Dao dao = new Board_Dao();
			//글쓰기
			MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8", new DefaultFileRenamePolicy());
			
			AttendanceBoad board = new AttendanceBoad();
			board.setB_code(2);
			board.setContent(multi.getParameter("content"));
			System.out.println("content: " + multi.getParameter("content"));
			board.setEmail_id(user.getEmail_id());
			board.setNick(user.getNick());
			board.setTitle(multi.getParameter("content"));
			
			row = dao.insertBoard(board);
			String msg = "";
			if(row > 0) {
				System.out.println("작성 성공");
				msg = "성공";
			}else {
				System.out.println("작성 실패");
				msg = "실패";
			}
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", "checkBoard.do");
			//작성목록 가져가기
				boardlist = dao.getBoardListAttendence(1, nowday, nextday);//임시적으로 1페이지만 구함
				request.setAttribute("boardlist", boardlist);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		forward = new ActionForward();
	  	forward.setRedirect(false);
	  	forward.setPath("/WEB-INF/view/redirect.jsp");
	  	
		return forward;
	}

}
