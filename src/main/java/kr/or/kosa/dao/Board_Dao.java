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
import kr.or.kosa.dto.Calender;

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
			String sql = "select * from "
						+ "(select rownum rn, idx, title, nick, content, hits, to_char(w_date, 'yyyy-MM-dd') as w_date, report_count, email_id, b_code from Board)"
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
					board.setW_date(rs.getString("w_date"));
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
	
	//자유 게시판 전체 조회
	public List<Board> getRegular_boardList(int b_code, int cpage, int pagesize){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boardlist = null;
		
		try {
			
			conn = ds.getConnection();
			String sql = "select *"
						+ "from ( select rownum rn, b.idx, b.title, b.nick, b.content, b.hits, to_char(b.w_date, 'yyyy-MM-dd') as w_date, b.report_count, b.notic, b.email_id, b.b_code, r.b_idx, r.refer, r.depth, r.step"
							+ "from board b join regular_board r"
							+ "on b.idx = r.idx"
							+ "where b.b_code = ?"
							+ "order by refer desc, step asc )"
						+ "where rn between ? and ?";
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize;
			
			pstmt.setInt(1, b_code);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			
			rs = pstmt.executeQuery();
			
			boardlist = new ArrayList<Board>();
			
			while(rs.next()) {
				Board board = new Board();
				board.setIdx(rs.getInt("idx"));
				board.setTitle(rs.getString("title"));
				board.setNick(rs.getString("nick"));
				board.setContent(rs.getString("content"));
				board.setHits(rs.getInt("hits"));
				board.setW_date(rs.getString("w_date"));
				board.setReport_count(rs.getInt("report_count"));
				board.setEmail_id(rs.getString("email_id"));
				board.setB_code(rs.getInt("b_code"));
				
				boardlist.add(board);
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
		
		
		return boardlist;
	}
	
	//이미지 게시판 전체 조회
	public List<Board> getImg_boardList(int b_code, int cpage, int pagesize){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boardlist = null;
		
		try {
			
			conn = ds.getConnection();
			String sql = "select * from (select rownum rn, idx, title, nick, content, hits, to_char(w_date, 'yyyy-MM-dd') as w_date, report_count, email_id, b_code from Board where b_code=?) where rn <= ? and rn >= ?";
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize;
			
			pstmt.setInt(1, b_code);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			
			rs = pstmt.executeQuery();
			
			boardlist = new ArrayList<Board>();
			
			while(rs.next()) {
				Board board = new Board();
				board.setIdx(rs.getInt("idx"));
				board.setTitle(rs.getString("title"));
				board.setNick(rs.getString("nick"));
				board.setContent(rs.getString("content"));
				board.setHits(rs.getInt("hits"));
				board.setW_date(rs.getString("w_date"));
				board.setReport_count(rs.getInt("report_count"));
				board.setEmail_id(rs.getString("email_id"));
				board.setB_code(rs.getInt("b_code"));
				
				boardlist.add(board);
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
		
		return boardlist;
	}
	
	//일정 관리 캘린더 전체 조회
	public List<Calender> getCalender_list(int b_code, String year, String month){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Calender> calenderlist = null;
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b.idx, b.title, b.nick, b.content, b.hits, to_char(b.w_date, 'yyyy-MM-dd') as w_date, b.report_count, b.notic, b.email_id, b.b_code, c.b_idx, to_char(c.start_date, 'yyyy-MM-dd') as start_date, to_char(c.end_date, 'yyyy-MM-dd') as end_date, c.finish "
						+ "from board b join calender c "
						+ "on b.idx = c.idx "
						+ "where b.b_code=? and c.start_date between to_date(?, 'YY/MM/DD') and to_date(?, 'YY/MM/DD')";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b_code);
			String req1 = year.concat("/").concat(month).concat("/").concat("01");
			pstmt.setString(2, req1);
			
			int[] maxDate = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
			int y = Integer.parseInt(year);
			if(y % 4 ==0 && y % 100 !=0 || y % 400 ==0) {
				maxDate[1]=29; // 윤년일 경우
			}
			int date = maxDate[Integer.parseInt(month)]-1;
			String req2 = year.concat("/").concat(month).concat("/").concat(String.valueOf(date));
			pstmt.setString(3, req2);
			
			rs = pstmt.executeQuery();
			
			calenderlist = new ArrayList<Calender>();
			
			while(rs.next()) {
				Calender board = new Calender();
				board.setIdx(rs.getInt("idx"));
				board.setTitle(rs.getString("title"));
				board.setNick(rs.getString("nick"));
				board.setContent(rs.getString("content"));
				board.setHits(rs.getInt("hits"));
				board.setW_date(rs.getString("w_date"));
				board.setReport_count(rs.getInt("report_count"));
				board.setEmail_id(rs.getString("email_id"));
				board.setB_code(rs.getInt("b_code"));
				board.setB_idx(rs.getInt("b_idx"));
				board.setStart_date(rs.getString("start_date"));
				board.setEnd_date(rs.getString("end_date"));
				board.setFinish(rs.getString("finish"));
				
				calenderlist.add(board);
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
		
		return calenderlist;
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
			
			pstmt.setInt(1, b_code);
			
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
			String sql = "select idx, title, nick, content, hits, to_char(w_date, 'yyyy-MM-dd') as w_date, report_count, email_id, b_code from Board where idx=?";
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
