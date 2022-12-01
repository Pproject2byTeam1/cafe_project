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

import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.User;

@WebServlet("/BoardInfoLoad")
public class BoardInfoLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardInfoLoad() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
			
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
			for(Board_Info info : infolist) {
				out.print("<div class='con'>");
					out.print("<div class='maincard row m-2 draggable' draggable='true'>");
						out.print("<div class='col-md-10 pt-1'>");
							out.print("<input id='b_code' value='" + info.getB_code() + "' type='hidden' />");
							out.print("<br> <h5>" + info.getB_name() + "</h5>");
						out.print("</div>");
						out.print("<div align='right' class='col-md-2 pt-2'>");
							if(!info.getB_type().equals("b2")) {
								out.print("<h4><i class='bi bi-trash3 trash'></i></h4>");
								out.print("<h4><i class='bi bi-arrow-down-square moreinfo'></i></h4>");
							}
						out.print("</div>");
					out.print("</div>");
				out.print("</div>");
			}
			
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
