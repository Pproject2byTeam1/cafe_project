package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.Calender_Dao;


@WebServlet("/DeleteCalender")
public class DeleteCalender extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteCalender() {
        super();
    }


    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			Calender_Dao dao = new Calender_Dao();
			int row = dao.deleteCalender(idx);
			
			String msg = "";
			
			if(row < 0) {
				msg = "실패";
			}else {
				msg = "확인";
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
