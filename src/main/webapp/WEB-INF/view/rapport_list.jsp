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
<link href="assets/css/free.css" rel="stylesheet">
<style>
/* .btn-success btn-lg {
   float: right;
} */
.selectpicker {
	width: 75px;
	height: 35px;
	border-radius: 5px;
}

._3Espq6 {
	width: 38px;
	height: 22px;
	font-size: 0.9rem;
	text-align: center;
}

.parent {
	width: 500px;
	display: flex;
	flex-direction: column;
}

.name {
	font-size: 0.9rem;
	margin-top: auto;
	vertical-align: bottom;
}

._1R-fi- {
	margin-bottom: 0.5rem;
	color: #2b2d36;
	line-height: 1.5;
	font-weight: 700;
	font-size: 1.5rem;
	letter-spacing: -0.01875rem;
	margin-bottom: 0;
	color: var(- -gray-600);
	text-align: center;
}

.jdc {
	text-align: center;
}

.son_name {
	font-size: 13px;
	font-weight: bold;
}

.son_date {
	font-size: 13px;
}

.son_time {
	gk font-size: 13px;
}
/*  표 style */
.card-body {
	overflow-x: auto;
}

.table table-hover {
	width: 100%;
	min-width: 500px;
	display: block;
	overflow: auto;
}
</style>
</head>

<body>
	<c:set var="pagesize" value='<%=request.getAttribute("pagesize")%>' />
	<c:set var="cpage" value='<%=request.getAttribute("cpage")%>' />
	<c:set var="pagecount" value='<%=request.getAttribute("pagecount")%>' />
	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">

		<jsp:include page="/common/top.jsp"></jsp:include>

	</header>
	<!-- End Header -->

	<!-- ======= Sidebar ======= -->

	<jsp:include page="/common/side2.jsp"></jsp:include>

	<!-- End Sidebar -->

	<main id="main" class="main">

		<div class="pagetitle">
			<h1>신고현황</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">Home</a></li>
					<li class="breadcrumb-item active">신고현황</li>
				</ol>
			</nav>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-5"></div>
				<div class="col-md-4">
					<div class="row">
						<div class="col-md-5">
							<select class=" form-select">
								<option>전체등급</option>
								<option>준회원</option>
								<option>일반회원</option>
							</select>
						</div>
						<div class="col-md-1"></div>
						<div class="col-md-5">
							<select class="form-select">
								<option>전체조회</option>
								<option>OO조회</option>
								<option>OO조회</option>
							</select>
						</div>
					</div>
				</div>

				<div class="col-md-3">
					<div class="search-bar">
						<form class="search-form d-flex align-items-center" method="POST"
							action="#">
							<input type="text" name="query" placeholder="Search"
								class="form-control" title="Enter search keyword">
							<button type="submit" title="Search" class="btn btn-secondary">
								<i class="bi bi-search"></i>
							</button>
						</form>
					</div>
				</div>

			</div>
		</div>

		<br>

		<div class="container-fluid">
			<div class="card">
				<div></div>
				<div class="card-body">
					<!-- <h5 class="card-title">Table with hoverable rows</h5>-->
					<!-- Table with hoverable rows -->
					<table class="table table-hover">

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

				<script type="text/javascript">
						console.log("${member}");
						</script>
						<c:if test="${reportlist== null}">
							<tr>
								<td >데이터가 없습니다</td>
							</tr>
						</c:if>
							<c:forEach var="reportlist" items="${reportlist}" varStatus="status">
							<tr>
							<c:choose>
								<c:when test="${request.b_code eq'null'} ">
								<th scope="col">댓글</th>
								</c:when>
								<c:otherwise>
								<th scope="col">글</th>
								</c:otherwise>
							</c:choose>
							<c:choose>
							<c:when test="${request.b_code eq'1'}">
								<th scope="col">자유게시판</th>
								</c:when>
								<c:when test="${request.b_code eq'2'}">
								<th scope="col">출석게시판</th>
								</c:when>
								<c:when test="${request.b_code eq'3'}">
								<th scope="col">전체일정</th>
								</c:when>
									<c:when test="${request.b_code eq'4'}">
								<th scope="col">사진공유</th>
								</c:when>
									<c:when test="${request.b_code eq'5'}">
								<th scope="col">유로거래</th>
								</c:when>
								<c:otherwise>
								<th scope="col">자료공유</th>
								</c:otherwise>
							</c:choose>

								<th scope="col">${reportlist.title}</th>
								<th scope="col">${reportlist.nick}</th>
								<th scope="col">${reportlist.email_id}</th>
								<th scope="col">${reportlist.hits}</th>
								<th scope="col">${reportlist.report_count}</th>
								<th scope="col"><button type="button"
										class="btn btn-danger">신고페이지</button></th>
							</tr>
						</c:forEach>
						



					</table>
					<!-- End Table with hoverable rows -->
					<!-- 페이징  -->
					<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
					
		                <c:if test="${cpage > 1}">
		                  <li class="page-item">
		                    <a class="page-link" href="user_list.do?cp=${cpage-1}&ps=${pagesize}" tabindex="-1" aria-disabled="true"><<</a>
		                  </li>
	                    </c:if>
	                    	
	                    <c:forEach var="i" begin="1" end="${pagecount}" step="1">
	                    	<c:choose>
								<c:when test="${cpage==i}">
										<li class="page-item"><a class="page-link active" >${i}</a></li>
								</c:when>
								<c:otherwise>
		                  			<li class="page-item"><a class="page-link" href="user_list.do?cp=${i}&ps=${pagesize}">${i}</a></li>
								</c:otherwise>
							</c:choose>
	                    </c:forEach>
	                    
	                    <c:if test="${cpage < pagecount}">
	                    	<li class="page-item">
							<a class="page-link" href="user_list.do?cp=${cpage+1}&ps=${pagesize}">>></a>
							</li>
						</c:if>
					</ul>
				</nav>
					<!-- End Centered Pagination -->
				</div>
			</div>

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

</body>

</html>
