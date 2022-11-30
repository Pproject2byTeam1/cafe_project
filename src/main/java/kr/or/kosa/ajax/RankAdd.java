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


@WebServlet("/RankAdd")
public class RankAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RankAdd() {
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
			int highpoint = dao.highPoint();
			//추가 제한 로직
			if(rank <= highrank) {
				msg = "새로 추가하려는 등급의 순번(" + highrank + " 순위)이 기존 등급의 순번(" + rank + ")보다 낮습니다.";
			}else if(r_point <= highpoint) {
				msg = "새로 추가하려는 등급의 포인트(" + highpoint + " 점)가 기존 등급의 포인트(" + rank + " 점)보다 낮습니다.";
			}else {
				int result = dao.insertRank(rank, r_name, r_point);
				
				if(result > 0) {
					msg = r_name + "(" + rank + "순위 / " + r_point + " 점 이상) 등급이 추가 되었습니다."; 
				} else {
					msg = "등급 등록이 실패하였습니다.";
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
