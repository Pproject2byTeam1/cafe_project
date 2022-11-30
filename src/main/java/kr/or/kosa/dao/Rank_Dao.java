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

import kr.or.kosa.dto.Rank;

//등급
public class Rank_Dao {

DataSource ds = null;
	
	public Rank_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//등급 전체 조회
	public List<Rank> getRankListAll(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Rank> ranklist = new ArrayList<Rank>();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select rank, r_name, r_point from Rank";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					Rank rank = new Rank();
					rank.setRank(rs.getInt("rank"));
					rank.setR_name(rs.getString("r_name"));
					rank.setR_point(rs.getInt("r_point"));
					
					ranklist.add(rank);
				}while(rs.next());
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
		
		return ranklist;
	}
	
	
	//스텝이나  관리자 뺀 등급 전체 조회
	public List<Rank> getRankExecptionManager() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Rank> ranklist = null;
		
		try {
			
			conn = ds.getConnection();
			String sql = "select rank, r_name, r_point from rank where rank >= 1";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			ranklist = new ArrayList<Rank>();
			
			if(rs.next()) {
				do {
					Rank rank = new Rank();
					rank.setRank(rs.getInt("rank"));
					rank.setR_name(rs.getString("r_name"));
					rank.setR_point(rs.getInt("r_point"));
					
					ranklist.add(rank);
				}while(rs.next());
			}
			
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
		
		return ranklist;
	}
	
	//등급 추가
	public int insertRank(int rank, String r_name, int point) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into Rank(rank, r_name, r_point) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rank);
			pstmt.setString(2, r_name);
			pstmt.setInt(3, point);
			
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
	
	//등급 수정
	public int editRank(int rank, String r_name, int point) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "UPDATE rank SET r_name = ?, r_point = ? WHERE rank = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r_name);
			pstmt.setInt(2, point);
			pstmt.setInt(3, rank);
			
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
	
	
	//등급 삭제
	public int deleteRank(int rank) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
	
		try {
			
			conn = ds.getConnection();
			String sql = "delete from Rank where rank=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rank);
			
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
	
	
	//현재 존재하는 등급 중 가장 상위 등급 가져오기
	public int highRank(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int highrank = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "select * from (select rownum rn, rank, r_name, r_point from rank order by rank desc) where rn = 1";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				do {
					
					highrank = rs.getInt("rank");
					
				}while(rs.next());
			}
			
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
		
		return highrank;
	}
	
	//현재 존재하는 등급 중 가장 상위 등급 포인트 기준 가져오기
	public int highPoint(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int highpoint = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "select * from (select rownum rn, rank, r_name, r_point from rank order by rank desc) where rn = 1";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				do {
				
					highpoint = rs.getInt("r_point");
					
				}while(rs.next());
			}
			
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
		
		return highpoint;
	}
	
	// 등급 포인트 기준 변경시 가능한 포인트 구하기
	public int possiblePoint(int rank){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int lowpoint = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "select rank, r_name, r_point from rank where rank=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (rank-1));
			
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				do {
					lowpoint = rs.getInt("r_point");
				}while(rs.next());
			}
			
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
		
		return lowpoint;
	}
	
	
}
