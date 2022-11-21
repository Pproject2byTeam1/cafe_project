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
            <div class="col-1">
            
            </div>
            
            <div class="col-10">
               <div class="marketcard">
                  <div class="marketcard-body row">
                     <div class="col-md-6 col mb-3">
                        <img src="image/marketboard_img/1detail.jpg">
                     </div>
                     <div class="col-md-6">
                        <div class="mt-2">
                           <h2><strong>맥북 팝니다!</strong></h2>
                        </div>
                        <div class="mt-2">
						상품명 : 맥북 2022 램 16<br>
						가격 : 1,300,000<br>
						거래방법 : 직거래<br>
						전화번호 : 010-1234-1234<br>
						상품설명 : 블라블라 직거래 혜화<br>
						<p>
						</div>
                        <div class="ps-2 ms-10 row justify-content-between">
                           <div class="col navbar-nav">
                              <div>
                              	
                              </div>
                           </div>

                        </div>


                     </div>
                  </div>
                  <div class="mb-4 park-card-body">
                  
                     <div class="row mb-3">
                        <h5 class="col-sm-2 mt-2"><Strong>댓글</Strong></h5>
                        <div class="col-sm-7">
                           <input type="text" class="form-control replyinput">
                        </div>
                        <div class="col-sm-3">
                           <button type="button" class="btn btn-outline-secondary btn-sm rounded-pill">완료</button>
                           <button type="button" class="btn btn-outline-secondary btn-sm rounded-pill">취소</button>
                        </div>
                     </div>

                     <div class="timeline ms-2">
                        <div class="reply-container">
                           <Strong>작성자</Strong> <br />
                           <p class="ms-2">이것은 댓글 내용이들어갈 거얌 근데 고양이 졸라 귀엽다ㅏ다다닫 고양이는 세계 제일!!!!!!!!!!</p>
                        </div>
                        
                        <div class="reply-container">
                           <Strong>작성자</Strong> <br />
                           <p class="ms-2">이것은 댓글 내용이들어갈 거얌 근데 고양이 졸라 귀엽다ㅏ다다닫 고양이는 세계 제일!!!!!!!!!!</p>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            
            <div class="col-1"></div>
         </div>
      </div>





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