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
			
			out.print("<table class=‘table text-center’ id=‘ranklist’>");
			out.print("<thead class=‘thead-light’>");
				out.print("<tr>");
					out.print("<th>");
					out.print("</th>");
					out.print("<th>");
					out.print("</th>");
					out.print("<th>");
					out.print("</th>");
					out.print("<th>");
					out.print("<button type=‘button’ class=‘btn float-right btn-success’");
					out.print("id=‘saveRank’ value=‘save’ style=‘float: right’>저장하기(</button>");	
					out.print("</th>");
				out.print("</tr>");

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
					out.print("수정/삭제");
					out.print("</th>");

				out.print("</tr>");
			out.print("</thead>");
			out.print("<tbody>");
			
				out.print("<c:forEach var=‘rank’ items=‘${list}’ varStatus=‘status’>");
				
					out.print("<c:if test=‘${rank.rank>;0}’>");
						out.print("<tr class=‘${rank.rank}’>");

							out.print("<td class=‘${rank.rank}’>“);${rank.rank}");
							out.print("</td>");
							out.print("<td>");
							out.print("<input type=‘text’ class=‘form-control’ id=‘name’ placeholder=‘${rank.r_name}’ value=‘${rank.r_name}’ readonly>");
							out.print("</td>");
								out.print("<input type=‘hidden’ class=‘isReg’ value=‘false’>");
							out.print("<td>");
								out.print("<c:choose>");
									out.print("<c:when test=‘${rank.rank == 1}’>");
									out.print("0 점 이상");
									      out.print("</c:when>");

									out.print("<c:otherwise>");
										out.print("<input type=‘text’ class=‘form-control’ id=‘point’");
											out.print("placeholder=‘${rank.r_point}’ readonly>");
											out.print("<input type=‘hidden’ class=‘isReg’ value=‘false’>");
									out.print("</c:otherwise>");
								out.print("</c:choose>");
							out.print("</td>");
							out.print("<td>");				
							out.print("<input type=‘button’ class=‘editRank btn btn btn-secondary’ value=‘수정하기’ style=‘float: right’>");
							out.print("</td>");

						out.print("</tr>");
					out.print("</c:if>");
				out.print("</c:forEach>");
						
			out.print("</tbody>");
		out.print("</table>");

			
			JSONObject json = new JSONObject();
			json.put("list", list);
			json.put("size", size);
			json.put("max", max);
			
			out.print(json);
			
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
