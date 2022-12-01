<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${fn:length(board) == 0}">
<tr>
<td colspan = 4>데이터가 없습니다.</td>
</tr>
</c:if>
<c:forEach var="board" items="${board}" varStatus="status">
<tr class="listrow">
<td class="td1">
<span class="d-none b_code">${board.b_code}</span>
${board.title}
<span class="d-none idx">${board.idx}</span>
<c:choose>
<c:when test="${b==1}">
<span class="badge bg-success rounded-pill">${board.c_count}</span>
</c:when>
<c:when test="${b==2}">
<span class="badge bg-primary rounded-pill">${board.c_count}</span>
</c:when>
<c:when test="${b==3}">
<span class="badge bg-danger rounded-pill">${board.c_count}</span>
</c:when>
<c:when test="${b==4}">
<span class="badge bg-warning rounded-pill">${board.c_count}</span>
</c:when>
<c:otherwise></c:otherwise>
</c:choose>
</td>
<td><a data-bs-toggle="dropdown">${board.nick}</a>
<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
<li class="anw_memo"><a class="dropdown-item sendToMemo" href="write_memo.do?sender_id=${board.email_id}" >답장 보내기</a></li>
<li class="view_user_activity"><a class="dropdown-item viewActivity" href="user_activity.do?email_id=${board.email_id}" >활동 내역 보기</a></li>
</ul>
</td>
<td class="w_date">${board.w_date}</td>
<td>${board.hits}</td>
</tr>
</c:forEach>