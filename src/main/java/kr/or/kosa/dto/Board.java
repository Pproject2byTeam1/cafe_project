package kr.or.kosa.dto;

import java.util.Date;

public class Board {

	private int idx;
	private String title;
	private String nick;
	private String content;
	private int hits;
	private Date w_date;
	private int report_count;
	private String notic;
	private String email_id;
	private int b_code;
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public Date getW_date() {
		return w_date;
	}
	public void setW_date(Date w_date) {
		this.w_date = w_date;
	}
	public int getReport_count() {
		return report_count;
	}
	public void setReport_count(int report_count) {
		this.report_count = report_count;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public int getB_code() {
		return b_code;
	}
	public void setB_code(int b_code) {
		this.b_code = b_code;
	}
	public String getNotic() {
		return notic;
	}
	public void setNotic(String notic) {
		this.notic = notic;
	}
	@Override
	public String toString() {
		return "Board [idx=" + idx + ", title=" + title + ", nick=" + nick + ", content=" + content + ", hits=" + hits
				+ ", w_date=" + w_date + ", report_count=" + report_count + ", notic=" + notic + ", email_id="
				+ email_id + ", b_code=" + b_code + "]";
	}
	
	
	
}
