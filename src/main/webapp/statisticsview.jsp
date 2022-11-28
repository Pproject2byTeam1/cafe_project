<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<script
	src="https://cdn.tiny.cloud/1/avqk22ebgv68f2q9uzprdbapxmxjwdbke8xixhbo24x2iyvp/tinymce/6/tinymce.min.js"
	referrerpolicy="origin"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
<link href="assets/css/free.css" rel="stylesheet">
<style>
/* .btn-success btn-lg {
   float: right;
} */
.selectpicker {
	width: 75px;
	height: 35px;
	border-radius: 5px;
}

._3Espq6 {
	width: 38px;
	height: 22px;
	font-size: 0.9rem;
	text-align: center;
}

.parent {
	width: 500px;
	display: flex;
	flex-direction: column;
}

.name {
	font-size: 0.9rem;
	margin-top: auto;
	vertical-align: bottom;
}

._1R-fi- {
	margin-bottom: 0.5rem;
	color: #2b2d36;
	line-height: 1.5;
	font-weight: 700;
	font-size: 1.5rem;
	letter-spacing: -0.01875rem;
	margin-bottom: 0;
	color: var(- -gray-600);
	text-align: center;
}

.jdc {
	text-align: center;
}

.son_name {
	font-size: 13px;
	font-weight: bold;
}

.son_date {
	font-size: 13px;
}

.son_time {
	gk font-size: 13px;
}
/*  표 style */
.card-body {
	overflow-x: auto;
}

.table table-hover {
	width: 100%;
	min-width: 500px;
	display: block;
	overflow: auto;
}
</style>
<script>
	tinymce.init({
		selector : '#mytextarea'
	});
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>

<body>
	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">
		<c:import url="/WEB-INF/view/common/top.jsp" />
	</header>
	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<c:import url="/WEB-INF/view/common/side.jsp" />
	<!-- End Sidebar -->
	<!-- ======= Header ======= -->

	<!-- ======= Sidebar ======= -->

	<jsp:include page="/common/side2.jsp"></jsp:include>

	<!-- End Sidebar -->

	<main id="main" class="main">
		<div class="pagetitle">
			<h1>통계정보</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">Home</a></li>
					<li class="breadcrumb-item active">통계정보</li>
				</ol>
			</nav>
		</div>
		<!--버튼 날짜   -->
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-2">
				
				<div align="center">시작일</div> 	
				<input type="date" id="calend_date" class="form-control">
				
				</div>
				<div class="col-md-2">
				
				종료일: 	
				<input type="date" id="calend_date" class="form-control">
				</div>
				<div class="col-md-2">
			  <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button type="button" class="btn btn-danger">오늘</button>
                <button type="button" class="btn btn-warning">주</button>
                <button type="button" class="btn btn-success">월</button>
              </div>
				</div>
				<div class="col-md-2">	
				<select class="form-select" id="validationDefault04" required>
						<option selected disabled value="">게시판을 선택하세요</option>
						<option>자유게시판</option>
						<option>출석체크</option>
						<option>전체일정</option>
						<option>사진공유</option>
						<option>유로거래</option>
						<option>자료공유</option>
					</select>
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
		<br>
		<br>



		<!-- 여기부터 정보를 보이기 -->
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4">
					<div class="card">여기는 그래프</div>
				</div>
				<div class="col-md-4">
					<!-- Table with hoverable rows -->
					전체게시판 랭킹
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">NO.</th>
								<th scope="col">Name</th>
								<th scope="col">조회수</th>
								<th scope="col">작성글수</th>

							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">1</th>
								<td>자유게시판</td>
								<td>43234</td>
								<td>1234</td>
							</tr>

						</tbody>
					</table>
					<!-- End Table with hoverable rows -->
				</div>
				<div class="col-md-4">전체 시간대별 활동령</div>
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


</body>

</html>
