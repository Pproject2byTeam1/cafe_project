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

	//달력 특정 게시글 조회
	public Calender getCalenderByIdx(int idx) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Calender calender = new Calender();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b_idx, idx, start_date, end_date, finish from Calender where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				calender.setB_idx(rs.getInt("b_idx"));
				calender.setIdx(rs.getInt("idx"));
				calender.setStart_date(rs.getDate("start_date"));
				calender.setEnd_date(rs.getDate("end_date"));
				calender.setFinish(rs.getString("finish"));
				
			}else {
				System.out.println("조회 데이터 없음");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return calender;
	}
	
	//달력 특정 글 삽입
	public int insertCalender(Calender calender) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "insert into Calender(idx, start_date, end_date, finish) values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, calender.getIdx());
			pstmt.setDate(2, calender.getStart_date());
			pstmt.setDate(3, calender.getEnd_date());
			pstmt.setString(4, calender.getFinish());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return row;
	}
	
	//달력 특정 글 수정
	public int updateCalender(Calender calender) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "update Calender set start_date=?, end_date=?, finish=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDate(1, calender.getStart_date());
			pstmt.setDate(2, calender.getEnd_date());
			pstmt.setString(3, calender.getFinish());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return row;
	}
	
	//달력 특정 글 삭제
	public int deleteCalender(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "delete from Calender where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return row;
	}
	
}
