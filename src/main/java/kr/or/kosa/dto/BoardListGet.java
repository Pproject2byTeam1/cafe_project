package kr.or.kosa.dto;

public class BoardListGet extends Board {
	private String b_name;//게시판 종류이름
	private int c_count;//댓글 수
	private int like;//좋아요 수
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public int getC_count() {
		return c_count;
	}
	public void setC_count(int c_count) {
		this.c_count = c_count;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "BoardListGet [b_name=" + b_name + ", c_count=" + c_count + ", like=" + like + "]";
	}
	
}
