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
			<h1>로그인</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">Home</a></li>
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
										<button type="submit" class="btn btn btn-primary">로그인</button>
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
								<ul>
								 <li id="GgCustomLogin">
								  <a href="javascript:void(0)">
								   <span>Login with Google</span>
								  </a>
								 </li>
								</ul>
								<a href="#"><img alt=""
									src="image/login_img/naver_login.png" class="w-100 p-3"></a>
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
<script>

//처음 실행하는 함수
function init() {
	gapi.load('auth2', function() {
		gapi.auth2.init();
		options = new gapi.auth2.SigninOptionsBuilder();
		options.setPrompt('select_account');
        // 추가는 Oauth 승인 권한 추가 후 띄어쓰기 기준으로 추가
		options.setScope('email profile openid https://www.googleapis.com/auth/user.birthday.read');
        // 인스턴스의 함수 호출 - element에 로그인 기능 추가
        // GgCustomLogin은 li태그안에 있는 ID, 위에 설정한 options와 아래 성공,실패시 실행하는 함수들
		gapi.auth2.getAuthInstance().attachClickHandler('GgCustomLogin', options, onSignIn, onSignInFailure);
	})
}

function onSignIn(googleUser) {
	var access_token = googleUser.getAuthResponse().access_token
	$.ajax({
    	// people api를 이용하여 프로필 및 생년월일에 대한 선택동의후 가져온다.
		url: 'https://people.googleapis.com/v1/people/me'
        // key에 자신의 API 키를 넣습니다.
		, data: {personFields:'birthdays', 
					key:'AIzaSyBT4atMy324m0EAyHoCZ3wFZHuAkM3-wNI', 'access_token': access_token}
		, method:'GET'
	})
	.done(function(e){
        //프로필을 가져온다.
		var profile = googleUser.getBasicProfile();
		console.log(profile)
		console.log(e.birthdays);
	})
	.fail(function(e){
		console.log(e);
	})
}
function onSignInFailure(t){		
	console.log(t);
}
</script>
//구글 api 사용을 위한 스크립트
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
</body>

</html>