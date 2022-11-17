package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.kosa.dto.Img_Board;

//이미지 게시판 특정 게시판
public class Img_Board_Dao {

	DataSource ds = null;
	
	public Img_Board_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//이미지 게시판 특정 게시글 조회
	public Img_Board getImg_BoardByIdx(int idx) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Img_Board img_board = new Img_Board(); 
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b_idx, idx, img_name from Img_Board where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				img_board.setB_idx(rs.getInt("b_idx"));
				img_board.setIdx(rs.getInt("idx"));
				img_board.setImg_name(rs.getString("img_name"));
				
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
		
		return img_board;
	}
	
	//이미지 게시판 특정 글 삽입
	public int insertImg_Board(Img_Board img_board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "insert into Regualr_Board(idx, img_name) values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, img_board.getIdx());
			pstmt.setString(2, img_board.getImg_name());
			
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
	
	//이미지 게시판 특정 글 수정
	public int updateImg_Board(Img_Board img_board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "update Img_Board set img_name where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, img_board.getImg_name());
			pstmt.setInt(2, img_board.getIdx());
			
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
	
	//이미지 게시판 특정 글 삭제
	public int deleteImg_Board(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "delete from Img_Board where idx=?";
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
