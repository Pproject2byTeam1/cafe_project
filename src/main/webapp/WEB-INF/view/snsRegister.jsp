<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<c:if test="${member.userid != null}">
	<script type="text/javascript">
	   location.href="registerok.do";
	</script>
	</c:if>
	<main id="main" class="main">

		<div class="pagetitle">
			<h1>로그인</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">Home</a></li>
					<li class="breadcrumb-item active">회원가입</li>
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
						<!-- 회원가입 -->
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">Register</h5>
								<form action="registerok.do" method="post" name="registerForm" id="registerForm">
									<div class="input-group mb-3">
										<span class="input-group-text">
											이메일
										</span> 
										<input type="email" class="form-control" name="email_id" id="email_id" >
									</div>
									<div class="input-group mb-3">
										<span class="input-group-text">이름</span>
										<input type="text" class="form-control" name="name" id="name">
									</div>
									<div class="input-group mb-3">
				                     <span class="input-group-text">비밀번호</span>
				                        <input name="password" type="password" class="form-control" id="Password" min="10">
				                    </div>
									<p>사용자의 비밀번호는 문자종류 상관없이 10자 이상</p>
				
				                    <div class="input-group mb-3">
				                      <span class="input-group-text">비밀번호 확인</span>
				                        <input name="repassword" type="password" class="form-control" id="rePassword">
				                    </div>
				                    <div id = "aaaa"></div>
									<div class="input-group mb-3">
										<span class="input-group-text">닉네임</span>
										<input type="text" class="form-control" name="nick" id="nick">
										<p>사용자의 닉네임은 공백없이 한글, 영문, 숫자만 입력 가능(한글 2자, 영문 4자 이상)</p>
										<div id = "qqqq"></div>
									</div>
									<div class="input-group mb-3">
										<span class="input-group-text">출생년도</span>
										<input type="date" class="form-control"
											name="date" id="date">
									</div>
									<div class="input-group mb-3">
										<span class="input-group-text">전화번호</span>
										<input type="tel" class="form-control"
											name="phone" id="phone">
									</div>
									<div class="text-lg-end">
										<button type="button" class="btn btn btn-primary" id="registerbtn">등록</button>
									</div>
								</form>
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
  var naver_id_login = new naver_id_login("OGifRqdUtHJWY_oTLDrQ", "http://localhost:8090/WebCafe_Project/login_view.do");
  // 접근 토큰 값 출력
  //alert(naver_id_login.oauthParams.access_token);
  // 네이버 사용자 프로필 조회
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  
  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  function naverSignInCallback() {
	  const email = naver_id_login.getProfileData('email');
	  const name = naver_id_login.getProfileData('name');
	  var birthday = naver_id_login.getProfileData('birthday');
	  var birthyear = naver_id_login.getProfileData('birthyear');
	  var mobile = naver_id_login.getProfileData('mobile');
	  $('#email_id').val(email);
	  $('#email_id').prop("readonly",true);
	  $('#name').val(name);
	  $('#name').prop("readonly",true);
	  if(mobile != null){
		  $('#phone').val(mobile.replace("-",""));
		}else{
			alert("전화번호 정보를 가져오는데 실패하였습니다");
			$('#phone').val("010");
		}
	  
	  if(birthday != null && birthyear != null){
		  $('#date').val(birthyear + "-" + birthday);
	  }else{
		  alert("생년월을 정보를 가져오는데 실패하였습니다");
		  birthday = "01-01";
		  birthyear = "2000";
		  $('#date').val(birthyear + "-" + birthday);
	  }
	  //console.log(birthyear + "-" + birthday);
   // alert(naver_id_login.getProfileData('name'));
   // alert(naver_id_login.getProfileData('birthday'));
   	  //alert(naver_id_login.getProfileData('birthyear'));
  }
  $(function(){
	  $('#nick').keyup(function(){
			//서버처리결과받기
			$.ajax({
				url:"nickVerification.do",
				data:{nickname: $('#nick').val()},
				type: "POST",
				dataType:"json",
				success: function(responseText){
					var text = responseText;
					if(text.toString().replace(/\s/gi, "") == "true"){
						$("#qqqq").html("<p class='text-danger'>사용이 불가합니다.</p>");
					}else{
						$("#qqqq").html("<p class='text-success'>사용 가능합니다.</p>");
					}
				}
			});
		});
		$("#rePassword").keyup(function () {
			if ($("#Password").val() != $("#rePassword").val() || $("#rePassword").val().length < 10) {
              $("#aaaa").html("<p class='text-danger' >비밀번호가 일치하지 않거나 10자 미만입니다.</p>");
            } else {
              $("#aaaa").html("<p class='text-success' >일치합니다.</p>");
            }
        });
		$('#tel').keyup(function(){
			if($('#tel').val().length != 11 || $('#tel').val() == null){
			}
		});
		$("#loginbtn").click(function(){
	 		  check();
	 	  });
		window,addEventListener("keydown",function(event){
	 		  if(event.defaultPrevented){
	 			  return;
	 		  }
	 		  var handled = false;
	 		  if(event.keyCode == 123)
	 			  handled = true;
	 		  if(handled){
	 			  swal("특정 행위를 감지","F12키를 누르지 마십시오",'error');
	 			  event.preventDefault();
	 		  }
	 	  },true);
	       $("#registerbtn").click(function(){
	 		  check();
	 	  });  
       function check() {
			if (!registerForm.email_id.value) {
				swal("경고","아이디를 입력하세요","warning");
				registerForm.email_id.focus();
				return false;
			}
			if (!registerForm.name.value) {
				swal("경고","이름을 입력하세요","warning");
				registerForm.name.focus();
				return false;
			}
			if (!registerForm.nick.value) {
				swal("경고","닉네임을 입력하세요","warning");
				registerForm.nick.focus();
				return false;
			}
			if(!registerForm.password.value){            
				swal("경고","비밀번호를 입력하세요","warning");
				registerForm.password.focus();
			     return false;
			 }
			if(!registerForm.date.value){            
				swal("경고","생년월일를 입력하세요","warning");
				registerForm.date.focus();
			     return false;
			 }
			if(!registerForm.phone.value){            
				swal("경고","전화번호를 입력하세요","warning");
				registerForm.phone.focus();
			     return false;
			 }
			document.registerForm.submit();
		}
  });
</script>
</body>

</html>