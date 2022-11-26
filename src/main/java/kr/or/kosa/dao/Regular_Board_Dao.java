package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Regular_Board;

//자유 게시판
public class Regular_Board_Dao {

	DataSource ds = null;
	
	public Regular_Board_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//자유게시판 특정 게시글 조회
	public Regular_Board getRegular_BoardByIdx(int idx) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Regular_Board board = new Regular_Board(); 
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b.idx, b.title, b.nick, b.content, b.hits, to_char(b.w_date, 'YYYY-MM-dd') w_date, b.report_count, b.notic, b.email_id, b.b_code, i.refer, i.depth, i.step "
							+ "from board b join regular_board i "
							+ "on b.idx = i.idx "
							+ "where b.idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				board.setIdx(rs.getInt("idx"));
				board.setTitle(rs.getString("title"));
				board.setNick(rs.getString("nick"));
				board.setContent(rs.getString("content"));
				board.setHits(rs.getInt("hits"));
				board.setW_date(rs.getString("w_date"));
				board.setEmail_id(rs.getString("email_id"));
				board.setReport_count(rs.getInt("report_count"));
				board.setNotic(rs.getString("notic"));
				board.setEmail_id(rs.getString("email_id"));
				board.setB_code(rs.getInt("b_code"));
				board.setDepth(rs.getInt("depth"));
				board.setStep(rs.getInt("step"));
				
				
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
		
		return board;
	}
	
	//자유 게시판 답글 삽입
	public int insertRegualr_BoardReply(Regular_Board regualr_board) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "INSERT ALL "
					+ "INTO board (idx, title, nick, content, email_id, b_code) "
					+ "VALUES (IDX_SEQ.nextval, ?, ?, ?, ?, ?) "
					+ "INTO regular_board (b_idx, idx, refer, depth, step) "
					+ "VALUES (IMG_B_IDX_SEQ.nextval, IDX_SEQ.currval, ?, ?, ?) "
					+ "select * from dual";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, regualr_board.getTitle());
			pstmt.setString(2, regualr_board.getNick());
			pstmt.setString(3, regualr_board.getContent());
			pstmt.setString(4, regualr_board.getEmail_id());
			pstmt.setInt(5, regualr_board.getB_code());
			pstmt.setInt(6, regualr_board.getRefer());
			pstmt.setInt(7, regualr_board.getDepth()+1);
			pstmt.setInt(8, regualr_board.getStep()+1);
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return row;
	}
	
	//자유 게시판 특정 글 삭제
	public int deleteRegualr_Board(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "delete from Regular_Board where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return row;
	}
	
	
}
