package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
		Regular_Board regular_board = new Regular_Board(); 
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b_idx, idx, refer, depth, step from Regular_Board where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				regular_board.setB_idx(rs.getInt("b_idx"));
				regular_board.setIdx(rs.getInt(rs.getInt("idx")));
				regular_board.setRefer(rs.getInt("refer"));
				regular_board.setDepth(rs.getInt("depth"));
				regular_board.setStep(rs.getInt("step"));
				
			}else {
				System.out.println("조회 데이터 없음");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return regular_board;
	}
	
	//자유 게시판 특정 글 삽입
	public int insertRegualr_Board(Regular_Board regualr_board) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "insert into Regualr_Board(idx, refer, depth, step) values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, regualr_board.getIdx());
			pstmt.setInt(2, regualr_board.getRefer());
			pstmt.setInt(3, regualr_board.getDepth());
			pstmt.setInt(4, regualr_board.getStep());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
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
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return row;
	}
	
	
}
