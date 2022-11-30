package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.AdminDao;
import kr.or.kosa.dao.Board_Dao;

@WebServlet("/RepCount")
public class RepCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RepCount() {
        super();
    }

private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    		
    	try {  		
    		int idx = Integer.parseInt(request.getParameter("idx"));	
    		
			
			Board_Dao dao =new Board_Dao();
	int row = dao.upReport(idx);
	
			String msg = "";
			
			if(row >0) {
				msg = "신고되었습니다";
			}else {
				msg = "신고되지않았습니다";
			}
			
		//	out.print("신고수 결과" +msg);
			 out.print(msg);
    		
    	} catch(Exception e) {
    		System.out.println("신고"+e.getMessage());
    	}
    	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
