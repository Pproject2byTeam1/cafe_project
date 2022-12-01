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

import kr.or.kosa.dto.DataBoard;

public class DataBoardDao {
	DataSource ds = null;

	public DataBoardDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	public List<DataBoard> getdata_boardList(int b_code, int cpage, int pagesize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DataBoard> boardlist = null;

		try {
			conn = ds.getConnection();
			String sql = "select * " + "from (select * "
					+ "from (select ROW_NUMBER() OVER(ORDER BY b_idx desc) AS rn, b.idx, b.title, b.nick, b.content, b.hits, to_char(b.w_date, 'yyyy-MM-dd') as w_date, b.report_count, b.notic, b.email_id, b.b_code, d.ori_name, d.refer, d.depth, d.step "
					+ "from board b join data_board d " + "on b.idx = d.idx " + "where b.b_code = ? "
					+ "order by refer desc, step asc) " + "where rn >= ?) " + "where rn <= ?";
			pstmt = conn.prepareStatement(sql);

			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;

			pstmt.setInt(1, b_code);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			boardlist = new ArrayList<DataBoard>();

			while (rs.next()) {
				DataBoard board = new DataBoard();
				board.setIdx(rs.getInt("idx"));
				board.setTitle(rs.getString("title"));
				board.setNick(rs.getString("nick"));
				board.setContent(rs.getString("content"));
				board.setHits(rs.getInt("hits"));
				board.setW_date(rs.getString("w_date"));
				board.setReport_count(rs.getInt("report_count"));
				board.setEmail_id(rs.getString("email_id"));
				board.setB_code(rs.getInt("b_code"));
				board.setOri_name(rs.getString("ori_name"));
				board.setRefer(rs.getInt("refer"));
				board.setDepth(rs.getInt("depth"));
				board.setStep(rs.getInt("step"));

				boardlist.add(board);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환
			} catch (Exception e2) {

			}
		}

		return boardlist;
	}

	// 자료 게시판 특정 글 조회
	public DataBoard getData_BoardByIdx(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataBoard board = new DataBoard();

		try {

			conn = ds.getConnection();
			String sql = "select b.idx, b.title, b.nick, b.content, b.hits, to_char(b.w_date, 'YYYY-MM-dd') w_date, b.report_count, b.notic, b.email_id, b.b_code, i.ori_name, i.save_name, i.volume, i.refer, i.depth, i.step "
					+ "from board b join data_board i " + "on b.idx = i.idx " + "where b.idx = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				board.setIdx(rs.getInt("idx"));
				board.setTitle(rs.getString("title"));
				board.setNick(rs.getString("nick"));
				board.setContent(rs.getString("content"));
				board.setHits(rs.getInt("hits"));
				board.setW_date(rs.getString("w_date"));
				board.setEmail_id(rs.getString("email_id"));
				board.setReport_count(rs.getInt("report_count"));
				board.setNotic(rs.getString("notic"));
				board.setEmail_id(rs.getString("email_id"));
				board.setOri_name(rs.getString("ori_name"));
				board.setSave_name(rs.getString("save_name"));

				board.setVolume(rs.getInt("volume"));
				board.setRefer(rs.getInt("refer"));
				board.setDepth(rs.getInt("depth"));
				board.setStep(rs.getInt("step"));

			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return board;
	}

	// 전체 자료게시판 조회
	public List<DataBoard> getAllDatalist(int b_code, int cpage, int pagesize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DataBoard> datalist = null;

		try {

			conn = ds.getConnection();
			String sql = "select *"
					+ "from (select rownum rn, b.idx, b.title, b.nick, b.content, b.hits, b.w_date,d.depth,d.step,d.refer "
					+ "        from board b join data_board d" + "        on b.idx = d.idx" + "        where b_code=? "
					+ "        order by refer desc, step desc) where rn <= ? and rn >= ?";
			pstmt = conn.prepareStatement(sql);

			datalist = new ArrayList<DataBoard>();
			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;
			pstmt.setInt(1, b_code);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				DataBoard data = new DataBoard();

				data.setIdx(rs.getInt("idx"));
				data.setTitle(rs.getString("title"));
				data.setNick(rs.getString("nick"));
				data.setContent(rs.getString("content"));
				data.setHits(rs.getInt("hits"));
				data.setW_date(rs.getString("w_date"));

				// 계층형
				data.setRefer(rs.getInt("refer"));
				data.setStep(rs.getInt("step"));
				data.setDepth(rs.getInt("depth"));

				datalist.add(data);

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

		return datalist;
	}

	// 자료게시판 글 삽입 + 트랜잭션 처리
	public int writeData(DataBoard data) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int row = 0;

		try {

			conn = ds.getConnection();
			conn.setAutoCommit(false);

			// Board 삽입
			String sql = "INSERT ALL INTO board (idx, title, nick, content, email_id, b_code) "
					+ "VALUES (IDX_SEQ.nextval, ?, ?, ?, ?, ?) INTO data_board (b_idx, idx, ori_name, save_name, volume, refer) "
					+ "VALUES (DATA_IDX_SEQ.nextval, IDX_SEQ.currval, ?, ?, ?, ?) select * from dual";

			// 유저 작성글 수 증가
			String sql2 = "UPDATE user_details SET w_count = nvl(w_count + 1, 0) WHERE email_id=?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, data.getEmail_id());
			pstmt2.executeUpdate();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, data.getTitle());
			pstmt.setString(2, data.getNick());
			pstmt.setString(3, data.getContent());
			pstmt.setString(4, data.getEmail_id());
			pstmt.setInt(5, data.getB_code());
			pstmt.setString(6, data.getOri_name());
			pstmt.setString(7, data.getSave_name());
			pstmt.setInt(8, data.getVolume());

			int refermax = getMaxRefer();
			int refer = refermax + 1;
			pstmt.setInt(9, refer);

			row = pstmt.executeUpdate();

			// 해당 유저의 포인트 조회
			String sql4 = "select point from member where email_id = ?";
			pstmt4 = conn.prepareStatement(sql4);
			pstmt4.setString(1, data.getEmail_id());
			rs = pstmt4.executeQuery();

			int point = 0;

			if (rs.next()) {
				point = rs.getInt("point");
			}

			point += 10;

			// 해당 유저 포인트 적립
			String sql3 = "update member set point = nvl(point + 10, 0) where email_id=?";
			pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, data.getEmail_id());
			row = pstmt3.executeUpdate();

			// 포인트 정보 가져오기
			String sql5 = "select r_point from rank where rank >= 1";
			pstmt5 = conn.prepareStatement(sql5);
			rs1 = pstmt5.executeQuery();

			List<Integer> pointlist = new ArrayList<Integer>();

			if (rs1.next()) {
				do {
					int number = rs1.getInt("r_point");

					pointlist.add(number);
				} while (rs1.next());
			}
			int rank = 0;
			for (int i = 0; i < pointlist.size() - 1; i++) {
				int min = pointlist.get(i);
				int max = pointlist.get(i + 1);

				if (point < max) {
					if (point >= min)
						rank = i + 1;
				}
			}

			// 해당 회원의 rank 수정
			String sql6 = "update member set rank = ? where email_id = ?";
			pstmt6 = conn.prepareStatement(sql6);
			pstmt6.setInt(1, rank);
			pstmt6.setString(2, data.getEmail_id());
			row = pstmt6.executeUpdate();

			if (row < 0) {
				throw new Exception("자료게시판 작성 실패");
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
				rs1.close();
				rs.close();
				pstmt5.close();
				pstmt4.close();
				pstmt3.close();
				pstmt2.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return row;
	}

	// 자료게시판 답글 삽입
	public int rewriteData(DataBoard data) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int row = 0;

		try {

			conn = ds.getConnection();
			conn.setAutoCommit(false);

			// Board 삽입
			String sql = "INSERT ALL INTO board (idx, title, nick, content, email_id, b_code) "
					+ "VALUES (IDX_SEQ.nextval, ?, ?, ?, ?, ?) INTO data_board (b_idx, idx, ori_name, save_name, volume, refer,depth,step) "
					+ "VALUES (DATA_IDX_SEQ.nextval, IDX_SEQ.currval, ?, ?, ?, ?,?,?) select * from dual";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, data.getTitle());
			pstmt.setString(2, data.getNick());
			pstmt.setString(3, data.getContent());
			pstmt.setString(4, data.getEmail_id());
			pstmt.setInt(5, data.getB_code());
			pstmt.setString(6, data.getOri_name());
			pstmt.setString(7, data.getSave_name());
			pstmt.setInt(8, data.getVolume());

			int refermax = getMaxRefer();
			int refer = refermax + 1;
			pstmt.setInt(9, refer);

			row = pstmt.executeUpdate();

			if (row < 0) {
				throw new Exception("Board 삽입 실패");
			}

			if (row < 0) {
				throw new Exception("자료게시판 작성 실패");
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
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return row;
	}

	// 글쓰기 (refer) 값 생성하기(원본글)
	private int getMaxRefer() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int refer_max = 0;

		try {

			conn = ds.getConnection(); // 빌려주세여^^ 이따 반납할게요
			String sql = "select nvl(max(refer),0) from data_board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				refer_max = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close(); // 반납이요 ^^
			} catch (Exception e) {

			}
		}

		return refer_max;

	}

	// 좋아요 개수
	public int getYes(int idx) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int yes_max = 0;

		try {
			conn = ds.getConnection();
			String sql = "select  count(*) as cnt from yes where idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				yes_max = rs.getInt("cnt");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e) {

			}
		}

		return yes_max;

	}

	// 댓글개수

	public int getComments(int idx1) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int co_max = 0;

		try {
			conn = ds.getConnection();
			String sql = "select count(*) as cnt from comments where idx=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				co_max = rs.getInt("cnt");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e) {

			}
		}

		return co_max;

	}

	// 데이터 게시판 답글 삽입
	public int insertData_BoardReply(DataBoard databoard) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt7 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int row = 0;

		try {

			conn = ds.getConnection();
			conn.setAutoCommit(false);

			String sql = "INSERT ALL " + "INTO board (idx, title, nick, content, email_id, b_code) "
					+ "VALUES (IDX_SEQ.nextval, ?, ?, ?, ?, ?) "
					+ "INTO data_board (b_idx, idx, ori_name, save_name, volume, refer, depth, step) "
					+ "VALUES (DATA_IDX_SEQ.nextval, IDX_SEQ.currval, ?, ?, ?, ?, ?, ?) " + "select * from dual";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, databoard.getTitle());
			pstmt.setString(2, databoard.getNick());
			pstmt.setString(3, databoard.getContent());
			pstmt.setString(4, databoard.getEmail_id());
			pstmt.setInt(5, databoard.getB_code());
			pstmt.setString(6, databoard.getOri_name());
			pstmt.setString(7, databoard.getSave_name());
			pstmt.setInt(8, databoard.getVolume());
			pstmt.setInt(9, databoard.getRefer());
			pstmt.setInt(10, databoard.getDepth() + 1);
			pstmt.setInt(11, databoard.getStep() + 1);

			row = pstmt.executeUpdate();

			if (row <= 0) {
				throw new Exception("board 삽입 실패");
			}

			String sql2 = "UPDATE user_details SET re_count = nvl(re_count + 1, 0) WHERE email_id=?";
			pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setString(1, databoard.getEmail_id());

			row = pstmt2.executeUpdate();

			if (row <= 0) {
				throw new Exception("user_detals update 실패");
			}

			String sql3 = "UPDATE data_board set step = nvl(step + 1, 0) where refer=?";
			pstmt3 = conn.prepareStatement(sql3);

			pstmt3.setInt(1, databoard.getRefer());

			row = pstmt3.executeUpdate();

			// 해당 유저의 포인트 조회
			String sql4 = "select point from member where email_id = ?";
			pstmt4 = conn.prepareStatement(sql4);
			pstmt4.setString(1, databoard.getEmail_id());
			rs = pstmt4.executeQuery();

			int point = 0;

			if (rs.next()) {
				point = rs.getInt("point");
			}

			point += 10;

			// 해당 유저 포인트 적립
			String sql7 = "update member set point = nvl(point + 10, 0) where email_id=?";
			pstmt7 = conn.prepareStatement(sql7);
			pstmt7.setString(1, databoard.getEmail_id());
			row = pstmt7.executeUpdate();

			// 포인트 정보 가져오기
			String sql5 = "select r_point from rank where rank >= 1";
			pstmt5 = conn.prepareStatement(sql5);
			rs1 = pstmt5.executeQuery();

			List<Integer> pointlist = new ArrayList<Integer>();

			if (rs.next()) {
				do {
					pointlist.add(rs.getInt("r_point"));
				} while (rs.next());
			}
			int rank = 0;
			for (int i = 0; i < pointlist.size() - 1; i++) {
				int min = pointlist.get(i);
				int max = pointlist.get(i + 1);

				if (point < max && point >= min) {
					rank = i + 1;
				}
			}

			// 해당 회원의 rank 수정
			String sql6 = "update member set rank = ? where email_id = ?";
			pstmt6 = conn.prepareStatement(sql6);
			pstmt6.setInt(1, rank);
			pstmt6.setString(2, databoard.getEmail_id());
			row = pstmt6.executeUpdate();

			if (row <= 0) {
				throw new Exception("comments depth, step update 실패");
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
				rs1.close();
				rs.close();
				pstmt5.close();
				pstmt4.close();
				pstmt3.close();
				pstmt2.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return row;
	}

	// 개시글 삭제하기
	public int deleteDataBoard(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int row = 0;

		try {

			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			//해당 유저의 포인트 조회
			String sql4 = "select point from member where email_id = (select email_id from board where idx=?)";
			pstmt4 = conn.prepareStatement(sql4);
			pstmt4.setInt(1, idx);
			rs = pstmt4.executeQuery();
			
			int point = 0;
			
			if(rs.next()) {
				point = rs.getInt("point");
			}
			
			point -= 10;
			
			//해당 유저 포인트 감소
			String sql3 = "update member set point = nvl(point - 10, 0) where email_id=(select email_id from board where idx=?)";
			pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setInt(1, idx);
			row = pstmt3.executeUpdate();
			
			//포인트 정보 가져오기
			String sql5 = "select r_point from rank where rank >= 1";
			pstmt5 = conn.prepareStatement(sql5);
			rs1 = pstmt5.executeQuery();
			
			List<Integer> pointlist = new ArrayList<Integer>();
		
			if(rs1.next()) {
				do {
					pointlist.add(rs1.getInt("r_point"));
				}while(rs1.next());
			}
			int rank = 0;
			for(int i=0; i<pointlist.size()-1; i++) {
				int min = pointlist.get(i);
				int max = pointlist.get(i+1);
				
				if(point < max && point >= min) {
					rank = i+1;
				}
			}
			
			//해당 회원의 rank 수정
			String sql6 = "update member set rank = ? where email_id = (select email_id from board where idx=?)";
			pstmt6 = conn.prepareStatement(sql6);
			pstmt6.setInt(1, rank);
			pstmt6.setInt(2, idx);
			row = pstmt6.executeUpdate();

			//글 삭제

			String sql = "delete from board where idx=? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

			row = pstmt.executeUpdate();

			if (row < 0) {
				throw new Exception("Board 삭제 실패");
			}

			String sql2 = "delete data_board where idx=?";
			pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setInt(1, idx);

			row = pstmt.executeUpdate();

			if (row < 0) {
				throw new Exception("data_baord 수정 실패");
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
				rs1.close();
				rs.close();
				pstmt5.close();
				pstmt4.close();
				pstmt3.close();
				pstmt2.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return row;
	}

	// 객채로 title content 수정
	public int updateDataBoardTitle(DataBoard board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int row = 0;

		try {

			conn = ds.getConnection();
			conn.setAutoCommit(false);

			String sql = "UPDATE board SET title =?, content=? WHERE idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getIdx());

			row = pstmt.executeUpdate();

			if (row < 0) {
				throw new Exception("Board 수정 실패");
			}

			String sql2 = "UPDATE data_board set ori_name=?, save_name=?, volume=? where idx=?";
			pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setString(1, board.getOri_name());
			pstmt2.setString(2, board.getSave_name());
			pstmt2.setInt(3, board.getVolume());
			pstmt2.setInt(4, board.getIdx());

			row = pstmt2.executeUpdate();

			if (row < 0) {
				throw new Exception("data_baord 수정 실패");
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
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return row;
	}

	// 대댓글
	public int reWriteOk(DataBoard board) {

		// refer , step , depth 설정을 하려면 기존 정보(read 글)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int result = 0;
		try {
			conn = ds.getConnection();

			int idx = board.getIdx();
			// idx,title, nick ,content,email_id ,b_code
			// b_code ori_name, save_name, volume refer

			// 1.현제 내가 읽은 글의 refer, depth ,step
			String refer_depth_step_sal = "select refer, depth ,step from data_baord where idx = ?";

			// step업데이트
			String step_update_sql = "update data_board set step= step+1 where step  > ? and refer =? ";

			// 대댓글
			String rewrite_sql = "INSERT ALL INTO board (idx, title, nick, content, email_id, b_code) "
					+ "VALUES (IDX_SEQ.nextval, ?, ?, ?, ?, ?) INTO data_board (b_idx, idx, ori_name, save_name, volume, refer,depth,step) "
					+ "VALUES (DATA_IDX_SEQ.nextval, IDX_SEQ.currval, ?, ?, ?, ?,?,?) select * from dual";

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
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getNick());
				pstmt.setString(3, board.getContent());
				pstmt.setString(4, board.getEmail_id());
				pstmt.setInt(5, board.getB_code());
				pstmt.setString(6, board.getOri_name());
				pstmt.setString(7, board.getSave_name());
				pstmt.setInt(8, board.getVolume());

				pstmt.setInt(9, refer);
				pstmt.setInt(10, depth + 1);
				pstmt.setInt(11, step + 1);

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
}