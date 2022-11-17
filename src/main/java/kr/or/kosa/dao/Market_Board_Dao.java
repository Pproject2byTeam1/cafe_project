package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.kosa.dto.Market_Board;
import kr.or.kosa.dto.Regular_Board;

public class Market_Board_Dao {

	DataSource ds = null;
	
	public Market_Board_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//거래 게시판 특정 게시글 조회
	public Market_Board getMarket_BoardByIdx(int idx) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Market_Board market_board = new Market_Board(); 
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b_idx, idx, m_mode, cate, price, sold from Market_Board where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				market_board.setB_idx(rs.getInt("b_idx"));
				market_board.setIdx(rs.getInt(rs.getInt("idx")));
				market_board.setM_mode(rs.getString("m_mode"));
				market_board.setCate(rs.getString("m_mode"));
				market_board.setPrice(rs.getInt("price"));
				market_board.setSold(rs.getString("sold"));
				
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
		
		return market_board;
		
	}
	
	//거래 게시판 특정 글 삽입
	public int insertMarket_Board(Market_Board market_board) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "insert into Market_Board(idx, m_mode, cate, price, sold) values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, market_board.getIdx());
			pstmt.setString(2, market_board.getM_mode());
			pstmt.setString(3, market_board.getCate());
			pstmt.setInt(4, market_board.getPrice());
			pstmt.setString(5, market_board.getSold());
			
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
	
	//거래 게시판 특정 글 삭제
	public int deleteMarket_Board(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "delete from Market_Board where idx=?";
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
