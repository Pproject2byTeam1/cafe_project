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

public class ReplyDataWriteService implements Action {

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
			
			//답글 쓰기
			
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
		    
		    String msg = "";
			String url = "";
			
			if(user == null) {
				msg = "실패";
				url = "/WebCafe_Project/login_view.do";
			}else {
				
				//글쓰기
				MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8",
						new DefaultFileRenamePolicy());
				
				int b_code = Integer.parseInt(multi.getParameter("b_code"));
				int replyIdx = Integer.parseInt(multi.getParameter("reply_idx"));
				int depth = Integer.parseInt(multi.getParameter("depth"));
				int step = Integer.parseInt(multi.getParameter("step"));
				String title = multi.getParameter("title");
				String content = multi.getParameter("content");
				
				Enumeration filenames = multi.getFileNames();
				
				String file1 = (String) filenames.nextElement();
				String filename1 = multi.getFilesystemName(file1);
				String oriname = multi.getOriginalFileName(file1);
				long length = multi.getFile(file1).length();
				int size1 = Long.valueOf(length).intValue();
				
				DataBoard board = new DataBoard();
				board.setRefer(replyIdx);
				board.setB_code(b_code);
				board.setTitle(title);
				board.setNick(user.getNick());
				board.setContent(content);
				board.setEmail_id(user.getEmail_id());
				board.setDepth(depth);
				board.setStep(step);
				board.setOri_name(oriname);
				board.setSave_name(filename1);
				board.setVolume(size1);
				
				DataBoardDao datadao = new DataBoardDao();
				result = datadao.insertData_BoardReply(board);
			
				if (result > 0) {
					msg = "성공";
					url = "/WebCafe_Project/databoard_list.do?b_code=" + b_code;
				} else {
					msg = "실패";
					url = "/WebCafe_Project/databoard_list.do?b_code=" + b_code;
				}
				
				request.setAttribute("b_code", b_code);
			}
			
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			request.setAttribute("infolist", infolist);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/redirect.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
