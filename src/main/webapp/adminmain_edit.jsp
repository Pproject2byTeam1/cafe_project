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
  
  <!-- bootstrab -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
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
  <link href="assets/css/marketboard_read.css" rel="stylesheet">
  
  <style type="text/css">
  
	.image-box {
	width:900px;
	}
	
	.icon-box {
    width:50;
    height:50px;
    overflow:hidden;
    margin:0 auto;
	}

	.image-banner {
   
	}
	
  </style>

   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

</head>

<body class="toggle-sidebar">

  <!-- ======= Header ======= -->
     <header id="header" class="header fixed-top d-flex align-items-center">
        <c:import url="/WEB-INF/view/common/admintop.jsp" />
     </header><!-- End Header -->
     
     <!-- ======= Sidebar ======= -->
     
     <!-- End Sidebar -->

  <main id="main" class="main">

    <div class="pagetitle">

    </div><!-- End Page Title -->

		<!-- 관리자 메인페이지 관리 시작 -->
        <div class="col-xl-12">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div align="right" class="col-md-12">
						<!-- 본인확인 -->
						<c:if test="${member.isAdmin =='M'}">
						<button type="button" id="edit" 
							class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
						<button type="button" id="delete" 
							class="btn btn-outline-secondary btn-sm rounded-pill">취소</button>
						</c:if>
						<!-- 본인확인 -->
						
					</div>
				</div>
					<form name="bbs" action=".do" method="POST" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-4">
								칸반보드
							</div>
							<div class="col-md-8">
								<div class="row">
									<div class="marketcard">
										<div class="marketcard-body">
										<div class="row">
										    <div class="col-md-8">
												<textarea id="inputname" name="cafe_name" row="1" cols="20" maxlength="20">카페이름</textarea>
										    </div>
										</div>
										    <p>
										    <div class="icon-box col-md-12">
												카페아이콘 : <input type="file" class="form-control" placeholder="카페 아이콘" accept="image/*" id="getfile" name="cafe_icon" required>
											</div>
										
											<div class="image-box col-md-12">
												카페 메인 배너 : <input type="file" class="form-control" placeholder="카페 메인 배너" accept="image/*" id="getfile" name="cafe_img" onchange="setThumbnail(event);" required>
												
												<div id="image-thumbnail"></div>
											</div>
											
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
									tiny
									</div>
								</div>
								<div class="row">
									<div class="marketcard col-md-6">
										<table class="table table-striped">
										  <thead>
										    <tr>
										      <th scope="col">First</th>
										      <th scope="col">Last</th>
										      <th scope="col">Handle</th>
										    </tr>
										  </thead>
										  <tbody>
										    <tr>
										      <td>Mark</td>
										      <td>Otto</td>
										      <td>@mdo</td>
										    </tr>
										    <tr>
										      <td>Jacob</td>
										      <td>Thornton</td>
										      <td>@fat</td>
										    </tr>
										    <tr>
										      <td>Jacob</td>
										      <td>Thornton</td>
										      <td>@fat</td>
										    </tr>
										  </tbody>
										</table>
									</div>
									<div class="marketcard col-md-6">
									게시판 리스트 2
									</div>
								</div>
								<div class="row">
									<div class="marketcard col-md-6">
									게시판 리스트 3
									</div>
									<div class="marketcard col-md-6">
									게시판 리스트 4
									</div>
								</div>
							</div>
						</div>
					</form>
					
				</div>
			</div>
		</div>
  		
      </div>

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  
	
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
  

	  function setThumbnail(event) {
	      var reader = new FileReader();
	
	      reader.onload = function(event) {
	        var img = document.createElement("img");
	        img.setAttribute("src", event.target.result);
	        img.setAttribute("style", "width=600px; height=150px;");
	        document.querySelector("div#image-thumbnail").appendChild(img);
	      };
	
	      reader.readAsDataURL(event.target.files[0]);
	    }
	  
	  $('#inputname').keyup(function(){
			
			var inputLength = $(this).val().length; //입력한 값의 글자수
			var remain = 20-inputLength;
			var value = document.getElementById("inputname").value;
		    console.log(value);
			//2. h1 태그 영역에 30/70 이렇게 출력이 되도록 작성...html()
			$("#cafename").html(value);
			
			//3. remain이 50~70 사이면 글자색을 blue.. css 연결
			//   remain이 30~49 사이면 글자색을 orange.. css
			//   remain이 10~29 사이면 글자색을 magenta.. css
			//   remain이 1~9 사이면 글자색을 red.. css
			//   더 이상 글자 입력이 안 되도록
			if(remain<1){
				alert("더 이상 글자 입력 안 됩니다.");
			}
		});

   
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
  			console.log("pwd: "+$('#currentPassword').val());
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
  			console.log("tel: "+$('#tel').val());
  			console.log("length: "+$('#tel').val().length);
  			if($('#tel').val().length != 11 || $('#tel').val() == null){
  				$("#chageInfo").attr("disabled",true);
  			}else{
  				$("#chageInfo").removeAttr("disabled");
  			}
  		})
  	});
  </script>

</body>

</html>