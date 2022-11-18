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
//건들지 마세요 추수 수정 예정
public class Board_Dao {

	DataSource ds = null;
	
	public Board_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//게시글 전체 조회
	public List<Board> getBoardListAll(int cpage, int pagesize){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boardlist = new ArrayList<Board>();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select * from"
						+ "(select rownum rn, idx, title, nick, content, hits, w_date, report_count, email_id, b_code from Board)"
						+ "where rn between ? and ?";
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize;
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
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
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	
		return boardlist;
	}
	
	//특정 카테고리별 조회
	public List<Board> getBoardListByB_code(int b_code, int cpage, int pagesize){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs = null;
		List<Board> boardlist = new ArrayList<Board>();
		
		try {
			conn = ds.getConnection();
			
			//게시판 형식이 무엇인지 파악
			//b1 or b4일 경우 계층형 게시판
			String sql = "select b_type from board_info where b_type in ('b1', 'b4') and b_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b_code);
			
			rs1 = pstmt.executeQuery();
			
			String sql1 = "";
			
			if(rs1.getString("b_type").equals("b1")) {
				sql1 = "select *"
						+ "from ( select rownum rn, b.idx, b.title, b.nick, b.content, b.hits, b.w_date, b.report_count, b.notic, b.email_id, b.b_code, r.b_idx, r.refer, r.depth, r.step"
								+ "from board b join regular_board r"
								+ "on b.idx = r.idx"
								+ "where b.b_code = ?"
								+ "order by refer desc, step asc )"
						+ "where rn between ? and ?";
			}else if(rs1.getString("b_type").equals("b4")) {
				sql1 = "select *"
						+ "from ( select rownum rn, b.idx, b.title, b.nick, b.content, b.hits, b.w_date, b.report_count, b.notic, b.email_id, b.b_code, d.b_idx, d.refer, d.depth, d.step"
								+ "from board b join data_board d"
								+ "on b.idx = d.idx"
								+ "where b.b_code = ?"
								+ "order by refer desc, step asc)"
						+ "where rn between ? and ?";
			}else {
				sql1 = "select * "
						+ "from (select rownum rn, idx, title, nick, content, hits, w_date, report_count, email_id, b_code"
								+ "from Board where b_code=?)"
						+ "where rn between ? and ?";
			}
			
			pstmt = conn.prepareStatement(sql1);
			
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize;
			
			pstmt.setInt(1, b_code);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
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
				rs1.close();
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return boardlist;
	}
	
	//특정 카테고리 총 게시물 건수
	public int totalBoardCountByB_code(int b_code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalcount = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "select count(*) cnt from board where b_code=?";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalcount = rs.getInt("cnt");
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
		
		return totalcount;
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
				conn.close();
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
				conn.close();
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
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return row;
	}
	
	//답글 작성(미완)
	public int reWrite(Board board) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			
			
			
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
	
	//조회수 증가
	public int updateHits(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "update Board set hits=(select hits+1 from Board where idx=?) where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			pstmt.setInt(2, idx);
			
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
	
	//신고수 증가
	public int updateReport_count(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "update Board set report_count=(select report_count+1 from Board where idx=?) where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			pstmt.setInt(2, idx);
			
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
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return row;
	}
	
}
