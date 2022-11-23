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

import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.User_Details;

//등급
public class User_Dao {

	DataSource ds = null;

	public User_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	// 유저 전체 조회
	public List<User_Details> getUserListAll() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User_Details> userlist = new ArrayList<User_Details>();

		try {

			conn = ds.getConnection();
			String sql = "select m.rank, m.email_id, m.nick, m.name, u.phone, to_char(u.year_birth, 'yyMMdd') as year_birth, u.gender "
					+ "from member m join user_details u "
					+ "on m.email_id = u.email_id";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					User_Details user = new User_Details();
					
					user.setRank(rs.getInt("rank"));
					user.setEmail_id(rs.getString("email_id"));
					user.setNick(rs.getString("nick"));
					user.setName(rs.getString("name"));
					user.setPhone(rs.getString("phone"));
					user.setYear_birth(rs.getString("year_birth"));
					user.setIsAdmin(rs.getString("isadmin"));
					
					userlist.add(user);
					
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
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return userlist;
	}
	
	
	//admin 제외한 유저 조회
	public List<User_Details> list(int cpage , int pagesize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User_Details> userlist = null;
		try {
			conn = ds.getConnection();
			
			String sql = "select * from "
					+ "(select rownum rn, m.rank, m.email_id, m.nick, m.name, u.phone, to_char(u.year_birth, 'yyMMdd') as year_birth, m.isadmin "
					+ "from member m join user_details u "
					+ "on m.email_id = u.email_id "
					+ "where not nick ='admin' "
					+ "order by name) where rn <= ? and rn >= ?";
			
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
			int end = cpage * pagesize; // 1 * 5 >> 5
			
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			userlist = new ArrayList<User_Details>();
			
			while(rs.next()) {
				User_Details user = new User_Details();
				
				user.setRank(rs.getInt("rank"));
				user.setEmail_id(rs.getString("email_id"));
				user.setNick(rs.getString("nick"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				user.setYear_birth(rs.getString("year_birth"));
				user.setIsAdmin(rs.getString("isadmin"));
				
				userlist.add(user);
			}
			
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			} catch (Exception e2) {
				
			}
			
		}
		
		return userlist;
	}
	
	
	//admin을 제외한 총 유저 인원 구하기
	public int totalUserCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalcount = 0;
		try {
			conn = ds.getConnection(); //dbcp 연결객체 얻기
			String sql="select count(*) cnt from member where NOT nick='admin'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalcount = rs.getInt("cnt");
			}
		}catch (Exception e) {
			
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환  connection pool 에 반환하기
			}catch (Exception e) {
				
			}
		}
		return totalcount;
	}

}
