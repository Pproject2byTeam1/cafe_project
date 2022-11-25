<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>marketboard_list</title>

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
<!-- 거래게시판 CSS 시작 -->
<link href="assets/css/marketboard_list.css" rel="stylesheet">
<!-- 거래게시판 CSS 끝 -->
<script type="text/javascript">
	$(document).ready(function(){
		let b_code = "<c:out value='${b_code}'/>";
			
			//글쓰기
			$("#Write").click(function(){
				console.log("write 클릭");
				location.href="marketboard_write.do?b_code=" + b_code;
				
			});
		
		
			$('.search').click(function(){
			    var search = $(this).val();

			    var requestdata = {"b_code": b_code, "search": search};	

				/* 검색 비동기 */					
					$.ajax({
						type: "POST",
						url: "MarketSearch",
						data: requestdata,
						dataType: "JSON",
						success: function(data){
														
							$(".searchlist").empty();
							
							$(data).each(function(){
								
								console.log(this);
								html = '<div class="mcard" onclick="location.href=' + "'marketboard_read.do?b_code=" + this.b_code + '&idx=' + this.idx + "'" + ';">';
								html += '<div class="mimg">'
								html += '<img src="image/board/' + this.b_code + '/' + this.img_name + '" id="mimg"/>';
								html += '</div>'
								html += '<span id="marketB_Text.ns">' + this.m_mode + '|' + this.sold + '|' + this.cate + '</span>';
								html += '<p>';
								html += '<span id="marketB_Title">' + this.title + '</span>';
								html += '<br>';
								html += '<span id="marketB_Text">' + this.content + '</span>';
								html += '<br>';
								html += '<span id="marketB_Price">' + this.price + '</span>';
								html += '<br>'
								html += '<span id="marketB_info">조회수 : ' + this.hits + '| 댓글 : 3 | 찜 : 3</span>';
								html += '<br>';
									html += '<span id="marketB_Text.ns"><img src="image/rank_icon/1.gif" alt="Profile"'
										html += 'class="rounded-circle">' + this.nick + '|' + this.w_date + '</span>';
								html += '</div>';
								
								$('.searchlist').append(html);
								
							}); 
							
							$('.searchlist .mcard').last().remove();
							
						}
					});
				});
				
			});

	
</script>
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
					<li class="breadcrumb-item"><a href="index.html">전체글 : ${totalboardcount}</a></li>
					<li class="breadcrumb-item active">판매중 : ${soldcount}</li>
					<li class="breadcrumb-item">판매완료 : ${totalboardcount-soldcount}</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<!-- Contents -->
		<section class="section">
			<div class="col-md-12">
			<div class="marketcard">
				<div class="card-body">
				<span class="card-title">거래 게시판에서 발생하는 모든 문제는 본인에게 있습니다.</span>
				<div align="right" class="col-md-12">
					<div>
						<button type="button" id="Write" 
							class="btn btn-outline-secondary btn-sm rounded-pill">글쓰기</button>
						</div>
					</div>
					
					<!-- Bordered Tabs -->
	
					<ul class="nav nav-tabs nav-tabs-bordered" id="searchTab"
						role="tablist">
						<li class="nav-item" role="presentation">
							<button class="search nav-link active" value="all" id="all"
								data-bs-toggle="tab" data-bs-target="#bordered-home"
								type="button" role="tab" aria-controls="home"
								aria-selected="true">전체보기</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="search nav-link" value="판매중" id="sold" data-bs-toggle="tab"
								data-bs-target="#bordered-profile" type="button" role="tab"
								aria-controls="profile" aria-selected="false">판매중</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="search nav-link" value="찜" id="yes" data-bs-toggle="tab"
								data-bs-target="#bordered-contact" type="button" role="tab"
								aria-controls="contact" aria-selected="false">나의 찜</button>
						</li>
					</ul>
				
					<div class="tab-content pt-2" id="borderedTabContent">
						<div class="tab-pane fade show active" id="bordered-home"
							role="tabpanel" aria-labelledby="home-tab"></div>
						<div class="tab-pane fade" id="bordered-profile" role="tabpanel"
							aria-labelledby="profile-tab"></div>
						<div class="tab-pane fade" id="bordered-contact" role="tabpanel"
							aria-labelledby="contact-tab"></div>
						<div></div>
					</div>
					<!-- End Bordered Tabs -->
				
				<c:if test="${list == null}">
  					<p>데이터가 없습니다</p>
  				</c:if>
					<!-- 보드	리스트 출력 시작 -->
					<div class="searchlist container container__content--flow">
						<c:forEach var="list" items="${list}" varStatus="status">
						<div class="mcard" onclick="location.href='marketboard_read.do?b_code=${list.b_code}&idx=${list.idx}';">
							<div class="mimg">
							<img src="image/board/${list.b_code}/${list.img_name}" id="mimg"/>
							</div>
							<span id="marketB_Text.ns">${list.m_mode} | ${list.sold} | ${list.cate}</span>
							<p>
							<span id="marketB_Title">${list.title}</span>
							<br>
							<span id="marketB_Text">${list.content}</span>
							<br>
							<span id="marketB_Price">${list.price}</span>
							<br>
							<span id="marketB_info">조회수 : ${list.hits} | 댓글 : 3 | 찜 : 3</span>
							<br>
								<span id="marketB_Text.ns"><img src="image/rank_icon/1.gif" alt="Profile"
									class="rounded-circle"> ${list.nick} | ${list.w_date}</span>
						</div>
						</c:forEach>					
					</div>
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
			</div>
			</div>
				<!-- 보드 페이지 끝 -->
		</section>
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