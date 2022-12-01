package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dto.User;

@WebServlet("/AttendenceCheck")
public class AttendenceCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AttendenceCheck() {
        super();
    }

private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
    		//유저정보 가져오기
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
		    String email_id = user.getEmail_id();
    		
    		//날짜계산 준비
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		Date nowdate = new Date();
    		String nowday = format.format(nowdate);
    		
    		Date date=null;
    		try {
    			date = format.parse(nowday);
    		} catch (ParseException e2) {
    			e2.printStackTrace();
    		}
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(date);
    		cal.add(Calendar.DATE, 1); //날짜 +1일
    		Date nexdate = new Date(cal.getTimeInMillis());
    		String nextday = format.format(nexdate);
    		
			Board_Dao dao = new Board_Dao();
			int count = dao.AttendenceCheckMe(nowday,nextday,email_id);
			System.out.println(count);
			out.print(count);
			
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
