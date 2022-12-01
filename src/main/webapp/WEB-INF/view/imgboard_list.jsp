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
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

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
  	<link href="assets/css/imgboard.css" rel="stylesheet">
  	
  	<!-- 무한 스크롤 -->
  	<script src="assets/js/jquery.jscroll.min.js" type="text/javascript"></script>
	
  	<script type="text/javascript">
  	
  		$(document).ready(function(){
  			
  			/* <!-- 무한 스크롤 js 코드 --> */
  			$('#autoScroll').jscroll({
  				autoTrigger: true,
  				loadingHtml: '<div class="spinner-border" role="status"><span class="visually-hidden">Loading...</span></div>',
  				nextSelector: 'a.nextPage:last',
  				contentSelector: '#autoScroll'
  			});
  			
  			/* 글쓰기 */
  			$("#addbtn").click(function(){
  				
  				let b_code = "<c:out value='${b_code}'/>";
  				
  				location.href="imgboardWriteView.do?b_code=" + b_code;
  				
  			});
  			
  			/* 검색 비동기 */
  			$("#pbtn").click(function(){
  				
  				let b_code = "<c:out value='${b_code}'/>";
  				
  				const requestdata = {"b_code": b_code, "search": $("#searchInput").val()};
  				
  				$.ajax({
  					type: "POST",
  					url: "ImgSearch",
  					data: requestdata,
  					dataType: "JSON",
  					success: function(data){
  						
  						$("#columns").empty();
  						
  						$(data).each(function(){
  							html = '<figure>';
  								html += '<a href="img_board_read.do?idx=' + this.idx + '"><img src="upload/' + this.img_name + '"></a>';
  								html += '<figcaption>' + this.title + '</figcaption>'
  							html += "</figure>";
  						
  							$('#columns').append(html);
  						});
  					}
  				});
  			});
  			
  		});
  	
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
  	<!-- 여기서부터 작성 와랄ㄹ라  -->
  	
  		<header id="parkimg" class="mt-10 header d-flex align-items-center mt-10 mb-5">
			<div class="flex-fill">
				<div class="p-5 search-form d-flex align-items-center">
					<input class="flex-fill" type="text" id="searchInput" placeholder="Search" title="Enter search keyword">
					<button class="flex-fill me-3" type="button" title="Search" id="pbtn">
						<i class="bi bi-search"></i>
					</button>
					<button class="flex-fill ms-5 mt-2" type="button" title="Search" id="addbtn">
						<h4><i class="bi bi-plus-circle"></i></h4>
					</button>
				</div>
			</div>
		</header>

		<div class="pagetitle">
			<h1>이미지 게시판</h1>
			<!-- 게시판 이름 끌고오기 b_name -->
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="cafemain.do">Home</a></li>
					<li class="breadcrumb-item active">이미지 게시판</li>
				</ol>
			</nav>
		</div>
		
		<div class="scroll" id="autoScroll">
  		
  			<div id="columns">
  			
  				<c:if test="${list == null}">
  					<p>데이터가 없습니다</p>
  				</c:if>
  				
  				<c:forEach var="list" items="${list}" varStatus="status">
	  				<figure id="imgtag">
	            		<a href="img_board_read.do?idx=${list.idx}&b_code=${list.b_code}">
	            			<img src="upload/${list.img_name}">
	            		</a>
	            		<figcaption>${list.title}</figcaption> 
	        		</figure>
  				</c:forEach>
  				
  			</div>
  			
  			<div class="next">
				<a class="nextPage" href="img_board_list.do?b_code=${b_code}&cp=${cpage+1}"></a>
  			</div>
  			
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
  
  

<!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
</html>