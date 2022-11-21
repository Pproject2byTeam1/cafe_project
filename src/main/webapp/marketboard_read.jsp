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
<<<<<<< Updated upstream
<link href="assets/css/marketboard_read.css" rel="stylesheet">
=======
<style type="text/css">
#YES {
	width: 50px;
}

#columns {
   column-width: 300px;
   column-gap: 20px;
}

#columns figure {
   display: inline-block;
   margin: 0;
   margin-bottom: 15px;
}

#columns figure img {
   width: 100%;
   border-radius: 5%;
}

#columns figure figcaption {
   padding: 10px;
}

.marketcard {
  background-color : white;
  margin-bottom: 30px;
  border-radius: 3%;
  box-shadow: 0px 0 30px rgba(1, 41, 112, 0.1);
  padding : 15px;
}

.marketcard-body {
  padding: 10px 10px 10px 10px;
}

.marketcard-body img{
   width: 100%;
   border-radius: 5%;
}

.replyinput {
   border-radius: 30px;
}

.product-title {
    text-align:center;
    display:table;
    border:0.5px solid #cecece;
    width:100%;
    height:100%;
}

.product-img-div {
    display:table-cell;
    vertical-align:middle;
}

.product-img {
    max-width:100%;
    max-height:100%;
    object-fit: contain;
    border-radius: 5px;
}

#marketB_Title {
	font-size: 36px;
	color: #012970;
	font-family: "Poppins", sans-serif;
}

#marketB_Text {
	font-size: 24px;
	font-weight: 500;
	color: #012970;
	font-family: "Poppins", sans-serif;
}

.marketB_img {
    width:100%;
    height:100%;
    object-fit:cover;
}

.ns {
	color: red;
}

#marketB_Price {
	font-size: 36px;
	color: orange;
	font-family: "Poppins", sans-serif, bold;
}

*, *::before, *::after {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

.container {
	display: flex;
	justify-content: center;
	margin-inline: auto;
}

.container__content--flow {
	padding-block: 1rem;
	display: flex;
	flex-wrap: wrap;
	gap: 1rem;
}

.container__content--flow>* {
	flex-grow: 1;
	margin: 10px 10px 10px 0;
	flex-basis: calc(( 30rem - 100%)* 999);
	min-width: 300px;
	max-width: 300px;
}

.mcard {
	display: flex;
	flex-direction: column;
	background-color: #fff;
	border-radius: 0.5em;
	box-shadow: 0 0.125em 0.25em #0004;
	transition: all 400ms ease;
	position: relative;
	padding: 10px;
}

.mcard::before {
	content: "";
	position: absolute;
	inset: 0;
	opacity: 0;
	border-radius: inherit;
	transition: opacity 300ms ease;
	box-shadow: 0 0.125em 0.25em #0003, 0 0.65em 0.85em #0003, 0 0.96em
		1.35em #0002;
}

.mcard:hover::before {
	opacity: 1;
}

.mcard:nth-child(odd) {
	flex: 1 1 25%;
}

.container__content--flow:hover .mcard:not(:hover) {
	background-color: #fff6;
	box-shadow: none;
	opacity: 0.8;
}

.mcard:hover {
	scale: 1.05;
}

@media ( max-width : 830px) {
	.card:nth-child(odd) {
		flex: 1 1;
	}
}
</style>
>>>>>>> Stashed changes
<!-- 거래게시판 CSS 끝 -->

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">

		<jsp:include page="/common/top.jsp"></jsp:include>

	</header>
	<!-- End Header -->

	<!-- ======= Sidebar ======= -->

	<jsp:include page="/common/side.jsp"></jsp:include>

	<!-- ======= End Sidebar ======= -->
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
 <div class="col-1"></div>
 <div class="col-10">
	<div class="marketcard">
		<div class="marketcard-body">
			<h5 class="card-title">거래 게시판에서 발생하는 모든 문제는 본인에게 있습니다.</h5>
			<div class="row">
				<div class="col-md-12">
					<span id="marketB_Title">맥북 팝니다!</span><p>
					<hr>
					<div class="row">
						<div class="col-md-8">
							<img class="product-img" src="image/marketboard_img/1detail.jpg">
						</div>
						<div align="center" class="col-md-4">
							<p>
							<span id="marketB_Price">1,300,000</span>
							<p>
								<span id="marketB_Text.ns">직거래</span> |<span
									id="marketB_Text.ns">판매중</span><br>
							<h6>
								USER_NICK
							</h6>
							<span id="marketB_Text.ns">22.11.18 12:10</span>
							<p>
								<span id="marketB_Text.ns">조회수 : 120</span> <span
									id="marketB_Text.ns">찜 : 3</span>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
						<hr>
						상품명 : 맥북 2022 램 16<br>
						가격 : 1,300,000<br>
						거래방법 : 직거래<br>
						전화번호 : 010-1234-1234<br>
						상품설명 : 블라블라 직거래 혜화<br>
						<p>
						</div>
						<hr>
						<div align="right" class="col-md-12">
						<div class="btn-group" role="group" aria-label="게시판 기능">
                           <button type="button" class="btn btn-outline-secondary btn-sm rounded-pill">글쓰기</button>
                           <button type="button" class="btn btn-outline-secondary btn-sm rounded-pill">목록</button>
                           <button type="button" class="btn btn-outline-secondary btn-sm rounded-pill">TOP</button>
						</div>
						</div>

					</div>
				</div>
			
			
				
			
			</div>
		</div>
	</div>
	

</div>
</div>
</div>
 
 <div class="row"> 	
 <div class="col-1"></div>


			<!-- End Bordered Tabs -->
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
			</div>

			<!-- 보드 페이지 끝 -->

		</section>
	</main>
	<!-- End Contents -->
</body>
</html>