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


@WebServlet("/ModifyCalender")
public class ModifyCalender extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ModifyCalender() {
        super();
    }

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
    		int b_code = Integer.parseInt(request.getParameter("b_code"));
    		int idx = Integer.parseInt(request.getParameter("idx"));
			String title = request.getParameter("title");
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
			String content = request.getParameter("content");
			String finish = request.getParameter("finish");
			
			Calender calender = new Calender();
			calender.setB_code(b_code);
			calender.setIdx(idx);
			calender.setTitle(title);
			calender.setStart_date(start_date);
			calender.setEnd_date(end_date);
			calender.setContent(content);
			calender.setFinish(finish);
    		
			Calender_Dao dao = new Calender_Dao();
			int row = dao.modifyCalender(calender);
			
			String msg = "";
			
			if(row > 0) {
				msg = "확인";
			}else {
				msg = "실패";
			}
			
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
