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

import kr.or.kosa.dto.AttendanceBoad;

public class ChartDao {

	DataSource ds = null;
	
	public ChartDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	public List<AttendanceBoad> getTopViews(int number){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AttendanceBoad> boardlist = new ArrayList<AttendanceBoad>();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select * "
					+ "from (select idx, title, nick, content, hits, w_date, report_count, notic, email_id, b_code "
					+ "from board "
					+ "order by hits desc) "
					+ "where rownum <= ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					
					AttendanceBoad board = new AttendanceBoad();
					board.setIdx(rs.getInt("idx"));
					board.setTitle(rs.getString("title"));
					board.setNick(rs.getString("nick"));
					board.setContent(rs.getString("content"));
					board.setHits(rs.getInt("hits"));
					board.setW_date(rs.getString("w_date"));
					board.setReport_count(rs.getInt("report_count"));
					board.setNotic(rs.getString("notic"));
					board.setEmail_id(rs.getString("email_id"));
					board.setB_code(rs.getInt("b_code"));
					
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
}
