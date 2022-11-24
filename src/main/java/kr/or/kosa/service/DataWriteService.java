package kr.or.kosa.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dto.DataBoard;

public class DataWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		String msg = "";
		String url = "";
		int result = 0;
		
		
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		int size = 1024 * 1024 * 10;
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8",
					new DefaultFileRenamePolicy());
			
		int b_code = Integer.parseInt(multi.getParameter("b_code"));	
		int b_idx = Integer.parseInt(multi.getParameter("b_idx"));
			
			String ori_name = multi.getParameter("ori_name");
			String save_name = multi.getParameter("save_name");
			int volume=Integer.parseInt(multi.getParameter("volume"));
		
		
			
			Enumeration filenames = multi.getFileNames();
		
			String file1 = (String) filenames.nextElement();
			String filename1 = multi.getFilesystemName(file1);
		
		
				DataBoardDao dao = new DataBoardDao();
			
			DataBoard board = new DataBoard();
			board.setB_idx(b_idx);
			board.setOri_name(ori_name);
			board.setVolume(volume);
			board.setDepth(volume);
			if (filename1 == null) {
				board.setSave_name("");
			} else {
				board.setSave_name(filename1);
			}

			result = dao.insertData_Board(board);

			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (result > 0) {
			msg = "insert success";
			url = "board_list.do";
		} else {
			msg = "insert fail";
			url = "board_write.do";
		}

		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url);
		forward.setRedirect(false);
		forward.setPath("/data_content.jsp");

		return forward;
	}


}
