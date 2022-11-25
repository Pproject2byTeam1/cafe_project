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

import kr.or.kosa.dto.Board_Type;

//게시판 형식
public class Board_Type_Dao {

DataSource ds = null;
	
	public Board_Type_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//게시판 형식 전체 조회
	public List<Board_Type> getBoardTypeListAll(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board_Type> boardtypelist = new ArrayList<Board_Type>();
		
		try {
			
			conn = ds.getConnection();
			String sql = "select b_type, b_type_name from Board_Type";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					Board_Type board_type = new Board_Type();
					board_type.setB_type(rs.getString("b_type"));
					board_type.setB_type_name(rs.getString("b_type_name"));
					
					boardtypelist.add(board_type);
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
		
		return boardtypelist;
		
	}

	
	
	
}
