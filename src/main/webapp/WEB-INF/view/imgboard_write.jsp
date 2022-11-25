<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카페人중독</title>

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

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
<link href="assets/css/imgboard.css" rel="stylesheet">

	<!-- sweetalert -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

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
		<!-- 여기서부터 작성 와랄ㄹ라  -->

		<c:if test="${member == null }">
			<jsp:forward page="/WEB-INF/view/login.jsp"/>
		</c:if>


		<div class="pagetitle">
			<h1>IMG BOARD</h1>
			<!-- 게시판 이름 끌고오기 b_name -->
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">Home</a></li>
					<li class="breadcrumb-item"><a
						href="img_board_list.do?b_code=${b_code}">IMG BOARD</a></li>
					<li class="breadcrumb-item active">IMG Write</li>
				</ol>
			</nav>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-1"></div>


				<form name="bbs" action="board_writeok.do" method="POST" enctype="multipart/form-data">
					<div class="col-10">
						<div class="park-card p-4">
							<div class="park-card-body row">
								<div class="mb-3 row">
									<label for="staticEmail" class="col-2 col-form-label">Title : </label>
									<div class="col-8">
										<input type="text" class="form-control" id="title" name="title">
									</div>
									<div class="col-2">
										<button type="button" id="btn" class="btn btn-primary">확인</button>
									</div>
								</div>
								<div class="col-md-6">
								</div>
								<div class="col-md-12">
									<input type="file" class="form-control" accept="image/board/*" id="getfile" name="filename1">
								</div>
							</div>
						</div>
						
						<div class="park-card p-4">
							<div class="park-card-body row">
								<textarea name="content"> 
        			 				글을 작성해주세요.
        						</textarea>
							</div>
						</div>
					</div>
				</form>
			</div>

			<div class="col-1"></div>
		</div>

		<!-- 여기까지만 작성  -->
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

<!-- Template Main JS File -->
<script src="assets/js/main.js"></script>
</html>