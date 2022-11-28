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
import kr.or.kosa.dto.DataBoard;
import kr.or.kosa.dto.MarketBoard;
import kr.or.kosa.utils.tagRemove;

public class MarketBoardDao {

	DataSource ds = null;

	public MarketBoardDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	// 거래게시판 글 삽입 + 트랜잭션 처리
	public int writeMarket(MarketBoard market) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int row = 0;

		try {

			conn = ds.getConnection();

			// Board 삽입
			String sql = "INSERT ALL INTO board (idx, title, nick, content, email_id, b_code) "
		            + "VALUES (IDX_SEQ.nextval, ?, ?, ?, ?, ?) INTO market_board (b_idx, idx, m_mode, cate, price, sold, img_name) " 
		            + "VALUES (MARKET_IDX_SEQ.nextval, IDX_SEQ.currval, ?, ?, ?, ?, ?) select * from dual";
			
			// 유저 작성글 수 증가
			String sql2 = "UPDATE user_details SET w_count = nvl(w_count + 1, 0) WHERE email_id=?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, market.getEmail_id());
			pstmt2.executeUpdate();
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, market.getTitle());
			pstmt.setString(2, market.getNick());
			pstmt.setString(3, market.getContent());
			pstmt.setString(4, market.getEmail_id());
			pstmt.setInt(5, market.getB_code());
			pstmt.setString(6, market.getM_mode());
			pstmt.setString(7, market.getCate());
			pstmt.setInt(8, market.getPrice());
			pstmt.setString(9, market.getSold());
			pstmt.setString(10, market.getImg_name());
			
			row = pstmt.executeUpdate();
			
			if (row < 0) {
				throw new Exception("Board 삽입 실패");
			}

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
				pstmt.close();
				pstmt2.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return row;
	}

//거래게시물 목록보기
	public List<MarketBoard> listMarket(int b_code, int cpage, int pagesize) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MarketBoard> list = null;

		try {

			conn = ds.getConnection();
			String sql = "select * " +
							"from (select * " +
						    "from (select rownum rn, b.idx , m.b_idx , m.sold, m.m_mode, m.cate, " +
						    "b.title, b.content, m.img_name, m.price, b.hits, b.nick, b.w_date, " +  
						    "b.report_count, b.email_id, b.b_code " +
						    "from board b join market_board m on b.idx=m.idx order by b_idx desc) " +
						    "where b_code=? and rownum <= ?) " +
						    "where rn >=?";
			pstmt = conn.prepareStatement(sql);

			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;
			pstmt.setInt(1, b_code);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			
			System.out.println("출력 시작번호 : " + start);
			System.out.println("출력 끝번호 : " + end);
			
			rs = pstmt.executeQuery();
			
			tagRemove tag = new tagRemove();
			list = new ArrayList<MarketBoard>();

			while (rs.next()) {
				MarketBoard board = new MarketBoard();
				board.setIdx(rs.getInt("idx"));
				board.setB_idx(rs.getInt("b_idx"));
				board.setB_code(b_code);
				board.setSold(rs.getString("sold"));
				board.setM_mode(rs.getString("m_mode"));
				board.setCate(rs.getString("cate"));
				board.setTitle(rs.getString("title"));
				//태그제거 정규표현식 + 글자수 제한
				//board.setContent(tag.htmlTagRemoveString(rs.getString("content")));
				board.setContent(rs.getString("content"));
				board.setImg_name(rs.getString("img_name"));
				board.setPrice(rs.getInt("price"));
				board.setHits(rs.getInt("hits"));
				board.setNick(rs.getString("nick"));
				board.setW_date(rs.getString("w_date"));
				board.setReport_count(rs.getInt("report_count"));
				board.setEmail_id(rs.getString("email_id"));
				list.add(board);
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
		return list;
	}

	// 거래게시판 특정조건 조회
	public List<MarketBoard> searchMarket(int b_code, int cpage, int pagesize, String sold, String search) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MarketBoard> list = null;
		
		if (sold.equals("all") && search.equals("no")) {
			return listMarket(b_code, cpage, pagesize);
			
		} else {
			
			try {
				conn = ds.getConnection();
				String sql1 = "select * " +
						"from (select * " +
					    "from (select rownum rn, b.idx , m.b_idx , m.sold, m.m_mode, m.cate, " +
					    "b.title, b.content, m.img_name, m.price, b.hits, b.nick, b.w_date, " +  
					    "b.report_count, b.email_id, b.b_code " +
					    "from board b join market_board m on b.idx=m.idx order by b_idx desc) " +
					    "where b_code=? and rownum <= ?) " +
					    "where rn >=? and search=?"; //all, 검색
				String sql2 = "select * " +
						"from (select * " +
					    "from (select rownum rn, b.idx , m.b_idx , m.sold, m.m_mode, m.cate, " +
					    "b.title, b.content, m.img_name, m.price, b.hits, b.nick, b.w_date, " +  
					    "b.report_count, b.email_id, b.b_code " +
					    "from board b join market_board m on b.idx=m.idx order by b_idx desc) " +
					    "where b_code=? and rownum <= ?) " +
					    "where rn >=? and sold=?"; // 판매중
				String sql3 = "select * " +
						"from (select * " +
					    "from (select rownum rn, b.idx , m.b_idx , m.sold, m.m_mode, m.cate, " +
					    "b.title, b.content, m.img_name, m.price, b.hits, b.nick, b.w_date, " +  
					    "b.report_count, b.email_id, b.b_code " +
					    "from board b join market_board m on b.idx=m.idx order by b_idx desc) " +
					    "where b_code=? and rownum <= ?) " +
					    "where rn >=? and sold=? and search=?"; // 판매중 , 검색

				if(sold.equals("all")) {
					pstmt = conn.prepareStatement(sql1);
					pstmt.setString(4, search);
				} else if(sold.equals("판매중") && search.equals("no")) {
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(4, sold);
				} else {
					pstmt = conn.prepareStatement(sql3);
					pstmt.setString(4, sold);
					pstmt.setString(5, search);
				}
				int start = cpage * pagesize - (pagesize - 1);
				int end = cpage * pagesize;

				pstmt.setInt(1, b_code);
				pstmt.setInt(2, end);
				pstmt.setInt(3, start);
				
				rs = pstmt.executeQuery();
				tagRemove tag = new tagRemove();
				
				list = new ArrayList<MarketBoard>();
				
				while (rs.next()) {

					MarketBoard board = new MarketBoard();
					board.setIdx(rs.getInt("idx"));
					board.setB_idx(rs.getInt("b_idx"));
					board.setB_code(rs.getInt("b_code"));
					board.setSold(rs.getString("sold"));
					board.setM_mode(rs.getString("m_mode"));
					board.setCate(rs.getString("cate"));
					board.setTitle(rs.getString("title"));
					//컨텐츠 태그제거정규표현식 + 글자수 제한
					//board.setContent(tag.htmlTagRemoveString(rs.getString("content")));
					board.setContent(rs.getString("content"));
					board.setImg_name(rs.getString("img_name"));
					board.setPrice(rs.getInt("price"));
					board.setHits(rs.getInt("hits"));
					board.setNick(rs.getString("nick"));
					board.setW_date(rs.getString("w_date"));
					board.setReport_count(rs.getInt("report_count"));
					board.setEmail_id(rs.getString("email_id"));

					list.add(board); 
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
			
			return list;
			
		}
	}

	// 게시판 코드 넣어 특정 게시판의 총 게시물 건수 구하기
	public int countMarket(int b_code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int countmarket = 0;
		try {
			conn = ds.getConnection(); // dbcp 연결객체 얻기
			String sql = "select count(*) b_idx from board where b_code=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_code);
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
		System.out.println(countmarket);
		return countmarket;
	}

	// 판매 중인 거래글 카운트
	public int countSoldF(int b_code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int countf = 0;
		try {
			conn = ds.getConnection(); // dbcp 연결객체 얻기
			String sql = "select count(*) idx from board b join market_board m on b.idx = m.idx where m.sold = '판매중' and b_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				countf = rs.getInt("idx");
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
	public MarketBoard readMarket(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MarketBoard read = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select * "
					+ "from (select m.idx, m.sold, m.m_mode, m.cate, b.b_code, b.title, b.content, m.img_name, m.price, b.hits, b.nick, b.w_date, b.report_count, b.email_id"
					+ " from board b join market_board m on b.idx = m.idx) where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();

			read = new MarketBoard();

			if (rs.next()) {
				read.setIdx(idx);
				read.setB_code(rs.getInt("b_code"));
				read.setSold(rs.getString("sold"));
				read.setM_mode(rs.getString("m_mode"));
				read.setCate(rs.getString("cate"));
				read.setTitle(rs.getString("title"));
				read.setContent(rs.getString("content"));
				read.setImg_name(rs.getString("img_name"));
				read.setPrice(rs.getInt("price"));
				read.setHits(rs.getInt("hits"));
				read.setNick(rs.getString("nick"));
				read.setW_date(rs.getString("w_date"));
				read.setReport_count(rs.getInt("report_count"));
				read.setEmail_id(rs.getString("email_id"));

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

		return read;
	}

	// 유저 거래게시글 삭제하기
	public int delMarket(int idx, String email_id) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);

			// 댓글 삭제 쿼리
			String delcomments = "delete comments where idx=?";
			pstmt1 = conn.prepareStatement(delcomments);
			pstmt1.setInt(1, idx);
			row = pstmt1.executeUpdate();
							
			// 게시글 삭제 (거래게시판)
			String delmarket = "delete market_board where idx=?";
			pstmt2 = conn.prepareStatement(delmarket);
			pstmt2.setInt(1, idx);
			row = pstmt2.executeUpdate();
			System.out.println("거래삭제 row = " + row);
			
			if (row < 0) {
				throw new Exception("거래게시판 삭제 실패");
			}
			
			// 게시글 삭제 (게시판)
			String delboard = "delete from board where idx=?";
			pstmt3 = conn.prepareStatement(delboard);
			pstmt3.setInt(1, idx);
			row = pstmt3.executeUpdate();
			System.out.println("보드삭제 row = " + row);
			
			if (row < 0) {
				throw new Exception("보드게시판 삭제 실패");
			} else { //이메일 검증 실패
				conn.commit();
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
				pstmt1.close();
				pstmt2.close();
				pstmt3.close();
				conn.close();// 반환
			} catch (Exception e2) {

			}
		}
		return row;
	}


	// 댓글 입력하기
	public int writeComments(Comments comments) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into comments(co_idx, idx_fk, content, email_id, nick, w_date, report_count, refer, depth, step)"
					+ " values(co_idx.nextval,?,?,?,sysdate,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comments.getIdx());
			pstmt.setString(2, comments.getContent());
			pstmt.setString(3, comments.getEmail_id());
			pstmt.setString(4, comments.getNick());
			pstmt.setInt(5, comments.getReport_count());
			// 계층형
			int refermax = getMaxCoRefer();
			int refer = refermax + 1;
			pstmt.setInt(6, refer);
			pstmt.setInt(7, comments.getDepth());
			pstmt.setInt(8, comments.getStep());

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

	// 댓글 (refer) 값 생성하기(원댓글)
	private int getMaxCoRefer() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int refer_max = 0;
		try {
			conn = ds.getConnection(); // 빌려주세여^^ 이따 반납할게요
			String sql = "select nvl(max(refer),0) from comments";
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

	// 댓글 목록보기
	public List<Comments> commentsList(int idx_fk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Comments> commentslist = null;

		try {
			conn = ds.getConnection();
			String listCo_sql = "select co_idx, idx, content, email_id, nick, w_date, report_count, refer, depth, step from comments where idx=? ORDER BY refer DESC , step ASC";

			pstmt = conn.prepareStatement(listCo_sql);
			pstmt.setInt(1, idx_fk);

			rs = pstmt.executeQuery();
			commentslist = new ArrayList<Comments>();
			while (rs.next()) {
				Comments comments = new Comments();
				comments.setCo_idx(rs.getInt("idx"));
				comments.setContent(rs.getString("content"));
				comments.setEmail_id(rs.getString("email_id"));
				comments.setNick(rs.getString("nick"));
				comments.setW_date(rs.getString("w_date"));
				comments.setReport_count(rs.getInt("report_count"));

				// 계층형
				comments.setRefer(rs.getInt("refer"));
				comments.setDepth(rs.getInt("depth"));
				comments.setStep(rs.getInt("step"));

				commentslist.add(comments);
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
		return commentslist;
	}

	// 유저 본인 댓글 삭제하기
	public int delComment(int co_idx, String email_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;

		try {

			String checkemail = "select email_id from comments where co_idx=?";
			String delUserComment = "update comments set content = '작성자가 삭제한 댓글입니다.' where co_idx=?";

			conn = ds.getConnection();

			pstmt = conn.prepareStatement(checkemail);
			pstmt.setInt(1, co_idx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String email = rs.getString("email_id");
				if (email_id.equals(email)) {
					pstmt.close();
					pstmt = conn.prepareStatement(delUserComment);
					pstmt.setInt(1, co_idx);
					row = pstmt.executeUpdate();
				} else {
					row = 0;
				}
			} else { // 삭제하는 글이 존재하지 않는 경우
				row = -1;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
				e2.printStackTrace();
			}
		}
		return row;
	}

	// 관리자,스태프 댓글 삭제하기
	public int adminDelComment(int co_idx, String email_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;

		try {

			String checkadmin = "select isadmin from member where email_id=?";
			String delAdminComment = "update comments set content = '관리자가 삭제한 댓글입니다.' where co_idx=?";
			String delStaffComment = "update comments set content = '스탭이 삭제한 댓글입니다.' where co_idx=?";

			conn = ds.getConnection();

			pstmt = conn.prepareStatement(checkadmin);
			pstmt.setInt(1, co_idx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String isadmin = rs.getString("isadmin");
				if (isadmin == "M") {
					pstmt.close();
					pstmt = conn.prepareStatement(delAdminComment);
					pstmt.setInt(1, co_idx);
					row = pstmt.executeUpdate();
				} else if (isadmin == "S") {
					pstmt.close();
					pstmt = conn.prepareStatement(delStaffComment);
					pstmt.setInt(1, co_idx);
					row = pstmt.executeUpdate();
				} else {
					row = 0;
				}
			} else { // 삭제하는 글이 존재하지 않는 경우
				row = -1;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
				e2.printStackTrace();
			}
		}
		return row;
	}

	//객채로 title content 수정
		public int updateMarketBoardTitle(MarketBoard board) {
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
				
				
				
				if(row < 0) {
		             throw new Exception("Board 수정 실패");
		        }
				
				String sql2 = "UPDATE market_board set m_mode=?, cate=?, price=?, sold=?, img_name=? where idx=?";
				pstmt2 = conn.prepareStatement(sql2);
				
				pstmt2.setString(1, board.getM_mode());
				pstmt2.setString(2, board.getCate());
				pstmt2.setInt(3, board.getPrice());
				pstmt2.setString(4, board.getSold());
				pstmt2.setString(5, board.getImg_name());
				pstmt2.setInt(6, board.getIdx());
				
				row = pstmt2.executeUpdate();
				
				if(row < 0) {
		             throw new Exception("data_baord 수정 실패");
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

}