package kr.or.kosa.service;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.Rank_Dao;
import kr.or.kosa.dto.Rank;
import kr.or.kosa.dto.User;

public class RankUpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	

		ActionForward forward = new ActionForward();
		
		try {
			Board_Info_Dao infodao = new Board_Info_Dao();
			Rank_Dao dao = new Rank_Dao();
			String msg = "";
			//session 로그인 한사람
			HttpSession session = request.getSession();
 	        User user = (User) session.getAttribute("member");
			String url ="";
			int row =0;
			if(user == null) {
					msg ="로그인 해주세요";
				  String board_msg = "세션이 만료되었습니다.";
		          String board_url = "/WebCafe_Project/login_view.do";
	
		            request.setAttribute("board_msg", board_msg);
		            request.setAttribute("board_url", board_url);
		              
		            url="/WEB-INF/view/redirect.jsp";
			}else {
		            Rank dto = new Rank();
		            dto.setRank(row);
		            
		            
		            
		            
			}
			
			
			
			List<Rank> list = dao.getRankListAll();
			request.setAttribute("list", list);
			
			
			
			
			
			
		} catch (NamingException e) {
			
			System.out.println("rankupdate:"+ e.getMessage());
		}
		
		
		
		
		
		
		
		
		
		return null;
	}

}
