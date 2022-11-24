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

import kr.or.kosa.dto.Board_Info;

//게시판 종류
public class Board_Info_Dao {

	DataSource ds = null;
	
	public Board_Info_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//side bar 게시판 종류 전체 출력
	public List<Board_Info> getSideBoardList(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board_Info> list = new ArrayList<Board_Info>();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b_code, b_name, side_idx, main_idx, b_type "
						+ "from board_info order by side_idx";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					
					Board_Info info = new Board_Info();
					
					info.setB_code(rs.getInt("b_code"));
					info.setB_name(rs.getString("b_name"));
					info.setSide_idx(rs.getInt("side_idx"));
					info.setMain_idx(rs.getInt("main_idx"));
					info.setB_type(rs.getString("b_type"));
					
					list.add(info);
				}while(rs.next());
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
		
		return list;
	}
	
	//게시판 종류 탐색
	public Board_Info getBoardInfo(int b_code) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board_Info board_info = new Board_Info();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b_code, b_name, side_idx, main_idx, b_type from Board_Info where b_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b_code);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board_info.setB_code(rs.getInt("b_code"));
				board_info.setB_name(rs.getString("b_name"));
				board_info.setSide_idx(rs.getInt("side_idx"));
				board_info.setMain_idx(rs.getInt("main_idx"));
				board_info.setB_type(rs.getString("b_type"));
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
		
		return board_info;
	}
	
	//게시판 종류 수정
	public int updateBoardInfo(Board_Info board_info) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			String sql = "update Board_Info set b_name=?, side_idx=?, main_idx=? where b_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board_info.getB_name());
			pstmt.setInt(2, board_info.getSide_idx());
			pstmt.setInt(3, board_info.getMain_idx());
			
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
	
	//게시판 종류 삭제
	public int deleteBoardInfo(int b_code) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			String sql = "delete from Board_Info where b_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b_code);
			
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
