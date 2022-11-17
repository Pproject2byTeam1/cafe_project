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

import kr.or.kosa.dto.Board;

//게시판 글
public class Board_Dao {

	DataSource ds = null;
	
	public Board_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//게시글 전체 조회
	public List<Board> getBoardListAll(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boardlist = new ArrayList<Board>();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select idx, title, nick, content, hits, w_date, report_count, email_id, b_code from Board";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					
					Board board = new Board();
					board.setIdx(rs.getInt("idx"));
					board.setTitle(rs.getString("title"));
					board.setNick(rs.getString("nick"));
					board.setContent(rs.getString("content"));
					board.setHits(rs.getInt("hits"));
					board.setW_date(rs.getDate("w_date"));
					board.setReport_count(rs.getInt("report_count"));
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
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	
		return boardlist;
	}
	
	//특정 카테고리별 조회
	public List<Board> getBoardListByB_code(int b_code){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boardlist = new ArrayList<Board>();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select idx, title, nick, content, hits, w_date, report_count, email_id, b_code from Board where b_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b_code);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					Board board = new Board();
					board.setIdx(rs.getInt("idx"));
					board.setTitle(rs.getString("title"));
					board.setNick(rs.getString("nick"));
					board.setContent(rs.getString("content"));
					board.setHits(rs.getInt("hits"));
					board.setW_date(rs.getDate("w_date"));
					board.setReport_count(rs.getInt("report_count"));
					board.setEmail_id(rs.getString("email_id"));
					board.setB_code(rs.getInt("b_code"));
					
					boardlist.add(board);
				}while(rs.next());
			} else {
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
		
		return boardlist;
	}
	
	//특정 게시글 조회
	public Board getBoardByIdx(int idx) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = new Board();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select idx, title, nick, content, hits, w_date, report_count, email_id, b_code from Board where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board.setIdx(rs.getInt("idx"));
				board.setTitle(rs.getString("title"));
				board.setNick(rs.getString("nick"));
				board.setContent(rs.getString("content"));
				board.setHits(rs.getInt("hits"));
				board.setW_date(rs.getDate("w_date"));
				board.setReport_count(rs.getInt("report_count"));
				board.setEmail_id(rs.getString("email_id"));
				board.setB_code(rs.getInt("b_code"));
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
		
		return board;
	}
	
	//게시글 삽입
	public int insertBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "insert into Board(title, nick, content, email_id, b_code) values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getNick());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getEmail_id());
			pstmt.setInt(5, board.getB_code());
			
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
	
	//게시글 수정
	public int updateBoard(Board board) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "update Board set title=?, content=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getIdx());
			
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
	
	//조회수 증가
	public int updateHits(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "update Board set hits=(select hits+1 from Board where idx=?) where idx=?;";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			pstmt.setInt(2, idx);
			
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
	
	//신고수 증가
	public int updateReport_count(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "update Board set report_count=(select report_count+1 from Board where idx=?) where idx=?;";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			pstmt.setInt(2, idx);
			
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
	
	//게시글 삭제
	public int deleteBoard(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "delete from board where idx=?";
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
