package kr.or.kosa.action;

/*
 servlet(front) 요청 받음
 1. 화면 출력
 2. 로직 처리
 
 화면아니면 로직
 
 */

public class ActionForward {
	private boolean isRedirect = false; //view 전환 여부 .. redirect or forward
	private String path = null; //이동 경로 (뷰의 주소)
	
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
}
