<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>marketboard_list</title>

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
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
<!-- 거래게시판 CSS 시작 -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/marketboard_list.css" rel="stylesheet">
<!-- 거래게시판 CSS 끝 -->

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<script type="text/javascript">

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
			<h1>등급 안내</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="cafemain.do">카페 소개</a></li>
					<li class="breadcrumb-item active">등급 안내</li>
				</ol>
			</nav>
		</div>



		<div class="container">
			<div class="container-fluid">
				<div class="card">
				<div class="card-body">
				
				<p><p><p><p><p>
					<h3>등급 안내</h3>
					<p>글 작성 시 : 10 포인트 / 댓글 작성시 : 2 포인트</p>
				<form onclick="" style="cursor: pointer">
					<table class="table text-center" id='ranklist'>
						<thead class="thead-light">
							<tr>
								<th></th>
								<th></th>
								<th></th>
								
							</tr>

							<tr>
								<th>등급레벨</th>
								<th>등급 이름</th>
								<th>등급 포인트 기준</th>
								

							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="rank" items="${list}" varStatus="status">
							
								<c:if test="${rank.rank>0}">
									<tr class="${rank.rank}">

										<td class="${rank.rank}">${rank.rank}<input type="hidden" class="r_rank" value="${rank.rank}"></td>
										<td>
										${rank.r_name}
										<input type="hidden" class="r_name" value="${rank.r_name}">
										</td>
										<td>
											<c:choose>
												<c:when test="${rank.rank == 1}">
														0 점 이상
												      </c:when>

												<c:otherwise>
													${rank.r_point} 점 이상
													<input type="hidden" class="r_point" value="${rank.r_point}">
												</c:otherwise>
											</c:choose>
										</td>
										
									</tr>
								</c:if>
							</c:forEach>
									
						</tbody>
					</table>
					<div style="display: inline-block; margin: 0 5px;  float: right;">

					</div>		
				</form>
				</div>
				</div>
			</div>

		</div>
  




      <!-- 여기까지만 작성  -->

		<!-- End Centered Pagination -->
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