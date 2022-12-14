package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Calender;
import kr.or.kosa.dto.User;
import kr.or.kosa.dto.Yes;
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
			
			Board_Dao dao = new Board_Dao(); 
			List<Calender> list = dao.getCalender_list(b_code);
			
			JSONArray jsonlist = JSONArray.fromObject(list);
			
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
		    
		    JSONArray json = new JSONArray();
			json.add(jsonlist);
			
			if(user != null) {
		    	 String email_id = user.getEmail_id();
				
				Yes_Dao yesdao = new Yes_Dao();
				List<Yes> yes = yesdao.getYesBy_idx(email_id);
				
				JSONArray jsonlist1 = JSONArray.fromObject(yes);
				json.add(jsonlist1);
		    }
			
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
