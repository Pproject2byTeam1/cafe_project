<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<aside id="sidebar" class="sidebar">

	<ul class="sidebar-nav" id="sidebar-nav">

		<!-- 카페 정보 시작-->
		<c:if test="${member.isAdmin eq 'M' }">
			<div class="pagetitle">
				<h1>카페 정보</h1>
				<div class="card-body">
					<a class="card-title">관리자 
						<span>| <img src="image/rank_icon/100.gif" alt="Profile"> ADMIN_NICK </span>
					</a><br> 
					<a class="card-title">회원수 <span>| 00명 | 등급안내</span></a><br>
					<a class="card-title">카페 관리<span>| (관리자활성)</span></a>
				</div>
			</div>
			<!-- 카페 정보 끝 -->
		</c:if>
			<!-- 프로필 시작-->
		<c:if test="${member != null }">
			<div class="pagetitle card mt-2 pt-4">
				<div class="card-body">
					<div class="card d-flex justify-content-center pb-2">
						<div class="card-body mt-4 ms-5 ps-1 pt-1">
							<h1>
								<img class="pb-2" src="image/rank_icon/${member.rank}.gif" alt="Profile"> ${member.nick } 
							</h1>
						</div>
					</div>
					<div class="card justify-content-center">
						<div class="card-body mt-4 ms-3 ps-4">
							<span>&ensp;&ensp;&ensp;&ensp; ${member.rank} | ${member.point}P</span><br> 
							<span>글 ${member.w_count}개 | 댓글 ${member.re_count}개 </span><br>
							<span>&ensp;&ensp;&ensp; ${member.point}&nbsp;POINT</span><br> 
						</div>
					</div>
				</div>
			</div>
		</c:if>
		
		<!-- 프로필 끝 -->
		<c:forEach var="infolist" items="${infolist}">
			<li class="nav-item">
				<c:if test="${infolist.b_type eq 'b1'}">
					<a class="nav-link " href="regular_list.do?b_code=${infolist.b_code}"> <i
					 	class="bi bi-grid"></i> <span>${infolist.b_name}</span>
					</a>
				</c:if> 
				<c:if test="${infolist.b_type eq 'b2'}">
					<a class="nav-link " href="#"> <i class="bi bi-grid"></i> <span>${infolist.b_name}</span>
					</a>
				</c:if> 
				<c:if test="${infolist.b_type eq 'b3'}">
					<a class="nav-link " href="img_board_list.do?b_code=${infolist.b_code}"> <i
						class="bi bi-grid"></i> <span>${infolist.b_name}</span>
					</a>
				</c:if> 
				<c:if test="${infolist.b_type eq 'b4'}">
					<a class="nav-link " href="data_list.do?b_code=${infolist.b_code}"> <i class="bi bi-grid"></i> <span>${infolist.b_name}</span>
					</a>
				</c:if> 
				<c:if test="${infolist.b_type eq 'b5'}">
					<a class="nav-link " href="marketboard_list.do?b_code=${infolist.b_code}"> <i class="bi bi-grid"></i> <span>${infolist.b_name}</span>
					</a>
				</c:if> 
				<c:if test="${infolist.b_type eq 'b6'}">
					<a class="nav-link " href="calendar_list.do?b_code=${infolist.b_code}"> <i class="bi bi-grid"></i> 
					<span>${infolist.b_name}</span>
					</a>
				</c:if></li>
		</c:forEach>
	</ul>

</aside>