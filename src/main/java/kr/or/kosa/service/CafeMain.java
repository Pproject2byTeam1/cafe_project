package kr.or.kosa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.ChartDao;
import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.CafeBanner;
import kr.or.kosa.dto.Chart;
import kr.or.kosa.dto.MarketBoard;

public class CafeMain implements Action {

	@Override
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();
		
		try {
			//top
			CafeBannerDao bannerdao = new CafeBannerDao();
			CafeBanner banner = bannerdao.getCafeBanner();
			
			//사이드 바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			
			MarketBoardDao market_dao = new MarketBoardDao(); 
			
			//저장된 보드 정보 가져오기
			List<Board_Info> info = infodao.getBoardNameList();
			int b1 = info.get(0).getB_code();
			int b2 = info.get(1).getB_code();
			int b3 = info.get(2).getB_code();
			int b4 = info.get(3).getB_code();
			
			//날짜계산 준비
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date nowdate =  new Date();
			String nowday = format.format(nowdate);
			Date date=null;
			try {
				date = format.parse(nowday);
				request.setAttribute("date5", format.format(date));//입력된 날짜 yyyy-MM-dd
				
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date);
			cal1.add(Calendar.DATE, -1); //날짜 -1일
			Date befdate = new Date(cal1.getTimeInMillis());
			String Beforeday = format.format(befdate);
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date);
			cal2.add(Calendar.MONTH, -1); //날짜 -1달
			Date befMondate = new Date(cal2.getTimeInMillis());
			String BeforeMonthday = format.format(befMondate);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.YEAR, -1); //날짜 -1년
			Date befYeardate = new Date(cal.getTimeInMillis());
			String BeforeYearday = format.format(befYeardate);
			
			//각종 보드리스트들 가져오기 (한 최근 것 5개?)
			Board_Dao dao = new Board_Dao(); 
			
			List<Board> board1 = dao.getBoardList(b1,BeforeMonthday,nowday);
			List<Board> board2 = dao.getBoardList(b2,BeforeMonthday,nowday);
			List<Board> board3 = dao.getBoardList(b3,BeforeMonthday,nowday);
			List<Board> board4 = dao.getBoardList(b4,BeforeMonthday,nowday);
			List<MarketBoard> market_list = market_dao.listMarket(5, 1, 7);//거래게시판(7개만..)
			List<Board> chart = dao.viewchart();
			
			//랭크차트
			ChartDao chartdao = new ChartDao();
			
			List<Chart> rankchart = chartdao.getTopRankpoint(BeforeMonthday, nowday, 5);
			request.setAttribute("board1info", info.get(0));
			request.setAttribute("board1", board1);
			request.setAttribute("board2info", info.get(1));
			request.setAttribute("board2", board2);
			request.setAttribute("board3info", info.get(2));
			request.setAttribute("board3", board3);
			request.setAttribute("board4info", info.get(3));
			request.setAttribute("board4", board4);
			request.setAttribute("market_list", market_list);
			
			request.setAttribute("chart", chart);
			request.setAttribute("rankchart", rankchart);
			
			request.setAttribute("banner", banner);//top
			request.setAttribute("infolist", infolist);
			
			request.setAttribute("Beforeday", Beforeday);
			request.setAttribute("BeforeMonth", BeforeMonthday);
			request.setAttribute("BeforeYear", BeforeYearday);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/cafe_main.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
