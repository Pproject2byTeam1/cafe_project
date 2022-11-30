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
import kr.or.kosa.dao.Rank_Dao;
import kr.or.kosa.dto.Comments;
import kr.or.kosa.dto.User;


@WebServlet("/RankDelete")
public class RankDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RankDelete() {
        super();
    }

private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {
    		String msg = "";
			int rank= Integer.parseInt(request.getParameter("rank"));
			String r_name= request.getParameter("r_name");
			int r_point= Integer.parseInt(request.getParameter("r_point"));
    		
			Rank_Dao dao = new Rank_Dao();
			int highrank = dao.highRank();
			
			//추가 제한 로직
			if(rank <= highrank) {
				msg = "삭제하려는 등급의 순번(" + highrank + " 순위)보다 기존 등급의 순번(" + rank + ")이 높습니다. 가장 높은 등급을 먼저 지우세요.";
			}else {
				int result = dao.deleteRank(rank);
				
				if(result > 0) {
					msg = highrank + " 번째 " + r_name + "등급이 삭제되었습니다";
				} else {
					msg = "등급이 제거되지 않았습니다.";
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
