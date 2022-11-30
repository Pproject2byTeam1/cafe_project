package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.ChartDao;
import kr.or.kosa.dto.AttendanceBoad;
import kr.or.kosa.dto.Chart;
import net.sf.json.JSONArray;

@WebServlet("/ChartList")
public class ChartList extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ChartList() {
        super();
 
    }
    
    protected void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	String chart = request.getParameter("chart");
    	
    	JSONArray jsonlist = null;
    	
    	try {  		
    		
    		if (chart.equals("rankpointselect")) {
    			
				int number = Integer.parseInt(request.getParameter("number"));
				String startDate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");
				
				ChartDao cdao = new ChartDao();
				List <Chart> rankpoint = cdao.getTopRankpoint(startDate, endDate, number);
				
				jsonlist = JSONArray.fromObject(rankpoint);
				
			}
    		
    		if (chart.equals("allboardtopview")) {
    			int number = Integer.parseInt(request.getParameter("number"));

        		ChartDao cdao = new ChartDao();
        		List<AttendanceBoad> topview = cdao.getTopViews(number);
    			
    			jsonlist = JSONArray.fromObject(topview);
    			
			}
    		
    		
    			//JSON으로 보내기
    			out.print(jsonlist);
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

}
