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
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.DataBoard;
import kr.or.kosa.dto.User;

public class DataBoardWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		int size = 1024 * 1024 * 10;
		
		int result = 0;

		try {
			//사이드바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
			//글쓰기
			MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8", new DefaultFileRenamePolicy());
			
			//내용 가져오기
			int b_code = Integer.parseInt(multi.getParameter("b_code"));
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
		
			//데이터게시판 추가 가져오기 (oriname/savenamevolume)
			String m_mode = multi.getParameter("m_mode");
			String cate = multi.getParameter("cate");
			int price = Integer.parseInt(multi.getParameter("price"));
			String ori_name = multi.getParameter("ori_name");
			int volume = Integer.parseInt(multi.getParameter("volume"));
			
			//유저정보 가져오기
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
			
		    String msg = "";
			String url = "";
		    
			
			
			if(user == null) {
		    	msg = "실패";
				url = "/WebCafe_Project/login_view.do";
		    }else {
		    	Enumeration filenames = multi.getFileNames();
				
		    	//파일 중복 정의
				String file1 = (String) filenames.nextElement();
				String filename1 = multi.getFilesystemName(file1);
				
				//게시판 DAO 기능 불러오기
				DataBoardDao datadao = new DataBoardDao();
				//게시판 형태 가져오기
				DataBoard data = new DataBoard();
				
				//게시판 write DAO에 넣어줄 값들.
				data.setB_code(b_code);
				data.setTitle(title);
				data.setContent(content);
				data.setEmail_id(user.getEmail_id());
				data.setNick(user.getNick());
				
				//자료 게시판 추가 사항.
				data.getOri_name();
				
				//파일 이름 판단 및 img_name DAO 넣기
				if(filename1 == null) {
					data.setOri_name(filename1);
				}else {
					data.setOri_name(filename1);
				}
				
				//필요 요소 넣고 작성 메서드 콜
				result = datadao.writeData(data);
				
				if (result > 0) {
					msg = "성공";
					url = "/WebCafe_Project/databoard_list.do?b_code=" + b_code;
				} else {
					msg = "실패";
					url = "/WebCafe_Project/databoard_write.do?b_code=" + b_code;
				}
		    }
			
			//리스트 페이지로 돌아갈때 가져갈 내용들
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			request.setAttribute("infolist", infolist);
			request.setAttribute("b_code", b_code);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/redirect.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;

	}

}
