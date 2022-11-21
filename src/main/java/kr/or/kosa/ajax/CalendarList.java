package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Calender;
import net.sf.json.JSONArray;


@WebServlet("/CalendarList")
public class CalendarList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
    		int b_code = Integer.parseInt(request.getParameter("b_code"));
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			
			Board_Dao dao = new Board_Dao(); 
			List<Calender> list = dao.getCalender_list(b_code, year, month);
			
			JSONArray jsonlist = JSONArray.fromObject(list);
			
			out.print(jsonlist);
    		
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
