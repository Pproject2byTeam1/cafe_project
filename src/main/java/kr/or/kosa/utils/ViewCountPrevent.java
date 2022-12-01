package kr.or.kosa.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.Board_Dao;

public class ViewCountPrevent {

	public void viewCountPrevent(int idx, HttpServletRequest request, HttpServletResponse response) {
		
		//조회수 중복 방지
		Cookie oldCookie = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("postView")) {
					oldCookie = cookie;
				}
			}
		}
		
		try {
			
			Board_Dao bdao = new Board_Dao();
			
			if (oldCookie != null) { //postView 쿠키를 가지고는 있음
				if (!oldCookie.getValue().contains("[" + idx + "]")) { // 현재 게시글의 idx를 가지고 있는지 판별해 없으면

					bdao.updateHits(idx);
					oldCookie.setValue(oldCookie.getValue() + "_[" + idx + "]");
					oldCookie.setPath("/");
					oldCookie.setMaxAge(60 * 60 * 24);

					response.addCookie(oldCookie);
				}
			} else { //해당 쿠키 부재
				bdao.updateHits(idx);
				Cookie newCookie = new Cookie("postView", "[" + idx + "]");
				newCookie.setPath("/");
				newCookie.setMaxAge(60 * 60 * 24);
				response.addCookie(newCookie);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
