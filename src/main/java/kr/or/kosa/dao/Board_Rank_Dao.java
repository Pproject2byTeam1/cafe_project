package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.Board_Rank;

//게시판 권한
public class Board_Rank_Dao {

	DataSource ds = null;
	
	public Board_Rank_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//게시판 권한 탐색
	public Board_Rank getBoardRank(int b_code) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board_Rank board_rank = new Board_Rank();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b_code, w_rank, re_rank from Board_Rank where b_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b_code);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board_rank.setB_code(rs.getInt("b_code"));
				board_rank.setW_rank(rs.getInt("w_rank"));
				board_rank.setRe_rank(rs.getInt("re_rank"));
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
		
		return board_rank;
	}
	
}
