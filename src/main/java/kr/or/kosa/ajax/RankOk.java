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
import kr.or.kosa.dao.Rank_Dao;
import kr.or.kosa.dto.Rank;
import kr.or.kosa.dto.User;


@WebServlet("/ReplyReplyOk")
public class RankOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RankOk() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
		
		try {
			int result = 0;
			String msg = "";
			
		int rank =Integer.parseInt(request.getParameter("rank"));
		String r_name =request.getParameter("r_name");
		int r_point =Integer.parseInt(request.getParameter("r_point"));
			
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
		    
		    if(user == null) {
		    	msg = "로그인 해주세요";
		    }else {
			  Rank rankdto = new Rank();
			  rankdto.setRank(rank);
			  rankdto.setR_name(r_name);
			  rankdto.setR_point(r_point);
			    
			  Rank_Dao rdao = new Rank_Dao();
			  result = rdao.insertRank(rankdto);
			    
				if(result > 0){
			    	msg ="등급추가 성공";
			    }else{
			    	msg="등급추가 실패";
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
