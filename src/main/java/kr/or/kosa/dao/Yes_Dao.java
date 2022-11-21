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

import kr.or.kosa.dto.Yes;

public class Yes_Dao {
	
	DataSource ds = null;

	public Yes_Dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
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
			
			if(rs.next()) {
				do {
					Yes yes = new Yes();
					yes.setYes_idx(rs.getInt("yes_idx"));
					yes.setEmail_id(rs.getString("email_id"));
					yes.setIdx(rs.getInt("idx"));
					
					yeslist.add(yes);
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
		
		return yeslist;
	}
	
}
