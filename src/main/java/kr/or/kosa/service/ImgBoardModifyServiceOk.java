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

public class ImgBoardModifyServiceOk implements Action {

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
			int idx = Integer.parseInt(multi.getParameter("idx"));
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String img_name_ori = multi.getParameter("img_name");
			
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
		    
		    String msg = "";
			String url = "";
			
			if(user == null) {
				
				msg = "로그인이 필요한 기능입니다.";
				url = "/WebCafe_Project/login_view.do";
				
			}else {
				Enumeration filenames = multi.getFileNames();
				
				String file1 = (String) filenames.nextElement();
				String filename1 = "";
				String type = "";
				
				filename1 = multi.getFilesystemName(file1);
				type = multi.getContentType(file1);
				
				if(!type.equals("image/jpeg")) {
					msg = "올바른 이미지를 선택하세요";
					url = "/WebCafe_Project/imgboardModifyView.do?b_code=" + b_code;
				} else {
					Img_Board_Dao imgdao = new Img_Board_Dao();
					
					Img_Board img = new Img_Board();
					
					img.setIdx(idx);
					img.setTitle(title);
					img.setContent(content);
					img.setB_code(b_code);
					img.setEmail_id(user.getEmail_id());
					img.setNick(user.getNick());
					img.setImg_name(filename1);
				
					result = imgdao.updateImg_Board(img);
					
					if (result > 0) {
						msg = "성공";
						url = "/WebCafe_Project/img_board_list.do?b_code=" + b_code + "&idx=" + idx;
					} else {
						msg = "실패";
						url = "/WebCafe_Project/img_board_read.do?b_code=" + b_code + "&idx=" + idx;
					}
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
			e.getMessage();
		}
		
		return forward;
	}

}
