package kr.or.kosa.dto;


public class Calender extends Board {
	private int b_idx;
	private int idx;
	private String start_date;
	private String end_date;
	private String finish;
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getFinish() {
		return finish;
	}
	public void setFinish(String finish) {
		this.finish = finish;
	}
	@Override
	public String toString() {
		return "Calender [b_idx=" + b_idx + ", idx=" + idx + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", finish=" + finish + "]";
	}
	
	
}
