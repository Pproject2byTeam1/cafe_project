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

import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dto.Comments;
import kr.or.kosa.dto.User;


@WebServlet("/ReplyList")
public class ReplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReplyList() {
        super();
    }

private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {
    		
			int idx= Integer.parseInt(request.getParameter("idx"));
    		
    		CommentsDao dao = new CommentsDao();
    		List<Comments> replylist = dao.getCommentListByIdx(idx);
    		
    		HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
    		
    		for(Comments reply : replylist) {
    			if(reply.getDepth() <= 0) {
	    			out.print("<div class='comment-card'>");
	    				out.print("<div class='comment-box'>");
	    					out.print("<div class='row'>");
	    						out.print("<div class='col'>");
	    							out.print("<a data-bs-toggle=\"dropdown\"><h5 class='card-title' id='replynick'>");
	    								out.print("<img src='image/rank_icon/1.gif' alt='Profile' class='rounded-circle'>"+reply.getNick());
	    							out.print("</h5></a>");
	    							out.print("<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">\r\n"
	    									+ "		<li class=\"anw_memo\"><a class=\"dropdown-item sendToMemo\" href=\"write_memo.do?sender_id=${board.email_id}\" >답장 보내기</a></li>\r\n"
	    									+ "		<li class=\"view_user_activity\"><a class=\"dropdown-item viewActivity\" href=\"user_activity.do?email_id=${board.email_id}\" >활동 내역 보기</a></li>\r\n"
	    									+ "	</ul>");
	    						out.print("</div>");
	    						out.print("<div id='replydate' class='col comment-date'>" + reply.getW_date() + "</div>");
	    					out.print("</div>");
	    					out.print("<h6 class='card-text' id='replycontent'>" + reply.getContent() + "</h6>");
	    					out.print("<h6></h6>");
	    					out.print("<div align='right' class='actions'>");
	    						out.print("<input id='co_idx' value='" + reply.getCo_idx() + "' type='hidden' />");
	    						out.print("<input id='idx' value='" + reply.getIdx() + "' type='hidden' />");
	    						out.print("<input id='depth' value='" + reply.getDepth() + "' type='hidden' />");
	    						out.print("<input id='step' value='" + reply.getStep() + "' type='hidden' />");
	    						out.print("<button type='button' id='replyreplywrite' class='btn btn-outline-secondary btn-sm rounded-pill'>대댓글</button>");
	    						out.print("<c:if test='${member.email_id eq comments.email_id }'>");
	    							out.print("<button type='button' id='replydel' class='btn btn-outline-secondary btn-sm rounded-pill'>삭제</button>");
	    						out.print("</c:if>");
	    					out.print("</div>");
	    				out.print("</div>");
	    			out.print("</div>");
    			}else {
	    			out.print("<div class='Recomment-box'>");
	    				out.print("<div class='row'>");
	    					out.print("<div class='col'>");
	    						out.print("<h5 class='card-title'>");
	    							out.print("<i class='bi bi-arrow-return-right'></i>");
	    							out.print("<img src='image/rank_icon/1.gif' alt='Profile' class='rounded-circle'>"+reply.getNick());
	    						out.print("</h5>");	
	    					out.print("</div>");
	    					out.print("<div class='col comment-date'>"+reply.getW_date()+"</div>");
	    				out.print("</div>");
	    				out.print("<h6 class='Recomment-text'>"+reply.getContent()+"</h6>");
	    				out.print("<h6></h6>");
	    				out.print("<div align='right' class='actions'>");
	    					if(user.getEmail_id().equals(reply.getEmail_id())) {
	    						out.print("<input id='co_idx2' value='" + reply.getCo_idx() + "' type='hidden' />");
	    						out.print("<button type='button' id='replydel2' class='btn btn-outline-secondary btn-sm rounded-pill'>삭제</button>");
	    					}	
	    				out.print("</div>");
	    			out.print("</div>");
    			}
    		}
    	
    		
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
