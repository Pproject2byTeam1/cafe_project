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
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.Img_Board_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.Img_Board;
import kr.or.kosa.dto.User;

public class ImgBoardWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		int size = 1024 * 1024 * 10;
		
		int result = 0;
		
		try {
			
			//사이드 바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
			//글쓰기
			MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8",
					new DefaultFileRenamePolicy());
			
			int b_code = Integer.parseInt(multi.getParameter("b_code"));
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
		    
		    String msg = "";
			String url = "";
		    
		    if(user == null) {
		    	
		    	msg = "실패";
				url = "/WebCafe_Project/login_view.do";
		    	
		    }else {
		    	Enumeration filenames = multi.getFileNames();
				
				String file1 = (String) filenames.nextElement();
				String filename1 = multi.getFilesystemName(file1);
				
				Img_Board_Dao imgdao = new Img_Board_Dao();
				
				Img_Board img = new Img_Board();
				
				img.setTitle(title);
				img.setContent(content);
				img.setB_code(b_code);
				img.setEmail_id(user.getEmail_id());
				img.setNick(user.getNick());
				
				if(filename1 == null) {
					img.setImg_name("");
				}else {
					img.setImg_name(filename1);
				}
				
				result = imgdao.insertImg_Board(img);
				
				if (result > 0) {
					msg = "성공";
					url = "/WebCafe_Project/img_board_list.do?b_code=" + b_code;
				} else {
					msg = "실패";
					url = "/WebCafe_Project/img_board_list.do?b_code=" + b_code;
				}
		    }
			
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			request.setAttribute("infolist", infolist);
			request.setAttribute("b_code", b_code);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/redirect.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
