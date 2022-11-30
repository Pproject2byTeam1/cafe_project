package kr.or.kosa.dto;

public class Board_Rank {
	private int b_code;
	private int w_rank;
	private int re_rank;
	
	public int getB_code() {
		return b_code;
	}
	public void setB_code(int b_code) {
		this.b_code = b_code;
	}
	public int getW_rank() {
		return w_rank;
	}
	public void setW_rank(int w_rank) {
		this.w_rank = w_rank;
	}
	public int getRe_rank() {
		return re_rank;
	}
	public void setRe_rank(int re_rank) {
		this.re_rank = re_rank;
	}
	@Override
	public String toString() {
		return "Board_Rank [b_code=" + b_code + ", w_rank=" + w_rank + ", re_rank=" + re_rank + "]";
	}
	
	
}
