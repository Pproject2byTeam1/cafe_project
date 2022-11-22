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

import kr.or.kosa.dto.Data_Board;

public class Data_Board_Dao {
	DataSource ds = null;

	public Data_Board_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	// 자료 게시판 특정 글 조회
	public Data_Board getData_BoardByIdx(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Data_Board data_board = new Data_Board();

		try {

			conn = ds.getConnection();
			String sql = "select b_idx, idx, ori_name, save_name, volume, refer, depth, step from Data_Board where idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				data_board.setB_idx(rs.getInt("b_idx"));
				data_board.setIdx(rs.getInt(rs.getInt("idx")));
				data_board.setOri_name(rs.getString("ori_name"));
				data_board.setSave_name(rs.getString("save_name"));
				data_board.setVolume(rs.getInt("volume"));
				data_board.setRefer(rs.getInt("refer"));
				data_board.setDepth(rs.getInt("depth"));
				data_board.setStep(rs.getInt("step"));

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

		return data_board;
	}

	// 자료 게시판 특정 글 삽입
	public int insertData_Board(Data_Board data_board) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {

			conn = ds.getConnection();
			String sql = "insert into Regualr_Board(idx, ori_name, save_name, volume, refer, depth, step) values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, data_board.getIdx());
			pstmt.setString(2, data_board.getOri_name());
			pstmt.setString(3, data_board.getSave_name());
			pstmt.setInt(4, data_board.getVolume());
			pstmt.setInt(5, data_board.getRefer());
			pstmt.setInt(6, data_board.getDepth());
			pstmt.setInt(7, data_board.getStep());

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

	// 자료 게시판 특정 글 수정
	public int updateData_Board(Data_Board data_board) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {

			conn = ds.getConnection();
			String sql = "update Data_Board set ori_name=?, save_name=? volume=? where idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, data_board.getOri_name());
			pstmt.setString(2, data_board.getSave_name());
			pstmt.setInt(3, data_board.getVolume());
			pstmt.setInt(4, data_board.getIdx());

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

	// 자료 게시판 특정 글 삭제
	public int deleteData_Board(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {

			conn = ds.getConnection();
			String sql = "delete from Data_Board where idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

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

	// 전체 자료게시판 조회
	public List<Data_Board> getAllDatalist(int cpage, int pagesize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Data_Board> datalist = null;

		try {

			conn = ds.getConnection();
			String sql = "select * from (select rownum rn,idx,refer,depth,step from (select * from data_board order by refer desc, step asc))where rownum <=?";
			pstmt = conn.prepareStatement(sql);

			datalist = new ArrayList<Data_Board>();
			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);

			rs = pstmt.executeQuery();
			datalist = new ArrayList<Data_Board>();

			while (rs.next()) {
				Data_Board data = new Data_Board();
				data.setB_idx(rs.getInt("B_IDX"));
				data.setB_idx(rs.getInt("IDX"));
				data.setRefer(rs.getInt("refer"));
				data.setDepth(rs.getInt("dapt"));
				data.setStep(rs.getInt("step"));

				// 계층형

				data.setRefer(rs.getInt("refer"));
				data.setStep(rs.getInt("step"));
				data.setDepth(rs.getInt("depth"));

				datalist.add(data);

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

		return datalist;
	}

	// 글쓰기 refer 값 생성하기
	private int getMaxRefer() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int refer_max = 0;
		try {
			conn = ds.getConnection();
			String sql = "select nvl(max(refer),0) from data_board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				refer_max = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e) {

			}
		}

		return refer_max;

	}
	//게시뭉 총 건수 구학;
	public int totaldataBoard() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalcount = 0;
		try {
			conn = ds.getConnection(); 
			String sql="select count(*) cnt from data_board";
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
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		
		
		return totalcount;
	}
}
