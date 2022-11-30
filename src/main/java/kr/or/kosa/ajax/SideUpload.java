package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dto.Board_Info;

@WebServlet("/SideUpload")
public class SideUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SideUpload() {
        super();
    }

   private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
			
    		int max = Integer.parseInt(request.getParameter("max"));
    		
    		int[] arr = new int[max];
    		
    		for(int i=0; i<max; i++) {
    			String index = String.valueOf(i);
    			arr[i] = Integer.parseInt(request.getParameter(index));
    		}
    		
    		Board_Info_Dao dao = new Board_Info_Dao();
    		int row = dao.updateBoardSideIndex(arr);
    		
    		String msg = "";
    		
    		if(row > 0) {
    			msg = "정보를 업데이트하였습니다.";
    		}else {
    			msg = "정보 업데이트를 실패하였습니다.";
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
