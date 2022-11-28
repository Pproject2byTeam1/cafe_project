<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>databoard_write</title>

<!-- jQuery -->
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

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
<!-- 거래게시판 CSS 시작 -->
<link href="assets/css/marketboard_read.css" rel="stylesheet">
<!-- 거래게시판 CSS 끝 -->

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">

  	<script type="text/javascript">
 
  	
  	</script>

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
		
		<c:if test="${member == null }">
			<jsp:forward page="/WEB-INF/view/login.jsp"/>
		</c:if>
		
		<!-- Page Title -->
		<div class="pagetitle">
			<h1>자료 게시판 글쓰기</h1>
			<nav>

			</nav>
		</div>
		<!-- End Page Title -->

		<!-- Contents -->
		<div class="container">
			<div class="row">

				<div class="col-md-12">
					<div class="marketcard">
						<div class="marketcard-body" id="pageContainer">

							<div class="row">
								<!-- 상단부 2/3으로 나눠 글 내용 시작 -->
								<div class="col-md-12">
									<!-- 글 제목 -->
									<form name="bbs" action="databoard_writeok.do" method="POST" enctype="multipart/form-data">
									<input type="text" class="form-control" id="title" placeholder="제목을 입력하세요." name="title" required>
									<input id="b_code" name="b_code" value="${b_code}" type="hidden" />
									<p>
									<hr>
									
										<div>
											<input type="file" class="form-control" accept="image/board/*" id="getfile" name="filename1" required>
										</div>
										<div>

										</div>
									
									
										<div class="col-md-12">
											<hr>
											<!-- 글 내용 작성 -->
											<div class="park-card p-4">
												<div class="park-card-body row">
													<textarea name="content" placeholder="내용을 입력해주세요."></textarea>
												</div>
											</div>
											<p>
										</div>
										<hr>
										<div align="right" class="col-md-12">
											<div>
												<button type="submit" id="submit" 
													class="btn btn-outline-secondary btn-sm rounded-pill">등록</button>
												<button type="button" id="List" 
													class="btn btn-outline-secondary btn-sm rounded-pill">목록</button>
									
											</div>
										</div>
									</form>
									</div>
								</div>
								
								
							</div>
						</div>
					</div>

				</div>
			</div>
		

		<!-- 보드 페이지 시작 -->

		<!-- 보드 페이지 끝 -->
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

<script type="text/javascript">
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
		
		var file = document.querySelector('#getfile');

		file.onchange = function() {
			var fileList = file.files;

			// 읽기
			var reader = new FileReader();
			reader.readAsDataURL(fileList[0]);

			//로드 한 후
			reader.onload = function() {
				
				$('#pageContainer').css("display", "flex");
				$('#pageContainer').css("justify-content", "center");
				$('#pageContainer').append("<img id='preview' src='' width='500px' style='padding-top: 25px;'>")
				document.querySelector('#preview').src = reader.result;
			};
		};
	</script>

<!-- Template Main JS File -->
<script src="assets/js/main.js"></script>
</html>