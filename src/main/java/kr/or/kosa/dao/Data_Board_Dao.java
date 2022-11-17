package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.kosa.dto.Data_Board;
import kr.or.kosa.dto.Regular_Board;

public class Data_Board_Dao {
	DataSource ds = null;

	public Data_Board_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	//자료 게시판 특정 글 조회
	public Data_Board getData_BoardByIdx(int idx){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Data_Board data_board = new Data_Board(); 
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b_idx, idx, ori_name, save_name, volume, refer, depth, step from Data_Board where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				data_board.setB_idx(rs.getInt("b_idx"));
				data_board.setIdx(rs.getInt(rs.getInt("idx")));
				data_board.setOri_name(rs.getString("ori_name"));
				data_board.setSave_name(rs.getString("save_name"));
				data_board.setVolume(rs.getInt("volume"));
				data_board.setRefer(rs.getInt("refer"));
				data_board.setDepth(rs.getInt("depth"));
				data_board.setStep(rs.getInt("step"));
				
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
		
		return data_board;
	}
	
	//자료 게시판 특정 글 삽입
	public int insertData_Board(Data_Board data_board) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "insert into Regualr_Board(idx, ori_name, save_name, volume, refer, depth, step) values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, data_board.getIdx());
			pstmt.setString(2, data_board.getOri_name());
			pstmt.setString(3, data_board.getSave_name());
			pstmt.setInt(4, data_board.getVolume());
			pstmt.setInt(5, data_board.getRefer());
			pstmt.setInt(6, data_board.getDepth());
			pstmt.setInt(7, data_board.getStep());
			
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
	
	//자료 게시판 특정 글 수정
	public int updateData_Board(Data_Board data_board) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "update Data_Board set ori_name=?, save_name=? volume=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, data_board.getOri_name());
			pstmt.setString(2, data_board.getSave_name());
			pstmt.setInt(3, data_board.getVolume());
			pstmt.setInt(4, data_board.getIdx());
			
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
	
	//자료 게시판 특정 글 삭제
	public int deleteData_Board(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "delete from Data_Board where idx=?";
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


