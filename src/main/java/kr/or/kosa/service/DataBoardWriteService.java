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
import kr.or.kosa.dao.Board_Rank_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.Board_Rank;
import kr.or.kosa.dto.CafeBanner;
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
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);//top
			
			//사이드바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
			//글쓰기
			MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8", new DefaultFileRenamePolicy());
			
			//내용 가져오기
			int b_code = Integer.parseInt(multi.getParameter("b_code"));
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			
			//유저정보 가져오기
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
			
		    String msg = "";
			String url = "";
		    
			if(user == null) {
		    	msg = "로그인이 필요한 기능입니다.";
				url = "/WebCafe_Project/login_view.do";
		    }else {
		    	Enumeration filenames = multi.getFileNames();
				
		    	//파일 중복 정의
		    	String file1 = (String) filenames.nextElement(); 
				String savename = multi.getFilesystemName(file1); //서버저장이름(중복시에_1)
				String oriname = multi.getOriginalFileName(file1); //원본저장이름
				
				long length = multi.getFile(file1).length(); // 파일 객체 가져오기
				int size1 = Long.valueOf(length).intValue(); // 파일 크기 타입 long -> int
				
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
				
				//파일 이름 판단 및 databoard DAO 넣기
				data.setOri_name(oriname);
				data.setSave_name(savename);
				data.setVolume(size1);
				
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
