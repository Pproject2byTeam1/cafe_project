package kr.or.kosa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.ChartDao;
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.Chart;
import kr.or.kosa.dto.MarketBoard;
import kr.or.kosa.dto.Regular_Board;
import kr.or.kosa.dto.User;

public class CafeMain implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();
		
		try {
			
			//사이드 바
			Board_Info_Dao infodao = new Board_Info_Dao();
			List<Board_Info> infolist = infodao.getSideBoardList();
			MarketBoardDao market_dao = new MarketBoardDao(); 
			
			//각종 보드리스트들 가져오기 (한 최근 것 50개?)
			Board_Dao dao = new Board_Dao(); 
			
			List<Board> regular_list = dao.getBoardList(1);//자유게시판
			List<Board> data_list = dao.getBoardList(6);//정보게시판
			List<MarketBoard> market_list = market_dao.listMarket(5, 1, 7);//거래게시판(7개만..)
			List<Board> chart = dao.viewchart();
			
			//날짜계산 준비
			String inputdate = (String) request.getParameter("inputdate");
			System.out.println("inputdate: "+inputdate);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date nowdate = null;
			String nowday = "";
			if(inputdate == null) {//입력받은 날짜가 없다면
				nowdate= new Date();
				nowday = format.format(nowdate);
			}else {//있으면
				try {
					nowdate= format.parse(inputdate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				nowday = format.format(nowdate);
			}
			Date date=null;
			try {
				date = format.parse(nowday);
				request.setAttribute("date5", format.format(date));//입력된 날짜 yyyy-MM-dd
				
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			cal.add(Calendar.MONTH, -1); //날짜 -1달
			Date befMondate = new Date(cal.getTimeInMillis());
			String BeforeMonthday = format.format(befMondate);
			
			
			//랭크차트
			ChartDao chartdao = new ChartDao();
			
			List<Chart> rankchart = chartdao.getTopRankpoint(BeforeMonthday, nowday, 5);
			
			request.setAttribute("regular_list", regular_list);
			request.setAttribute("data_list", data_list);
			request.setAttribute("market_list", market_list);
			
			request.setAttribute("chart", chart);
			request.setAttribute("rankchart", rankchart);
			
			request.setAttribute("infolist", infolist);
			
			forward = new ActionForward();
		  	forward.setRedirect(false);
		  	forward.setPath("/WEB-INF/view/cafe_main.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
