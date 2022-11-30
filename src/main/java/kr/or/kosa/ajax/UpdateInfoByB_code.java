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
import kr.or.kosa.dao.Board_Rank_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.Board_Rank;
import kr.or.kosa.dto.User;

@WebServlet("/UpdateInfoByB_code")
public class UpdateInfoByB_code extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateInfoByB_code() {
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
		    		int b_code = Integer.parseInt(request.getParameter("b_code"));
		    		int w_rank = Integer.parseInt(request.getParameter("w_rank"));
		    		int re_rank = Integer.parseInt(request.getParameter("re_rank"));
		    		
		    		if(form == null || form.equals("")) {
		    			form = "!";
		    		}
		    		
		    		Board_Info_Dao dao = new Board_Info_Dao();
		    		
		    		Board_Info info = new Board_Info();
		    		info.setB_name(b_name);
		    		info.setForm(form);
		    		info.setB_code(b_code);
		    		
		    		Board_Rank rank = new Board_Rank();
		    		rank.setW_rank(w_rank);
		    		rank.setB_code(b_code);
		    		rank.setRe_rank(re_rank);
		    		
		    		System.out.println(rank);
		    		System.out.println(info);
		    		
			    	int row = dao.updateBoardInfo(info, rank);
			    	
					if(row > 0) {
						msg = "게시판 정보가 수정되었습니다.";
					}else {
						msg = "게시판 정보 수정이 실패하였습니다.";
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
