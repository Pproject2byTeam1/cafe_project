package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.kosa.dto.CafeBanner;

public class CafeBannerDao {
	
	DataSource ds = null;
	
	public CafeBannerDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//카페 정보 전체 출력
	public CafeBanner getCafeBanner() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CafeBanner cafe = new CafeBanner();
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "select * from cafe_banner";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cafe.setCafe_name(rs.getString("cafe_name"));
				cafe.setCafe_img(rs.getString("cafe_img"));
				cafe.setCafe_icon(rs.getString("cafe_icon"));
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
		
		return cafe;
	}
	
	//카페 배너 이미지 수정
	public int UploadCafeBanner(String cafe_img) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "update cafe_banner set cafe_img = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cafe_img);
			
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
