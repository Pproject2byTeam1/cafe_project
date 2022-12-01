package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dao.Img_Board_Dao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.Comments;
import kr.or.kosa.dto.Img_Board;
import kr.or.kosa.dto.User;
import kr.or.kosa.utils.ViewCountPrevent;

public class Img_Board_Read_Service implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		try {
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);//top
			
			//사이드 바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			
			//조회수 증가
			ViewCountPrevent prevent = new ViewCountPrevent();
			prevent.viewCountPrevent(idx, request, response);
			
			Img_Board_Dao dao = new Img_Board_Dao();
			Img_Board imgboard = dao.getImg_BoardByIdx(idx);
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("member");
			
	        if(user != null) {
	        	Yes_Dao yesdao = new Yes_Dao();
				String yes = yesdao.getYesEmailByIdxEmail(idx , user.getEmail_id());
				
				if(yes != null) {
					request.setAttribute("yes", yes);
				}else {
					request.setAttribute("yes", "no");
				}
	        }
	        
	        //댓글 조회
	        CommentsDao codao = new CommentsDao();
	        List<Comments> comments = codao.getCommentListByIdx(idx);
	        
	        request.setAttribute("comments", comments);
			request.setAttribute("imgboard", imgboard);
			request.setAttribute("b_code", b_code);
			request.setAttribute("infolist", infolist);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/imgboard_read.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return forward;
	}

}
