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

import kr.or.kosa.dto.Comments;

//댓글
public class CommentsDao {

	DataSource ds = null;

	public CommentsDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//특정 게시글에 대한 댓글 전체 조회
	public List<Comments> getCommentListByIdx(int idx){
		
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
			
			if(rs.next()) {
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
		
		return commentslist;
	}

	
	//댓글 삽입
	public int insertComments(Comments comments) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "insert into comments(idx, content, email_id, nick, refer, depth, step)"
						+ "values(?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comments.getIdx());
			pstmt.setString(2, comments.getContent());
			pstmt.setString(3, comments.getEmail_id());
			pstmt.setString(4, comments.getNick());
			pstmt.setInt(5, row);
			
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
				
				if(rs.next()) {
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
		
		//자신이 쓴 댓글 조회
		public List<Comments> getCommentListByMe(String email_id){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Comments> clist = null;
			
			try {
				
				conn = ds.getConnection();
				String sql = "select c.co_idx ,b.idx, b.title,nvl(c2.\"cnt\",0) as \"c_count\", c.content, to_char(c.w_date,'yyyy-MM-dd') as w_date \r\n"
						+ "from comments c join board b \r\n"
						+ "on c.idx = b.idx \r\n"
						+ "left join (select count(co_idx)as \"cnt\", idx from comments group by idx) c2\r\n"
						+ "on c2.idx = b.idx\r\n"
						+ "where c.EMAIL_ID = ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, email_id);
				rs = pstmt.executeQuery();
				clist = new ArrayList<Comments>();
				
				while(rs.next()) {
					Comments comment = new Comments();
					comment.setCo_idx(rs.getInt("co_idx"));
					comment.setIdx(rs.getInt("idx"));
					String str = rs.getString("content");
					if(str.length() < 15) {
					comment.setContent(rs.getString("content"));
					}else {
						comment.setContent(str.substring(0,14) + " ...");
					}
					comment.setW_date(rs.getString("w_date"));
					
					clist.add(comment);
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
			
			return clist;
		}
}
