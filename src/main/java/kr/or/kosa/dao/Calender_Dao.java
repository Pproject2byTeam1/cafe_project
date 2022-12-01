package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.kosa.dto.Calender;

public class Calender_Dao {

	DataSource ds = null;

	public Calender_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//캘린더 일정 추가 + 트랜잭션 처리
	public int AddCalender(Calender calender) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			
			conn.setAutoCommit(false);
			
			//Board 삽입
			String sql = "insert into board (idx,title,nick,content,email_id,b_code) "
						+ "values(idx_seq.nextval, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, calender.getTitle());
			pstmt.setString(2, calender.getNick());
			pstmt.setString(3, calender.getContent());
			pstmt.setString(4, calender.getEmail_id());
			pstmt.setInt(5, calender.getB_code());
			
			row = pstmt.executeUpdate();
			if(row < 0) {
				throw new Exception("Board 삽입 실패");
			}
			
			//Calender 삽입
			String sql2 = "insert into calender(b_idx,idx,start_date,end_date,finish) "
						+ "values(calender_b_idx_seq.nextval, idx_seq.currval, to_date(?, 'yy/mm/dd'), to_date(?, 'yy/mm/dd'), ?)";
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setString(1, calender.getStart_date());
			pstmt2.setString(2, calender.getEnd_date());
			String finish = "";
			if(calender.getFinish().equals("Not Started")) {
				finish = "N";
			}else if(calender.getFinish().equals("In progress")) {
				finish = "F";
			}else {
				finish = "T";
			}
			pstmt2.setString(3, finish);
			
			row = pstmt2.executeUpdate();
			
			if(row < 0) {
				throw new Exception("Calender 삽입 실패");
			}
			
			String sql3 = "UPDATE user_details SET re_count = nvl(re_count + 1, 0) WHERE email_id=?";
			pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, calender.getEmail_id());
			row = pstmt3.executeUpdate();
			
			if(row < 0) {
				throw new Exception("작성 정보 업로드 실패");
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

	//일정 수정
	public int modifyCalender(Calender calender) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			String sql = "update board set title=?, content=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, calender.getTitle());
			pstmt.setString(2, calender.getContent());
			pstmt.setInt(3, calender.getIdx());
			
			row = pstmt.executeUpdate();
			if(row < 0) {
				throw new Exception("Board 수정 실패");
			}
			
			String sql2 = "update calender set start_date=to_date(?, 'YY/MM/dd'), end_date=to_date(?, 'YY/MM/dd'), finish=? where idx=?";
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setString(1, calender.getStart_date());
			pstmt2.setString(2, calender.getEnd_date());
			
			String finish = "";
			if(calender.getFinish().equals("Not Started")) {
				finish = "N";
			}else if(calender.getFinish().equals("In progress")) {
				finish = "F";
			}else {
				finish = "T";
			}
			
			pstmt2.setString(3, finish);
			pstmt2.setInt(4, calender.getIdx());
			
			row = pstmt2.executeUpdate();
			if(row < 0) {
				throw new Exception("Calender 수정 실패");
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
	
	//일정 삭제
	public int deleteCalender(int idx) {
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
			
			if(row < 0) {
				throw new Exception("Board 삭제 실패");
			}
			
			String sql2 = "delete calender where idx=?";
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setInt(1, idx);
			
			row = pstmt.executeUpdate();
			
			if(row < 0) {
				throw new Exception("Calender 수정 실패");
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
