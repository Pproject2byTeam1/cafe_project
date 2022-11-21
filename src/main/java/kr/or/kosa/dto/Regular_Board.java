package kr.or.kosa.dto;

public class Regular_Board{
	private int b_idx;
	private int idx;
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
		return "Reular_Board [b_idx=" + b_idx + ", idx=" + idx + ", refer=" + refer + ", depth=" + depth + ", step="
				+ step + "]";
	}
	
	
	
}
