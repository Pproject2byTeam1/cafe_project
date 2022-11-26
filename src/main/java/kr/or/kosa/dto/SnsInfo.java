package kr.or.kosa.dto;

public class SnsInfo {
	private String name;
	private String email_id;
	private String nick;
	private String sns_id;
	private String sns_type;
	private String sns_name;
	private String sns_profile;
	private String sns_connect_date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getSns_id() {
		return sns_id;
	}
	public void setSns_id(String sns_id) {
		this.sns_id = sns_id;
	}
	public String getSns_type() {
		return sns_type;
	}
	public void setSns_type(String sns_type) {
		this.sns_type = sns_type;
	}
	public String getSns_name() {
		return sns_name;
	}
	public void setSns_name(String sns_name) {
		this.sns_name = sns_name;
	}
	public String getSns_profile() {
		return sns_profile;
	}
	public void setSns_profile(String sns_profile) {
		this.sns_profile = sns_profile;
	}
	public String getSns_connect_date() {
		return sns_connect_date;
	}
	public void setSns_connect_date(String sns_connect_date) {
		this.sns_connect_date = sns_connect_date;
	}
	@Override
	public String toString() {
		return "SnsInfo [name=" + name + ", email_id=" + email_id + ", nick=" + nick + ", sns_id=" + sns_id
				+ ", sns_type=" + sns_type + ", sns_name=" + sns_name + ", sns_profile=" + sns_profile
				+ ", sns_connect_date=" + sns_connect_date + "]";
	}
	
}
