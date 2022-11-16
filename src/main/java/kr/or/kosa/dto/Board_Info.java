package kr.or.kosa.dto;

public class Board_Info {
	private int b_code;
	private String b_name;
	private int side_idx;
	private int main_idx;
	private String b_type;
	
	public int getB_code() {
		return b_code;
	}
	public void setB_code(int b_code) {
		this.b_code = b_code;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public int getSide_idx() {
		return side_idx;
	}
	public void setSide_idx(int side_idx) {
		this.side_idx = side_idx;
	}
	public int getMain_idx() {
		return main_idx;
	}
	public void setMain_idx(int main_idx) {
		this.main_idx = main_idx;
	}
	public String getB_type() {
		return b_type;
	}
	public void setB_type(String b_type) {
		this.b_type = b_type;
	}
	
	@Override
	public String toString() {
		return "Board_Info [b_code=" + b_code + ", b_name=" + b_name + ", side_idx=" + side_idx + ", main_idx="
				+ main_idx + ", b_type=" + b_type + "]";
	}
	
	
	
}
