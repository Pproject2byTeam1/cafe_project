package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.kosa.dto.Comments;

//댓글
public class CommentsDao {

	DataSource ds = null;

	public CommentsDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	// 특정 게시글에 대한 댓글 전체 조회
	public List<Comments> getCommentListByIdx(int idx) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Comments> commentslist = new ArrayList<Comments>();

		try {

			conn = ds.getConnection();
			String sql = "select co_idx, idx, content, email_id, nick, to_char(w_date, 'yyyy-MM-dd') as w_date, report_count, refer, depth, step "
					+ "from Comments where idx=? order by refer desc, step asc";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {

					Comments comments = new Comments();
					comments.setCo_idx(rs.getInt("co_idx"));
					comments.setIdx(rs.getInt("idx"));
					comments.setContent(rs.getString("content"));
					comments.setEmail_id(rs.getString("email_id"));
					comments.setNick(rs.getString("nick"));
					comments.setW_date(rs.getString("w_date"));
					comments.setReport_count(rs.getInt("report_count"));
					comments.setRefer(rs.getInt("refer"));
					comments.setDepth(rs.getInt("depth"));
					comments.setStep(rs.getInt("step"));

					commentslist.add(comments);
				} while (rs.next());
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

		return commentslist;
	}

	// 댓글 삽입
	public int insertComments(Comments comments) {

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

			String sql = "INSERT INTO comments (co_idx, idx, content, email_id, nick, refer) "
					+ "VALUES (CO_IDX_SEQ.nextval, ?, ?, ?, ?, CO_IDX_SEQ.currval)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comments.getIdx());
			pstmt.setString(2, comments.getContent());
			pstmt.setString(3, comments.getEmail_id());
			pstmt.setString(4, comments.getNick());

			row = pstmt.executeUpdate();

			if (row <= 0) {
				throw new Exception("comments 삽입 실패");
			}

			String sql2 = "UPDATE user_details SET re_count = nvl(re_count + 1, 0) WHERE email_id=?";
			pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setString(1, comments.getEmail_id());

			row = pstmt2.executeUpdate();

			// 해당 유저의 포인트 조회
			String sql4 = "select point from member where email_id = ?";
			pstmt4 = conn.prepareStatement(sql4);
			pstmt4.setString(1, comments.getEmail_id());
			rs = pstmt4.executeQuery();

			int point = 0;

			if (rs.next()) {
				point = rs.getInt("point");
			}

			point += 2;

			// 해당 유저 포인트 적립
			String sql3 = "update member set point = nvl(point + 2, 0) where email_id=?";
			pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, comments.getEmail_id());
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
			pstmt6.setString(2, comments.getEmail_id());
			row = pstmt6.executeUpdate();

			if (row <= 0) {
				throw new Exception("user_detals update 실패");
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
				e2.printStackTrace();
			}
		}

		return row;
	}

	// 댓글 삭제

	public int userdeleteCommentByCo_idx(int co_idx, String email_id) {

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

			// 해당 유저의 포인트 조회
			String sql4 = "select point from member where email_id = ?";
			pstmt4 = conn.prepareStatement(sql4);
			pstmt4.setString(1, email_id);
			rs = pstmt4.executeQuery();
			
			
			int point = 0;

			if (rs.next()) {
				point = rs.getInt("point");
			}

			point -= 2;
			
			
			//해당 유저 포인트 감소
			String sql3 = "update member set point = ? where email_id=?";
			pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setInt(1, point);
			pstmt3.setString(2, email_id);
			row = pstmt3.executeUpdate();
			
			//포인트 정보 가져오기
			String sql5 = "select r_point from rank where rank >= 1";
			pstmt5 = conn.prepareStatement(sql5);
			rs1 = pstmt5.executeQuery();
			
			List<Integer> pointlist = new ArrayList<Integer>();

			if (rs1.next()) {
				do {
					pointlist.add(rs1.getInt("r_point"));
				} while (rs1.next());
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
			pstmt6.setString(2, email_id);
			row = pstmt6.executeUpdate();

			// 글 삭제

			String sql = "update comments set content = '작성자가 삭제한 댓글입니다.' where co_idx = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, co_idx);

			row = pstmt.executeUpdate();

			if (row <= 0) {
				throw new Exception("comments 삭제 실패");
			}

			String sql2 = "UPDATE user_details SET re_count = nvl(re_count - 1, 0) WHERE email_id=?";
			pstmt2 = conn.prepareStatement(sql2);

			pstmt2.setString(1, email_id);

			row = pstmt2.executeUpdate();

			if (row <= 0) {
				throw new Exception("user_detals update 실패");
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


	// 대댓글 삽입
		public int insertReplyReply(Comments comments) {

			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmt3 = null;
			PreparedStatement pstmt4 = null;
			PreparedStatement pstmt5 = null;
			PreparedStatement pstmt6 = null;
			PreparedStatement pstmt7 = null;
			ResultSet rs = null;
			ResultSet rs1 = null;
			int row = 0;

			try {

				conn = ds.getConnection();
				conn.setAutoCommit(false);

				String sql = "INSERT INTO comments (co_idx, idx, content, email_id, nick, refer, depth, step) "
						+ "VALUES (CO_IDX_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, comments.getIdx());
				pstmt.setString(2, comments.getContent());
				pstmt.setString(3, comments.getEmail_id());
				pstmt.setString(4, comments.getNick());
				pstmt.setInt(5, comments.getRefer());
				pstmt.setInt(6, comments.getDepth() + 1);
				pstmt.setInt(7, comments.getStep() + 1);

				row = pstmt.executeUpdate();

				if (row <= 0) {
					throw new Exception("comments 삽입 실패");
				}

				String sql2 = "UPDATE user_details SET re_count = nvl(re_count + 1, 0) WHERE email_id=?";
				pstmt2 = conn.prepareStatement(sql2);

				pstmt2.setString(1, comments.getEmail_id());

				row = pstmt2.executeUpdate();

				if (row <= 0) {
					throw new Exception("user_detals update 실패");
				}

				String sql3 = "UPDATE comments set step = nvl(step + 1, 0) where refer=?";
				pstmt3 = conn.prepareStatement(sql3);

				pstmt3.setInt(1, comments.getRefer());

				row = pstmt3.executeUpdate();

				// 해당 유저의 포인트 조회
				String sql4 = "select point from member where email_id = ?";
				pstmt4 = conn.prepareStatement(sql4);
				pstmt4.setString(1, comments.getEmail_id());
				rs = pstmt4.executeQuery();

				int point = 0;

				if (rs.next()) {
					point = rs.getInt("point");
				}

				point += 2;

				// 해당 유저 포인트 적립
				String sql7 = "update member set point = nvl(point + 10, 0) where email_id=?";
				pstmt7 = conn.prepareStatement(sql7);
				pstmt7.setString(1, comments.getEmail_id());
				row = pstmt7.executeUpdate();

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
				pstmt6.setString(2, comments.getEmail_id());
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
					pstmt2.close();
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}

			return row;
		}


	// 댓글개수 int로 출력 (파라미터: idx값)
	public int getCommentCountBy_idx(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalcount = 0;

		try {

			conn = ds.getConnection();
			String sql = "select count(*) cnt from comments where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalcount = rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return totalcount;
	}

	// 자신이 쓴 댓글 조회
	public List<Comments> getCommentListByMe(String email_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Comments> clist = null;

		try {

			conn = ds.getConnection();
			String sql = "select c.co_idx ,b.idx, b.b_code, b.title,nvl(c2.\"cnt\",0) as \"c_count\", c.content, to_char(c.w_date,'yyyy-MM-dd') as w_date \r\n"
					+ "from comments c join board b \r\n" + "on c.idx = b.idx \r\n"
					+ "left join (select count(co_idx)as \"cnt\", idx from comments group by idx) c2\r\n"
					+ "on c2.idx = b.idx\r\n" + "where c.EMAIL_ID = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email_id);
			rs = pstmt.executeQuery();
			clist = new ArrayList<Comments>();

			while (rs.next()) {
				Comments comment = new Comments();
				comment.setCo_idx(rs.getInt("co_idx"));
				comment.setIdx(rs.getInt("idx"));
				comment.setEmail_id(rs.getString("title"));// 임시방편으로 다른 String에다 넣음
				comment.setDepth(rs.getInt("c_count"));// 임시방편으로 다른 int에다 넣음
				comment.setRefer(rs.getInt("b_code"));// 임시방편으로 다른 int에다 넣음
				String str = rs.getString("content");
				if (str.length() < 15) {
					comment.setContent(rs.getString("content"));
				} else {
					comment.setContent(str.substring(0, 14) + " ...");
				}
				comment.setW_date(rs.getString("w_date"));

				clist.add(comment);
			}

		} catch (Exception e) {
			System.out.println("오류 :" + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환
			} catch (Exception e2) {

			}
		}

		return clist;
	}
}
