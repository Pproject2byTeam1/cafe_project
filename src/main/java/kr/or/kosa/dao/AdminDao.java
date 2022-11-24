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

import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.Calender;
import kr.or.kosa.dto.Img_Board;
import kr.or.kosa.dto.User;
import kr.or.kosa.dto.User_Details;

public class AdminDao {

	DataSource ds = null;

	public AdminDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");

	}

//신고 리스트
	public List<Board> reportlist(int cpage, int pagesize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> reportlist = null;
		try {
			conn = ds.getConnection();

			String sql = "select * from (select rownum rn,title, nick,email_id ,hits, report_count from board where report_count>0)where rn <= ? and rn >= ?";
		
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize - 1); // 1 * 5 - (5 - 1) >> 1
			int end = cpage * pagesize; // 1 * 5 >> 5

			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
		
			rs = pstmt.executeQuery();
			reportlist = new ArrayList<Board>();
			
			while (rs.next()) {
				Board report = new Board();
			
				report.setTitle(rs.getString("title"));
				report.setNick(rs.getString("nick"));
				report.setEmail_id(rs.getString("email_id"));
				report.setHits(rs.getInt("hits"));
				report.setReport_count(rs.getInt("report_count"));

				reportlist.add(report);
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

		return reportlist;
	}
//게시판 형식

	public Board_Info getBoardInfo(int b_code) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board_Info board_info = new Board_Info();

		try {

			conn = ds.getConnection();
			String sql = "select b_code, b_name, side_idx, main_idx, b_type from Board_Info where b_code=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, b_code);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				board_info.setB_code(rs.getInt("b_code"));
				board_info.setB_name(rs.getString("b_name"));
				board_info.setSide_idx(rs.getInt("side_idx"));
				board_info.setMain_idx(rs.getInt("main_idx"));
				board_info.setB_type(rs.getString("b_type"));
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

		return board_info;
	}
	// 이름별 등급조회

	public User getrank(String email_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User userdto = new User();

		try {
			conn = ds.getConnection();

			String sql = "select rank from member where email_id= ?";
			pstmt.setString(1, "email_id");
			rs = pstmt.executeQuery();
			if (rs.next()) {

				//userdto.setRank(rs.getInt("rank"));;

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return userdto;

	}

	// 신고 목록 갯수
	public int totalreportCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalcount = 0;
		try {
			conn = ds.getConnection(); // dbcp 연결객체 얻기
			String sql = "select count(idx) cnt from board where report_count>0";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalcount = rs.getInt("cnt");
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
		return totalcount;
	}
	
	
	
	
	
}
