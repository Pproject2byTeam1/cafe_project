package kr.or.dto;

public class Yes {
	private int yes_idx;
	private String email_id;
	private int idx;
	public int getYes_idx() {
		return yes_idx;
	}
	public void setYes_idx(int yes_idx) {
		this.yes_idx = yes_idx;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	@Override
	public String toString() {
		return "Yes [yes_idx=" + yes_idx + ", email_id=" + email_id + ", idx=" + idx + "]";
	}
	
	
	
}
