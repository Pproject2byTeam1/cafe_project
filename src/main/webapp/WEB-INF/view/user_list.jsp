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

<!-- jQuery -->
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

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
<link href="assets/css/free.css" rel="stylesheet">

<!-- sweetalert -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>


	$(function(){
		
		
		function del(data){
		
			$.ajax({
				url:"DeleteUser",
				data:data,
				dataType:"html",
				success:function(responsetxt){
				
					 $('#rapportlist').remove();
					
				}
				
		});
				
		};
		
		
		
		$(document).on('click', '.deletuser', function(){
		
			const data ={"email_id" : $('div').children('.id').val()};
			
			console.log(data);
			del(data);
			alert("강퇴되었습니다");
	});
	
	});




</script>
</head>

<body>

   <!-- ======= Header ======= -->
     <header id="header" class="header fixed-top d-flex align-items-center">
        <c:import url="/WEB-INF/view/common/top.jsp" />
     </header><!-- End Header -->
     
     <!-- ======= Sidebar ======= -->
     <c:import url="/WEB-INF/view/common/side.jsp" />
     <!-- End Sidebar -->

	<main id="main" class="main">
		<div class="pagetitle">
			<h1>회원조회</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">Home</a></li>
					<li class="breadcrumb-item active">회원조회</li>
				</ol>
			</nav>
		</div>
		
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-1" >
					<form name="list">
						<select class="form-select" name="ps" onchange="submit()">
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
					</form>
				</div>
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<div class="row">
						<div class="col-md-5"></div>
						<div class="col-md-1"></div>
						<div class="col-md-5">
							<select class="form-select">
								<option>전체조회</option>
								<option>OO조회</option>
								<option>OO조회</option>
							</select>
						</div>
					</div>
				</div>
	
				<div class="col-md-3">
					<div class="search-bar">
						<form class="search-form d-flex align-items-center" method="POST" action="#">
							<input type="text" name="query" placeholder="Search" class="form-control" title="Enter search keyword">
							<button type="submit" title="Search" class="btn btn-secondary"> <i class="bi bi-search"></i></button>
						</form>
					</div>
				</div>
				
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="card">
			<br>
					
				<div class="card-body">
					<!-- <h5 class="card-title">Table with hoverable rows</h5>-->
					<!-- Table with hoverable rows -->
					<table class="table table-hover">
	
						<tr>
							<th scope="col">등급</th>
							<th scope="col">이메일</th>
							<th scope="col">닉네임</th>
							<th scope="col">이름</th>
							<th scope="col">휴대폰</th>
							<th scope="col">생년월일</th>
							<th scope="col">관리등급</th>
							<th scope="col">설정</th>
	
						</tr>
						
						
						<c:if test="${alluser == null}">
							<tr><td colspan='5'>데이터가 없습니다</td></tr>
						</c:if>
						
						<c:forEach var="alluser" items="${alluser}">
						
							<tr id="rapportlist">
								<td scope="col"><img id="profile" class="col-3" src="image/rank_icon/${alluser.rank}.gif" alt="Profile" height="16" width="16" ></td>
								<td scope="col">${alluser.email_id}</td>
								<td scope="col">${alluser.nick}</td>
								<td scope="col">${alluser.name}</td>
								<td scope="col">${alluser.phone}</td>
								<td scope="col">${alluser.year_birth}</td>
								<td scope="col">${alluser.isAdmin}</td>
								<td scope="col">
								<div class="btn-group" role="group" aria-label="Basic example">
									<form action="user_details.do" method="post" target="payviewer" onsubmit="window.open('user_details.do', 'payviewer', 'width=768, height=753,resizeable, scrollbars');">
										<input type="text" value="${alluser.email_id}" name="id" style="display: none;">
										<input type="submit" class="btn btn-primary btn-sm EditPoint" value="등급설정">
									</form>
									<form action="user_activity.do" method="post">
										<input type="text" value="${alluser.email_id}" name="email_id" style="display: none;">
										<input type="submit" class="btn btn-primary btn-sm UserHistory" value="활동내역">
									</form>
								<!-- 	<form action="userkick.do" method="post"> -->
										<input type="text" value="${alluser.email_id}"  class ="id" name="id" style="display: none;">
										<input type="submit" class="btn btn-danger btn-sm Kick  deletuser" value="강퇴">
									<!-- </form> -->
								</div>
								</td>
							</tr>
						</c:forEach>
	
					</table>
					<!-- End Table with hoverable rows -->
				
	            </div>
           </div>
           
           <div class="col-md-12">
           		<!-- 페이징  -->
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
				</nav>
              <!-- End Centered Pagination -->
              
           </div>
		</div>
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
   
   <script src="assets/js/main.js"> </script>
  
   		

</body>

</html>