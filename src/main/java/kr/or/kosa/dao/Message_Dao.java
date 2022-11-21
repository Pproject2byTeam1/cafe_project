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

import kr.or.kosa.dto.Message;

public class Message_Dao {
	DataSource ds = null;
	
	public Message_Dao() throws NamingException{
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//1. 전체 쪽지조회
	public List<Message> getAllMessage(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Message> messagelist = null;
		
		try {
			
			conn = ds.getConnection();
			String sql = "SELECT M_IDX, SEND_ID, send_nick, RECEIVE_ID, receive_nick, m_content, to_char(m_date, 'yyyy-MM-dd') as m_date from message order BY m_idx desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			messagelist = new ArrayList<Message>();
			
			if(rs.next()) {
				Message message = new Message(); 
				message.setM_idx(rs.getInt("M_IDX"));
				message.setSend_id(rs.getString("SEND_ID"));
				message.setSend_nick(rs.getString("SEND_NICK"));
				message.setReceive_id(rs.getString("RECEIVE_ID"));
				message.setReceive_nick(rs.getString("RECEIVE_NICK"));
				message.setM_content(rs.getString("M_CONTENT"));
				message.setM_date(rs.getString("M_DATE"));
				
				messagelist.add(message);
				
			}else {
				System.out.println("조회 데이터 없음");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return messagelist;
	}
	
	//2. 특정 발신인의 쪽지 조회
	public List<Message> getMessageByReceiveId(String receive_id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Message> messagelist = null;
		
		try {
			
			conn = ds.getConnection();
			String sql = "SELECT M_IDX, SEND_ID, send_nick, RECEIVE_ID, receive_nick, m_content, to_char(m_date, 'yyyy-MM-dd') as m_date from message WHERE receive_id =? order BY m_idx desc";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, receive_id);
			
			rs = pstmt.executeQuery();
			
			messagelist = new ArrayList<Message>();
			
			if(rs.next()) {
				do {
					
					Message message = new Message(); 
					message.setM_idx(rs.getInt("m_idx"));
					message.setSend_id(rs.getString("send_id"));
					message.setSend_nick(rs.getString("send_nick"));
					message.setReceive_id(rs.getString("receive_id"));
					message.setReceive_nick(rs.getString("receive_nick"));
					message.setM_content(rs.getString("m_content"));
					message.setM_date(rs.getString("m_date"));
					
					messagelist.add(message);
					
				}while(rs.next());
				
			}else {
				System.out.println("조회 데이터 없음");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return messagelist;
	}
	
	//3. 쪽지 작성
	public int insertMessage(Message message) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "insert into message(M_IDX, SEND_ID, send_nick, RECEIVE_ID, receive_nick, m_content) "+
			"values(M_IDX_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, message.getSend_id());
			pstmt.setString(2, message.getSend_nick());
			pstmt.setString(3, message.getReceive_id());
			pstmt.setString(4, message.getReceive_nick());
			pstmt.setString(5, message.getM_content());
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return row;
	}
	
	//4. 특정 쪽지 삭제
	public int deleteMessageByIdx(String M_IDX){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "delete from message WHERE M_IDX in (?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, M_IDX);
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return row;
	}
}
