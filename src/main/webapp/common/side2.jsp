<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside id="sidebar" class="sidebar">

	<ul class="sidebar-nav" id="sidebar-nav">

		<!-- 카페 정보 시작-->
		<div class="pagetitle">
			<h1>카페 정보</h1>
			<div class="card-body">
				<a class="card-title">관리자 <span>| <img
						src="image/rank_icon/100.gif" alt="Profile"> ADMIN_NICK
				</span></a><br> <a class="card-title">회원수 <span>| 00명 | 등급안내</span></a><br>
				<a class="card-title">카페 관리<span>| (관리자활성)</span></a>
			</div>
		</div>
		<!-- 카페 정보 끝 -->
		
		<!-- 프로필 시작-->
		<div class="pagetitle">
			<h1>
				<img src="image/rank_icon/1.gif" alt="Profile"> USER_NICK
			</h1>
			<nav>
				<div class="card-body">
					<a class="card-title"><span>준회원 | 100P</span></a><br> <a
						class="card-title"><span>글 14개 | 댓글 160개 | 쪽지 5개</span></a><br>
					<a class="card-title">활동내역 <span>| POINT</span></a><br> <a
						class="card-title">나의 정보 <span>| COUNT</span></a><br> <a
						class="card-title">로그아웃 <span>| COUNT</span></a><br> <a
						class="card-title">가입하기 <span>| (비회훤활성)</span></a><br>
				</div>
			</nav>
		</div>
		<!-- 프로필 끝 -->
		<c:forEach var="infolist" items="${infolist}">
			<li class="nav-item"><c:if test="${infolist.b_type eq 'b1'}">
					<a class="nav-link " href="#"> <i class="bi bi-grid"></i> <span>${infolist.b_name}</span>
					</a>
				</c:if> <c:if test="${infolist.b_type eq 'b2'}">
					<a class="nav-link " href="#"> <i class="bi bi-grid"></i> <span>${infolist.b_name}</span>
					</a>
				</c:if> <c:if test="${infolist.b_type eq 'b3'}">
					<a class="nav-link " href="img_board_list.do?b_code=${infolist.b_code}"> <i
						class="bi bi-grid"></i> <span>${infolist.b_name}</span>
					</a>
				</c:if> <c:if test="${infolist.b_type eq 'b4'}">
					<a class="nav-link " href="#"> <i class="bi bi-grid"></i> <span>${infolist.b_name}</span>
					</a>
				</c:if> <c:if test="${infolist.b_type eq 'b5'}">
					<a class="nav-link " href="#"> <i class="bi bi-grid"></i> <span>${infolist.b_name}</span>
					</a>
				</c:if> <c:if test="${infolist.b_type eq 'b6'}">
					<a class="nav-link " href="calendar_list.do?b_code=${infolist.b_code}"> <i class="bi bi-grid"></i> <span>${infolist.b_name}</span>
					</a>
				</c:if></li>
		</c:forEach>
	</ul>

</aside>