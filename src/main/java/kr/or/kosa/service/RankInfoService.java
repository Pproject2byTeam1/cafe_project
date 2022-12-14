package kr.or.kosa.service;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.Rank_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.Rank;
import kr.or.kosa.dto.User;

public class RankInfoService implements Action {

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
			
			Rank_Dao dao = new Rank_Dao();
			
			String url = "user_grade.jsp";
	        		
    		//관리자일떄만
			List<Rank> list = dao.getRankExecptionManager();
			int size = list.size();
			int max = 0;
			for(Rank rank : list) {
				if(rank.getR_point() > max) {
					max = rank.getR_point();
				}
			}
			
			request.setAttribute("maxpoint", max);
			request.setAttribute("list", list);
			request.setAttribute("size", size);

			forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath(url);
			

		} catch (NamingException e) {
		
			System.out.println(e.getMessage());
		}
	
		return forward;
	}

}
