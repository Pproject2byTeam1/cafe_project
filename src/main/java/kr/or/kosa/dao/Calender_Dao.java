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

import kr.or.kosa.dto.Calender;

public class Calender_Dao {

	DataSource ds = null;

	public Calender_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//일정 참석 여부
	public int checkCal(String email_id, int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;	
	
		try {
			
			conn = ds.getConnection();
			String sql = "INSERT INTO YES(yes_idx, email_id, idx) VALUES (yes_idx_seq.nextval, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email_id);
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
	
	//참석 해제
	public int checkRemoveCal(String email_id, int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;	
	
		try {
			
			conn = ds.getConnection();
			String sql = "delete from yes where email_id=? and idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email_id);
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
	
}
