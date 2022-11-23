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
import kr.or.kosa.dto.Market_Board;

public class Market_Board_Dao {

	DataSource ds = null;

	public Market_Board_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//마켓 게시판 전체 리스트 조회
	public List<Market_Board> getMarket_BoadList(int cpage, int pagesize){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Market_Board> boardlist = null;
		
		try {
			
			conn = ds.getConnection();
			String sql = "select * from (select rownum rn, b_idx, idx, img_name, m_mode, cate, price, sold from market_board) where rn <= ? and rn >= ?";
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize;
			
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			
			boardlist = new ArrayList<Market_Board>();
			
			while(rs.next()) {
				Market_Board board = new Market_Board();
				board.setB_idx(rs.getInt("b_idx"));
				board.setIdx(rs.getInt("idx"));
				board.setImg_name(rs.getString("img_name"));
				board.setM_mode(rs.getString("m_mode"));
				board.setCate(rs.getString("cate"));
				board.setPrice(rs.getInt("price"));
				board.setSold(rs.getString("sold"));
				
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
	
	
	//거래게시판 글 삽입 + 트랜잭션 처리
	public int AddMarket(Market_Board market) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			
			conn.setAutoCommit(false);
			
			//Board 삽입
			String sql = "insert into board (idx,title,nick,content,email_id,b_code) "
						+ "values(idx_seq.nextval, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, market.getTitle());
			pstmt.setString(2, market.getNick());
			pstmt.setString(3, market.getContent());
			pstmt.setString(4, market.getEmail_id());
			pstmt.setInt(5, market.getB_code());
			
			row = pstmt.executeUpdate();
			if(row < 0) {
				throw new Exception("Board 삽입 실패");
			}
			
			//거래게시판 삽입
			String sql2 = "insert into market_board(b_idx,idx,m_mode,cate,price,sold,img_name) "
						+ "values(market_idx_seq.nextval, idx_seq.currval, ?, ?, ?, ?, ?)";
			pstmt2 = conn.prepareStatement(sql2);
			

			
			pstmt2.setString(0, "m_mode");
			pstmt2.setString(1, "cate");
			pstmt2.setString(2, "price");
			String sold = "";
			if(market.getSold().equals("판매중")) {
				sold = "F";
			}else if(market.getSold().equals("판매완료")) {
				sold = "T";
			}else if(market.getSold().equals("예약중")){
				sold = "N";
			}
			pstmt2.setString(3, sold);
			pstmt2.setString(4, "img_name");

			row = pstmt2.executeUpdate();
			if(row < 0) {
				throw new Exception("거래게시판 작성 실패");
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
	
	//거래 게시판 특정 글 수정
	public Board EditMarket(int idx) {
		return this.getContent(idx);
		//조회화면 동일 (기존에 있는 함수 재활용)
	}
	
	//이미지 게시판 특정 글 삭제
	public int deleteMarket_Board(int b_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "delete from Market_Board where b_idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b_idx);
			
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
