package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dto.Board;
import net.sf.json.JSONArray;

@WebServlet("/getBoardList")
public class getBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public getBoardList() {
        super();
    }
    
private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			String date = request.getParameter("date");
    		
			//날짜계산 준비
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date nowdate =  new Date();
			String nowday = format.format(nowdate);
			
			Board_Dao dao = new Board_Dao(); 
			List<Board> board = dao.getBoardList(b_code,date,nowday);
			
			JSONArray jsonlist = JSONArray.fromObject(board);
		    //request.setAttribute("board", board);
			out.print(jsonlist);
			System.out.println(jsonlist);
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
