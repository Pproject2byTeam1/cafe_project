package kr.or.dto;

public class Board_Type {
	private String b_type;
	private String b_type_name;
	public String getB_type() {
		return b_type;
	}
	public void setB_type(String b_type) {
		this.b_type = b_type;
	}
	public String getB_type_name() {
		return b_type_name;
	}
	public void setB_type_name(String b_type_name) {
		this.b_type_name = b_type_name;
	}
	@Override
	public String toString() {
		return "Board_Type [b_type=" + b_type + ", b_type_name=" + b_type_name + "]";
	}
	
	
}
