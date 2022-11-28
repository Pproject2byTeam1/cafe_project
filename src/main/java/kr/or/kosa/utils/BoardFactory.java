package kr.or.kosa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kr.or.kosa.dao.Board_Dao;
import kr.or.kosa.dao.DataBoardDao;
import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dto.AttendanceBoad;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Calender;
import kr.or.kosa.dto.DataBoard;
import kr.or.kosa.dto.Img_Board;
import kr.or.kosa.dto.MarketBoard;
import kr.or.kosa.dto.Regular_Board;

public class BoardFactory {

	public static List<? extends Board> createBoard(String b_type, int b_code) {
		
		List<? extends Board> board = null;
		
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Date nowdate = new Date();
			String nowday = nowday = format.format(nowdate);
			
			Date date=null;
			try {
				date = format.parse(nowday);
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			int year = cal.get(Calendar.YEAR);
			String y = String.valueOf(year);
			int month = cal.get(Calendar.MONTH)+1;
			String m = String.valueOf(month);
			
			cal.add(Calendar.DATE, 1);
			Date nexdate = new Date(cal.getTimeInMillis());
			String nextday = format.format(nexdate);
			
			if(b_type.equals("b1")) { //자유 게시판
				
				board = new ArrayList<Regular_Board>();
				Board_Dao dao = new Board_Dao();
				board = dao.getRegular_boardList(b_code, 1, 5);
				
			}else if(b_type.equals("b2")) { //출석체크
				
				board = new ArrayList<AttendanceBoad>();
				Board_Dao dao = new Board_Dao();
				board = dao.getBoardListAttendence(b_code, 1, nowday, nextday);
				
			}else if(b_type.equals("b3")) { //이미지 게시판
				
				board = new ArrayList<Img_Board>();
				Board_Dao dao = new Board_Dao();
				board = dao.getImg_boardList(b_code, 1, 5);
				
			}else if(b_type.equals("b4")) { //자료 게시판
				
				board = new ArrayList<DataBoard>();
				DataBoardDao dao = new DataBoardDao();
				board =  dao.getdata_boardList(b_code, 1, 5);
				
			}else if(b_type.equals("b5")) { //거래 게시판
				
				board = new ArrayList<MarketBoard>();
				MarketBoardDao market_dao = new MarketBoardDao(); 
				board = market_dao.listMarket(b_code, 1, 5);
				
			}else if(b_type.equals("b6")) { //일정 관리 게시판
				
				board = new ArrayList<Calender>();
				Board_Dao dao = new Board_Dao();
				board = dao.getCalender_list(b_code, y, m);
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		return board;
	}
	
}
