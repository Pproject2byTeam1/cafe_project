package kr.or.dto;

public class Img_Board {
	private int b_idx;
	private int idx;
	private int img_name;
	
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
	public int getImg_name() {
		return img_name;
	}
	public void setImg_name(int img_name) {
		this.img_name = img_name;
	}
	@Override
	public String toString() {
		return "Img_Board [b_idx=" + b_idx + ", idx=" + idx + ", img_name=" + img_name + "]";
	}
	
}
