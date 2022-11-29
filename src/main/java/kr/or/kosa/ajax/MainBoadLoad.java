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
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.User;
import kr.or.kosa.utils.BoardFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/MainBoadLoad")
public class MainBoadLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MainBoadLoad() {
        super();
    }

private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
		    
		  //게시판 종류 정보 가져오기
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
			JSONObject json1 = new JSONObject();
			JSONObject json2 = new JSONObject();
			JSONObject json3 = new JSONObject();
			JSONObject json4 = new JSONObject();
			
			for(Board_Info info : infolist) {
				String b_type = info.getB_type();
				int b_code = info.getB_code();
				
				if(info.getMain_idx() == 1) {
					List<? extends Board> board = BoardFactory.createBoard(b_type, b_code); //b_type에 따라 다른 게시판 출력 가져오는 심플 팩토리 패턴
					json1.put("main1", board);
				} else if(info.getMain_idx() == 2) {
					List<? extends Board> board = BoardFactory.createBoard(b_type, b_code); //b_type에 따라 다른 게시판 출력 가져오는 심플 팩토리 패턴
					json2.put("main2", board);
				} else if(info.getMain_idx() == 3) {
					List<? extends Board> board = BoardFactory.createBoard(b_type, b_code); //b_type에 따라 다른 게시판 출력 가져오는 심플 팩토리 패턴
					json3.put("main3", board);
				} else if(info.getMain_idx() == 4) {
					List<? extends Board> board = BoardFactory.createBoard(b_type, b_code); //b_type에 따라 다른 게시판 출력 가져오는 심플 팩토리 패턴
					json4.put("main4", board);
				}
			}
			
			JSONArray jsonlist = new JSONArray();
			jsonlist.add(json1);
			jsonlist.add(json2);
			jsonlist.add(json3);
			jsonlist.add(json4);
			
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
