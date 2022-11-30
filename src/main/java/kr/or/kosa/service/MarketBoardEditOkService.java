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
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.MarketBoard;
import kr.or.kosa.dto.User;

public class MarketBoardEditOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		int size = 1024 * 1024 * 10;
		
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
 	        
 	        System.out.println("여기 타는거에요?");
 			
 			// 로그인 안할경우 로그인 페이지로
			if (user == null) {

	            String board_msg = "세션이 만료되었습니다.";
	            String board_url = "/WebCafe_Project/login_view.do";
	              
	            request.setAttribute("board_msg", board_msg);
	            request.setAttribute("board_url", board_url);
	              
	            url="/WEB-INF/view/redirect.jsp";
	           
	         }else {
	        	 
	        	 MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8",
							new DefaultFileRenamePolicy());
	        	 
	        	
	  			int b_code = Integer.parseInt(multi.getParameter("b_code"));
	  			int idx = Integer.parseInt(multi.getParameter("idx")); 
	  			
	  			
	  			String title = multi.getParameter("title");
	 			String content = multi.getParameter("content");
	 			int price = Integer.parseInt(multi.getParameter("price"));
	 			String cate = multi.getParameter("cate");
	 			String m_mode = multi.getParameter("m_mode");
	 			String sold = multi.getParameter("sold");
	 			
	 			Enumeration filenames = multi.getFileNames();
				
				String file1 = (String) filenames.nextElement();
				String filename1 = multi.getFilesystemName(file1);
				
				
				
	 			MarketBoard marketboard = new MarketBoard();
	 			MarketBoardDao mdao = new MarketBoardDao();
	 		
	 			marketboard = mdao.readMarket(idx);
	 			
	 			marketboard.setTitle(title);
	 			marketboard.setContent(content);
	 			marketboard.setPrice(price);
	 			marketboard.setCate(cate);
	 			marketboard.setM_mode(m_mode);
	 			marketboard.setSold(sold);
	 			marketboard.setImg_name(filename1);
	 			
	 			
	 			int result = mdao.updateMarketBoardTitle(marketboard);
	 			
	 			
	 			
				
				if (result > 0) {

					String board_msg = "완료되었습니다.";
		            String board_url = "/WEB_INF/marketboard_read.do?b_code="+ b_code +"&idx="+ idx;
		              
		            request.setAttribute("board_msg", board_msg);
		            request.setAttribute("board_url", board_url);
		        	
					url="/WEB-INF/view/redirect.jsp";
					
				}
	 			
	 			
	         }
			
 			request.setAttribute("infolist", infolist);
			
			forward = new ActionForward();
 		  	forward.setRedirect(false);
 		  	forward.setPath(url);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
