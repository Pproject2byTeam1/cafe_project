package kr.or.kosa.dto;

public class Chart {
	private int rankpoint;
	private String title;
	private String nick;
	private String w_date;
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
	
	@Override
	public String toString() {
		return "Chart [rankpoint=" + rankpoint + ", title=" + title + ", nick=" + nick + ", w_date=" + w_date + "]";
	}
	
	
	
	
}
