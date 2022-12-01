package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.Rank_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.Rank;
import kr.or.kosa.dto.User;
import kr.or.kosa.utils.BoardFactory;
import net.sf.json.JSONObject;

public class AdminMainEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();

		try { 
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			request.setAttribute("banner", banner);//top
			//세션정보
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("member");
			//게시판 종류 정보 가져오기
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
			JSONObject json1 = new JSONObject();
			JSONObject json2 = new JSONObject();
			JSONObject json3 = new JSONObject();
			JSONObject json4 = new JSONObject();
			
			for(Board_Info info : infolist) {
				String b_type = info.getB_type();
				int b_code = info.getB_code();
				
				if(info.getMain_idx() == 1) {
					List<? extends Board> board = BoardFactory.createBoard(b_type, b_code); //b_type에 따라 다른 게시판 출력 가져오는 심플 팩토리 패턴
					json1.put("main1", board);
				} else if(info.getMain_idx() == 2) {
					List<? extends Board> board = BoardFactory.createBoard(b_type, b_code); //b_type에 따라 다른 게시판 출력 가져오는 심플 팩토리 패턴
					json2.put("main2", board);
				} else if(info.getMain_idx() == 3) {
					List<? extends Board> board = BoardFactory.createBoard(b_type, b_code); //b_type에 따라 다른 게시판 출력 가져오는 심플 팩토리 패턴
					json3.put("main3", board);
				} else if(info.getMain_idx() == 4) {
					List<? extends Board> board = BoardFactory.createBoard(b_type, b_code); //b_type에 따라 다른 게시판 출력 가져오는 심플 팩토리 패턴
					json4.put("main4", board);
				}
			}
			
			//등급 정보 가져오기
			Rank_Dao rankdao = new Rank_Dao();
			List<Rank> ranklist = rankdao.getRankListAll();
			
			//카페배너 정보 가져오기
			CafeBannerDao cafebannerdao = new CafeBannerDao();
			CafeBanner cafebanner = cafebannerdao.getCafeBanner();
		
			request.setAttribute("main1", json1); //게시판 출력 가져오기
			request.setAttribute("main2", json2); //게시판 출력 가져오기
			request.setAttribute("main3", json3); //게시판 출력 가져오기
			request.setAttribute("main4", json4); //게시판 출력 가져오기
			request.setAttribute("infolist", infolist); //게시판 종류 정보 가져오기
			request.setAttribute("ranklist", ranklist); //등급 정보 가져오기
			request.setAttribute("cafebanner", cafebanner); //카페배너 정보 가져오기
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/adminmain_edit.jsp");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}

}
