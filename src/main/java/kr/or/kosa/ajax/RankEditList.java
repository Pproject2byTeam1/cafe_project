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
import net.sf.json.JSONObject;


@WebServlet("/RankEditList")
public class RankEditList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RankEditList() {
        super();
    }

private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	HttpSession session = request.getSession();
		User user = (User) session.getAttribute("member");

		String msg = "";
		
    	try {
    		
    		Rank_Dao dao = new Rank_Dao();
    		String url = "";
			//로그인 안한경우
			if (user == null && !(user.getIsAdmin().equals("M"))) {
	            msg = "관리자로 로그인하세요";   
			}
			
			List<Rank> list = dao.getRankExecptionManager();
			int size = list.size();
			int max = 0;
			for(Rank rank : list) {
				if(rank.getR_point() > max) {
					max = rank.getR_point();
				}
			}

			out.print("<thead class='thead-light'>");
				out.print("<tr>");
					out.print("<th>");
					out.print("</th>");
					out.print("<th>");
					out.print("</th>");
					out.print("<th>");
					out.print("</th>");
					out.print("<th>");
					out.print("</th>");
				out.print("</tr>");
				out.print("<input type='hidden' class='max' value='" + max + "'>");
				out.print("<input type='hidden' class='size' value='" + size + "'>");
				out.print("<tr>");
					out.print("<th>");
					out.print("등급레벨");
					out.print("</th>");
					out.print("<th>");
					out.print("등급 이름");
					out.print("</th>");
					out.print("<th>");
					out.print("등급 포인트 기준");
					out.print("</th>");
					out.print("<th>");
					out.print("수정/저장");
					out.print("</th>");

				out.print("</tr>");
			out.print("</thead>");
			out.print("<tbody>");
				for(Rank rank : list) {
					
				if(rank.getRank()>0) {
					out.print("<tr class='" + rank.getRank() + "'>");
					
						out.print("<td class='" + rank.getRank() + "'>" + rank.getRank() + ""); 
						out.print("<input type='hidden' class='r_rank' value='" + rank.getRank() + "'>");
						out.print("</td>");
						out.print("<td>");
							out.print(rank.getR_name());
							out.print("<input type='hidden' class='r_name' value='" + rank.getR_name() + "'>");
						out.print("</td>");
							
					out.print("<td>");
					
						if(rank.getRank() == '1') {
							out.print("0 점 이상");
						}else {
							out.print(rank.getR_point() + " 점 이상");
							out.print("<input type='hidden' class='r_point' value='" + (rank.getR_point()+1) + "'>");
						}
					out.print("</td>");
					out.print("<td>");
					out.print("<input type='button' class='editRank btn btn btn-secondary' value='수정하기' style='float: right'>");
					out.print("</td>");
					
				out.print("</tr>");
					}
					
				}
			out.print("</tbody>");

					
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
