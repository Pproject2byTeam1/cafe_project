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

import kr.or.kosa.dto.Board_Info;

//게시판 종류
public class Board_Info_Dao {

	DataSource ds = null;
	
	public Board_Info_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//side bar 게시판 종류 전체 출력
	public List<Board_Info> getSideBoardList(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board_Info> list = new ArrayList<Board_Info>();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b_code, b_name, side_idx, main_idx, b_type "
						+ "from board_info order by side_idx";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					
					Board_Info info = new Board_Info();
					
					info.setB_code(rs.getInt("b_code"));
					info.setB_name(rs.getString("b_name"));
					info.setSide_idx(rs.getInt("side_idx"));
					info.setMain_idx(rs.getInt("main_idx"));
					info.setB_type(rs.getString("b_type"));
					
					list.add(info);
				}while(rs.next());
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
		
		return list;
	}
	
	//게시판 종류 탐색
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
			
			if(rs.next()) {
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
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return board_info;
	}
	
	//게시판 종류 수정
	public int updateBoardInfo(Board_Info board_info) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			String sql = "update Board_Info set b_name=?, side_idx=?, main_idx=? where b_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board_info.getB_name());
			pstmt.setInt(2, board_info.getSide_idx());
			pstmt.setInt(3, board_info.getMain_idx());
			
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
	
	//게시판 종류 삭제
	public int deleteBoardInfo(int b_code) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			String sql = "delete from Board_Info where b_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b_code);
			
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

	//메인 위치 번호 업로드

	public int updateMain_Index(int b_code, int main_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			String sql = "update board_info set main_idx = -1 where main_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, main_idx);
			
			row = pstmt.executeUpdate();
			
			String sql2 = "update board_info set main_idx = ? where b_code = ?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, main_idx);
			pstmt2.setInt(2, b_code);
			
			row = pstmt2.executeUpdate();
			
			if(row < 0) {
				throw new Exception("업로드 실패");
			}else {
				conn.commit();
			}
			
		} catch (Throwable e) {
			if(conn != null) {
				try {
					conn.rollback(); // 트랜잭션 실행 이전 상태로 돌리기
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		} finally {
			try {
				conn.setAutoCommit(true);
				pstmt2.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return row;
	}
	
	//게시판 추가
	public int insertBoard(Board_Info info) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row=0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "insert into board_info(b_code, b_name, side_idx, b_type, form) "
						+ "values(boardinfo_idx_seq.nextval, ?, boardinfo_idx_seq.currval, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, info.getB_name());
			pstmt.setString(2, info.getB_type());
			pstmt.setString(3, info.getForm());
			
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
