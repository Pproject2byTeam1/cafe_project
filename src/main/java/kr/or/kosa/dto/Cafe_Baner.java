package kr.or.kosa.dto;

public class Cafe_Baner {
	
	private String cafe_name;
	private String cafe_img;
	private String cafe_icon;
	
	public String getCafe_name() {
		return cafe_name;
	}
	public void setCafe_name(String cafe_name) {
		this.cafe_name = cafe_name;
	}
	public String getCafe_img() {
		return cafe_img;
	}
	public void setCafe_img(String cafe_img) {
		this.cafe_img = cafe_img;
	}
	public String getCafe_icon() {
		return cafe_icon;
	}
	public void setCafe_icon(String cafe_icon) {
		this.cafe_icon = cafe_icon;
	}
	
	@Override
	public String toString() {
		return "Cafe_Baner [cafe_name=" + cafe_name + ", cafe_img=" + cafe_img + ", cafe_icon=" + cafe_icon + "]";
	}
	
}
