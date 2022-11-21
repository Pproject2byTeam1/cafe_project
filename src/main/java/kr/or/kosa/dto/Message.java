package kr.or.kosa.dto;

import java.util.Date;

public class Message {
	private int m_idx;
	private String send_id;
	private String send_nick;
	private String receive_id;
	private String receive_nick;
	private String m_content;
	private String m_date;
	public int getM_idx() {
		return m_idx;
	}
	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	public String getSend_nick() {
		return send_nick;
	}
	public void setSend_nick(String send_nick) {
		this.send_nick = send_nick;
	}
	public String getReceive_id() {
		return receive_id;
	}
	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
	}
	public String getReceive_nick() {
		return receive_nick;
	}
	public void setReceive_nick(String receive_nick) {
		this.receive_nick = receive_nick;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		this.m_content = m_content;
	}
	public String getM_date() {
		return m_date;
	}
	public void setM_date(String m_date) {
		this.m_date = m_date;
	}
	@Override
	public String toString() {
		return "Message [m_idx=" + m_idx + ", send_id=" + send_id + ", send_nick=" + send_nick + ", receive_id="
				+ receive_id + ", receive_nick=" + receive_nick + ", m_content=" + m_content + ", m_date=" + m_date
				+ "]";
	}
	
	
	
}
