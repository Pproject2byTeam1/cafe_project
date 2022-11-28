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
  
.maincard {
	background-color : white;
	margin-bottom: 30px;
	border-radius: 1em;
	box-shadow: 0px 0 30px rgba(1, 41, 112, 0.1);
	padding : 15px;
}

#wrap1::after {
    content: "";
    display: block;
    clear: both;
}

.container {
  position: relative;
  width: 100%;
  height: 200px;
  background-color: #e0e0e0;
}
.container.wide {
  width: 100%;
  height: 200px;
}

.ex {
  margin: 20px 0;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
}

.ex-container {
  margin-right: 20px;
  margin-bottom: 20px;
}

.ex-container:last-child {
  margin: 0;
}

@media (max-width: 100%;) {
  .flex {
    flex-direction: column;
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

  <main id="main" class="main">

    <div class="pagetitle">

    </div><!-- End Page Title -->

		<!-- 관리자 메인페이지 관리 시작 -->

		<div class="row">
			<div class="col-md-12">
				<div align="right" class="col-md-12">
					<!-- 본인확인 -->
					<c:if test="${member.isAdmin =='M'}">
					<button type="submit" id="edit" 
						class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
					<button type="button" id="cancel" 
						class="btn btn-outline-secondary btn-sm rounded-pill">취소</button>
					</c:if>
					<!-- 본인확인 -->
					
				</div>
			</div>
		<form name="bbs" action=".do" method="POST" enctype="multipart/form-data">
			<div class="container-fluid">
			<div class="wrap1">
			<div class="col-md-12">
				<div class="maincard">
					<div class="row">
						<div class="col-md-4">
							<div>
						      <a class="logo d-flex align-items-center">
						        <img src="assets/img/logo.png" alt="">
						        <span id="cafename" class="d-none d-lg-block"><input type="text" name="cafe_name" id="inputname" placeholder="카페 이름" rows="1" cols="20"></input></span>
						      </a>
						    </div>
						</div>
				
						<div class="col-md-8">
							<div class="row">
							 <label for="form-control" class="col">아이콘 : <input type="file" name="cafe_icon" class="form-control" placeholder="카페 아이콘" accept="image/*" id="getfile" onchange="seticon(event)" required></label>
							 <label for="form-control" class="col">메인 배너 : <input type="file" name="cafe_img"  class="form-control" placeholder="카페 메인 배너" accept="image/*" id="getfile" onchange="setBanner(event)" required></label>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
				
			<!-- 게시판 리스트 기능 -->
			<div class="row">
				<div class="col-md-4">
					<div class="maincard">
							<div class="row">
								<div class="col">
									<div align="center" class="col-md-4">							
										<button type="submit" id="edit" 
											class="btn btn-outline-secondary btn-sm rounded-pill">게시판 추가</button>
										<button type="button" id="cancel" 
											class="btn btn-outline-secondary btn-sm rounded-pill">구분선 추가</button>
										<button type="button" id="cancel" 
											class="btn btn-outline-secondary btn-sm rounded-pill">휴지통</button>
									</div>
								</div>
								<div class="col">
								
								</div>
							</div>
					</div>
				</div>
				<div class="col-md-8">
					<div class="maincard">
						<div class="col-md-12">
							<div class="ex-container">
								<div class="container wide" id="image-thumbnail"></div>
								
							</div>
						</div>
						<div class="col-md-12">
						<hr>
							<textarea rows="" cols=""></textarea>
						</div>
						
					</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="maincard">
							<table class="table table-striped">
							  <thead>
							    <tr>
							      <th scope="col">자유게시판</th>
							      <th scope="col">제목</th>
							      <th scope="col">시간</th>
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
					</div>
					<div class="col-md-6">
						<div class="maincard">
							
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="maincard">
						게시판3
						</div>
					</div>
					<div class="col-md-6">
						<div class="maincard">
						게시판4
						</div>
					</div>
				</div>
				</div>
			</div>
		</div>
		</form>
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
  

	  function setBanner(event) {
	      var reader = new FileReader();
	
	      reader.onload = function(event) {
	        var img = document.createElement("img");
	        img.setAttribute("src", event.target.result);
	       
	        
	        document.querySelector("div#image-thumbnail").appendChild(img);
	      };
	
	      reader.readAsDataURL(event.target.files[0]);
	    }
	  
	  function setIcon(event) {
	      var reader = new FileReader();
	
	      reader.onload = function(event) {
	        var img = document.createElement("img");
	        img.setAttribute("src", event.target.result);
	        img.setAttribute("style", "width=50px; height=50px;");
	       
	        document.querySelector("div#image-icon").appendChild(img);
	      };
	
	      reader.readAsDataURL(event.target.files[0]);
	    }
	  
	  $('#inputname').keyup(function(){
			
			var inputLength = $(this).val().length; //입력한 값의 글자수
			var remain = 20-inputLength;
			var value = document.getElementById("inputname").value;
		   
			
			$("#cafename").html(value);
		
			if(remain<1){
				alert("더 이상 글자 입력 안 됩니다.");
			}
		});

	  </script>   

	 
	<script>
		tinymce
				.init({
					selector : 'textarea',
					plugins : 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage tinycomments tableofcontents footnotes mergetags autocorrect',
					toolbar : 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
					tinycomments_mode : 'embedded',
					tinycomments_author : 'Author name',
					mergetags_list : [ {
						value : 'First.Name',
						title : 'First Name'
					}, {
						value : 'Email',
						title : 'Email'
					}, ]
				});
	</script>

</body>

</html>