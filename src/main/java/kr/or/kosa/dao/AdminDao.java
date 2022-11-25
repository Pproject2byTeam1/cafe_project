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
import kr.or.kosa.dto.User;

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

			String sql = "select * from (select rownum rn,b_code, idx ,title, nick,email_id ,hits, report_count from board where report_count>3)where rn <= ? and rn >= ?";
		
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize - 1); // 1 * 5 - (5 - 1) >> 1
			int end = cpage * pagesize; // 1 * 5 >> 5

			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
		
			rs = pstmt.executeQuery();
			reportlist = new ArrayList<Board>();
			
			while (rs.next()) {
				Board report = new Board();
				report.setIdx(rs.getInt("b_code"));
				report.setIdx(rs.getInt("idx"));
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
	
	
	public List<Board> reportlist2(int cpage, int pagesize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> reportlist = null;
		try {
			conn = ds.getConnection();

			String sql = "select * from (select a.b_code, b.b_name a.title,a.nick, a.email_id, a.report_count, a.hits  from board a join board_info b on a.b_code = b.b_code where a.report_count > 0 order by idx)where rn <= ? and rn >= ?";
		
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize - 1); // 1 * 5 - (5 - 1) >> 1
			int end = cpage * pagesize; // 1 * 5 >> 5

			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
		
			rs = pstmt.executeQuery();
			reportlist = new ArrayList<Board>();
			
			while (rs.next()) {
				
				Board report = new Board();
				Board_Info info = new Board_Info();
				report.setIdx(rs.getInt("b_code"));
				info.setB_name(rs.getString("b_name"));
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

	public Board_Info getBoardInfo(int idx) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board_Info board_info = new Board_Info();

		try {

			conn = ds.getConnection();
			String sql = "select b.b_name b. from board a, board_info b where a.b_code = b.b_code and a.idx =?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				board_info.setB_code(rs.getInt("b_code"));
				board_info.setB_name(rs.getString("b_name"));
			
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
	//게시판 형식
	
	
	
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
