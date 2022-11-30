<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>카페人중독</title>
<meta content="" name="description">
<meta content="" name="keywords">
<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- ======= Sidebar ======= -->

<jsp:include page="/WEB-INF/view/common/side.jsp"></jsp:include>
<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- 테이블 정렬 -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css"
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
<link href="assets/css/free.css" rel="stylesheet">

<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
<script type="text/javascript">
	$(function() {

		/* 삭제  */
		function del(data) {

			$.ajax({
				url : "Deleterapport",
				data : data,
				dataType : "html",
				success : function(responsetxt) {

					$('#rapportlist').remove();

				}

			});

		}
		;

		$(document).on('click', '.deletebtn', function() {

			const data = {
				"idx" : $(this).parent().parent().children('.idx').val()
			};

		
			del(data);

		});

	});


</script>

</head>

<body>
	<c:set var="pagesize" value='<%=request.getAttribute("pagesize")%>' />
	<c:set var="cpage" value='<%=request.getAttribute("cpage")%>' />
	<c:set var="pagecount" value='<%=request.getAttribute("pagecount")%>' />
	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">
		<c:import url="/WEB-INF/view/common/top.jsp" />
	</header>
	<!-- End Header -->

	<!-- ======= Sidebar ======= -->

	<jsp:include page="/WEB-INF/view/common/side.jsp"></jsp:include>

	<!-- End Sidebar -->

	<main id="main" class="main">

		<div class="pagetitle">
			<h1>신고현황</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="cafemain.do">Home</a></li>
					<li class="breadcrumb-item active">신고현황</li>
				</ol>
			</nav>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-5"></div>
				<div class="col-md-4">
					<div class="row">

						<div class="col-md-1"></div>
						<div class="col-md-5"></div>
					</div>
				</div>

				<div class="col-md-3">
					<div class="search-bar">
						<!-- <form class="search-form d-flex align-items-center" method="POST"
							action="#">
							<input type="text" id="searchInput"  name="query" placeholder="Search" class="form-control" title="닉네임을 입력하세요" >
							<button type="submit" title="" class="btn btn-secondary" id="search">
								<i class="bi bi-search"></i>
							</button>
						</form> -->
						<div align="right">

							<form action="rapport_list.do?" method="post">

								<select class="selectpicker" data-width="100" name="ps"
									onchange="submit()">
									<c:forEach var="i" begin="5" end="20" step="5">
										<c:choose>
											<c:when test="${pagesize == i}">
												<option value="${i}" selected>${i}건</option>
											</c:when>
											<c:otherwise>
												<option value="${i}">${i}건</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>

								</select>
							</form>


						</div>
					</div>


				</div>

			</div>
		</div>

		<br>

		<div class="container-fluid">
			<div class="card">
				<div class="card-body">
					<table class="table table-hover " id=dataTable1>
						<tr>

							<th scope="col">글/댓글</th>
							<th scope="col">게시판 종류</th>
							<th scope="col">글제목</th>
							<th scope="col">닉네임</th>
							<th scope="col">이메일</th>
							<th scope="col">조회수</th>
							<th scope="col">신고수</th>
							<th scope="col">신고페이지</th>

						</tr>
		

						<c:if test="${reportlist.size() == 0}">
							<tr>
								<th  colspan="8"> 조회된 데이터가 없습니다</th>
							</tr>
						</c:if>
						<c:forEach var="reportlist" items="${reportlist}"
							varStatus="status">
							<tr id="rapportlist">
								<input class="idx" name="idx" id="idx" value="${reportlist.idx}"type="hidden" />
									<input class="b_code" name="b_code" id="b_code" value="${reportlist.b_code}"type="hidden" />
								<c:choose>
									<c:when test="${request.b_code eq'null'} ">
										<td scope="col">댓글</td>
									</c:when>
									<c:otherwise>
										<td scope="col">글</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${reportlist.b_code eq'1'}">
										<td scope="col">자유게시판</td>
									</c:when>
									<c:when test="${reportlist.b_code eq'2'}">
										<td scope="col">출석게시판</td>
									</c:when>
									<c:when test="${reportlist.b_code eq'3'}">
										<td scope="col">전체일정</td>
									</c:when>
									<c:when test="${reportlist.b_code eq'4'}">
										<td scope="col">사진공유</td>
									</c:when>
									<c:when test="${reportlist.b_code eq'5'}">
										<td scope="col">유로거래</td>
									</c:when>
									<c:otherwise>
										<td scope="col">자료게시판</td>
									</c:otherwise>
								</c:choose>

								<td scope="col">${reportlist.title}</td>
								<td scope="col">${reportlist.nick}</td>
								<td scope="col">${reportlist.email_id}</td>
								<td scope="col">${reportlist.hits}</td>
								<td scope="col">${reportlist.report_count}</td>
								<td scope="col">
									<c:choose>
									<c:when test="${reportlist.b_code eq'1'}">
										<button type="button" class="btn btn-danger"
										onclick="window.open('regular_post.do?b_code=${reportlist.b_code}&idx=${reportlist.idx}&cp=${cpage}&ps=${pagesize}')">신고페이지</button>
									<button type="button" class="btn btn-danger deletebtn">신고취소</button>
									</c:when>
									<c:when test="${reportlist.b_code eq'2'}">
									<button type="button" class="btn btn-danger"
										onclick="window.open('checkBoard.do?b_code=${reportlist.b_code}&idx=${reportlist.idx}&cp=${cpage}&ps=${pagesize}')">신고페이지</button>
									<button type="button" class="btn btn-danger deletebtn">신고취소</button>
									</c:when>
									<c:when test="${reportlist.b_code eq'3'}">
										<button type="button" class="btn btn-danger"
										onclick="window.open('checkBoard.do?b_code=${reportlist.b_code}&idx=${reportlist.idx}&cp=${cpage}&ps=${pagesize}')">신고페이지</button>
									<button type="button" class="btn btn-danger deletebtn">신고취소</button>
									</c:when>
									<c:when test="${reportlist.b_code eq'4'}">
									<button type="button" class="btn btn-danger"
										onclick="window.open('checkBoard.do?b_code=${reportlist.b_code}&idx=${reportlist.idx}&cp=${cpage}&ps=${pagesize}')">신고페이지</button>
									<button type="button" class="btn btn-danger deletebtn">신고취소</button>
									</c:when>
									<c:when test="${reportlist.b_code eq'5'}">
										<button type="button" class="btn btn-danger"
										onclick="window.open('marketboard_read.do?b_code=${reportlist.b_code}&idx=${reportlist.idx}&cp=${cpage}&ps=${pagesize}')">신고페이지</button>
									<button type="button" class="btn btn-danger deletebtn">신고취소</button>
									</c:when>
									<c:otherwise>
										<button type="button" class="btn btn-danger"
										onclick="window.open('databoard_read.do?b_code=${reportlist.b_code}&idx=${reportlist.idx}&cp=${cpage}&ps=${pagesize}')">신고페이지</button>
									<button type="button" class="btn btn-danger deletebtn">신고취소</button>
									</c:otherwise>
								</c:choose>
								
									
								</th>
							</tr>
						</c:forEach>

					</table>


					<!-- End Table with hoverable rows -->
					<!-- 페이징  -->

					<!-- End Centered Pagination -->
				</div>
			</div>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">

					<c:if test="${cpage > 1}">
						<li class="page-item"><a class="page-link"
							href="rapport_list.do?cp=${cpage-1}&ps=${pagesize}" tabindex="-1"
							aria-disabled="true"><<</a></li>
					</c:if>

					<c:forEach var="i" begin="1" end="${pagecount}" step="1">
						<c:choose>
							<c:when test="${cpage==i}">
								<li class="page-item"><a class="page-link active">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="rapport_list.do?cp=${i}&ps=${pagesize}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<c:if test="${cpage < pagecount}">
						<li class="page-item"><a class="page-link"
							href="rapport_list.do?cp=${cpage+1}&ps=${pagesize}">>></a></li>
					</c:if>
				</ul>
			</nav>
		</div>


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
		
	</script>
</body>

</html>
