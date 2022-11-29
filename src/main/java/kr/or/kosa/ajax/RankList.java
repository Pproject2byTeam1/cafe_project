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

import kr.or.kosa.dao.Rank_Dao;
import kr.or.kosa.dto.Rank;
import kr.or.kosa.dto.User;


@WebServlet("/RankList")
public class RankList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RankList() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
		
		
		try {
	    	Rank_Dao dao = new Rank_Dao();			
			List<Rank> ranklist = dao.getRankListAll();
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");		
    		for(Rank rank :ranklist) {
    			if(rank.getRank()>1) {
    				 out.print("<tr>");
						/*
						 * out.print("<td>"); out.print(rank.getRank()); out.print("</td>");
						 * out.print("<td>"); <input type="text" class="form-control" id="기입하세용"
						 * placeholder="${rank.r_name}" value="${rank.r_name}" id="r_rank">
						 * 
						 * </td> <td> <input type="text" class="form-control" id="기입하세용"
						 * placeholder="${rank.r_point}점 이상" id="r_point">
						 * 
						 * </td> <td> <input type="button" class="btn btn-danger"
						 * onclick="alert('삭제되었습니다!')" value="삭제"> </td>
						 * 
						 * </tr>
						 */
    				
    				
    				
    			}
    			
    			
    			
    			
    			
    			
    		}
			
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
