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
import kr.or.kosa.dto.User;
import kr.or.kosa.dto.User_Details;

//등급
public class UserDao {

	DataSource ds = null;

	public UserDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	// 유저 전체 조회
//	public List<User_Details> getUserListAll() {
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<User_Details> userlist = new ArrayList<User_Details>();
//
//		try {
//
//			conn = ds.getConnection();
//			String sql = "select m.rank, m.email_id, m.nick, m.name, u.phone, to_char(u.year_birth, 'yyMMdd') as year_birth, u.gender "
//					+ "from member m join user_details u "
//					+ "on m.email_id = u.email_id";
//			pstmt = conn.prepareStatement(sql);
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				do {
//					User_Details user = new User_Details();
//					
//					user.setRank(rs.getInt("rank"));
//					user.setEmail_id(rs.getString("email_id"));
//					user.setNick(rs.getString("nick"));
//					user.setName(rs.getString("name"));
//					user.setPhone(rs.getString("phone"));
//					user.setYear_birth(rs.getString("year_birth"));
//					user.setIsAdmin(rs.getString("isadmin"));
//					
//					userlist.add(user);
//					
//				} while (rs.next());
//			} else {
//				System.out.println("조회 데이터 없음");
//			}
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
//			}
//		}
//
//		return userlist;
//	}
	
	
	//admin 제외한 유저 조회
	public List<UserDetails> list(int cpage , int pagesize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UserDetails> userlist = null;
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
			
			userlist = new ArrayList<UserDetails>();
			
			while(rs.next()) {
				UserDetails user = new UserDetails();
				
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
	
	//특정 유저 단순정보 조회
	public User selectUserById(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			conn = ds.getConnection(); //dbcp 연결객체 얻기
			String sql="select email_id, password, name, nick, to_char(birth, 'yyyyMMdd') as birth, point, isadmin, rank from member where email_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					user = new User();
					
					user.setEmail_id(rs.getString("email_id"));
					user.setPassword(rs.getString("password"));
					user.setName(rs.getString("name"));
					user.setNick(rs.getString("nick"));
					user.setBirth(rs.getString("birth"));
					user.setPoint(rs.getInt("point"));
					user.setIsAdmin(rs.getString("isadmin"));
					user.setRank(rs.getInt("rank"));
				}while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환  connection pool 에 반환하기
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
	//특정 유저 상세정보 조회
		public UserDetails selectUserDetailById(String userid) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			UserDetails user = null;
			
			try {
				conn = ds.getConnection(); //dbcp 연결객체 얻기
				String sql="select email_id, to_char(join_date, 'yyyyMMdd') as join_date, to_char(last_visit_date, 'yyyyMMdd') as last_visit_date,visit_count,w_count,re_count,to_char(year_birth, 'yyyyMMdd') as year_birth, phone from user_details where email_id =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					do {
						user = new UserDetails();
						
						user.setEmail_id(rs.getString("email_id"));
						user.setJoin_date(rs.getString("join_date"));
						user.setLast_visit_date(rs.getString("last_visit_date"));
						user.setVisit_count(rs.getInt("visit_count"));
						user.setW_count(rs.getInt("w_count"));
						user.setRe_count(rs.getInt("re_count"));
						user.setYear_birth(rs.getString("year_birth"));
						user.setPhone(rs.getString("phone"));
					}while(rs.next());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();//반환  connection pool 에 반환하기
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return user;
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
	
	
	
	//특정 유저 조회
	public User idSearchUser(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User userlist = null;
		
		try {
			conn = ds.getConnection();
			
			String sql = "select email_id, password, name, point "
					+"from member "
					+"where email_id=?";
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				userlist = new User();
				
				userlist.setEmail_id(rs.getString("email_id"));
				userlist.setPassword(rs.getString("password"));
				userlist.setName(rs.getString("name"));
				userlist.setPoint(rs.getInt("point"));				
				
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
	
	
	//특정 유저 비번, 닉네임, 포인트 바꾸기
		public User editSettingUser(String id, String password, String name, int point) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			User userlist = null;
			
			try {
				conn = ds.getConnection();
				
				String sql = "UPDATE member SET password=?, name=?, point=? "
						+ "WHERE email_id=?";
				
				pstmt = conn.prepareStatement(sql);
				
				
				pstmt.setString(1, password);
				pstmt.setString(2, name);
				pstmt.setInt(3, point);
				pstmt.setString(4, id);
				rs = pstmt.executeQuery();
				
				
				while(rs.next()) {
					userlist = new User();
					
					userlist.setEmail_id(rs.getString("email_id"));
					userlist.setPassword(rs.getString("password"));
					userlist.setName(rs.getString("name"));
					userlist.setPoint(rs.getInt("point"));				
					
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
	
	
	
	

}
