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
import kr.or.kosa.dto.Comments;
import kr.or.kosa.dto.User;


@WebServlet("/ReplyReplyOk")
public class ReplyReplyOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReplyReplyOk() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
		
		try {
			int result = 0;
			String msg = "";
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			int co_idx = Integer.parseInt(request.getParameter("co_idx"));
			int depth = Integer.parseInt(request.getParameter("depth"));
			int step = Integer.parseInt(request.getParameter("step"));
			String content = request.getParameter("content");
			
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
		    
		    if(user == null) {
		    	msg = "로그인 해주세요";
		    }else {
			    Comments comments = new Comments();
			    comments.setRefer(co_idx);
			    comments.setIdx(idx);
			    comments.setContent(content);
			    comments.setEmail_id(user.getEmail_id());
			    comments.setNick(user.getNick());
			    comments.setDepth(depth);
			    comments.setStep(step);
			    
			    CommentsDao codao = new CommentsDao();
			    result = codao.insertReplyReply(comments);
			    
				if(result > 0){
			    	msg ="댓글 입력 성공";
			    }else{
			    	msg="댓글 입력 실패";
			    }
		    }
			
			out.print(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
