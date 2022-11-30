package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.User;


@WebServlet("/BoardInfoInsert")
public class BoardInfoInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardInfoInsert() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
		    
		    String msg = "";
		    
		    if(user == null) {
		    	msg = "로그인이 필요한 기능입니다.";
		    }else {
		    	
		    	if(!user.getIsAdmin().equals("M")) {
		    		
		    		msg = "관리자 권한이 필요한 기능입니다.";
		    		
		    	}else {
		    		
		    		String b_name = request.getParameter("b_name");
		    		String form = request.getParameter("form");
		    		String b_type = request.getParameter("b_type");
			    	
			    	Board_Info_Dao infodao = new Board_Info_Dao();
			    	
			    	Board_Info info = new Board_Info();
			    	info.setB_name(b_name);
			    	info.setForm(form);
			    	info.setB_type(b_type);
			    	
			    	int row = infodao.insertBoard(info);
					
					if(row > 0) {
						msg = "새로운 카테고리가 추가 되었습니다.";
					}else {
						msg = "새 카테고리 추가를 실패하였습니다.";
					}
		    	}
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
