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

import kr.or.kosa.dto.User;
import kr.or.kosa.dto.Yes;

public class Yes_Dao {

	DataSource ds = null;

	public Yes_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	// yes 여부 판단
	public Yes getYesByIdxEmail(int idx, String email_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Yes yes = new Yes();

		try {

			conn = ds.getConnection();
			String sql = "select yes_idx, email_id, idx from yes where email_id=? and idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email_id);
			pstmt.setInt(2, idx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				yes.setEmail_id(email_id);
				yes.setIdx(idx);
				yes.setYes_idx(rs.getInt("yes_idx"));
			} else {
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

		return yes;
	}

	public String getYesEmailByIdxEmail(int idx, String email_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Yes yes = new Yes();

		try {

			conn = ds.getConnection();
			String sql = "select yes_idx, email_id, idx from yes where email_id=? and idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email_id);
			pstmt.setInt(2, idx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				yes.setEmail_id(email_id);
				yes.setIdx(idx);
				yes.setYes_idx(rs.getInt("yes_idx"));
			} else {
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

		return yes.getEmail_id();
	}

	public List<Yes> getYesBy_idx(String email_id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Yes> yeslist = new ArrayList<Yes>();

		try {

			conn = ds.getConnection();
			String sql = "select yes_idx, email_id, idx from yes where email_id=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					Yes yes = new Yes();
					yes.setYes_idx(rs.getInt("yes_idx"));
					yes.setEmail_id(rs.getString("email_id"));
					yes.setIdx(rs.getInt("idx"));

					yeslist.add(yes);
				} while (rs.next());

			} else {
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

		return yeslist;
	}

	// yes 추가
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

	public int boardYesCount(int idx) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalcount = 0;

		try {
			conn = ds.getConnection(); // dbcp 연결객체 얻기
			String sql = "select count(*) cnt from yes where idx = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalcount = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.getMessage();
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

	public List<String> getYesMember(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> nicklist = new ArrayList<String>();
		
		try {
			
			conn = ds.getConnection(); // dbcp 연결객체 얻기
			String sql = "select nick "
						+ "from member m join (select email_id from yes where idx = ?) s "
						+ "on m.email_id = s.email_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					nicklist.add(rs.getString("nick"));
				}while(rs.next());
			}
			
		}  catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환 connection pool 에 반환하기
			} catch (Exception e) {

			}
		}
		
		return nicklist;
	}

	// yes 탈퇴
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

	// yes개수 int로 출력 (파라미터: idx값)
	public int getYesCountBy_idx(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalcount = 0;

		try {

			conn = ds.getConnection();
			String sql = "select count(*) cnt from yes where idx = ?";
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

}