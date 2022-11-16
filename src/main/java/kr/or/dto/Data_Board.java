package kr.or.dto;

public class Data_Board {
	private int b_idx;
	private int idx;
	private String ori_name;
	private String save_name;
	private int volume;
	private int refer;
	private int depth;
	private int step;
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
	public String getOri_name() {
		return ori_name;
	}
	public void setOri_name(String ori_name) {
		this.ori_name = ori_name;
	}
	public String getSave_name() {
		return save_name;
	}
	public void setSave_name(String save_name) {
		this.save_name = save_name;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
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
		return "Data_Board [b_idx=" + b_idx + ", idx=" + idx + ", ori_name=" + ori_name + ", save_name=" + save_name
				+ ", volume=" + volume + ", refer=" + refer + ", depth=" + depth + ", step=" + step + "]";
	}
	
	
}
