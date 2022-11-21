package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Img_Board_Dao;
import kr.or.kosa.dto.Img_Board;
import net.sf.json.JSONArray;


@WebServlet("/ImgSearch")
public class ImgSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ImgSearch() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
    		int b_code = Integer.parseInt(request.getParameter("b_code"));
    		String search = request.getParameter("search");
			
			Img_Board_Dao dao = new Img_Board_Dao(); 
			List<Img_Board> list = dao.searchImg(b_code, search);
			
			JSONArray jsonlist = JSONArray.fromObject(list);
			
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
