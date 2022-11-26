package kr.or.kosa.service;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dao.Regular_Board_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.DataBoard;
import kr.or.kosa.dto.User;

public class DataBoardEditOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		int size = 1024 * 1024 * 10;
		
		try {
			
			Board_Info_Dao infodao = new Board_Info_Dao();
 			List<Board_Info> infolist = infodao.getSideBoardList();
			
 			HttpSession session = request.getSession();
 	        User user = (User) session.getAttribute("member");
 			
 	       String url = "";
 	
 			
 			// 로그인 안할경우 로그인 페이지로
			if (user == null) {

	            String board_msg = "세션이 만료되었습니다.";
	            String board_url = "/WebCafe_Project/login_view.do";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	           
	         }else {
	        	 
	        	 MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8",
							new DefaultFileRenamePolicy());
	        	 
	        	
	  			int b_code = Integer.parseInt(multi.getParameter("b_code"));
	  			int idx = Integer.parseInt(multi.getParameter("idx")); 
	  			
	  			String title = multi.getParameter("title");
	 			String content = multi.getParameter("content");
	 			
	 			Enumeration filenames = multi.getFileNames();
				
				String file1 = (String) filenames.nextElement();
				String filename1 = multi.getFilesystemName(file1);
				String oriname = multi.getOriginalFileName(file1);
				long length = multi.getFile(file1).length();
				int size1 = Long.valueOf(length).intValue();
				
				
	 			DataBoard databoard = new DataBoard();
	 			DataBoardDao dbao = new DataBoardDao();
	 		
	 			databoard = dbao.getData_BoardByIdx(idx);
	 			
	 			databoard.setTitle(title);
	 			databoard.setContent(content);
	 			
	 			databoard.setOri_name(oriname);
	 			databoard.setSave_name(filename1);
	 			databoard.setVolume(size1);
	 			
	 			int result = dbao.updateDataBoardTitle(databoard);
	 			
	 			
	 			
				
				if (result > 0) {

					String board_msg = "완료되었습니다.";
		            String board_url = "/WebCafe_Project/databoard_read.do?b_code="+ b_code +"&idx="+ idx;
		              
		            request.setAttribute("board_msg", board_msg);
		            request.setAttribute("board_url", board_url);
		        	
					url="/WEB-INF/view/redirect.jsp";
					
				}
	 			
	 			
	         }
			
 			request.setAttribute("infolist", infolist);
			
			forward = new ActionForward();
 		  	forward.setRedirect(false);
 		  	forward.setPath(url);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
