package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.Calender_Dao;
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dto.Comments;
import net.sf.json.JSONArray;

@WebServlet("/LoadCalenderComments")
public class LoadCalenderComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoadCalenderComments() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			CommentsDao dao = new CommentsDao();
			List<Comments> colist = dao.getCommentListByIdx(idx);
			
			JSONArray jsonlist = JSONArray.fromObject(colist);
			
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
