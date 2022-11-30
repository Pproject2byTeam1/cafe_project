package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.User;
import kr.or.kosa.dto.UserDetails;

public class UserListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		 
		try {
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);//top
			
			Board_Info_Dao infodao = new Board_Info_Dao();
	        List<Board_Info> infolist = infodao.getSideBoardList();
	        
	        HttpSession session = request.getSession();
	        User user = (User) session.getAttribute("member");
	        String url = "";
	        
	        if (user == null) {
				
	        	String board_msg = "권한이 없습니다.";
	            String board_url = "/WebCafe_Project/login_view.do";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	            
			}  else if (user.getIsAdmin().equals("S") || user.getIsAdmin().equals("M") ) {

	        	UserDao dao = new UserDao();
				
				int totalusercount = dao.totalUserCount();
				
				//상세보기 >> 다시  LIST 넘어올때  >> 현재 페이지 설정
				String ps = request.getParameter("ps");
				String cp = request.getParameter("cp");
				
				//List 페이지 처음 호출 경우
				if(ps == null || ps.trim().equals("")) {
					ps = "5"; //5개씩 
				}
				
				if(cp == null || cp.trim().equals("")) {
					//default 값 설정
					cp = "1"; // 1번째 페이지 보겠다 
				}
				
				int pagesize = Integer.parseInt(ps);
				int cpage = Integer.parseInt(cp);
				int pagecount=0;
				
				if(totalusercount % pagesize == 0) {
					pagecount = totalusercount / pagesize;
				}else {
					pagecount = (totalusercount / pagesize) + 1; 
				}
				
				List<UserDetails> alluser = dao.list(cpage, pagesize);
				
				request.setAttribute("infolist", infolist);
				request.setAttribute("pagesize", pagesize);
				request.setAttribute("cpage", cpage);
				request.setAttribute("pagecount", pagecount);
				request.setAttribute("totalusercount", totalusercount);
				request.setAttribute("alluser", alluser);
				
				url="/WEB-INF/view/user_list.jsp";
	        
	        	
			}
	        
	        forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath(url);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
