package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.kosa.dto.Regular_Board;

//자유 게시판
public class Regular_Board_Dao {

	DataSource ds = null;
	
	public Regular_Board_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//자유게시판 특정 게시글 조회
	public Regular_Board getRegular_BoardByIdx(int idx) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Regular_Board board = new Regular_Board(); 
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b.idx, b.title, b.nick, b.content, b.hits, to_char(b.w_date, 'YYYY-MM-dd') w_date, b.report_count, b.notic, b.email_id, b.b_code, i.refer, i.depth, i.step "
							+ "from board b join regular_board i "
							+ "on b.idx = i.idx "
							+ "where b.idx = ?";
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
				board.setEmail_id(rs.getString("email_id"));
				board.setReport_count(rs.getInt("report_count"));
				board.setNotic(rs.getString("notic"));
				board.setEmail_id(rs.getString("email_id"));
				board.setB_code(rs.getInt("b_code"));
				board.setDepth(rs.getInt("depth"));
				board.setStep(rs.getInt("step"));
				
				
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
	
	//자유 게시판 답글 삽입
//	public int insertRegualr_BoardReply(Regular_Board regualr_board) {
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int row = 0;
//		
//		try {
//			
//			conn = ds.getConnection();
//			String sql = "INSERT ALL "
//					+ "INTO board (idx, title, nick, content, email_id, b_code) "
//					+ "VALUES (IDX_SEQ.nextval, ?, ?, ?, ?, ?) "
//					+ "INTO regular_board (b_idx, idx, refer, depth, step) "
//					+ "VALUES (REGULAR_B_IDX_SEQ.nextval, IDX_SEQ.currval, ?, ?, ?) "
//					+ "select * from dual";
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, regualr_board.getTitle());
//			pstmt.setString(2, regualr_board.getNick());
//			pstmt.setString(3, regualr_board.getContent());
//			pstmt.setString(4, regualr_board.getEmail_id());
//			pstmt.setInt(5, regualr_board.getB_code());
//			pstmt.setInt(6, regualr_board.getRefer());
//			pstmt.setInt(7, regualr_board.getDepth()+1);
//			pstmt.setInt(8, regualr_board.getStep()+1);
//			
//			row = pstmt.executeUpdate();
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			try {
//				pstmt.close();
//				conn.close();
//			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
//			}
//		}
//		
//		return row;
//	}
	
	
	//자유 게시판 글쓰기 ,트랜젝션
		public int writeRegualr_Board(Regular_Board regualr_board) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			int row = 0;
			
			try {
				
				conn = ds.getConnection();
				conn.setAutoCommit(false); //커밋 꺼줌
				
				//유저 글쓴거 넣기
				String sql = "INSERT ALL "
						+ "INTO board (idx, title, nick, content, email_id, b_code) "
						+ "VALUES (IDX_SEQ.nextval, ?, ?, ?, ?, ?) "
						+ "INTO regular_board (b_idx, idx, refer) "
						+ "VALUES (REGULAR_B_IDX_SEQ.nextval, IDX_SEQ.currval, ?) "
						+ "select * from dual";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, regualr_board.getTitle());
				pstmt.setString(2, regualr_board.getNick());
				pstmt.setString(3, regualr_board.getContent());
				pstmt.setString(4, regualr_board.getEmail_id());
				pstmt.setInt(5, regualr_board.getB_code());
				
				pstmt.setInt(6, getMaxRefer()+1);
				
				row = pstmt.executeUpdate();
				
				if(row < 0) {
 		             throw new Exception("작성 실패");
 		        }
				
				String sql2 = "UPDATE user_details SET w_count = nvl(w_count + 1, 0) WHERE email_id=? ";
				pstmt2 = conn.prepareStatement(sql2);
				
				pstmt2.setString(1, regualr_board.getEmail_id());
				
				row = pstmt2.executeUpdate();
				
				if(row < 0) {
		             throw new Exception("유저 글 업데이트 실패");
		        } else {
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
					pstmt2.close();
					conn.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
			
			return row;
		}
		
		
		//글쓰기 (refer) 값 생성하기(원본글)
		private int getMaxRefer() {
			Connection conn = null;
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			int refer_max=0;
			
			try {
				
				conn = ds.getConnection(); //빌려주세여^^  이따 반납할게요 
				String sql="select nvl(max(refer),0) from Regular_board";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					refer_max = rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close(); // 반납이요 ^^
				}catch (Exception e) {
					
				}
			}
			return refer_max;
		}
	

	
	public int deleteRegualr_Board(int idx, String email_id) {
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
				throw new Exception("regular_board 삭제 실패");
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
	
	
	//게시글 상세 (답글 쓰기) 
		public int insertRegualr_BoardReply(Regular_Board regualr_board) {

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			try {
				conn = ds.getConnection();
				
				String title = regualr_board.getTitle();
				String nick = regualr_board.getNick();
				String content = regualr_board.getContent();
				String email_id = regualr_board.getEmail_id();
				int b_code = regualr_board.getB_code();
				
				//1. 답글 
				//현재 내가 읽은 글의 refer , depth , step (원본글 ,답글)
				String refer_depth_step_sal ="select refer , depth , step from regular_board where idx=?";
				
				//2. 위치
				//step (순서) : 나중에 쓴 답글이 위로 올라오게 하겠다
				//내가 읽은 글의 step 보다 큰 값은 +1 해서 증가 시켜 놓는다
				//refer 값으로 판단
				//ex)   원본글                 refer=1 , step=0 , depth=0 
			    //				원본글답글           refer=1 , step=1+1 >2 >3,  depth=1
				//	        원본글답글           refer=1 , step=1>2 ,       depth=1
				//      원본글답글           refer=1 , step=0+1>1    
				String step_update_sql = "update jspboard set step= step+1 where step  > ? and refer =? ";
				// "update jspboard set step= step+1 where step  > 0 and refer =1 ";
				
				//답글  insert 
				String rewrite_sql="insert into jspboard(idx,writer,pwd,subject,content,email,homepage,writedate,readnum,filename,filesize,refer,depth,step)" + 
					    		   " values(jspboard_idx.nextval,?,?,?,?,?,?,sysdate,0,?,0,?,?,?)";
				
				pstmt = conn.prepareStatement(refer_depth_step_sal);
				pstmt.setInt(1, regualr_board.getIdx());
				rs = pstmt.executeQuery();
				
				if(rs.next()) { //데이터가 있다면 ... 원본글의  refer , step , depth 존재
					int refer = rs.getInt("refer");
					int step = rs.getInt("step");
					int depth = rs.getInt("depth");
					
					pstmt = conn.prepareStatement(step_update_sql); //컴파일
					//기존 step + 1 >> update 구문 실행
					pstmt.setInt(1, step);
					pstmt.setInt(2, refer);
					pstmt.executeUpdate();

					//filename,filesize,refer,depth,step
					/*
					 * pstmt = conn.prepareStatement(rewrite_sql); //컴파일 pstmt.setString(1, writer);
					 * pstmt.setString(2, pwd); pstmt.setString(3, subject); pstmt.setString(4,
					 * content); pstmt.setString(5, email); pstmt.setString(6, homepage);
					 * pstmt.setString(7, filename);
					 */
					
					//답변
					pstmt.setInt(8, refer);
					pstmt.setInt(9, depth+1); // 규칙 현재 읽은 글에 depth + 1
					pstmt.setInt(10, step+1); // 순서 update 통해서  자리 확보 + 1
					
					int row = pstmt.executeUpdate();
					if(row > 0) {
						result = row;
					}else {
						result = -1;
					}

				}
		
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();//반환
				}catch (Exception e) {
					
				}
			}
			
			return result;
		}
	
	
	
}
