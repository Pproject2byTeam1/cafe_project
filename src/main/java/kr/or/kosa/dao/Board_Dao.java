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
import javax.swing.border.Border;

import kr.or.kosa.dto.AttendanceBoad;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Calender;
import kr.or.kosa.dto.Img_Board;
import kr.or.kosa.dto.Regular_Board;

//게시판 글
//건들지 마세요 추수 수정 예정
public class Board_Dao {

	DataSource ds = null;
	
	public Board_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//게시글 전체 조회
	public List<AttendanceBoad> getBoardListAll(int cpage, int pagesize){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AttendanceBoad> boardlist = new ArrayList<AttendanceBoad>();
		
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
					
					AttendanceBoad board = new AttendanceBoad();
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
	public List<Regular_Board> getRegular_boardList(int b_code, int cpage, int pagesize){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Regular_Board> boardlist = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select * "
							+ "from (select * "
								+ "from (select ROW_NUMBER() OVER(ORDER BY b_idx desc) AS rn, b.idx, b_idx, refer, depth, step, title, nick, content, hits, to_char(w_date, 'yyyy-MM-dd') as w_date, report_count, notic, email_id, b_code "
									+ "from board b join regular_board d "
									+ "on b.idx = d.idx "
									+ "where b.b_code = ? "
									+ "order by refer desc, step asc) "
								+ "where rn >= ?) "
							+ "where rn <= ?";
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize;
			
			pstmt.setInt(1, b_code);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			boardlist = new ArrayList<Regular_Board>();
			
			while(rs.next()) {
				Regular_Board board = new Regular_Board();
				board.setIdx(rs.getInt("idx"));
				String title = rs.getString("title");
				if(title.length() >10) {
					title = title.substring(0, 9) + "...";
				}
				board.setTitle(title);
				board.setNick(rs.getString("nick"));
				board.setContent(rs.getString("content"));
				board.setHits(rs.getInt("hits"));
				board.setW_date(rs.getString("w_date"));
				board.setReport_count(rs.getInt("report_count"));
				board.setEmail_id(rs.getString("email_id"));
				board.setB_code(rs.getInt("b_code"));
				board.setDepth(rs.getInt("depth"));
				
				
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
				System.out.println(e2.getMessage());
			}
		}
		
		
		return boardlist;
	}
	//자료게시판 글목록
	
	
	//이미지 게시판 전체 조회
	public List<Img_Board> getImg_boardList(int b_code, int cpage, int pagesize){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Img_Board> boardlist = null;
		
		try {
			
			conn = ds.getConnection();
			String sql = "select * from  "
						+ "(select rownum rn, b.idx, b.title, b.nick, b.content, b.hits, to_char(b.w_date, 'yyyy-MM-dd') as w_date, b.report_count, b.email_id, b.b_code, i.b_idx, i.img_name "
						+ "from Board b join img_board i "
						+ "on b.idx = i.idx "
						+ "where b_code=? order by rn desc) "
						+ "where rn <= ? and rn >= ?";
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize;
			
			pstmt.setInt(1, b_code);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			
			rs = pstmt.executeQuery();
			
			boardlist = new ArrayList<Img_Board>();
			
			while(rs.next()) {
				Img_Board board = new Img_Board();
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
				board.setImg_name(rs.getString("img_name"));
				
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
	public AttendanceBoad getBoardByIdx(int idx) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AttendanceBoad board = new AttendanceBoad();
		
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
	
	//게시글 삽입(출석판용)
	public int insertBoard(AttendanceBoad board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into Board(idx, title, nick, content, email_id, b_code) values(IDX_SEQ.nextval, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getNick());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getEmail_id());
			pstmt.setInt(5, board.getB_code());
			
			row = pstmt.executeUpdate();
			
			if(row <= 0) {
				throw new Exception("board 삽입 실패");
			}else {
				conn.commit();
			}
		} catch (Throwable e) {
			if(conn != null) {
				try {
					conn.rollback(); // 트랜잭션 실행 이전 상태로 돌리기
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		} finally {
			try {
				conn.setAutoCommit(true);
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return row;
	}
	
	//게시글 수정
	public int updateBoard(AttendanceBoad board) {
		
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
	
	//idx값으로 title content 수정
		public int updateUseIdxBoard(String title, String content, int idx) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			int row = 0;
			
			try {
				
				conn = ds.getConnection();
				String sql = "UPDATE board SET title=?, content=? WHERE idx=? ";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				pstmt.setInt(3, idx);
				
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
		
		//출석 게시글 전체 조회
		public List<AttendanceBoad> getBoardListAttendence(int b_code, int cpage, String startdate, String enddate){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<AttendanceBoad> boardlist = new ArrayList<AttendanceBoad>();
			
			try {
				
				conn = ds.getConnection();
				String sql = "select * from "
						+ "(select  rownum rn, b.idx, m.rank, b.NICK, b.CONTENT, to_char(b.w_date,'yyyy-MM-dd / hh:mm')as w_date, b.EMAIL_ID, b.B_CODE  \r\n"
						+ "from board b left join member m on b.email_id = m.email_id \r\n"
						+ "WHERE b.b_code = ? \r\n"
						+ "and b.w_date between ? and ? \r\n"
						+ ")where rn between ? and ?";
				pstmt = conn.prepareStatement(sql);
				
				int start = cpage * 10 - 9;
				int end = cpage * 10;
				
				pstmt.setInt(1, b_code);
				pstmt.setString(2, startdate);
				pstmt.setString(3, enddate);
				pstmt.setInt(4, start);
				pstmt.setInt(5, end);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					do {
						
						AttendanceBoad board = new AttendanceBoad();
						board.setIdx(rs.getInt("idx"));
						board.setHits(rs.getInt("rank"));
						board.setNick(rs.getString("nick"));
						board.setContent(rs.getString("content"));
						board.setW_date(rs.getString("w_date"));
						board.setEmail_id(rs.getString("email_id"));
						board.setB_code(rs.getInt("b_code"));
						
						boardlist.add(board);
					}while(rs.next());
				}else {
					System.out.println("조회 데이터 없음");
				}
				
			} catch (Exception e) {
				System.out.println("e: "+e.getMessage());
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
		
		//정해진 날짜의 출석판 총 게시글 수(출석판용)
		public int countAttendenceBoard(String startdate, String enddate) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count=0;
			
			try {
				
				conn = ds.getConnection();
				String sql = "select count(idx) cnt from board WHERE board.b_code = 2 and w_date between ? and ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, startdate);
				pstmt.setString(2, enddate);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt("cnt");
				}else {
					System.out.println("조회 데이터 없음");
				}
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
			
			return count;
		}
		
		//게시판 글 삭제(출석판용)
		public int delete_Board(int idx, String email_id) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			int row = 0;
			
			try {
				
				conn = ds.getConnection();
				conn.setAutoCommit(false);
				
				String sql = "delete from board where idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				row = pstmt.executeUpdate();
				
				if(row <= 0) {
					throw new Exception("board 삭제 실패");
				}
				
				String sql2 = "UPDATE user_details SET re_count = nvl(re_count - 1, 0) WHERE email_id=?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, email_id);
				row = pstmt2.executeUpdate();
				
				if(row <= 0) {
					throw new Exception("사용자 세부사항 수정 실패");
				}else {
					conn.commit();
				}
				
			} catch (Throwable e) {
				if(conn != null) {
					try {
						conn.rollback(); // 트랜잭션 실행 이전 상태로 돌리기
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			} finally {
				try {
					conn.setAutoCommit(true);
					pstmt2.close();
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
			
			return row;
		}
		
		//입력값에 따른, 보드 종류별 리스트 출력
		public List<Board> getBoardList(int b_code){
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Board> boardlist = new ArrayList<Board>();
			
			try {
				
				conn = ds.getConnection();
				String sql = "select * from\r\n"
						+ "(select rownum rn, idx, b_code, content, b_name, title, \"c_count\", nick, w_date, email_id, hits, \"like\",report_count,notic from \r\n"
						+ "(select b.idx,b.b_code, b.content, i.b_name, b.title, nvl(c.\"cnt\",0) as \"c_count\", b.nick,  to_char(b.w_date,'yyyy-MM-dd') as w_date, b.email_id, b.hits, nvl(l.\"cnt\",0) as \"like\" ,b.report_count, b.notic\r\n"
						+ "from board b left join (select count(email_id)as \"cnt\", idx from yes group by idx) l \r\n"
						+ "on l.idx = b.idx\r\n"
						+ "left join (select count(co_idx)as \"cnt\", idx from comments group by idx) c\r\n"
						+ "on c.idx = b.idx\r\n"
						+ "left join board_info i \r\n"
						+ "on b.b_code = i.b_code \r\n"
						+ "where i.b_code = ? order by b.idx desc))\r\n"
						+ "where rn BETWEEN 1 and 5";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, b_code);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					do {
						
						Board board = new AttendanceBoad();
						board.setIdx(rs.getInt("idx"));
						String title = rs.getString("title");
						if(title.length() > 10) {
							title = title.substring(0,10) + "...";
						}
						board.setTitle(title);
						board.setNick(rs.getString("nick"));
						board.setContent(rs.getString("content"));
						board.setHits(rs.getInt("hits"));
						board.setW_date(rs.getString("w_date"));
						board.setReport_count(rs.getInt("report_count"));
						board.setEmail_id(rs.getString("email_id"));
						board.setB_code(rs.getInt("b_code"));
						board.setC_count(rs.getInt("c_count"));
						board.setLike(rs.getInt("like"));
						
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
		
			public List<Board> viewchart(){
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Board> boardlist = new ArrayList<Board>();
			
			try {
				
				conn = ds.getConnection();
				String sql = "select count(idx) as b_cnt, sum(hits)as h_cnt from board group by b_code ORDER BY b_code asc";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					do {
						
						Board board = new AttendanceBoad();
						board.setB_code(rs.getInt("b_cnt"));//글의 수
						board.setC_count(rs.getInt("h_cnt"));//조회 수
						
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
			// 신고횟수 증가
			public int upReport(int idx) {
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				int row = 0;
				
				try {
					
					conn = ds.getConnection();
					String sql = " update Board set report_count =(report_count+1)  where idx=?";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, idx);
					
					
					row = pstmt.executeUpdate();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					try {
						 conn.setAutoCommit(true);
						pstmt.close();
						conn.close();
					} catch (Exception e2) {
						System.out.println(e2.getMessage());
					}
				}
				
				return row;
			}
}
