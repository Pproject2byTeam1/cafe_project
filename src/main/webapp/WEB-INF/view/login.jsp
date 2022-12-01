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

<!-- 경고창 이쁜거 -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

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
	
	<c:if test="${member != null}">
	<script type="text/javascript">
	   location.href="cafemain.do";
	</script>
	</c:if>

	<main id="main" class="main">

		<div class="pagetitle">
			<h1>로그인</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="cafemain.do">Home</a></li>
					<li class="breadcrumb-item active">로그인</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<section class="section dashboard">
			<div
				class="row align-items-top container h-100 d-flex justify-content-center">
				<!-- columns -->
				<div class="col-lg-6">
					<div class="row">
						<!-- 로그인 -->
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">Login</h5>

								<form action="loginok.do" method="post" name="loginForm" id="loginForm">
									<div class="input-group mb-3">
										<span class="input-group-text">
											<i class="bi bi-person-fill"></i>
										</span> 
										<input type="email" class="form-control" name="email_id" id="inputEmail5">
									</div>
									<div class="input-group mb-3">
										<span class="input-group-text"><i class="bi bi-key"></i></span>
										<input type="password" class="form-control"
											name="password" id="inputPassword5">
									</div>
									<div class="text-lg-end">
										<button type="button" class="btn btn btn-primary" id="loginbtn">로그인</button>
									</div>
								</form>
								
							</div>
						</div>
						<!-- End 로그인 -->
						<!-- 회원가입 -->
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">Register</h5>
								<p>소셜 로그인 및 이메일로 가입할 수 있습니다.</p>
								<hr>
								<a href="#"><img alt=""
									src="image/login_img/google_login.png" class="w-100 p-3"></a>
								<!-- 네이버 로그인 버튼 노출 영역 (근데 작다..)-->
    							<div id="naver_id_login" style="display: none;"></div>
    							<a href="#no"><img alt=""
									src="image/login_img/naver_login.png" class="w-100 p-3 naver-login" id="naverLogin"></a>
								<a href="#"><img alt=""
									src="image/login_img/kakao_login.png" class="w-100 p-3"></a>
									
							</div>
						</div>
						<!-- End 회원가입 -->
					</div>
				</div>
				<!-- columns -->
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
	
<script type="text/javascript">
        var naver_id_login = new naver_id_login("OGifRqdUtHJWY_oTLDrQ", "http://localhost:8090/WebCafe_Project/snsLogin.do");
        var state = naver_id_login.getUniqState();
        naver_id_login.setButton("green", 3, 40);
        naver_id_login.setDomain("http://localhost:8090/WebCafe_Project/login_view.do");
        naver_id_login.setState(state);
        //naver_id_login.setPopup();
        naver_id_login.init_naver_id_login();
        
       $(document).on("click", "#naverLogin", function(){
           var naverLogin = document.getElementById("naver_id_login").firstChild;
           naver_id_login_anchor.click();
       });
       
       window,addEventListener("keydown",function(event){
 		  if(event.defaultPrevented){
 			  return;
 		  }
 		  var handled = false;
 		  if(event.keyCode == 123)
 			  handled = true;
 		  if(handled){
 			 Swal.fire("특정 행위를 감지","F12키를 누르지 마십시오",'error');
 			  event.preventDefault();
 		  }
 	  },true);
       
       $("#loginbtn").click(function(){
	 		  check();
	 	  });
       
       function check() {
			if (!loginForm.email_id.value) {
				Swal.fire("경고","아이디를 입력하세요","warning");
				loginForm.email_id.focus();
				return false;
			}
			if(!loginForm.password.value){            
				Swal.fire("경고","비밀번호를 입력하세요","warning");
				loginForm.password.focus();
			     return false;
			 }
			const Toast = Swal.mixin({
			      toast: true,
			      position: 'center-center',
			      showConfirmButton: false,
			      timer: 300,
			      timerProgressBar: true,
			      didOpen: (toast) => {
			        toast.addEventListener('mouseenter', Swal.stopTimer)
			        toast.addEventListener('mouseleave', Swal.resumeTimer)
				}
			});
			Toast.fire({
				title:"처리중!",
				text: "로그인 처리중...",
				imageUrl: 'image/Rolling-1s-200px.gif',
				imageAlt: '로딩 이미지'}).then(function(){
					document.loginForm.submit();
			});
		}
</script>
</body>

</html>