<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    <div class="pagetitle">
      <h1>로그인</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
          <li class="breadcrumb-item active">로그인</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section dashboard">
      <div class="row align-items-top container h-100 d-flex justify-content-center">
		<!-- columns -->
        <div class="col-lg-6">
          <div class="row">
          	  <!-- 로그인 -->
	          <div class="card">
	            <div class="card-body">
	              <h5 class="card-title">Login</h5>
	              <div class="input-group mb-3">
	                <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
	                <input type="email" class="form-control" id="inputEmail5">
	              </div>
	              <div class="input-group mb-3">
	                <span class="input-group-text"><i class="bi bi-key"></i></span>
	                <input type="password" class="form-control" id="inputPassword5">
	              </div>
	              <div  class="text-lg-end">
	              	<button type="button" class="btn btn btn-primary">로그인</button>
	              </div>
	            </div>
	          </div><!-- End 로그인 -->
              <!-- 회원가입 -->
	          <div class="card">
	            <div class="card-body">
	              <h5 class="card-title">Register</h5>
	              <p>소셜 로그인 및 이메일로 가입할 수 있습니다.</p>
	              <hr>
	              <a href="#"><img alt="" src="image/login_img/google_login.png" class="w-100 p-3"></a>
	              <a href="#"><img alt="" src="image/login_img/naver_login.png" class="w-100 p-3"></a>
	              <a href="#"><img alt="" src="image/login_img/kakao_login.png" class="w-100 p-3"></a>
	            </div>
	          </div><!-- End 회원가입 -->
          </div>
         </div>
         <!-- columns -->
      </div>
    </section>

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer" class="footer">
    <div class="copyright">
      &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved
    </div>
    <div class="credits">
      <!-- All the links in the footer should remain intact. -->
      <!-- You can delete the links only if you purchased the pro version. -->
      <!-- Licensing information: https://bootstrapmade.com/license/ -->
      <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
      Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

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