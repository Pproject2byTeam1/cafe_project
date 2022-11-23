package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Market_Board;
import kr.or.kosa.dto.Message;

public class Market_Board_Dao2 {

	DataSource ds = null;

	public Market_Board_Dao2() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	// 거래게시판 글 삽입 + 트랜잭션 처리
	public int WriteMarket(Market_Board market) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int row = 0;

		try {

			conn = ds.getConnection();

			conn.setAutoCommit(false);

			// Board 삽입
			String sql = "insert into board (idx,title,nick,content,email_id,b_code) "
					+ "values(idx_seq.nextval, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, market.getTitle());
			pstmt.setString(2, market.getNick());
			pstmt.setString(3, market.getContent());
			pstmt.setString(4, market.getEmail_id());
			pstmt.setInt(5, market.getB_code());

			row = pstmt.executeUpdate();
			if (row < 0) {
				throw new Exception("Board 삽입 실패");
			}

			// 거래게시판 삽입
			String sql2 = "insert into market_board(b_idx,idx,m_mode,cate,price,sold,img_name) "
					+ "values(market_idx_seq.nextval, idx_seq.currval, ?, ?, ?, ?, ?)";
			pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setString(1, "m_mode");
			pstmt2.setString(2, "cate");
			pstmt2.setString(3, "price");
			String sold = "";
			if (market.getSold().equals("판매중")) {
				sold = "F";
			} else if (market.getSold().equals("판매완료")) {
				sold = "T";
			} else if (market.getSold().equals("예약중")) {
				sold = "N";
			}
			pstmt2.setString(4, sold);
			pstmt2.setString(5, "img_name");

			row = pstmt2.executeUpdate();
			if (row < 0) {
				throw new Exception("거래게시판 작성 실패");
			} else {
				conn.commit();
			}

		} catch (Throwable e) {
			if (conn != null) {
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

//게시물 목록보기
	public List<Market_Board> ListMarket(int cpage, int pagesize) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Market_Board> boardlist = null;

		try {

			conn = ds.getConnection();
			String sql = "select * from (select rownum rn, b_idx, idx, img_name, m_mode, cate, price, sold from market_board) where rn <= ? and rn >= ?";
			pstmt = conn.prepareStatement(sql);

			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;

			pstmt.setInt(1, end);
			pstmt.setInt(2, start);

			rs = pstmt.executeQuery();

			boardlist = new ArrayList<Market_Board>();

			while (rs.next()) {
				Market_Board board = new Market_Board();
				board.setB_idx(rs.getInt("b_idx"));
				board.setIdx(rs.getInt("idx"));
				board.setImg_name(rs.getString("img_name"));
				board.setM_mode(rs.getString("m_mode"));
				board.setCate(rs.getString("cate"));
				board.setPrice(rs.getInt("price"));
				board.setSold(rs.getString("sold"));

				boardlist.add(board);
			}

		} catch (Exception e) {
			System.out.println("오류 :" + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return boardlist;
	}

	//게시물 총 건수 구하기
	public int CountMarket() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int countmarket = 0;
		try {
			conn = ds.getConnection(); // dbcp 연결객체 얻기
			String sql = "select count(*) b_idx from market_board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				countmarket = rs.getInt("b_idx");
			}
		} catch (Exception e) {

		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환 connection pool 에 반환하기
			} catch (Exception e) {

			}
		}
		return countmarket;
	}
	
	//판매 중인 거래글 카운트
	public int CountSoldF() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int countf = 0;
		try {
			conn = ds.getConnection(); // dbcp 연결객체 얻기
			String sql = "select count(*) from market_board where sold like 'F'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				countf = rs.getInt("sold");
			}
		} catch (Exception e) {

		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환 connection pool 에 반환하기
			} catch (Exception e) {

			}
		}
		return countf;
	}

	// 거래 게시판 글 읽기
	public List<Market_Board> ReadMarket(int b_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Market_Board> readmarket = null;

		try {
			conn = ds.getConnection();
			String sql = "select * "
					+ "from (select b.b_idx, m.sold, m.m_mode, m.cate, b.title, b.content, m.img_name, m.price, b.hits, b.nick, b.w_date, b.report_count, b.email_id, b.b_code"
					+ " from board b join market_board m"
					+ " on b.idx = m.idx"
					+ " order by b_idx desc) where b_idx=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_idx);

			rs = pstmt.executeQuery();
			
			readmarket = new ArrayList<Market_Board>();
			
			if (rs.next()) {
				Market_Board read = new Market_Board();
				
				read.setSold(rs.getString("sold"));
				read.setM_mode(rs.getString("m_mode")); 
				read.setCate(rs.getString("m.cate")); 
				read.setTitle(rs.getString("title"));  
				read.setContent(rs.getString("content")); 
				read.setImg_name(rs.getString("img_name")); 
				read.setPrice(rs.getInt("price")); 
				read.setHits(rs.getInt("hits")); 
				read.setNick(rs.getString("nick")); 
				read.setW_date(rs.getDate("w_date")); 
				read.setReport_count(rs.getInt("report_count")); 
				read.setEmail_id(rs.getString("email_id")); 
				read.setB_code(rs.getInt("b_code")); 

				readmarket.add(read);
			}

		} catch (Exception e) {
			System.out.println("content: " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환하기
			} catch (Exception e2) {

			}
		}

		return readmarket;
	}


	// 게시글 삭제하기
	public int deleteMarket(int idx, String email_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			// 비인증 ..
			// 삭제 > 비번
			// 처리 > 글번호 ,비번

			// 아이디검증
			String email = "select email_id from board where idx=?";

			// 두개의 테이블 (FK) : 자식부터 삭제 , 부모 삭제
			// jspboard(pk) , reply(fk:idx)
			// reply idx_fk=1 delete, jspboard idx=1 delete
			String comments = "delete from comments where idx_fk=?";

			// 게시글 삭제
			String market = "delete * from market_board where idx_fk=?";
			String board = "delete from board where idx_pk=?";

			pstmt = conn.prepareStatement(email);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 삭제글은 존재
				// 사용자의 이메일
				if (email.equals(rs.getString("email_id"))) {
					// 실 삭제 처리
					// 트랜잭션 (둘다 처리 , 둘다 실패)
					// 두개를 하나의 논리적 단위
					// JDBC : auto commit
					conn.setAutoCommit(false);// 개발자가 rollback , commit 강제
					// 댓글삭제
					pstmt = conn.prepareStatement(comments);
					pstmt.setInt(1, idx);
					pstmt.executeUpdate();

					// 게시글 삭제 (거래게시판)
					pstmt = conn.prepareStatement(market);
					pstmt.setInt(1, idx);
					pstmt = conn.prepareStatement(board);
					pstmt.setInt(1, idx);
					
					pstmt.executeUpdate();

					if (row > 0) {
						conn.commit(); // 두개의 delete 실반영
					}

				} else { // 비밀번호가 일치 하지 않는 경우
					row = -1;
				}
			} else { // 삭제하는 글이 존재하지 않는 경우
				row = 0;
			}

		} catch (Exception e) {
			// rollback
			// 예외가 발생하면
			try {
				conn.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환
			} catch (Exception e2) {

			}
		}
		return row;
	}

	// 댓글 입력하기 (Table reply : fk(jspboard idx) )
	public int replywrite(int idx_fk, String content, String email_id, String nick) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into comments(co_idx, idx_fk, content, email_id, nick, w_date, report_count, refer, depth, step)"
					+ " values(co_idx.nextval,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx_fk);
			
			pstmt.setString(1, writer);
			pstmt.setString(2, userid);
			pstmt.setString(3, content);
			pstmt.setString(4, pwd);
			pstmt.setInt(5, idx_fk);

			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();// 반환
			} catch (Exception e) {

			}
		}

		return row;
	}

	// 댓글 조회하기
	public List<Reply> replylist(String idx_fk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Reply> list = null;

		try {
			conn = ds.getConnection();
			String reply_sql = "select * from reply where idx_fk=? order by no desc";

			pstmt = conn.prepareStatement(reply_sql);
			pstmt.setString(1, idx_fk);

			rs = pstmt.executeQuery();

			list = new ArrayList<>();
			while (rs.next()) {
				int no = Integer.parseInt(rs.getString("no"));
				String writer = rs.getString("writer");
				String userid = rs.getString("userid");
				String pwd = rs.getString("pwd");
				String content = rs.getString("content");
				java.sql.Date writedate = rs.getDate("writedate");
				int idx = Integer.parseInt(rs.getString("idx_fk"));

				Reply replydto = new Reply(no, writer, userid, pwd, content, writedate, idx);
				list.add(replydto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환
			} catch (Exception e) {

			}
		}

		return list;
	}

	// 댓글 삭제하기
	public int replyDelete(String no, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;

		try {

			String replyselect = "select pwd from reply where no=?";
			String replydelete = "delete from reply where no=?";

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(replyselect);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbpwd = rs.getString("pwd");
				if (pwd.equals(dbpwd)) {
					pstmt.close();
					pstmt = conn.prepareStatement(replydelete);
					pstmt.setString(1, no);
					row = pstmt.executeUpdate();
				} else {
					row = 0;
				}
			} else {
				row = -1;
			}
		} catch (Exception e) {

		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환
			} catch (Exception e) {

			}
		}

		return row;
	}

	// 게시글 상세 (답글 쓰기)
	public int reWriteOk(Board boardata) {
		// content.jsp ->(답글)-> rewrite.jsp(입력) -> submit() -> rewriteok.jsp
		// 게시물 글쓰기(INSERT > 답글 ....) : refer , step , depth
		// 내가 답글을 달려하는 하는 글의 글번호가 필요해요

		// refer , step , depth 설정을 하려면 기존 정보(read 글)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ds.getConnection();

			int idx = boardata.getIdx(); // 현재 읽은 글의 글번호

			String writer = boardata.getWriter();
			String email = boardata.getEmail();
			String homepage = boardata.getHomepage();
			String pwd = boardata.getPwd();
			String subject = boardata.getSubject();
			String content = boardata.getContent();
			String filename = boardata.getFilename();
			int filesize = 0;

			// 1. 답글
			// 현재 내가 읽은 글의 refer , depth , step (원본글 ,답글)
			String refer_depth_step_sal = "select refer , depth , step from jspboard where idx=?";

			// 2. 위치
			// step (순서) : 나중에 쓴 답글이 위로 올라오게 하겠다
			// 내가 읽은 글의 step 보다 큰 값은 +1 해서 증가 시켜 놓는다
			// refer 값으로 판단
			// ex) 원본글 refer=1 , step=0 , depth=0
			// 원본글답글 refer=1 , step=1+1 >2 >3, depth=1
			// 원본글답글 refer=1 , step=1>2 , depth=1
			// 원본글답글 refer=1 , step=0+1>1
			String step_update_sql = "update jspboard set step= step+1 where step  > ? and refer =? ";
			// "update jspboard set step= step+1 where step > 0 and refer =1 ";

			// 답글 insert
			String rewrite_sql = "insert into jspboard(idx,writer,pwd,subject,content,email,homepage,writedate,readnum,filename,filesize,refer,depth,step)"
					+ " values(jspboard_idx.nextval,?,?,?,?,?,?,sysdate,0,?,0,?,?,?)";

			pstmt = conn.prepareStatement(refer_depth_step_sal);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();

			if (rs.next()) { // 데이터가 있다면 ... 원본글의 refer , step , depth 존재
				int refer = rs.getInt("refer");
				int step = rs.getInt("step");
				int depth = rs.getInt("depth");

				pstmt = conn.prepareStatement(step_update_sql); // 컴파일
				// 기존 step + 1 >> update 구문 실행
				pstmt.setInt(1, step);
				pstmt.setInt(2, refer);
				pstmt.executeUpdate();

				// filename,filesize,refer,depth,step
				pstmt = conn.prepareStatement(rewrite_sql); // 컴파일
				pstmt.setString(1, writer);
				pstmt.setString(2, pwd);
				pstmt.setString(3, subject);
				pstmt.setString(4, content);
				pstmt.setString(5, email);
				pstmt.setString(6, homepage);
				pstmt.setString(7, filename);

				// 답변
				pstmt.setInt(8, refer);
				pstmt.setInt(9, depth + 1); // 규칙 현재 읽은 글에 depth + 1
				pstmt.setInt(10, step + 1); // 순서 update 통해서 자리 확보 + 1

				int row = pstmt.executeUpdate();
				if (row > 0) {
					result = row;
				} else {
					result = -1;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환
			} catch (Exception e) {

			}
		}

		return result;
	}

	// 게시글 수정하기 화면(답글)
	public Board getEditContent(String idx) {
		return this.getContent(Integer.parseInt(idx));
		// 조회화면 동일 (기존에 있는 함수 재활용)
	}

	// 게시글 수정하기 처리
	// public int boardEdit(Board boarddata){}
	public int boardEdit(HttpServletRequest boarddata) {
		String idx = boarddata.getParameter("idx");
		String pwd = boarddata.getParameter("pwd");
		String writer = boarddata.getParameter("writer");
		String email = boarddata.getParameter("email");
		String homepage = boarddata.getParameter("homepage");
		String subject = boarddata.getParameter("subject");
		String content = boarddata.getParameter("content");
		String filename = boarddata.getParameter("filename");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;

		try {
			conn = ds.getConnection();
			String sql_idx = "select idx  from jspboard where idx=? and pwd=?";
			String sql_udpate = "update jspboard set writer=? , email=? , homepage=? ,"
					+ " subject=? , content=? , filename=? where idx=?";
			pstmt = conn.prepareStatement(sql_idx);
			pstmt.setString(1, idx);
			pstmt.setString(2, pwd);

			rs = pstmt.executeQuery();
			// 판단 (데이터 있다며 : 수정가능 , 없다면 : 수정불가
			if (rs.next()) {
				// 경고
				pstmt.close();
				// 업데이트
				pstmt = conn.prepareStatement(sql_udpate);
				pstmt.setString(1, writer);
				pstmt.setString(2, email);
				pstmt.setString(3, homepage);
				pstmt.setString(4, subject);
				pstmt.setString(5, content);
				pstmt.setString(6, filename);
				pstmt.setString(7, idx);
				row = pstmt.executeUpdate();
				// System.out.println("row : " + row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환
			} catch (Exception e2) {

			}
		}

		return row;
	}

}
