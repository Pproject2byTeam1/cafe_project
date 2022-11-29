package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;

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
			
			if(b_code == 1) {//자유게시판
				url="regular_post.do?idx="+idx+"&b_code"+ b_code;
			}else if(b_code == 2) {//출석체크
				url="checkBoard.do?inputdate="+w_date+"&b_code"+ b_code;
			}else if(b_code == 3) {//전체일정
				url="calendar_list.do?idx="+idx+"&b_code"+ b_code;
			}else if(b_code == 4) {//사진공유
				url="img_board_read.do?idx="+idx+"&b_code"+ b_code;
			}else if(b_code == 5) {//유료 거래
				url="marketboard_read.do?idx="+idx+"&b_code"+ b_code;
			}else if(b_code == 6) {//자료 공유
				url="data_post.do?idx="+idx+"&b_code"+ b_code;
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
