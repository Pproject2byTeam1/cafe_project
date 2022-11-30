<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
   
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>카페人중독</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
   href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
   rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
   rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
   rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
   rel="stylesheet">
<link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
<link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<style>
/* .btn-success btn-lg {
   float: right;
} */
.selectpicker{
width:75px;height:35px; border-radius: 5px;
}
._3Espq6{
width :38px;
height:22px;
font-size:0.9rem;
 text-align:center;
}
.parent{
   width :500px;
 display: flex;
  flex-direction: column;
}
.name{
font-size:0.9rem;
  margin-top:auto;

vertical-align :bottom;

}
._1R-fi-{
margin-bottom: 0.5rem;
    color: #2b2d36;
    line-height: 1.5;
    font-weight: 700;
    font-size: 1.5rem;
    letter-spacing: -0.01875rem;
    margin-bottom: 0;
    color: var(--gray-600);
    text-align: center;
}


.jdc{
text-align: center;
}
 
.son_name{
   font-size: 13px; font-weight:bold;
}
.son_date{
   font-size: 13px;
}
.son_time{
   font-size: 13px; 
}
     
</style>
</head>

<body>
<c:set var="pagesize" value='<%=request.getAttribute("pagesize")%>' />
	<c:set var="cpage" value='<%=request.getAttribute("cpage")%>' />
	<c:set var="pagecount" value='<%=request.getAttribute("pagecount")%>' />
   <!-- ======= Header ======= -->
   <header id="header" class="header fixed-top d-flex align-items-center">
		<jsp:include page="/common/side2.jsp"></jsp:include>
      <jsp:include page="/common/top.jsp"></jsp:include>

   </header>
   <!-- End Header -->

   <!-- ======= Sidebar ======= -->

   <jsp:include page="/common/side.jsp"></jsp:include>

   <!-- End Sidebar -->

   <main id="main" class="main">

      <div class="pagetitle" >
         <h1>자료 게시판</h1>
         <nav>
            <ol class="breadcrumb">
               <li class="breadcrumb-item"><a href="index.html">Home</a></li>
               <li class="breadcrumb-item active">자료게시판</li>
            </ol>
         </nav>
      </div>
      <!-- End Page Title -->
      <!--글쓰기 버튼  -->
      <div align="right">
      <a></a>
   <p><a href="data_content.do">글쓰기 게시판</a></p>
         <button type="button" class="btn btn-success" location.href ='data_content.do' >글쓰기</button>&nbsp;
            <select class="selectpicker" data-width="75px" name="ps" onchange="submit()">
               <c:forEach var="i" begin="5" end="20" step="5">
							   		<c:choose>
							   			<c:when test="${pagesize == i}">
							   				<option value="${i}" selected>${i}건</option>
							   			</c:when>
						   				<c:otherwise>
						   					<option value="${i}">${i}건 </option>
						   				</c:otherwise>
							   		</c:choose>
							   </c:forEach>
      
             </select>
      </div>

      <!--  <div><br></div>-->
      <!--리스트테이블  -->
      <div class="card">
         <div class="card-body">

            <!-- Table with hoverable rows -->
            <table class="table table-hover" >
               <thead>
                  <tr>
                   <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                  </tr>
               </thead>
               <tbody>
               		<!-- 데이터가 한건도 없는 경우  -->
				<c:if test="${datalist == null}">
					<tr><td colspan='5'>데이터가 없습니다</td></tr>
				</c:if>
				<!--목록출력하기  -->
               <c:forEach var="board" items="${datalist}">
                  <tr>
                     <th scope="row"><input type="checkbox"></th>
                     <td>
                     <span class="mt-4 parent"><h3>${board.title}</h3></span><br>
                     <span class="son_name">${board.nick}</span>
                     <span class="son_date">${board.w_date}</span>
                     <span class="son_time"> </span>
                     </td>
                     <td></td>
                    
                  <td class="p-5 jdc"><span class="_3Espq6" >
                     <span class="_1R-fi-">${board.hits}</span><br>
                     <span>조회</span>
                     </span>
                  </td>
                  
                  <td class="p-5 jdc"><span class="_3Espq6" var="comment" items="${countlist}">
                     <span class="_1R-fi-">${countlist}</span><br>
                     <span>댓글</span>
                     </span>
                  </td>
                  <input type="hideen" >
                  <td class="p-5 jdc"><span class="_3Espq6" var="yes" items="${yeslist}">
                     <span class="_1R-fi-">${yeslist}</span><br>
                     <span>추천</span>
                  </td>
                  </tr>
                  
                
                 <%--  </c:forEach> --%>
                 
                
             <c:if test="${board.depth>0}">     
   <tr>	
         <th scope="row"><input type="checkbox"></th>
                     <td>
                   &nbsp;&nbsp;&nbsp;
                     <h3 ><img src="./image/re.gif">
                     [Re]${board.title}</h3>
                     
                  
                     </td>
                     <td></td>
                     
                  <td class="p-5 jdc"><span class="_3Espq6" >
                     <span class="_1R-fi-">${board.hits}</span><br>
                     <span>조회</span>
                     </span>
                  </td>
                  <td class="p-5 jdc"><span class="_3Espq6" var="yes" items="${yeslist}">
                     <span class="_1R-fi-">${yeslist}</span><br>
                     <span>댓글</span>
                     </span>
                  </td>
                  <td class="p-5 jdc"><span class="_3Espq6" var="comment" items="${countlist}">
                     <span class="_1R-fi-">${countlist}</span><br>
                     <span>추천
                  </td>
                  </tr> 
                  </c:if>
                  </c:forEach> 
               </tbody>
            </table>
            <!-- End Table with hoverable rows -->

         </div>

      </div>
      <!--하단 버튼  -->
      <div align="right">
         <button type="button" class="btn btn-secondary">글쓰기</button>&nbsp;
         <button type="button" class="btn btn-secondary">삭제</button>

      </div>
      
          <nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
					
		                <c:if test="${cpage > 1}">
		                  <li class="page-item">
		                    <a class="page-link" href="user_list.do?cp=${cpage-1}&ps=${pagesize}" tabindex="-1" aria-disabled="true"><<</a>
		                  </li>
	                    </c:if>
	                    	
	                    <c:forEach var="i" begin="1" end="${pagecount}" step="1">
	                    	<c:choose>
								<c:when test="${cpage==i}">
										<li class="page-item"><a class="page-link active" >${i}</a></li>
								</c:when>
								<c:otherwise>
		                  			<li class="page-item"><a class="page-link" href="user_list.do?cp=${i}&ps=${pagesize}">${i}</a></li>
								</c:otherwise>
							</c:choose>
	                    </c:forEach>
	                    
	                    <c:if test="${cpage < pagecount}">
	                    	<li class="page-item">
							<a class="page-link" href="user_list.do?cp=${cpage+1}&ps=${pagesize}">>></a>
							</li>
						</c:if>
					</ul>
				</nav><!-- End Centered Pagination -->
   </main>
   <!-- End #main -->

   <!-- ======= Footer ======= -->
   <footer id="footer" class="footer">
      <div class="copyright">
         &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights
         Reserved
      </div>
      <div class="credits">
         <!-- All the links in the footer should remain intact. -->
         <!-- You can delete the links only if you purchased the pro version. -->
         <!-- Licensing information: https://bootstrapmade.com/license/ -->
         <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
         Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
   </footer>
   <!-- End Footer -->

   <a href="#"
      class="back-to-top d-flex align-items-center justify-content-center"><i
      class="bi bi-arrow-up-short"></i></a>

   <!-- Vendor JS Files -->
   <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
   <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
   <script src="assets/vendor/chart.js/chart.min.js"></script>
   <script src="assets/vendor/echarts/echarts.min.js"></script>
   <script src="assets/vendor/quill/quill.min.js"></script>
   <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
   <script src="assets/vendor/tinymce/tinymce.min.js"></script>
   <script src="assets/vendor/php-email-form/validate.js"></script>

   <!-- Template Main JS File -->
   <script src="assets/js/main.js"></script>

</body>

</html>