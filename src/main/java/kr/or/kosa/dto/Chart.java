package kr.or.kosa.dto;

public class Chart {
	private int rankpoint;
	private String title;
	private String nick;
	private String w_date;
	private String b_name;
	private int hit_count;
	private int idx;
	private int b_code;
	private String email_id;
	private int hits;
	private String month;
	private int count;
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Chart [rankpoint=" + rankpoint + ", title=" + title + ", nick=" + nick + ", w_date=" + w_date
				+ ", b_name=" + b_name + ", hit_count=" + hit_count + ", idx=" + idx + ", b_code=" + b_code
				+ ", email_id=" + email_id + ", hits=" + hits + ", month=" + month + ", count=" + count + "]";
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getB_code() {
		return b_code;
	}
	public void setB_code(int b_code) {
		this.b_code = b_code;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public int getHit_count() {
		return hit_count;
	}
	public void setHit_count(int hit_count) {
		this.hit_count = hit_count;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public int getRankpoint() {
		return rankpoint;
	}
	public void setRankpoint(int rankpoint) {
		this.rankpoint = rankpoint;
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
	public String getW_date() {
		return w_date;
	}
	public void setW_date(String w_date) {
		this.w_date = w_date;
	}
	
	
	
	
	
}
