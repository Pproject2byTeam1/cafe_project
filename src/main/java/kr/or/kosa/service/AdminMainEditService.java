package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.Rank_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
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
					List<? extends Board> board = BoardFactory.createBoard(b_type, b_code);
					
					
					
				}
			}
			
			//등급 정보 가져오기
			Rank_Dao rankdao = new Rank_Dao();
			List<Rank> ranklist = rankdao.getRankListAll();


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}

}
