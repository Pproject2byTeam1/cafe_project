<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
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
  
  <!-- 경고창 이쁜거 -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

</head>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">
		<c:import url="/WEB-INF/view/common/top.jsp" />
	</header>

  <!-- ======= Sidebar ======= -->
  
   <c:import url="/WEB-INF/view/common/side.jsp" />
  
  <!-- End Sidebar -->

  <main id="main" class="main">

    <div class="pagetitle">
      <h1>Admin</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
          <li class="breadcrumb-item active">Admin 정보 변경</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section profile">
      <div class="row d-flex flex-column align-items-center">
		<!-- columns -->
        <div class="col-lg-10">
          <div class="card">
            <div class="card-body pt-3">
              <h5 class="card-title">Admin 정보변경</h5>

              <!-- Bordered Tabs -->
              <ul class="nav nav-tabs nav-tabs-bordered" id="borderedTab" role="tablist">
                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#bordered-home" >정보 보기</button>
                </li>
                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#bordered-profile">수정하기</button>
                </li>
                <li class="nav-item">
                  <button class="nav-link"data-bs-toggle="tab" data-bs-target="#bordered-contact">?</button>
                </li>
              </ul>
              
              <div class="tab-content pt-2" id="borderedTabContent">
                 <div class="tab-pane fade show active" id="bordered-home" role="tabpanel" aria-labelledby="home-tab">
                 	<div class="tab-pane fade show active profile-overview" id="profile-overview">
                
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Email</div>
                    <div class="col-lg-9 col-md-8">${user.email_id}</div>
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">Name</div>
                    <div class="col-lg-9 col-md-8">${user.name}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">닉네임</div>
                    <div class="col-lg-9 col-md-8">${user.nick}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">생년월일</div>
                    <div class="col-lg-9 col-md-8">${birthday}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">포인트</div>
                    <div class="col-lg-9 col-md-8">${user.point}</div>
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">가입일</div>
                    <div class="col-lg-9 col-md-8">${joindate}</div>
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">생년월일</div>
                    <div class="col-lg-9 col-md-8">${birthday}</div>
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">전화번호</div>
                    <div class="col-lg-9 col-md-8">${phone}</div>
                  </div>
                  
                 </div>
              </div>
              
              <div class="tab-pane fade" id="bordered-profile" role="tabpanel" aria-labelledby="profile-tab">
	            <form action="adminUpdate.do" method="post" name="adminchange">
	                <div class="row mb-3">
	                  <label for="inputEmail" class="col-sm-3 col-form-label">Email</label>
	                  <div class="col-sm-9">
	                    <input type="email" class="form-control" id="email" readonly value="${user.email_id}">
	                  </div>
	                </div>
	                
	                <div class="row mb-3">
	                  <label for="inputEmail" class="col-sm-3 col-form-label">Name</label>
	                  <div class="col-sm-9">
	                    <input type="text" class="form-control" id="name" name="name" value="${user.name}">
	                  </div>
	                </div>
	                
	                <div class="row mb-3">
                      <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">Current Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="password" type="password" class="form-control" id="currentPassword">
                      </div>
                    </div>
	                
	                <div class="row mb-3">
                      <label for="newPassword" class="col-md-4 col-lg-3 col-form-label">New Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="newpassword" type="password" class="form-control" id="newPassword" minlength ="10" required/>
                      </div>
                    </div>
					<p>사용자의 비밀번호는 문자종류 상관없이 10자 이상</p>

                    <div class="row mb-3">
                      <label for="renewPassword" class="col-md-4 col-lg-3 col-form-label">Re-enter New Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="renewpassword" type="password" class="form-control" id="renewPassword" minlength ="10" required/>
                        <div id = "aaaa"></div>
                      </div>
                    </div>
	                
	                <div class="row mb-3">
                      <label for="company" class="col-md-4 col-lg-3 col-form-label">닉네임</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="nickname" type="text" class="form-control" id="nickname" value="${user.nick}"><input type="text" hidden value="${user.nick}" name="orinick"/>
                        <div id="qqqq"></div>
                      </div>
                      <p>사용자의 닉네임은 공백없이 한글, 영문, 숫자만 입력 가능(한글 2자, 영문 4자 이상)</p>
                    </div>
                    
	                <div class="row mb-3">
	                  <label for="inputDate" class="col-sm-3 col-form-label">생년월일</label>
	                  <div class="col-sm-9">
	                    <input type="date" class="form-control" value="${inputDate}" id="date" name="date">
	                  </div>
	                </div>
	                
	                <div class="row mb-3">
                      <label for="company" class="col-md-4 col-lg-3 col-form-label">전화번호</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="tel" type="tel" class="form-control" id="tel" value="${details.phone}" placeholder="'-' 없이 작성해 주세요"><input type="text" hidden value="${details.phone}" name="oriphone"/>
                      </div>
                    </div>
                    
		              <div  class="text-lg-end">
		              	<button type="button" class="btn btn btn-primary" id="updateinfo">등록</button>
		              	<button type="reset" class="btn btn btn-danger">취소</button>
		              </div>
	              </form><!-- End Profile Edit Form -->
                </div>
                
                <div class="tab-pane fade" id="bordered-contact" role="tabpanel" aria-labelledby="contact-tab">
                  내용 아무거나
                </div>
            </div>
          </div>
          </div>
         <!-- columns -->
      </div>
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
  <script type="text/javascript">
  $(function(){
		$('#nickname').keyup(function(){
			//서버처리결과받기
			$.ajax({
				url:"nickVerification.do",
				data:{nickname: $('#nickname').val()},
				type: "POST",
				dataType:"json",
				success: function(responseText){
					var text = responseText;
					if(text.toString().replace(/\s/gi, "") == "true"){
						$("#qqqq").html("<p class='text-danger'>사용이 불가합니다.</p>");
						$("#updateinfo").attr("disabled",true);
					}else{
						$("#qqqq").html("<p class='text-success'>사용 가능합니다.</p>");
						$("#updateinfo").removeAttr("disabled");
					}
				}
			});
		});
		$("#renewPassword").keyup(function () {
			console.log("pwd: "+$('#currentPassword').val());
			$.ajax({
				url:"userVerification.do",
				data:{password: $('#currentPassword').val()},
				type: "POST",
				dataType:"json",
				success: function(responseText){
					var text = responseText;
					if(text.toString().replace(/\s/gi, "") == "true"){
	  					if ($("#newPassword").val() != $("#renewPassword").val() || $("#newPassword").val().length < 10) {
			              $("#aaaa").html("<p class='text-danger' >일치하지 않거나 글자수가 10자 미만입니다.</p>");
			           	  $("#updateinfo").attr("disabled",true);
			            } else {
			              $("#aaaa").html("<p class='text-success' >일치합니다.</p>");
			              $("#updateinfo").removeAttr("disabled");
			            }
	  				}else{
	  					$("#aaaa").html("<p class='text-danger' >비밀번호가 정확하지 않습니다.</p>");
	  				}
				}
			})
        });
		$('#tel').keyup(function(){
			if($('#tel').val().length != 11 || $('#tel').val() == null){
				$("#updateinfo").attr("disabled",true);
			}else{
				$("#updateinfo").removeAttr("disabled");
			}
		})
		function check() {
			if (!bbs.content.value || bbs.content.value == "") {
				Swal.fire("경고","내용을 입력하세요","warning");
				loginForm.email_id.focus();
				return false;
			}
			document.bbs.submit();
		}
		
		$("#updateinfo").click(function(){
	 		  check();
	 	  });
     
		function check() {
			if (!adminchange.name.value) {
				Swal.fire("경고","이름을 입력하세요","warning");
				loginForm.name.focus();
				return false;
			}
			if(!adminchange.password.value){
				Swal.fire("경고","비밀번호를 입력하세요","warning");
				loginForm.password.focus();
			     return false;
			 }
			if(!adminchange.newpassword.value){
				Swal.fire("경고","비밀번호를 입력하세요","warning");
				loginForm.newpassword.focus();
			     return false;
			 }
			if(!adminchange.renewpassword.value){
				Swal.fire("경고","비밀번호를 입력하세요","warning");
				loginForm.renewpassword.focus();
			     return false;
			 }
			if(!adminchange.nickname.value){
				Swal.fire("경고","닉네임을 입력하세요","warning");
				loginForm.nickname.focus();
			     return false;
			 }
			if(!adminchange.date.value){
				Swal.fire("경고","생년월일을 입력하세요","warning");
				loginForm.date.focus();
			     return false;
			 }
			if(!adminchange.tel.value){
				Swal.fire("경고","전화번호를 입력하세요","warning");
				loginForm.tel.focus();
			     return false;
			 }
			Swal.fire({
				title:"성공!",
				text: "수정 성공",
				type:"success"}).then(function(){
				document.loginForm.submit();
			});
			
		}
	});
  </script>

</body>

</html>