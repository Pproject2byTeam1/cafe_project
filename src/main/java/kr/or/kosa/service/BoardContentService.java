package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dto.Board_Info;

public class BoardContentService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String url="";
		try {
			int b_code = Integer.parseInt((String) request.getParameter("b_code"));
			String idx= (String) request.getParameter("idx");
			String w_date = (String) request.getParameter("w_date");
			//System.out.println(b_code + ", " + idx);
			
			Board_Info_Dao binfodao = new Board_Info_Dao();
			Board_Info board_info = binfodao.getBoardInfo(b_code);
			String b_type = board_info.getB_type();
			if(b_type != null) {
				if(b_type.equals("b1")) {//자유게시판
					url="regular_post.do?idx="+idx+"&b_code"+ b_code;
				}else if(b_type.equals("b2")) {//출석체크
					url="checkBoard.do?inputdate="+w_date+"&b_code"+ b_code;
				}else if(b_type.equals("b3")) {//전체일정
					url="calendar_list.do?idx="+idx+"&b_code"+ b_code;
				}else if(b_type.equals("b4")) {//사진공유
					url="img_board_read.do?idx="+idx+"&b_code"+ b_code;
				}else if(b_type.equals("b5")) {//유료 거래
					url="marketboard_read.do?idx="+idx+"&b_code"+ b_code+"&cp=1";
				}else if(b_type.equals("b6")) {//자료 공유
					url="databoard_read.do?idx="+idx+"&b_code"+ b_code+"&cp=1&ps=5";
				}
			}else {
				url="cafemain.do";
			}
			
			System.out.println("url: "+url);
			
		}catch (Exception e) {
			e.getMessage();
		}
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}

}
