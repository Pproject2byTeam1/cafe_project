package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.Rank_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.Rank;
import kr.or.kosa.utils.BoardFactory;

public class AdminMainEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();

		try { 
			
			//게시판 종류 정보 가져오기
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
			for(Board_Info info : infolist) {
				String b_type = info.getB_type();
				int b_code = info.getB_code();
				
				if(info.getMain_idx() == 1 || info.getMain_idx() == 2 || info.getMain_idx() == 3 || info.getMain_idx() == 4) {
					
					List<? extends Board> board = BoardFactory.createBoard(b_type, b_code); //b_type에 따라 다른 게시판 출력 가져오는 심플 팩토리 패턴
					
					request.setAttribute(b_type, board);
				}
			}
			
			//등급 정보 가져오기
			Rank_Dao rankdao = new Rank_Dao();
			List<Rank> ranklist = rankdao.getRankListAll();
			
			//카페배너 정보 가져오기
			CafeBannerDao cafebannerdao = new CafeBannerDao();
			CafeBanner cafebanner = cafebannerdao.getCafeBanner();
			
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
