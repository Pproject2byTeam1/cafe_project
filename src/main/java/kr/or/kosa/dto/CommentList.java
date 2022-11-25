package kr.or.kosa.dto;

public class CommentList {
	private int co_idx;
	private int idx;
	private String title;
	private int c_count;
	private String content;
	private String w_date;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getC_count() {
		return c_count;
	}
	public void setC_count(int c_count) {
		this.c_count = c_count;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getW_date() {
		return w_date;
	}
	public void setW_date(String w_date) {
		this.w_date = w_date;
	}
	@Override
	public String toString() {
		return "CommnetList [co_idx=" + co_idx + ", idx=" + idx + ", title=" + title + ", c_count=" + c_count
				+ ", content=" + content + ", w_date=" + w_date + "]";
	}
	
}
