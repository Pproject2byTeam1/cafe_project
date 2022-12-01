package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.ChartDao;
import kr.or.kosa.dto.Chart;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
    	
    	JSONArray jsonlist = new JSONArray();
    	JSONArray realjsonlist = new JSONArray();
    	
    	try {  		
    		
    		if (chart.equals("monthBoardWrite")) {
    			
    			int month = Integer.parseInt(request.getParameter("MBWmonth"));
    			
				ChartDao cdao = new ChartDao();
				List <Chart> monthBoardWrite = cdao.getMonth(month);
				
				realjsonlist = JSONArray.fromObject(monthBoardWrite);
				
				
			}
    		
    		if (chart.equals("monthBoardView")) {
    			
    			int month = Integer.parseInt(request.getParameter("MBVmonth"));
    			
				ChartDao cdao = new ChartDao();
				List <Chart> monthBoardView = cdao.getMonthView(month);
				
				realjsonlist = JSONArray.fromObject(monthBoardView);
				
				
			}
    		
    		
    		
    		if (chart.equals("boardCount")) {
    			
				ChartDao cdao = new ChartDao();
				List <Chart> BCboard = cdao.getBoardCount();
				
				realjsonlist = JSONArray.fromObject(BCboard);
				
			}
    		
    		if (chart.equals("boardUtilizationRate")) {
    			String startDate = request.getParameter("BURstartDate");
				String endDate = request.getParameter("BURendDate");
				
				ChartDao cdao = new ChartDao();
				List <Chart> BURboard = cdao.getBoardUtilizationRate(startDate, endDate);
				
				realjsonlist = JSONArray.fromObject(BURboard);
			}
    		
    		if (chart.equals("rankpointselect")) {
    			
				int number = Integer.parseInt(request.getParameter("number"));
				String startDate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");
				
				ChartDao cdao = new ChartDao();
				List <Chart> rankpoint = cdao.getTopRankpoint(startDate, endDate, number);
				
				realjsonlist = JSONArray.fromObject(rankpoint);
				
			}
    		
    		if (chart.equals("allboardtopview")) {
    			int number = Integer.parseInt(request.getParameter("number"));

        		ChartDao cdao = new ChartDao();
        		List<Chart> topview = cdao.getTopViews(number);
    			
        		for(Chart parkchart : topview) {
        			
        			JSONObject json = new JSONObject();
        			json.put("name", parkchart.getB_name());
        			
        			List<Integer> datalist = new ArrayList<Integer>();
        			datalist.add(parkchart.getHits());
        			
        			json.put("data", datalist);
        			
        			
        			
        			jsonlist.add(json);
        		}
        		
        		JSONArray json1 = JSONArray.fromObject(topview);
        	
        		realjsonlist.add(json1);
        		realjsonlist.add(jsonlist);
			}
    		
    			//JSON으로 보내기
    			out.print(realjsonlist);
    			
    			
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
