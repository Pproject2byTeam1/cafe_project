<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>marketboard_read</title>

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

</script>
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

		<!-- Page Title -->
		<div class="pagetitle">
			<h1>거래 게시판</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">전체글 : 000</a></li>
					<li class="breadcrumb-item active">판매중 : 000</li>
					<li class="breadcrumb-item">판매완료 : 000</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<!-- Contents -->
		<div class="container">
			<div class="row">

				<div class="col-md-12">
					<div class="marketcard">
						<div class="marketcard-body">

							<div class="row">
								<!-- 상단부 2/3으로 나눠 글 내용 시작 -->
								<div class="col-md-8">
									<span id="marketB_Title">${list.title}</span>
									<p>
									<hr>
									<div class="row">
										<div class="col-lg-9">
											<img class="marketB_img" src="image/board/5/${list.img_name}">
										</div>
										<div align="center" class="col-lg-3">
											<div class="info-body price">
												<span id="marketB_Price">${list.price}</span>
											</div>
											<div class="info-body">
												<span id="marketB_Text.ns">${list.m_mode}|${list.sold}|${list.cate}</span>
											</div>
											<div class="info-body">
												<span id="marketB_Text.ns">조회수:${list.hits} 댓글:3 찜:3</span>
											</div>
											<div class="info-body">
												<img src="image/rank_icon/1.gif" alt="Profile"
													style="width:15px" class="rounded-circle">
												<span id="marketB_Text.ns">${list.nick}</span>
											</div>
											<div class="info-body">
												<span id="marketB_Text.ns">${list.w_date}</span>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<hr>
											${list.content}
											<p>
										</div>
										<hr>
										<div align="right" class="col-md-12">
											<div>
												<button type="button" id="Write" 
													class="btn btn-outline-secondary btn-sm rounded-pill">글쓰기</button>
												<button type="button" id="List" 
													class="btn btn-outline-secondary btn-sm rounded-pill">목록</button>
												<button type="button" id="Top" 
													class="btn btn-outline-secondary btn-sm rounded-pill">TOP</button>
											</div>
										</div>

									</div>
								</div>
								<!-- 상단부 1/3으로 나눠 댓글시작 -->
								<div class="col-md-4">
									<br>
									<!-- 댓글 카드 섹션 -->
									<div>
										<!-- 작성란 -->
										<div class="quill-editor-default">
											<p>댓글 내용을 입력하세요</p>
										</div>
										<nav aria-label="Page navigation example">
											<ul class="pagination justify-content-end">

												<div class="col-md-4 text-lg-end">
													<br>
													<button type="button" id="cWrite" 
														class="btn btn-outline-secondary btn-sm rounded-pill">작성</button>
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

									<div class="marketcard-body">
										<div class="message">
											<h5 class="card-title">
												<img src="image/rank_icon/1.gif" alt="Profile"
													class="rounded-circle" style="width: 15px"> USER_NICK
											</h5>
											<h6 class="card-text">댓글의 내용~~~</h6>
											<h6></h6>
										</div>
										<div class="actions" align="right">
											<button type="button" id="rereply" 
												class="btn btn-outline-secondary btn-sm rounded-pill">대댓글</button>
											<button type="button" id="cEdit" 
												class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
											<button type="button" id="cDel" 
												class="btn btn-outline-secondary btn-sm rounded-pill">삭제</button>
										</div>
									</div>
									<!-- 댓글 목록 카드 섹션 -->
									<div class="marketcard-body">
										<div class="message">
											<h5 class="card-title">
												<img src="image/rank_icon/1.gif" alt="Profile"
													class="rounded-circle" style="width: 15px"> USER_NICK
											</h5>
											<h6 class="card-text">댓글의 내용~~~</h6>
											<h6></h6>
										</div>
										<div class="actions" align="right">
											<button type="button" id="rereply" 
												class="btn btn-outline-secondary btn-sm rounded-pill">대댓글</button>
											<button type="button" id="cEdit" 
												class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
											<button type="button" id="cDel" 
												class="btn btn-outline-secondary btn-sm rounded-pill">삭제</button>
										</div>
									</div>
									<!-- 댓글 목록 카드 섹션 -->
									<div class="marketcard-body">
										<div class="message">
											<h5 class="card-title">
												<img src="image/rank_icon/1.gif" alt="Profile"
													class="rounded-circle" style="width: 15px"> USER_NICK
											</h5>
											<h6 class="card-text">댓글의 내용~~~</h6>
											<h6></h6>
										</div>
										<div class="actions" align="right">
											<button type="button" id="rereply" 
												class="btn btn-outline-secondary btn-sm rounded-pill">대댓글</button>
											<button type="button" id="cEdit" 
												class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
											<button type="button" id="cDel" 
												class="btn btn-outline-secondary btn-sm rounded-pill">삭제</button>
										</div>
									</div>
								</div>
								<!-- 댓글 끝 -->
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>


		<!-- 보드 리스트 출력 시작 -->

		<!-- 보드 페이지 시작 -->

		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link" href="#"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">4</a></li>
				<li class="page-item"><a class="page-link" href="#">5</a></li>
				<li class="page-item"><a class="page-link" href="#">6</a></li>
				<li class="page-item"><a class="page-link" href="#">7</a></li>
				<li class="page-item"><a class="page-link" href="#">8</a></li>
				<li class="page-item"><a class="page-link" href="#">9</a></li>
				<li class="page-item"><a class="page-link" href="#">10</a></li>
				<li class="page-item"><a class="page-link" href="#"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>

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

<!-- Template Main JS File -->
<script src="assets/js/main.js"></script>
</html>