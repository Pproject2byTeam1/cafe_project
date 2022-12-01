<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	

    <div class="d-flex align-items-center justify-content-start">
      <a href="cafemain.do" class="logo d-flex align-items-center">
        <img src="upload/${banner.cafe_icon}" alt="icon" onerror="this.onerror=null; this.src='assets/img/logo.png'">
        <span class="d-none d-lg-block">${banner.cafe_name}</span>
      </a>
    </div><!-- End Logo -->

   <div class="d-flex align-items-center justify-content-end">
      <a href="cafemain.do" class="logo d-flex align-items-center">
        <span class="d-none d-lg-block">뒤로가기</span>
      </a>
   </div>
