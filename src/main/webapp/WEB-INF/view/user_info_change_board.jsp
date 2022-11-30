<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
     </header><!-- End Header -->
     
     <!-- ======= Sidebar ======= -->
     <c:import url="/WEB-INF/view/common/side.jsp" />
     <!-- End Sidebar -->

  <main id="main" class="main">

    <div class="pagetitle">
      <h1>${user.nick}님 마이페이지</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="cafemain.do">Home</a></li>
          <li class="breadcrumb-item active">User 정보 변경</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section profile">
      <div class="row d-flex flex-column align-items-center">

        <div class="col-xl-8">

          <div class="card">
            <div class="card-body pt-3">
              <!-- Bordered Tabs -->
              <ul class="nav nav-tabs nav-tabs-bordered">

                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">정보 보기</button>
                </li>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">유저정보 변경하기</button>
                </li>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-change-password">비밀번호 변경</button>
                </li>

              </ul>
              <div class="tab-content pt-2">

                <div class="tab-pane fade show active profile-overview" id="profile-overview">
                
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Email</div>
                    <div class="col-lg-9 col-md-8" id="id">${user.email_id}</div>
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
                    <div class="col-lg-3 col-md-4 label">관리등급</div>
                    <div class="col-lg-9 col-md-8">
                    <c:choose>
	                    <c:when test="${user.isAdmin == 'F' || user.isAdmin == 'f'}">
	                    일반
	                    </c:when>
	                    <c:when test="${user.isAdmin == 'S' || user.isAdmin == 's'}">
	                    스텝
	                    </c:when>
	                    <c:when test="${user.isAdmin == 'M' || user.isAdmin == 'm'}">
	                    운영자
	                    </c:when>
	                    <c:otherwise>
	                    없음
	                    </c:otherwise>
                    </c:choose>
                    </div>
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">가입일</div>
                    <div class="col-lg-9 col-md-8">${joindate}</div>
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">전화번호</div>
                    <div class="col-lg-9 col-md-8">${phone}</div>
                  </div>
                  
                  <div class="text-center">
		              <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#basicModal">
		                회원 탈퇴 하기
		              </button>
		              <div class="modal fade" id="basicModal" tabindex="-1">
		                <div class="modal-dialog">
		                  <div class="modal-content">
		                    <div class="modal-header">
		                      <h5 class="modal-title">경고</h5>
		                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		                    </div>
		                    <div class="modal-body">
		                    정말 회원탈퇴를 하시겠습니까?
		                    </div>
		                    <div class="modal-footer">
		                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="deleteUser">예</button>
		                      <button type="button" class="btn btn-primary" data-bs-dismiss="modal">아니오</button>
		                    </div>
		                  </div>
		                </div>
		              </div>
                   </div>

                </div>

                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">

                  <!-- Profile Edit Form -->
                  <form action="userupdate.do" method="post" name="userform">

                    <div class="row mb-3">
                      <label for="company" class="col-md-4 col-lg-3 col-form-label">닉네임</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="nickname" type="text" class="form-control" id="nickname" value="${user.nick}"><input type="text" hidden value="${user.nick}" name="orinick"/>
                        <div id="qqqq"></div>
                      </div>
                      <p>사용자의 닉네임은 공백없이 한글, 영문, 숫자만 입력 가능(한글 2자, 영문 4자 이상)</p>
                    </div>
                    
                    <div class="row mb-3">
                      <label for="company" class="col-md-4 col-lg-3 col-form-label">전화번호</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="tel" type="tel" class="form-control" id="tel" value="${details.phone}" placeholder="'-' 없이 작성해 주세요"><input type="text" hidden value="${details.phone}" name="oriphone"/>
                      </div>
                    </div>
                    
                    <div class="text-center">
                      <button type="button" class="btn btn-primary" id="chageInfo">변경사항 저장</button>
                    </div>
                  </form><!-- End Profile Edit Form -->

                </div>

                <div class="tab-pane fade pt-3" id="profile-change-password">
                  <!-- Change Password Form -->
                  <form action="userUpdatePwd.do" method="post" name="pwdform">

                    <div class="row mb-3">
                      <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">Current Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="password" type="password" class="form-control" id="currentPassword">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="newPassword" class="col-md-4 col-lg-3 col-form-label">New Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="newpassword" type="password" class="form-control" id="newPassword" min="10">
                      </div>
                    </div>
					<p>사용자의 비밀번호는 문자종류 상관없이 10자 이상</p>

                    <div class="row mb-3">
                      <label for="renewPassword" class="col-md-4 col-lg-3 col-form-label">Re-enter New Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="renewpassword" type="password" class="form-control" id="renewPassword">
                        <div id = "aaaa"></div>
                      </div>
                    </div>
 					
 
                    <div class="text-center">
                      <button type="button" class="btn btn-primary" id="changepwd">비밀번호 변경하기</button>
                    </div>
                   
                  </form><!-- End Change Password Form -->

                </div>

              </div><!-- End Bordered Tabs -->

            </div>
          </div>

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
  		$('#deleteUser').click(function(){//삭제처리
  			location.href = "deleteUser.do";
  		});
  		
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
  						$("#chageInfo").attr("disabled",true);
  					}else{
  						$("#qqqq").html("<p class='text-success'>사용 가능합니다.</p>");
  						$("#chageInfo").removeAttr("disabled");
  					}
  				}
  			});
  		});
  		$("#renewPassword").keyup(function () {
  		
  			$.ajax({
  				url:"userVerification.do",
  				data:{password: $('#currentPassword').val()},
  				type: "POST",
  				dataType:"json",
  				success: function(responseText){
  					var text = responseText;
  					if(text.toString().replace(/\s/gi, "") == "true"){
	  					if ($("#newPassword").val() != $("#renewPassword").val() || $("#renewPassword").val().length < 10) {
			              $("#aaaa").html("<p class='text-danger' >비밀번호가 일치하지 않거나 10자 미만입니다.</p>");
			           	  $("#changepwd").attr("disabled",true);
			            } else {
			              $("#aaaa").html("<p class='text-success' >일치합니다.</p>");
			              $("#changepwd").removeAttr("disabled");
			            }
	  				}else{
	  					$("#aaaa").html("<p class='text-danger' >비밀번호가 정확하지 않습니다.</p>");
	  				}
  				}
  			})
          });
  		$('#tel').keyup(function(){
  			
  			if($('#tel').val().length != 11 || $('#tel').val() == null){
  				$("#chageInfo").attr("disabled",true);
  			}else{
  				$("#chageInfo").removeAttr("disabled");
  			}
  		})
  		window,addEventListener("keydown",function(event){
  		  if(event.defaultPrevented){
  			  return;
  		  }
  		  var handled = false;
  		  if(event.keyCode == 123)
  			  handled = true;
  		  if(handled){
  			Swal.fire("경고","F12키를 누르지 마십시오",'error');
  			  event.preventDefault();
  		  }
  	  },true);
  		$("#changepwd").click(function(){
	   		  pwdcheck();
	   	  });
         
         function pwdcheck() {
  			if (!pwdform.renewpassword.value) {
  				Swal.fire("경고","새로운 비밀번호를 입력하세요","warning");
  				loginForm.renewpassword.focus();
  				return false;
  			}
  			if(!pwdform.password.value){            
  				Swal.fire("경고","비밀번호를 입력하세요","warning");
  				loginForm.password.focus();
  			     return false;
  			 }
  			Swal.fire({
				title:"성공!",
				text: "비밀번호가 변경되었습니다",
				type:"success"}).then(function(){
					document.pwdform.submit();
			});
  		}
         
         $("#chageInfo").click(function(){
	   		  infocheck();
	   	  });
        
        function infocheck() {
 			if (!userform.nickname.value) {
 				Swal.fire("경고","새로운 닉네임을 입력하세요","warning");
 				loginForm.nickname.focus();
 				return false;
 			}
 			if(!userform.tel.value){            
 				Swal.fire("경고","새로운 전화번호를 입력하세요","warning");
 				loginForm.tel.focus();
 			     return false;
 			 }
 			
 			Swal.fire({
				title:"성공!",
				text: "입력한 값으로 변경되었습니다.",
				type:"success"}).then(function(){
				document.userform.submit();
			});
 		}
  	});
  </script>

</body>

</html>