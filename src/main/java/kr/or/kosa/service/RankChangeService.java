package kr.or.kosa.service;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Rank_Dao;
import kr.or.kosa.dto.Rank;
import kr.or.kosa.dto.User;

public class RankChangeService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("member");
		String userId = (String) session.getAttribute("userid");
		
		try {
			Rank_Dao dao = new Rank_Dao();
			
			String url = "";
			//로그인 안한경우
			if (user == null ) {

	            String board_msg = "로그인을 해주세요";
	            String board_url = "/WebCafe_Project/login_view.do";
	               
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	           
	         }
			//관리자일떄만
			if(user.getIsAdmin().equals("M")){
				
					
					url="/WEB-INF/view/rankchange.jsp";

			}else {
				
				
				String board_msg ="권한이 없습니다.";
				String board_url = "/WebCafe_Project/login_view.do";
		        
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	    		
	    	
			}
			List<Rank> list = dao.getRankListAll();
			
			request.setAttribute("list", list);
		
			forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath(url);

		} catch (NamingException e) {
		
			System.out.println("Rank 변환1: "+e.getMessage());
		}
	
		

		
		return forward;
	}

}
