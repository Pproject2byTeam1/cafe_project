<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${messagelist == null}">
	<p>보관중인 쪽지가 없습니다</p>
</c:if>
<c:forEach var="list" items="${messagelist}" varStatus="status">
   		<div class="col-sm-12">
          	  <div class="form-check">
          	      <input class="form-check-input" type="checkbox" value="${list.m_idx}">
           <table>
           <tr>
            <td><img src="image/rank_icon/${list.send_rank}.gif"></td>
            <td>
            <a data-bs-toggle="dropdown"><h5 class="card-title">${list.send_nick}</h5></a>
            	<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                  	<li class="anw_memo"><a class="dropdown-item sendToMemo" href="write_memo.do?sender_id=${list.send_id}" >답장 보내기</a></li>
                  </ul>
            </td>
            <td>${list.m_date}</td>
           </tr>
           <tr>
           	<td colspan="3"><p>${list.m_content}</p></td>
           </tr>
        </table>
          </div>
        </div>
        <hr>
</c:forEach>