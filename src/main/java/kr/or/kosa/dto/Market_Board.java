package kr.or.kosa.dto;

public class Market_Board extends Board{
	private int b_idx;
	private int idx;
	private String m_mode;
	private String cate;
	private int price;
	private String sold;
	private String img_name;
	
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
	public String getM_mode() {
		return m_mode;
	}
	public void setM_mode(String m_mode) {
		this.m_mode = m_mode;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSold() {
		return sold;
	}
	public void setSold(String sold) {
		this.sold = sold;
	}
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	@Override
	public String toString() {
		return "Market_Board [b_idx=" + b_idx + ", idx=" + idx + ", m_mode=" + m_mode + ", cate=" + cate + ", price="
				+ price + ", sold=" + sold + ", img_name=" + img_name + "]";
	}
	
}
