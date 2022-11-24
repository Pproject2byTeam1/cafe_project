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
import kr.or.kosa.dto.Img_Board;

//이미지 게시판 특정 게시판
public class Img_Board_Dao {

	DataSource ds = null;
	
	public Img_Board_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//이미지 게시판 전체 이미지 조회
	public List<Img_Board> getImg_BoadList(int cpage, int pagesize){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Img_Board> boardlist = null;
		
		try {
			
			conn = ds.getConnection();
			String sql = "select * from (select rownum rn, b_idx, idx, img_name from img_board) where rn <= ? and rn >= ?";
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize;
			
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			
			boardlist = new ArrayList<Img_Board>();
			
			while(rs.next()) {
				Img_Board board = new Img_Board();
				board.setB_idx(rs.getInt("b_idx"));
				board.setIdx(rs.getInt("idx"));
				board.setImg_name(rs.getString("img_name"));
				
				boardlist.add(board);
			}
			
		} catch (Exception e) {
			System.out.println("오류 :" + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return boardlist;
	}
	
	//이미지 게시판 특정 게시글 조회
	public Img_Board getImg_BoardByIdx(int idx) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Img_Board img_board = new Img_Board(); 
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b.idx, b.title, b.nick, b.content, b.hits, to_char(b.w_date, 'YYYY.MM.dd') w_date, b.report_count, b.notic, b.email_id, b.b_code, i.b_idx, i.img_name "
						+ "from board b join img_board i "
						+ "on b.idx = i.idx "
						+ "where b.idx = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				img_board.setB_idx(rs.getInt("b_idx"));
				img_board.setTitle(rs.getString("title"));
				img_board.setNick(rs.getString("nick"));
				img_board.setContent(rs.getString("content"));
				img_board.setHits(rs.getInt("hits"));
				img_board.setW_date(rs.getString("w_date"));
				img_board.setReport_count(rs.getInt("report_count"));
				img_board.setNotic(rs.getString("notic"));
				img_board.setEmail_id(rs.getString("email_id"));
				img_board.setB_code(rs.getInt("b_code"));
				img_board.setIdx(rs.getInt("idx"));
				img_board.setImg_name(rs.getString("img_name"));

				System.out.println(img_board.getImg_name());
				
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
		
		return img_board;
	}
	
	//이미지 게시판 검색
	public List<Img_Board> searchImg(int b_code, String search){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Img_Board> imglist = null;
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b.idx, b.title, b.nick, b.content, b.hits, to_char(b.w_date, 'yyyy-MM-dd') as w_date, b.report_count, b.notic, b.email_id, b.b_code, i.b_idx, i.img_name "
						+ "from board b join img_board i "
						+ "on b.idx = i.idx "
						+ "where title like ? and b.b_code=? "
						+ "order by b.idx desc";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + search + "%");
			pstmt.setInt(2, b_code);
			
			rs = pstmt.executeQuery();
			
			imglist = new ArrayList<Img_Board>();
			
			if(rs.next()) {
				do {
					Img_Board board = new Img_Board();
					board.setIdx(rs.getInt("idx"));
					board.setTitle(rs.getString("title"));
					board.setNick(rs.getString("nick"));
					board.setContent(rs.getString("content"));
					board.setHits(rs.getInt("hits"));
					board.setW_date(rs.getString("w_date"));
					board.setReport_count(rs.getInt("report_count"));
					board.setNotic(rs.getString("notic"));
					board.setEmail_id(rs.getString("email_id"));
					board.setB_code(rs.getInt("b_code"));
					board.setB_idx(rs.getInt("b_idx"));
					board.setImg_name(rs.getString("img_name"));
					
					imglist.add(board);
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
		
		return imglist;
	}
	
	//이미지 게시판 특정 글 삽입
	public int insertImg_Board(Img_Board img_board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "insert into Regualr_Board(idx, img_name) values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, img_board.getIdx());
			pstmt.setString(2, img_board.getImg_name());
			
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
	
	//이미지 게시판 특정 글 수정
	public int updateImg_Board(Img_Board img_board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "update Img_Board set img_name where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, img_board.getImg_name());
			pstmt.setInt(2, img_board.getIdx());
			
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
	
	//이미지 게시판 특정 글 삭제
	public int deleteImg_Board(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "delete from Img_Board where idx=?";
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
}
