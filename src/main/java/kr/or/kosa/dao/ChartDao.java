	package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.border.Border;

import kr.or.kosa.dto.AttendanceBoad;
import kr.or.kosa.dto.Chart;


public class ChartDao {

	DataSource ds = null;
	
	public ChartDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//--b_code로 조회하는 게시판별 월별 글수
	//-- where의 b_code = 을 물음표로하세요
		/**
		 * @param number
		 * @return
		 */
		public List<Chart> getMonth(int month){
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Chart> boardlist = new ArrayList<Chart>();
			
			try {
				
				conn = ds.getConnection();
				String sql = "select substr(w_date, 4, 2) as \"month\", b_name, count(substr(w_date, 4, 2)) as \"count\" "
						+ "from ( "
						+ "    select b.idx, b.nick, b.hits, b.w_date, substr(b.w_date, 4,2) as \"month\", i.b_code, i.b_name, b.title "
						+ "    from board b left join board_info i "
						+ "    on b.b_code = i.b_code "
						+ "    where i.b_code = ? "
						+ ") "
						+ "group by substr(w_date, 4,2), b_name "
						+ "order by substr(w_date, 4, 2) ";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, month);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					do {
						
						Chart board = new Chart();
						
						board.setMonth(rs.getString("month"));
						board.setB_name(rs.getString("b_name"));
						board.setCount(rs.getInt("count"));
						
						boardlist.add(board);
					}while(rs.next());
				}else {
					System.out.println("조회 데이터 없음");
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		
			return boardlist;
		}
	
	
	
	//모든 게시판 조회수 상위 TOP
	public List<Chart> getTopViews(int number){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Chart> boardlist = new ArrayList<Chart>();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select * "
					+ "from ( "
					+ "select i.b_name, b.idx, b.hits, b.title "
					+ "from board b left join board_info i on b.B_CODE = i.B_CODE "
					+ "order by hits desc) "
					+ "where rownum <= ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					
					Chart board = new Chart();
					
					board.setB_name(rs.getString("b_name"));
					board.setIdx(rs.getInt("idx"));
					board.setHits(rs.getInt("hits"));
					board.setTitle(rs.getString("title"));
					
					boardlist.add(board);
				}while(rs.next());
			}else {
				System.out.println("조회 데이터 없음");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	
		return boardlist;
	}
	
	//--> 게시판 별 count 수
		public List<Chart> getBoardCount(){
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Chart> boardlist = new ArrayList<Chart>();
			
			try {
				
				conn = ds.getConnection();
				String sql = "select b_name, count(idx) hit_count "
						+ "from ( "
						+ "    select i.b_name, b.idx, b.hits, b.title "
						+ "    from board b left join board_info i "
						+ "    on b.b_code = i.b_code "
						+ "    order by hits "
						+ ")group by b_name "
						+ "order by hit_count desc ";
				
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					do {
						Chart board = new Chart();
						
						board.setB_name(rs.getString("b_name"));
						board.setHit_count(rs.getInt("hit_count"));
						
						boardlist.add(board);
					}while(rs.next());
				}else {
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		
			return boardlist;
		}
	
	
public List<Chart> getTopRankpoint(String startDate, String endDate, int number){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Chart> boardlist = new ArrayList<Chart>();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select * "
					+ "from ( "
					+ "    select b.idx,b.b_code, i.b_name, b.title, b.nick,  to_char(b.w_date,'yyyy-MM-dd') as w_date, b.email_id, (nvl(c.\"cnt\",0) + b.hits + nvl(l.\"cnt\",0)*5) as rankpoint "
					+ "    from board b left join (select count(email_id)as \"cnt\", idx "
					+ "                        from yes "
					+ "                        group by idx) l "
					+ "    on l.idx = b.idx "
					+ "    left join (select count(co_idx)as \"cnt\", idx from comments group by idx) c "
					+ "    on c.idx = b.idx "
					+ "    left join board_info i "
					+ "    on b.b_code = i.b_code "
					+ "    where b.w_date BETWEEN TO_DATE(?, 'YYYY-MM-DD') and TO_DATE(?, 'YYYY-MM-DD') "
					+ "    order by rankpoint desc "
					+ ") where rownum <= ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setInt(3, number);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					
					Chart board = new Chart();
					board.setRankpoint(rs.getInt("rankpoint"));
					board.setNick(rs.getString("nick"));
					board.setTitle(rs.getString("title"));
					board.setW_date(rs.getString("w_date"));
					board.setB_code(rs.getInt("b_code"));
					board.setIdx(rs.getInt("idx"));
					board.setB_name(rs.getString("b_name"));
					board.setEmail_id(rs.getString("email_id"));
					
					boardlist.add(board);
				}while(rs.next());
			}else {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return boardlist;
	}



public List<Chart> getBoardUtilizationRate(String startDate, String endDate){
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Chart> boardlist = new ArrayList<Chart>();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select sum(hits) as hit_count, B_CODE, b_name from( "
					+ "select idx, hits, b.b_code, b_name from board b left join board_info i on b.B_CODE = i.B_CODE "
					+ "where w_date BETWEEN TO_DATE(?, 'YYYY-MM-DD') and TO_DATE(?, 'YYYY-MM-DD')) group by B_CODE, b_name "
					+ "ORDER BY b_code asc ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					
					Chart board = new Chart();
					board.setHit_count(rs.getInt("hit_count"));
					board.setB_name(rs.getString("b_name"));
					
					boardlist.add(board);
				}while(rs.next());
			}else {
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return boardlist;
	}
}
