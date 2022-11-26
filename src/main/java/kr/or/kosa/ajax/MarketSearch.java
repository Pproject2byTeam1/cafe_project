package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dto.MarketBoard;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet("/MarketSearch")
public class MarketSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MarketSearch() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {
			
    		  		
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			String search = request.getParameter("search");
			
			MarketBoardDao market_dao = new MarketBoardDao(); 
			
			//찜 개수
			
			
			//판매중 개수
			
			//상세보기 >> 다시  LIST 넘어올때  >> 현재 페이지 설정
			String ps = request.getParameter("ps");
			String cp = request.getParameter("cp");
	
			if(ps == null || ps.trim().equals("")) {
				ps = "8"; //List 페이지 처음 호출 경우 -> 8개씩 
			}
			
			if(cp == null || cp.trim().equals("")) {
				cp = "1"; //default 값 설정 -> 1번째 페이지 보겠다 
			}
			
			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int pagecount = 0;
			int soldcount = market_dao.countSoldF(b_code);
			int totalboardcount = 0;
			//게시물 총 건수
			if(search.equals("판매중")) {
				totalboardcount = market_dao.countSoldF(b_code);
			}else {
				totalboardcount = market_dao.countMarket(b_code);
			}
			
			if(totalboardcount % pagesize == 0) {
				pagecount = totalboardcount / pagesize;
			}else {
				pagecount = (totalboardcount / pagesize) + 1; 
			}
			
			
			
			List<MarketBoard> list = market_dao.searchMarket(b_code, cpage, pagesize, search);
			
			JSONArray jsonlist = JSONArray.fromObject(list);
			JSONObject page = new JSONObject();
			page.put("page", pagesize);
			page.put("cpage",cpage);
			page.put("pagecount",pagecount);
			page.put("totalboardcount",totalboardcount);
			page.put("search",search);
			page.put("soldcount", soldcount);
			
			jsonlist.add(page);

			out.print(jsonlist);

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
