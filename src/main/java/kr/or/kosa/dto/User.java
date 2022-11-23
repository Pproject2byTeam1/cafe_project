package kr.or.kosa.dto;

public class User {
	private String email_id;
	private String password;
	private String name;
	private String nick;
	private String birth;
	private int point;
	private String isAdmin;
	private int rank;
	
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Override
	public String toString() {
		return "User [email_id=" + email_id + ", password=" + password + ", name=" + name + ", nick=" + nick
				+ ", birth=" + birth + ", point=" + point + ", isAdmin=" + isAdmin + ", rank=" + rank + "]";
	}
	
	
}
