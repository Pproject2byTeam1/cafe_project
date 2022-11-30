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

import kr.or.kosa.dto.BoardListGet;

public class BoardListGetDao {//오로지 조회 용도 그 외에는 사용 xx
	
	DataSource ds = null;
	
	public BoardListGetDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//자신이 쓴 글 조회
	public List<BoardListGet> getBoardListByMe(String email_id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardListGet> blist = null;
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b.idx,b.b_code, i.b_name, b.title, nvl(c.\"cnt\",0) as \"c_count\", b.nick,  to_char(b.w_date,'yyyy-MM-dd') as w_date, b.email_id, b.hits, nvl(l.\"cnt\",0) as \"like\" \r\n"
					+ "from board b left join (select count(email_id)as \"cnt\", idx from yes group by idx) l \r\n"
					+ "on l.idx = b.idx\r\n"
					+ "left join (select count(co_idx)as \"cnt\", idx from comments group by idx) c\r\n"
					+ "on c.idx = b.idx\r\n"
					+ "left join board_info i \r\n"
					+ "on b.b_code = i.b_code \r\n"
					+ "where b.email_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email_id);
			rs = pstmt.executeQuery();
			blist = new ArrayList<BoardListGet>();
			
			while(rs.next()) {
				BoardListGet board = new BoardListGet();
				board.setIdx(rs.getInt("idx"));
				board.setB_code(rs.getInt("b_code"));
				board.setB_name(rs.getString("b_name"));
				board.setTitle(rs.getString("title"));
				board.setC_count(rs.getInt("c_count"));
				board.setNick(rs.getString("nick"));
				board.setW_date(rs.getString("w_date"));
				board.setEmail_id(rs.getString("email_id"));
				board.setHits(rs.getInt("hits"));
				board.setLike(rs.getInt("like"));
				
				blist.add(board);
			}
			
		} catch (Exception e) {
			System.out.println("오류 :" + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			} catch (Exception e2) {
				
			}
		}
		
		return blist;
	}
	
	//자신이 좋아요 한 글 조회
		public List<BoardListGet> getBoardListByLike(String email_id){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<BoardListGet> blist = null;
			
			try {
				conn = ds.getConnection();
				String sql = "select b.idx, b.b_code, i.b_name, b.title, b.nick, nvl(c.\"cnt\",0) as \"c_count\", to_char(b.w_date,'yyyy-MM-dd') as w_date, b.email_id \r\n"
						+ "from board b left join yes y \r\n"
						+ "on b.idx = y.idx \r\n"
						+ "left join board_info i \r\n"
						+ "on b.b_code = i.b_code \r\n"
						+ "left join (select count(co_idx)as \"cnt\", idx from comments group by idx) c\r\n"
						+ "on c.idx = b.idx\r\n"
						+ "WHERE y.EMAIL_ID = ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, email_id);
				rs = pstmt.executeQuery();
				blist = new ArrayList<BoardListGet>();
				
				while(rs.next()) {
					BoardListGet board = new BoardListGet();
					board.setIdx(rs.getInt("idx"));
					board.setB_code(rs.getInt("b_code"));
					board.setB_name(rs.getString("b_name"));
					board.setTitle(rs.getString("title"));
					board.setC_count(rs.getInt("c_count"));
					board.setNick(rs.getString("nick"));
					board.setW_date(rs.getString("w_date"));
					board.setEmail_id(rs.getString("email_id"));
					
					blist.add(board);
				}
				
			} catch (Exception e) {
				System.out.println( e.getMessage());
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();//반환
				} catch (Exception e2) {
					
				}
			}
			
			return blist;
		}

}
