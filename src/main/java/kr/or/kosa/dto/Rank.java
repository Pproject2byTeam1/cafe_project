package kr.or.kosa.dto;

public class Rank {
	private int rank;
	private String r_name;
	private int r_point;
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public int getR_point() {
		return r_point;
	}
	public void setR_point(int r_point) {
		this.r_point = r_point;
	}
	
	@Override
	public String toString() {
		return "Rank [rank=" + rank + ", r_name=" + r_name + ", r_point=" + r_point + "]";
	}
	
	
}
