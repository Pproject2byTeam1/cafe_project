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
  	
  	 <!--fullcalendar css-->
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.css">
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/locales-all.js"></script>
  	
  	<script type="text/javascript">
  	
  		document.addEventListener('DOMContentLoaded', function() {
  			
			let today = new Date();
  			
  			let year = today.getFullYear();
  			let month = today.getMonth() + 1;
  			
  			const requestdata = {"b_code": 3, "year": year, "month": month};
  			
  			function loadlist(){
  				$.ajax({
  					type: "POST",
  					url: "CalendarList",
  					data: requestdata,
  					dataType: "JSON",
  					success: function(data){
  						
  						console.log(data);
  						
  						$(data).each(function(){
  							console.log(this.title);
  							
  						});
  						
  						let calendarEl = document.getElementById('calendar');
  						let calendar = new FullCalendar.Calendar(calendarEl, {
  							initialView : 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
  			  				headerToolbar : { // 헤더에 표시할 툴 바
  			  					start : 'prev next today',
  			  					center : 'title',
  			  					end : 'dayGridMonth,dayGridWeek,dayGridDay'
  			  				},
  			  				titleFormat : function(date) {
  			  					return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
  			  				},
  			  				selectable : true, // 달력 일자 드래그 설정가능
  			  				droppable : true,
  			  				editable : true,
  			  				nowIndicator: true, // 현재 시간 마크
  			  				locale: 'ko', // 한국어 설정
  			  				events: [
  			  					{
  			  						title: data[0].title,
  			  						start: data[0].start_date
  			  					}
  			  					]
  						});
  						
  						calendar.render();
  						
  					},
  					error: function(){
  						alert("error");
  					}
  				});
  			}
  			
  			loadlist();
  		});
  	
  	</script>
  	
</head>
<body>
  	<!-- ======= Header ======= -->
  	<header id="header" class="header fixed-top d-flex align-items-center">
     	<jsp:include page="/common/top.jsp"></jsp:include>
  	</header><!-- End Header -->
  	
  	<!-- ======= Sidebar ======= -->
    <jsp:include page="/common/side.jsp"></jsp:include>
  	<!-- End Sidebar -->
  
  
  
	<main id="main" class="main">
  	<!-- 여기서부터 작성 와랄ㄹ라  -->
  
  		<div class="pagetitle">
  			<h1>Calendar</h1> <!-- 게시판 이름 끌고오기 b_name -->
  			<nav>
  				<ol class="breadcrumb">
  					<li class="breadcrumb-item">
  						<a href="index.html">Home</a>
  					</li>
  					<li class="breadcrumb-item active">Calendar</li>
  				</ol>
  			</nav>
  		</div>
  		
  		
	  	<div class="container">
	        <div class="row">
	            <div id="calendar"></div>
	        </div>
	    </div>
	    
	    <c:forEach var="list" items="${list}">
	    	<p>${list.title}</p>
	    	<p>${list.start_date}</p>
	    </c:forEach>
  
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