package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dto.User;


@WebServlet("/ReplyDeleteOk")
public class ReplyDeleteOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyDeleteOk() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {
    		
    		int idx = Integer.parseInt(request.getParameter("idx"));
    		
    		HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
    		
    		CommentsDao codao = new CommentsDao();
    		
    		int result = codao.deleteCommentByCo_idx(idx, user.getEmail_id());
    		
    		String msg = "";
    		if(result > 0) {
    			msg = "성공";
    		}else {
    			msg = "실패";
    		}
    		
    		out.print(msg);
			
		} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

}
