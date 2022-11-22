package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.Calender_Dao;
import kr.or.kosa.dto.Calender;

@WebServlet("/AddCalender")
public class AddCalender extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddCalender() {
        super();
    }

private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
    		String email_id = request.getParameter("email_id");
    		String nick = request.getParameter("nick");
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			String title = request.getParameter("title");
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
			String content = request.getParameter("content");
			String finish = request.getParameter("finish");
			
			Calender calender = new Calender();
			calender.setTitle(title);
			calender.setNick(nick);
			calender.setContent(content);
			calender.setEmail_id(email_id);
			calender.setB_code(b_code);
			calender.setStart_date(start_date);
			calender.setEnd_date(end_date);
			calender.setFinish(finish);
			
			Calender_Dao dao = new Calender_Dao();
			int row = dao.AddCalender(calender);
			
			String msg = "";
			
			if(row > 0) {
				msg = "확인";
			}else {
				msg = "실패";
			}
			
			System.out.println(msg);
			
			out.print(msg);
			
    		
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
