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
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return ranklist;
	}
	
	//등급 조건 조회
	public Rank getRankById(int rank) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Rank rankdto = new Rank();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select rank, r_name, r_point from Rank where rank=?";
			
			pstmt.setInt(1, rank);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rankdto.setRank(rs.getInt("rank"));
				rankdto.setR_name(rs.getString("r_name"));
				rankdto.setR_point(rs.getInt("r_point"));
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
		
		return rankdto;
	}
	
	//등급 삽입
	public int insertRank(Rank rank) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "insert into Rank(rank, r_name, r_point) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rank.getRank());
			pstmt.setString(2, rank.getR_name());
			pstmt.setInt(3, rank.getR_point());
			
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
	
	//등급 수정
	public int updateRank(Rank rank) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "update Rank set rank=?, r_name=?, r_point=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rank.getRank());
			pstmt.setString(2, rank.getR_name());
			pstmt.setInt(3, rank.getR_point());
			
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
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	
		return row;
	}
}
