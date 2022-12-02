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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    
<!-- content에 자신의 OAuth2.0 클라이언트ID를 넣습니다. -->
<meta name ="google-signin-client_id" content="921928889538-s4valdhb19eee3o6h4hgeheq8i8q90qk.apps.googleusercontent.com">

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

<!-- 경고창 이쁜거 -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">

</script>
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

		<div class="pagetitle">
			<h1>Home</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="cafemain.do">Home</a></li>
					<li class="breadcrumb-item active">home</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<!-- Card with an image overlay -->
          <div class="card">
          <div class="embed-responsive">
            <img src="upload/${banner.cafe_img}" class="card-img-top" alt="..." onerror="this.onerror=null; this.src='https://via.placeholder.com/1800X350?text=No+Image'">
          </div>
            <c:forEach var="chart" items="${chart}" varStatus="status">
            <input type="text" id = "b_cnt${status.count}" value="${chart.b_code}" hidden="">
            <input type="text" id = "h_cnt${status.count}" value="${chart.c_count}" hidden="">
            </c:forEach>
          
          </div><!-- End Card with an image overlay -->
          
   <section class="section dashboard"><!-- 대충 영역을 잡는 부분 -->
      <div class="row">
          
          <!-- Left side columns -->
        <div class="col-lg-12">
          <div class="row">

            <!-- Card 1 -->
            <c:if test="${board1 != null}">
            <div class="col-lg-6">
              <div class="card info-card sales-card">

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6><span class="d-none index">1</span>
                    </li>
                    <li><a class="dropdown-item">Today</a><span class="d-none b_code">${board1info.b_code}</span><span class="d-none date">${Beforeday}</span></li>
                    <li><a class="dropdown-item">This Month</a><span class="d-none b_code">${board1info.b_code}</span><span class="d-none date">${BeforeMonth}</span></li>
                    <li><a class="dropdown-item">This Year</a><span class="d-none b_code">${board1info.b_code}</span><span class="d-none date">${BeforeYear}</span></li>
                  </ul>
                </div>

                <div class="card-body">
                  <h5 class="card-title">${board1info.b_name}<span>| ${board1info.b_type}</span></h5>

                    <div class="ps-3">
                      <table class="table table-hover">
                      	<thead>
                      	<tr><th><span class="text-success pt-1 fw-bold">제목</span></th><th><span class="text-success pt-1 fw-bold">작성자</span></th><th><span class="text-success pt-1 fw-bold">작성일자</span></th><th><span class="text-success pt-1 fw-bold">조회수</span></th></tr>
                      	</thead>
                      	<tbody class = board>
                      	<c:if test="${fn:length(board1) == 0}">
                      	<tr>
                      	<td colspan = 4>데이터가 없습니다.</td>
                      	</tr>
                      	</c:if>
                      	<c:forEach var="board" items="${board1}" varStatus="status">
                      	<tr class="listrow">
		                      	<td class="td1">
			                      	<span class="d-none b_code">${board.b_code}</span>
			                      	${board.title}
			                      	<span class="d-none idx">${board.idx}</span>
			                      	<span class="badge bg-success rounded-pill">${board.c_count}</span>
		                      	</td>
		                      	<td><a data-bs-toggle="dropdown">${board.nick}</a>
		                      	<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
									<li class="anw_memo"><a class="dropdown-item sendToMemo" href="write_memo.do?sender_id=${board.email_id}" >쪽지 보내기</a></li>
									<li class="view_user_activity"><a class="dropdown-item viewActivity" href="user_activity.do?email_id=${board.email_id}" >활동 내역 보기</a></li>
								</ul>
		                      	</td>
		                      	<td class="w_date">${board.w_date}</td>
		                      	<td>${board.hits}</td>
		                </tr>
                      	</c:forEach>
                      	</tbody>
                      </table>
                    </div>
                </div>

              </div>
            </div>
            </c:if>
            <!-- End Card 1 -->

            <!-- Card2 -->
            <c:if test="${board2 != null}">
            <div class="col-lg-6">
              <div class="card info-card sales-card">

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6><span class="d-none index">2</span>
                    </li>
                    <li><a class="dropdown-item">Today</a><span class="d-none b_code">${board2info.b_code}</span><span class="d-none date">${Beforeday}</span></li>
                    <li><a class="dropdown-item">This Month</a><span class="d-none b_code">${board2info.b_code}</span><span class="d-none date">${BeforeMonth}</span></li>
                    <li><a class="dropdown-item">This Year</a><span class="d-none b_code">${board2info.b_code}</span><span class="d-none date">${BeforeYear}</span></li>
                  </ul>
                </div>
                <div class="card-body">
                  <h5 class="card-title">${board2info.b_name}<span>| ${board2info.b_type}</span></h5>
                    <div class="ps-3">
                    <table class="table table-hover">
                      	<thead>
                      	<tr><th><span class="text-primary pt-1 fw-bold">제목</span></th><th><span class="text-primary pt-1 fw-bold">작성자</span></th><th><span class="text-primary pt-1 fw-bold">작성일자</span></th><th><span class="text-primary pt-1 fw-bold">조회수</span></th></tr>
                      	</thead>	
                      	<tbody class = board>
                      	<c:if test="${fn:length(board2) == 0}">
                      	<tr>
                      	<td colspan = 4>데이터가 없습니다.</td>
                      	</tr>
                      	</c:if>
                      	<c:forEach var="board" items="${board2}" varStatus="status">
                      	<tr class="listrow">
	                      	<td class="td1"><span class="d-none b_code">${board.b_code}</span><span class="d-none idx">${board.idx}</span>${board.title}<span class="badge bg-primary rounded-pill">${board.c_count}</span></td>
	                      	<td><a data-bs-toggle="dropdown">${board.nick}</a>
	                      	<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
								<li class="anw_memo"><a class="dropdown-item sendToMemo" href="write_memo.do?sender_id=${board.email_id}" >쪽지 보내기</a></li>
								<li class="view_user_activity"><a class="dropdown-item viewActivity" href="user_activity.do?email_id=${board.email_id}" >활동 내역 보기</a></li>
							</ul>
	                      	</td>
	                      	<td class="w_date">${board.w_date}</td>
	                      	<td>${board.hits}</td>
		                </tr>
                      	</c:forEach>
                      	</tbody>
                      </table>
                    </div>
                </div>
              </div>
            </div>
            </c:if>
            <!-- End Card2 -->

            <!-- Card3 -->
            <c:if test="${board3 != null}">
            <div class="col-lg-6">
              <div class="card info-card customers-card">
                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6><span class="d-none index">3</span>
                    </li>
                    <li><a class="dropdown-item">Today</a><span class="d-none b_code">${board3info.b_code}</span><span class="d-none date">${Beforeday}</span></li>
                    <li><a class="dropdown-item">This Month</a><span class="d-none b_code">${board3info.b_code}</span><span class="d-none date">${BeforeMonth}</span></li>
                    <li><a class="dropdown-item">This Year</a><span class="d-none b_code">${board3info.b_code}</span><span class="d-none date">${BeforeYear}</span></li>
                  </ul>
                </div>
                <div class="card-body">
                  <h5 class="card-title">${board3info.b_name}<span>| ${board3info.b_type}</span></h5>
                    <div class="ps-3">
                      <table class="table table-hover">
                      	<thead>
                      	<tr><th><span class="text-danger pt-1 fw-bold">제목</span></th><th><span class="text-danger pt-1 fw-bold">작성자</span></th><th><span class="text-danger pt-1 fw-bold">작성일자</span></th><th><span class="text-danger pt-1 fw-bold">조회수</span></th></tr>
                      	</thead>
                      	<tbody class = board>
                      	<c:if test="${fn:length(board3) == 0}">
                      	<tr>
                      	<td colspan = 4>데이터가 없습니다.</td>
                      	</tr>
                      	</c:if>
                      	<c:forEach var="board" items="${board3}" varStatus="status">
                      	<tr class="listrow">
	                      	<td class="td1"><span class="d-none b_code">${board.b_code}</span><span class="d-none idx">${board.idx}</span>${board.title}<span class="badge bg-danger rounded-pill">${board.c_count}</span></td>
	                      	<td><a data-bs-toggle="dropdown">${board.nick}</a>
	                      	<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
								<li class="anw_memo"><a class="dropdown-item sendToMemo" href="write_memo.do?sender_id=${board.email_id}" >쪽지 보내기</a></li>
								<li class="view_user_activity"><a class="dropdown-item viewActivity" href="user_activity.do?email_id=${board.email_id}" >활동 내역 보기</a></li>
							</ul>
	                      	</td>
	                      	<td class="w_date">${board.w_date}</td>
	                      	<td>${board.hits}</td>
		                </tr>
                      	</c:forEach>
                      	</tbody>
                      </table>
                    </div>
                </div>
              </div>
            </div>
            </c:if>
            <!-- End Card3 -->
            
            <!-- Card4 -->
            <c:if test="${board4 != null}">
            <div class="col-lg-6">
              <div class="card info-card customers-card">
                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6><span class="d-none index">4</span>
                    </li>
                    <li><a class="dropdown-item">Today</a><span class="d-none b_code">${board4info.b_code}</span><span class="d-none date">${Beforeday}</span></li>
                    <li><a class="dropdown-item">This Month</a><span class="d-none b_code">${board4info.b_code}</span><span class="d-none date">${BeforeMonth}</span></li>
                    <li><a class="dropdown-item">This Year</a><span class="d-none b_code">${board4info.b_code}</span><span class="d-none date">${BeforeYear}</span></li>
                  </ul>
                </div>

                <div class="card-body">
                  <h5 class="card-title">${board4info.b_name}<span>| ${board4info.b_type}</span></h5>

                    <div class="ps-3">
                      <table class="table table-hover">
                      	<thead>
                      	<tr><th><span class="text-warning pt-1 fw-bold">제목</span></th><th><span class="text-warning pt-1 fw-bold">작성자</span></th><th><span class="text-warning pt-1 fw-bold">작성일자</span></th><th><span class="text-warning pt-1 fw-bold">조회수</span></th></tr>
                      	</thead>
                      	<tbody class = board>
                      	<c:if test="${fn:length(board4) == 0}">
                      	<tr>
                      	<td colspan = 4>데이터가 없습니다.</td>
                      	</tr>
                      	</c:if>
                      	<c:forEach var="board" items="${board4}" varStatus="status">
                      	<tr class="listrow">
	                      	<td class="td1"><span class="d-none b_code">${board.b_code}</span><span class="d-none idx">${board.idx}</span>${board.title}<span class="badge bg-warning rounded-pill">${board.c_count}</span></td>
	                      	<td><a data-bs-toggle="dropdown">${board.nick}</a>
	                      	<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
								<li class="anw_memo"><a class="dropdown-item sendToMemo" href="write_memo.do?sender_id=${board.email_id}" >쪽지 보내기</a></li>
								<li class="view_user_activity"><a class="dropdown-item viewActivity" href="user_activity.do?email_id=${board.email_id}" >활동 내역 보기</a></li>
							</ul>
	                      	</td>
	                      	<td class="w_date">${board.w_date}</td>
	                      	<td>${board.hits}</td>
		                </tr>
                      	</c:forEach>
                      	</tbody>
                      </table>
                    </div>
                </div>
              </div>
            </div>
            </c:if>
            <!-- End Card4 -->

            <!-- Reports -->
            <div class="col-12">
              <div class="card">

                <!-- <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="#">Today</a></li>
                    <li><a class="dropdown-item" href="#">This Month</a></li>
                    <li><a class="dropdown-item" href="#">This Year</a></li>
                  </ul>
                </div> -->
                <div class="card-body">
                  <h5 class="card-title">Text Rank <span>/This Month</span></h5>
                  <c:if test="${fn:length(rankchart) == null}">
                  데이터가 없습니다
                  </c:if>
                  <c:forEach var="chart" items="${rankchart}" varStatus="status">
                  <hr>
                  <div class="d-flex align-items-center  revenue-card">
                  <c:choose>
                  	<c:when test="${status.count == 1}">
					<div class="card-icon rounded-circle d-flex align-items-center justify-content-center" style="color: #ffbd1b; background: #ffe8ba">
                      <i class="ri-trophy-line"></i>
                    </div>
                    <div class="ps-3">
                    <h3 class="pt-1 fw-bold">1st Text</h3>
                    </div>
                    </c:when>
                    <c:when test="${status.count == 2}">
                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center"style="color: #c0c0c0; background: #eaeaea">
                      <i class="ri-trophy-line"></i>
                    </div>
                    <div class="ps-3">
                    <h3 class="pt-1 fw-bold">2nd Text</h3>
                    </div>
                    </c:when>
                    <c:when test="${status.count == 3}">
                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center" style="color: #cd7f32; background: #f5d3b9">
                      <i class="ri-trophy-line"></i>
                    </div>
                    <div class="ps-3">
                    <h3 class="pt-1 fw-bold">3rd Text</h3>
                    </div>
                    </c:when>
                    <c:otherwise>
                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                      <i class="ri-thumb-up-line"></i>
                    </div>
                    <div class="ps-3">
                    <h3 class="pt-1 fw-bold">Good Text</h3>
                    </div>
                    </c:otherwise>
                  </c:choose>
                    <div class="ps-3">
                    <h5 class="card-title clickrank">
	                    <span class="d-none b_code">${chart.b_code}</span>
	                    <span class="d-none idx">${chart.idx}</span>
	                    <span class="d-none w_date">${chart.w_date}</span>
	                    ${chart.title}
                    </h5>
                    </div>
                    <div class="ps-3">
                    <a data-bs-toggle="dropdown">${chart.nick}</a><span> | ${chart.w_date}</span>
                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
						<li class="anw_memo"><a class="dropdown-item sendToMemo" href="write_memo.do?sender_id=${chart.email_id}" >쪽지 보내기</a></li>
						<li class="view_user_activity"><a class="dropdown-item viewActivity" href="user_activity.do?email_id=${chart.email_id}" >활동 내역 보기</a></li>
					</ul>
                    </div>
                  </div>
                  </c:forEach>
                  
                  <!-- End Line Chart -->

                </div>

              </div>
            </div><!-- End Reports -->

			<!-- Top Selling -->
            <div class="col-12">
              <div class="card top-selling overflow-auto">

                <div class="card-body pb-0">
                  <h5 class="card-title">거래게시판 <span>| Today</span></h5>

                  <table class="table table-borderless table-hover">
                    <thead class="table-warning">
                      <tr>
                        <th scope="col">판매여부</th>
                        <th scope="col">Preview</th>
                        <th scope="col">Product</th>
                        <th scope="col">가격</th>
                        <th scope="col">올린날짜</th>
                        <th scope="col">올린사람</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:if test="${fn:length(market_list)==0}">
                    <tr>
                    <td colspan="7">데이터가 없습니다.</td>
                    </tr>
                    </c:if>
                    <c:forEach var="board" items="${market_list}" varStatus="status">
                      <tr class="listrow">
                        <th>${board.sold}</th>
                        <th scope="row"><a href="#"><img src="upload/${board.img_name}" alt="" onerror="this.onerror=null; this.src='https://via.placeholder.com/300X250?text=No+Image'"></a></th>
                        <td class="td1"><span class="d-none b_code">${board.b_code}</span><span class="d-none idx">${board.idx}</span>${board.title}</td>
                        <td class="fw-bold">${board.price}원</td>
                        <td class="w_date">${board.w_date}</td>
                        <td><a data-bs-toggle="dropdown">${board.nick}</a>
                        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
							<li class="anw_memo"><a class="dropdown-item sendToMemo" href="write_memo.do?sender_id=${board.email_id}"" >답장 보내기</a></li>
							<li class="view_user_activity"><a class="dropdown-item viewActivity" href="user_activity.do?email_id=${board.email_id}" >활동 내역 보기</a></li>
						</ul>
                        </td>
                      </tr>
                      </c:forEach>
                    </tbody>
                  </table>

                </div>

              </div>
            </div><!-- End Top Selling -->

            <!-- Recent Sales -->
            <div class="col-12">
              <div class="card recent-sales overflow-auto">

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="#">Today</a></li>
                    <li><a class="dropdown-item" href="#">This Month</a></li>
                    <li><a class="dropdown-item" href="#">This Year</a></li>
                  </ul>
                </div>

          </div>
        </div><!-- End Left side columns -->
	</div>
	</div>
	</div>
	</section>
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
	<script type="text/javascript">
	$(function(){
		//랭크 페이지에서 이동
		$('.clickrank').click(function(){
  			const idx = $(this).children('.idx').text();
  			const b_code = $(this).children('.b_code').text();
  			const w_date = $(this).children(".w_date").text();
  			window.location.href = 'boardContent.do?idx='+idx+'&b_code='+b_code+'&w_date='+w_date;
  		});
		//테이블에서 이동
	  	clickLocation();
		
  		window,addEventListener("keydown",function(event){
			  if(event.defaultPrevented){
				  return;
			  }
			  var handled = false;
			  if(event.keyCode == 123)
				  handled = true;
			  if(handled){
				  swal("경고","F12키를 누르지 마십시오",'error');
				  event.preventDefault();
			  }
		  },true);
  		$('.dropdown-item').click(function(){
  			const b_code = $(this).parents().children('.b_code').text();
  			const date = $(this).parents().children('.date').text();
  			const loc = $(this).parents().parents().parents().parents().children('.card-body').children('.ps-3').children('.table').children('.board');
  			const index = $(this).parents().parents().children('.text-start').children('.index').text();
  			
  			$.ajax({
  				url:"getBoardList.do",
  				data:{
  					"b_code": b_code,
  					"date":date,
  					"index":index},
  				type: "POST",
  				dataType:"html",
  				success: function(responseText){
  					loc.empty();
  					loc.append(responseText);
  					clickLocation();
  				}
  			})
  		});
  	});
	function clickLocation(){
		$('.td1').click(function(){
			const idx = $(this).children('.idx').text();
			const b_code = $(this).children('.b_code').text();
			const w_date = $(this).parents().children(".w_date").text();
			window.location.href = 'boardContent.do?idx='+idx+'&b_code='+b_code+'&w_date='+w_date;
		});
	}
	</script>

</html>