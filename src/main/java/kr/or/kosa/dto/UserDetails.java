package kr.or.kosa.dto;

public class UserDetails extends User {
	private String email_id;
	private String join_date;
	private String last_visit_date;
	private int visit_count;
	private int w_count;
	private int re_count;
	private String year_birth;
	private String phone;

	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	public String getLast_visit_date() {
		return last_visit_date;
	}
	public void setLast_visit_date(String last_visit_date) {
		this.last_visit_date = last_visit_date;
	}
	public int getVisit_count() {
		return visit_count;
	}
	public void setVisit_count(int visit_count) {
		this.visit_count = visit_count;
	}
	public int getW_count() {
		return w_count;
	}
	public void setW_count(int w_count) {
		this.w_count = w_count;
	}
	public int getRe_count() {
		return re_count;
	}
	public void setRe_count(int re_count) {
		this.re_count = re_count;
	}
	public String getYear_birth() {
		return year_birth;
	}
	public void setYear_birth(String year_birth) {
		this.year_birth = year_birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User_Details [email_id=" + email_id + ", join_date=" + join_date + ", last_visit_date="
				+ last_visit_date + ", visit_count=" + visit_count + ", w_count=" + w_count + ", re_count=" + re_count
				+ ", year_birth=" + year_birth + ", phone=" + phone + "]";
	}
	
	
	
	
	
}
