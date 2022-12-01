package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.Yes_Dao;
import net.sf.json.JSONObject;

@WebServlet("/GetCountYes")
public class GetCountYes extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public GetCountYes() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
		    
		    String msg = "";
		    
		    int idx = Integer.parseInt(request.getParameter("idx"));
		    
		    Yes_Dao yesdao = new Yes_Dao();
		    int row = yesdao.boardYesCount(idx);
		    List<String> nicklist = yesdao.getYesMember(idx);
		    
		    JSONObject json = new JSONObject();
		    json.put("count", row);
		    json.put("nicklist", nicklist);
			
			out.print(json);
			
    		
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
