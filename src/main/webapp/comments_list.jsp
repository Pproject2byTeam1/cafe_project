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
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- comment CSS Files -->
<link href="assets/css/comments.css" rel="stylesheet">

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
<!-- coments CSS Files -->
<link href="assets/css/comments.css" rel="stylesheet">
<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">

</head>

<body>

	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">
		<jsp:include page="/common/top.jsp"></jsp:include>
	</header>
	<!-- End Header -->

	<!-- Sidebar -->
	<jsp:include page="/common/side.jsp"></jsp:include>
	<!-- End Sidebar -->

	<main id="main" class="main">
		<!-- Page Title -->
		<div class="pagetitle">
			<h1>댓글</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">Home</a></li>
					<li class="breadcrumb-item active">게시판 하단에 들어갈 댓글</li>
				</ol>
			</nav>
		</div>
		<!-- Page Title -->

		<section class="section dashboard">
			<div class="row">
				<!-- columns -->
				<div class="col-lg-12">

					<!-- 댓글 카드 섹션 -->
					<div class="ccard">
						<div class="comment-write">
							<h5 class="card-title">
								댓글 <span>/ 4개</span>
							</h5>

							<!-- 작성란 -->
							<div class="quill-editor-default">
								<p>댓글 내용을 입력하세요</p>
							</div>
							<nav aria-label="Page navigation example">
								<ul class="pagination justify-content-end">

									<div align="right" class="col-sm-2 text-lg-end">
										<br>
										<button type="button"
											class="btn btn-outline-secondary btn-sm rounded-pill">작성하기</button>
									</div>
									<div class="modal fade" id="basicModal" tabindex="-1">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-body">내용을 입력하세요.</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Close</button>
												</div>
											</div>
										</div>
									</div>
								</ul>
							</nav>
							<!-- End 작성란 -->
						</div>
					</div>

					<!-- 댓글 목록 카드 섹션 시작 -->
					<div class="ccard">
						<div class="comment-card">
							<div class="comment-box">
								<div class="row">
								<div class="col">
								<h5 class="card-title">
									<img src="image/rank_icon/1.gif" alt="Profile"
										class="rounded-circle"> USER_NICK
								</h5>
								</div>
								<div class="col comment-date">22.11.21 12:10</div>
								</div>
								<h6 class="card-text">댓글의 내용~~~</h6>
								<h6></h6>
							
							<div align="right" class="actions">
								<button type="button"
									class="btn btn-outline-secondary btn-sm rounded-pill">대댓글</button>
								<button type="button"
									class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
								<button type="button"
									class="btn btn-outline-secondary btn-sm rounded-pill">삭제</button>
							</div>
							</div>
						</div>
					
						<div class="Recomment-box">
								<div class="row">
								<div class="col">
								<h5 class="card-title">
									<i class="bi bi-arrow-return-right"></i>
									<img src="image/rank_icon/1.gif" alt="Profile"
										class="rounded-circle"> USER_NICK
								</h5>
								</div>
								<div class="col comment-date">22.11.21 12:10</div>
								</div>
								<h6 class="Recomment-text">대댓글 대댓글</h6>
								<h6></h6>
								
							<div align="right" class="actions">
								<button type="button"
									class="btn btn-outline-secondary btn-sm rounded-pill">대댓글</button>
								<button type="button"
									class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
								<button type="button"
									class="btn btn-outline-secondary btn-sm rounded-pill">삭제</button>
							</div>
						</div>

						<div class="comment-card">
							<div class="comment-box">
								<div class="row">
								<div class="col">
								<h5 class="card-title">
									<img src="image/rank_icon/1.gif" alt="Profile"
										class="rounded-circle"> USER_NICK
								</h5>
								</div>
								<div class="col comment-date">22.11.21 12:10</div>
								</div>
								<h6 class="card-text">댓글의 내용~~~</h6>
								<h6></h6>
							
							<div align="right" class="actions">
								<button type="button"
									class="btn btn-outline-secondary btn-sm rounded-pill">대댓글</button>
								<button type="button"
									class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
								<button type="button"
									class="btn btn-outline-secondary btn-sm rounded-pill">삭제</button>
							</div>
							</div>
							
					</div>
					<!-- 댓글 목록 카드 섹션 끝 -->

				</div>
			</div>

		</section>
	</main>
	<!-- End #main -->

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


<!-- Template Main JS File -->
<script src="assets/js/main.js"></script>
</html>