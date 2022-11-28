<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>카페人중독</title>
<meta content="" name="description">
<meta content="" name="keywords">

<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    
<!-- content에 자신의 OAuth2.0 클라이언트ID를 넣습니다. -->
<meta name ="google-signin-client_id" content="921928889538-s4valdhb19eee3o6h4hgeheq8i8q90qk.apps.googleusercontent.com">

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

	<main id="main" class="main">

		<div class="pagetitle">
			<h1>Home</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="cafemain.do">Home</a></li>
					<li class="breadcrumb-item active">home</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<!-- Card with an image overlay -->
          <div class="card">
          <div class="embed-responsive">
            <img src="image/sample/bulb.jpg" class="card-img-top" alt="...">
          </div>
            <div class="card-img-overlay">
              <h5 class="card-title">대문 이름</h5>
              <p class="card-text">설명란</p>
            </div>
          
          </div><!-- End Card with an image overlay -->
          
   <section class="section dashboard"><!-- 대충 영역을 잡는 부분 -->
      <div class="row">
          
          <!-- Left side columns -->
        <div class="col-lg-12">
          <div class="row">

            <!-- Sales Card -->
            <div class="col-lg-6">
              <div class="card info-card sales-card">

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="#">Today</a></li>
                    <li><a class="dropdown-item" href="#">This Month</a></li>
                    <li><a class="dropdown-item" href="#">This Year</a></li>
                  </ul>
                </div>

                <div class="card-body">
                  <h5 class="card-title">항목이름 <span>| 소제목</span></h5>

                    <div class="ps-3">
                      <table class="table table-hover table-sm datatable">
                      	<thead>
                      	<tr><th><span class="text-success pt-1 fw-bold">#</span></th><th><span class="text-success pt-1 fw-bold">항목 1</span></th><th><span class="text-success pt-1 fw-bold">항목 2</span></th><th><span class="text-success pt-1 fw-bold">항목 3</span></th><th><span class="text-success pt-1 fw-bold">항목 4</span></th></tr>
                      	</thead>
                      	<tbody>
                      	<tr><th><span class="text-success pt-1 fw-bold">1</span></th><td>내용</td><td>내용</td><td>내용</td><td>내용</td></tr>
                      	<tr><th><span class="text-success pt-1 fw-bold">2</span></th><td>내용</td><td>내용</td><td>내용</td><td>내용</td></tr>
                      	<tr><th><span class="text-success pt-1 fw-bold">3</span></th><td>내용</td><td>내용</td><td>내용</td><td>내용</td></tr>
                      	</tbody>
                      </table>
                    </div>
                </div>

              </div>
            </div><!-- End Sales Card -->

            <!-- Revenue Card -->
            <div class="col-lg-6">
              <div class="card info-card revenue-card">

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="#">Today</a></li>
                    <li><a class="dropdown-item" href="#">This Month</a></li>
                    <li><a class="dropdown-item" href="#">This Year</a></li>
                  </ul>
                </div>

                <div class="card-body">
                  <h5 class="card-title">항목이름2 <span>| 소제 2</span></h5>

                    <div class="ps-3">
                    <table class="table table-hover table-sm datatable">
                      	<thead>
                      	<tr><th><span class="text-primary pt-1 fw-bold">#</span></th><th><span class="text-primary pt-1 fw-bold">항목 1</span></th><th><span class="text-primary pt-1 fw-bold">항목 2</span></th><th><span class="text-primary pt-1 fw-bold">항목 3</span></th></tr>
                      	</thead>
                      	<tbody>
                      	<tr><th><span class="text-primary pt-1 fw-bold">1</span></th><td>내용</td><td>내용</td><td>내용</td></tr>
                      	<tr><th><span class="text-primary pt-1 fw-bold">2</span></th><td>내용</td><td>내용</td><td>내용</td></tr>
                      	<tr><th><span class="text-primary pt-1 fw-bold">3</span></th><td>내용</td><td>내용</td><td>내용</td></tr>
                      	</tbody>
                      </table>
                    </div>
                </div>

              </div>
            </div><!-- End Revenue Card -->

            <!-- Customers Card -->
            <div class="col-xl-12">

              <div class="card info-card customers-card">

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="#">Today</a></li>
                    <li><a class="dropdown-item" href="#">This Month</a></li>
                    <li><a class="dropdown-item" href="#">This Year</a></li>
                  </ul>
                </div>

                <div class="card-body">
                  <h5 class="card-title">항목 3 <span>| 소제 3</span></h5>

                    <div class="ps-3">
                      <table class="table table-hover datatable">
                      	<thead>
                      	<tr><th><span class="text-danger pt-1 fw-bold">#</span></th><th><span class="text-danger pt-1 fw-bold">항목 1</span></th><th><span class="text-danger pt-1 fw-bold">항목 2</span></th><th><span class="text-danger pt-1 fw-bold">항목 3</span></th><th><span class="text-danger pt-1 fw-bold">항목 4</span></th><th><span class="text-danger pt-1 fw-bold">항목 5</span></th><th><span class="text-danger pt-1 fw-bold">항목 6</span></th><th><span class="text-danger pt-1 fw-bold">항목 7</span></th><th><span class="text-danger pt-1 fw-bold">항목 8</span></th><th><span class="text-danger pt-1 fw-bold">항목 9</span></th></tr>
                      	</thead>
                      	<tbody>
                      	<tr><th><span class="text-danger pt-1 fw-bold">1</span></th><td>내ㅐㅐㅐㅐㅐ용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내ㅐㅐ용</td><td>내용</td><td>내용</td><td>내용</td></tr>
                      	<tr><th><span class="text-danger pt-1 fw-bold">2</span></th><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td></tr>
                      	<tr><th><span class="text-danger pt-1 fw-bold">3</span></th><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td></tr>
                      	<tr><th><span class="text-danger pt-1 fw-bold">4</span></th><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td></tr>
                      	<tr><th><span class="text-danger pt-1 fw-bold">5</span></th><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td></tr>
                      	</tbody>
                      </table>
                    </div>

                </div>
              </div>

            </div><!-- End Customers Card -->

            <!-- Reports -->
            <div class="col-12">
              <div class="card">

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="#">Today</a></li>
                    <li><a class="dropdown-item" href="#">This Month</a></li>
                    <li><a class="dropdown-item" href="#">This Year</a></li>
                  </ul>
                </div>
                <div class="card-body">
                  <h5 class="card-title">Reports <span>/Today</span></h5>
                  
                  <div class="d-flex align-items-center  revenue-card">
					<div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                      <i class="ri-thumb-up-line"></i>
                    </div>
                    <div class="ps-3">
                    <h3 class="pt-1 fw-bold">Best Text</h3>
                    </div>
                    <div class="ps-3">
                    <h5 class="card-title">글 제목</h5>
                    </div>
                    <div class="ps-3">
                    <a data-bs-toggle="dropdown"><img src="image/rank_icon/3.gif" alt="아이콘">스탠드</a><span> | 2022-03-18</span>
                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
						<li class="anw_memo"><a class="dropdown-item sendToMemo" href="#" >답장 보내기</a></li>
						<li class="view_user_activity"><a class="dropdown-item viewActivity" href="#" >활동 내역 보기</a></li>
					</ul>
                    </div>
                  </div>
                  <div class="d-flex align-items-center sales-card">
					<div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                      <i class="ri-thumb-up-line"></i>
                    </div>
                    <div class="ps-3">
                    <h3 class="pt-1 fw-bold">Another Text</h3>
                    </div>
                    <div class="ps-3">
                    <h5 class="card-title">글 제목</h5>
                    </div>
                    <div class="ps-3">
                    <a data-bs-toggle="dropdown"><img src="image/rank_icon/4.gif" alt="아이콘">dddr2</a><span> | 2021-09-27</span>
                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
						<li class="anw_memo"><a class="dropdown-item sendToMemo" href="#" >답장 보내기</a></li>
						<li class="view_user_activity"><a class="dropdown-item viewActivity" href="#" >활동 내역 보기</a></li>
					</ul>
                    </div>
                  </div>
                  
                  <!-- End Line Chart -->

                </div>

              </div>
            </div><!-- End Reports -->

            <!-- Recent Sales -->
            <div class="col-12">
              <div class="card recent-sales overflow-auto">

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="#">Today</a></li>
                    <li><a class="dropdown-item" href="#">This Month</a></li>
                    <li><a class="dropdown-item" href="#">This Year</a></li>
                  </ul>
                </div>

          </div>
        </div><!-- End Left side columns -->
	</div>
	</section>
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
	

</html>