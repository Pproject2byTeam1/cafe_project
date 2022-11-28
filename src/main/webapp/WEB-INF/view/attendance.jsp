<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:forEach var="list" items="${boardlist}" varStatus="status">
<!-- 1round -->
<div class="card-body row">
<hr>
<div class="col-sm-2">
	<h5 class="card-title"><img src="image/rank_icon/${list.hits}.gif" alt="랭크 아이콘">${list.nick}</h5>
</div>
  <div class="col-sm-7 align-self-center"><p class="text-justify ">${list.content}</p></div>
<div class="col-sm-3 align-self-center text-lg-end">
<nav style="--bs-breadcrumb-divider: '|';">
  <ol class="breadcrumb">
    <li class="breadcrumb-item">${list.w_date}</li>
    <c:if test="${list.email_id == userId}">
    <li class="breadcrumb-item active"><a href="#" class="btn btn-warning" id="attendanceDelete">삭제</a></li>
    </c:if>
  </ol>
</nav>
</div>
</div>
<!-- 1round -->
</c:forEach>