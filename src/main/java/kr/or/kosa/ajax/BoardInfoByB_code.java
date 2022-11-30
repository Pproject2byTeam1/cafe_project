package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.User;
import net.sf.json.JSONObject;


@WebServlet("/BoardInfoByB_code")
public class BoardInfoByB_code extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardInfoByB_code() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
		    
		    String msg = "";
		    JSONObject json = new JSONObject();
		    
		    if(user == null) {
		    	msg = "로그인이 필요한 기능입니다.";
		    }else {
		    	
		    	if(!user.getIsAdmin().equals("M")) {
		    		
		    		msg = "관리자 권한이 필요한 기능입니다.";
		    		
		    	}else {
		    		
		    		int b_code = Integer.parseInt(request.getParameter("b_code"));
			    	
		    		Board_Info_Dao infodao = new Board_Info_Dao();
		    		Board_Info info = infodao.getBoardInfo(b_code);
		  
		    		json.put("b_code", info.getB_code());
		    		json.put("b_name", info.getB_name());
		    		json.put("side_idx", info.getSide_idx());
		    		json.put("main_idx", info.getMain_idx());
		    		json.put("b_type", info.getB_type());
		    		json.put("form", info.getForm());
		    		
		    	}
		    }
			
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
