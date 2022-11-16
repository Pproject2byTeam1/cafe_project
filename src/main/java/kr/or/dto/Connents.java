package kr.or.dto;

import java.util.Date;

public class Connents {

	private int co_idx;
	private int idx;
	private String content;
	private String email_id;
	private String nick;
	private Date w_date;
	private int report_count;
	private int refer;
	private int depth;
	private int step;
	public int getCo_idx() {
		return co_idx;
	}
	public void setCo_idx(int co_idx) {
		this.co_idx = co_idx;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
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
	public int getRefer() {
		return refer;
	}
	public void setRefer(int refer) {
		this.refer = refer;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	
	@Override
	public String toString() {
		return "Connents [co_idx=" + co_idx + ", idx=" + idx + ", content=" + content + ", email_id=" + email_id
				+ ", nick=" + nick + ", w_date=" + w_date + ", report_count=" + report_count + ", refer=" + refer
				+ ", depth=" + depth + ", step=" + step + "]";
	}
	
	
	
}
