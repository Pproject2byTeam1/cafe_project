package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.User;

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
		    
		    msg = String.valueOf(row);
			
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
