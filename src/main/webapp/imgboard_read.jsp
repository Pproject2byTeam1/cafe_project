<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>카페人중독</title>
	
	<!-- jQuery -->
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

	<!-- Favicons -->
  	<link href="assets/img/favicon.png" rel="icon">
  	<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  	<!-- Google Fonts -->
  	<link href="https://fonts.gstatic.com" rel="preconnect">
  	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  	<!-- Vendor CSS Files -->
  	<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  	<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  	<link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  	<link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  	<link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  	<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  	<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

  	<!-- Template Main CSS File -->
  	<link href="assets/css/style.css" rel="stylesheet">
  	<link href="assets/css/imgboard.css" rel="stylesheet">
  	
  	
</head>
<body>
  	<!-- ======= Header ======= -->
  	<header id="header" class="header fixed-top d-flex align-items-center">
     	<jsp:include page="/common/top.jsp"></jsp:include>
  	</header><!-- End Header -->
  	
  	<!-- ======= Sidebar ======= -->
    <jsp:include page="/common/side.jsp"></jsp:include>
  	<!-- End Sidebar -->
  
  
  
	<main id="main" class="main">
  	<!-- 여기서부터 작성 와랄ㄹ라  -->
  	

		<div class="pagetitle">
			<h1>IMG BOARD</h1>
			<!-- 게시판 이름 끌고오기 b_name -->
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">Home</a></li>
					<li class="breadcrumb-item"><a href="#">IMG BOARD</a></li>
					<li class="breadcrumb-item active">IMG</li>
				</ol>
			</nav>
		</div>
		
		<div class="container">
			<div class="row"> 
				<div class="col-1">
				
				</div>
				
				<div class="col-10">
					<div class="park-card p-4">
						<div class="park-card-body row">
							<div class="col-md-6 col mb-3">
								<img src="image/imgTest/1.jpg">
							</div>
							<div class="col-md-6">
								<div class="ps-2 ms-10 row justify-content-between">
									<div class="col navbar-nav">
										<div class="row mt-2 ms-2">
											<h3 class="col-3 nav-item mt-1"><i class="bi bi-cloud-arrow-down"></i></h3>
											<h3 class="col-3 nav-item"><i class="bi bi-exclamation-triangle"></i></h3>
										</div>
									</div>
									<div class="col navbar-nav">
										<button type="button" class="nav-item btn btn-danger">저장</button>
									</div>
								</div>
								<div class="mt-2">
									<h2><strong>나는 너구리가 아닌 고양이</strong></h2>
								</div>
								<div class="mt-2">
									<p>핳하하핳하 나는 고양이야 발을 맛보고 있어 아주 맛나네 야무져 아주 귀엽고 깜찍하당</p>
								</div>
							</div>
						</div>
						<div class="mb-4 park-card-body">
						
							<div class="row mb-3">
								<h5 class="col-sm-2 mt-2"><Strong>댓글 2개</Strong></h5>
								<div class="col-sm-7">
									<input type="text" class="form-control replyinput">
								</div>
								<div class="col-sm-3">
									<button type="button" class="btn btn-outline-secondary btn-sm rounded-pill">완료</button>
									<button type="button" class="btn btn-outline-secondary btn-sm rounded-pill">취소</button>
								</div>
							</div>

							<div class="timeline ms-2">
								<div class="reply-container">
									<Strong>작성자</Strong> <br />
									<p class="ms-2">이것은 댓글 내용이들어갈 거얌 근데 고양이 졸라 귀엽다ㅏ다다닫 고양이는 세계 제일!!!!!!!!!!</p>
								</div>
								
								<div class="reply-container">
									<Strong>작성자</Strong> <br />
									<p class="ms-2">이것은 댓글 내용이들어갈 거얌 근데 고양이 졸라 귀엽다ㅏ다다닫 고양이는 세계 제일!!!!!!!!!!</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-1"></div>
			</div>
		</div>
  
  	<!-- 여기까지만 작성  -->
  	</main>
	
</body>

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
</html>