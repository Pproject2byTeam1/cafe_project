package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.Yes_Dao;

@WebServlet("/YesRemove")
public class YesRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public YesRemove() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
    		String email_id = request.getParameter("email_id");
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			Yes_Dao dao = new Yes_Dao();
			int row = dao.checkRemoveCal(email_id, idx);
			
			String msg = "";
			
			if(row > 0) {
				msg = "취소";
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
