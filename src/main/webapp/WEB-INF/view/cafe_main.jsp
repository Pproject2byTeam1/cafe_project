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
            <img src="image/sample/bulb.jpg" class="card-img-top" alt="...">
          </div>
            <div class="card-img-overlay">
              <h5 class="card-title">대문 이름</h5>
              <p class="card-text">설명란
            <c:forEach var="chart" items="${chart}" varStatus="status">
            <input type="text" id = "b_cnt${status.count}" value="${chart.b_code}" hidden="">
            <input type="text" id = "h_cnt${status.count}" value="${chart.c_count}" hidden="">
            </c:forEach>
              </p>
            </div>
          
          </div><!-- End Card with an image overlay -->
          
   <section class="section dashboard"><!-- 대충 영역을 잡는 부분 -->
      <div class="row">
          
          <!-- Left side columns -->
        <div class="col-lg-12">
          <div class="row">

            <!-- Card 1 -->
            <div class="col-lg-6">
              <div class="card info-card sales-card">

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

                <div class="card-body">
                  <h5 class="card-title">자유게시판 <span>| 막 글</span></h5>

                    <div class="ps-3">
                      <table class="table table-hover table-sm datatable">
                      	<thead>
                      	<tr><th><span class="text-success pt-1 fw-bold">제목</span></th><th><span class="text-success pt-1 fw-bold">작성자</span></th><th><span class="text-success pt-1 fw-bold">작성일자</span></th><th><span class="text-success pt-1 fw-bold">조회수</span></th></tr>
                      	</thead>
                      	<tbody>
                      	<c:forEach var="board" items="${regular_list}" varStatus="status">
                      	<tr class="listrow">
		                      	<td class="td1"><span class="d-none b_code">${board.b_code}</span><span class="d-none idx">${board.idx}</span>${board.title}<span class="badge bg-success rounded-pill">${board.c_count}</span></td>
		                      	<td><a data-bs-toggle="dropdown">${board.nick}</a>
		                      	<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
									<li class="anw_memo"><a class="dropdown-item sendToMemo" href="write_memo.do?sender_id=${board.email_id}" >답장 보내기</a></li>
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
            </div><!-- End Card 1 -->

            <!-- Revenue Card -->
            <div class="col-lg-6">
              <div class="card info-card revenue-card">

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

                <div class="card-body">
                  <h5 class="card-title">정보 게시판 <span>| 정보공유</span></h5>

                    <div class="ps-3">
                    <table class="table table-hover table-sm datatable">
                      	<thead>
                      	<tr><th><span class="text-primary pt-1 fw-bold">제목</span></th><th><span class="text-primary pt-1 fw-bold">작성자</span></th><th><span class="text-primary pt-1 fw-bold">작성일자</span></th><th><span class="text-primary pt-1 fw-bold">조회수</span></th></tr>
                      	</thead>
                      	<tbody>
                      	<c:forEach var="board" items="${data_list}" varStatus="status">
                      	<tr class="listrow">
		                      	<td class="td1"><span class="d-none b_code">${board.b_code}</span><span class="d-none idx">${board.idx}</span>${board.title}<span class="badge bg-primary rounded-pill">${board.c_count}</span></td>
		                      	<td><a data-bs-toggle="dropdown">${board.nick}</a>
		                      	<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
									<li class="anw_memo"><a class="dropdown-item sendToMemo" href="write_memo.do?sender_id=${board.email_id}" >답장 보내기</a></li>
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
            </div><!-- End Revenue Card -->

            <!-- Customers Card -->
            <div class="col-xl-12">

              <div class="card info-card customers-card">

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

                <div class="card-body">
                  <h5 class="card-title">항목 3 <span>| 소제 3</span></h5>

                    <div class="ps-3">
                      <table class="table table-hover datatable">
                      	<thead>
                      	<tr><th><span class="text-danger pt-1 fw-bold">#</span></th><th><span class="text-danger pt-1 fw-bold">항목 1</span></th><th><span class="text-danger pt-1 fw-bold">항목 2</span></th><th><span class="text-danger pt-1 fw-bold">항목 3</span></th><th><span class="text-danger pt-1 fw-bold">항목 4</span></th><th><span class="text-danger pt-1 fw-bold">항목 5</span></th><th><span class="text-danger pt-1 fw-bold">항목 6</span></th><th><span class="text-danger pt-1 fw-bold">항목 7</span></th><th><span class="text-danger pt-1 fw-bold">항목 8</span></th><th><span class="text-danger pt-1 fw-bold">항목 9</span></th></tr>
                      	</thead>
                      	<tbody>
                      	<tr><th><span class="text-danger pt-1 fw-bold">1</span></th><td>내ㅐㅐㅐㅐㅐ용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내ㅐㅐ용</td><td>내용</td><td>내용</td><td>내용</td></tr>
                      	</tbody>
                      </table>
                    </div>

                </div>
              </div>

            </div><!-- End Customers Card -->
            
            <!-- Customers Card -->
            <div class="col-xl-12">

              <div class="card info-card customers-card">

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

                <div class="card-body">
                  <h5 class="card-title">항목 3 <span>| 소제 3</span></h5>

                    <div class="ps-3">
                      <table class="table table-hover datatable">
                      	<thead>
                      	<tr><th><span class="text-warning  pt-1 fw-bold">#</span></th><th><span class="text-warning pt-1 fw-bold">항목 1</span></th><th><span class="text-warning pt-1 fw-bold">항목 2</span></th><th><span class="text-warning pt-1 fw-bold">항목 3</span></th><th><span class="text-warning pt-1 fw-bold">항목 4</span></th><th><span class="text-warning pt-1 fw-bold">항목 5</span></th><th><span class="text-warning pt-1 fw-bold">항목 6</span></th><th><span class="text-warning pt-1 fw-bold">항목 7</span></th><th><span class="text-warning pt-1 fw-bold">항목 8</span></th><th><span class="text-warning pt-1 fw-bold">항목 9</span></th></tr>
                      	</thead>
                      	<tbody>
                      	<tr><th><span class="text-warning  pt-1 fw-bold">1</span></th><td>내ㅐㅐㅐㅐㅐ용</td><td>내용</td><td>내용</td><td>내용</td><td>내용</td><td>내ㅐㅐ용</td><td>내용</td><td>내용</td><td>내용</td></tr>
                      	</tbody>
                      </table>
                    </div>

                </div>
              </div>

            </div><!-- End Customers Card -->

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
                    <h5 class="card-title">${chart.title}</h5>
                    </div>
                    <div class="ps-3">${chart.email_id}
                    <a data-bs-toggle="dropdown"><img src="image/rank_icon/3.gif" alt="아이콘">${chart.nick}</a><span> | ${chart.w_date}</span>
                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
						<li class="anw_memo"><a class="dropdown-item sendToMemo" href="write_memo.do?sender_id=${chart.email_id}" >답장 보내기</a></li>
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

                <div class="card-body pb-0">
                  <h5 class="card-title">거래게시판 <span>| 주간 장터</span></h5>

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
                    <c:forEach var="board" items="${market_list}" varStatus="status">
                      <tr class="listrow">
                        <th>${board.sold}</th>
                        <th scope="row"><a href="#"><img src="image/board/upload/${board.img_name}" alt="" onerror="this.onerror=null; this.src='https://via.placeholder.com/300X250?text=No+Image'"></a></th>
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
            
            
            <div class="col-lg-6">
	          <div class="card">
	            <div class="card-body">
	              <h5 class="card-title">Column Chart</h5>
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
	
	              <!-- Column Chart -->
	              <div id="columnChart"></div>
	
	              
	              <!-- End Column Chart -->
	
	            </div>
	          </div>
	        </div>

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
  		
  		$('.pager').click(function(){
  			clickLocation();
  		});
  		$('.dataTable-pagination-list').click(function(){
  			clickLocation();
  		});
  		$('.dataTable-sorter').click(function(){
  			clickLocation();
  		});
  		clickLocation();
  	});
	
	function clickLocation(){
		$('.listrow').click(function(){
  			const idx = $(this).children('.td1').children('.idx').text();
  			const b_code = $(this).children('.td1').children('.b_code').text();
  			const w_date = $(this).children(".w_date").text();
  			window.location.href = 'boardContent.do?idx='+idx+'&b_code='+b_code+'&w_date='+w_date;
  		});
	}
	var col1 = new Array();
	col1.push($('#b_cnt1').val());
	col1.push($('#b_cnt2').val());
	col1.push($('#b_cnt3').val());
	col1.push($('#b_cnt4').val());
	col1.push($('#b_cnt5').val());
	col1.push($('#b_cnt6').val());
	var col2 = new Array();
	col2.push($('#h_cnt1').val());
	col2.push($('#h_cnt2').val());
	col2.push($('#h_cnt3').val());
	col2.push($('#h_cnt4').val());
	col2.push($('#h_cnt5').val());
	col2.push($('#h_cnt6').val());
	var threechart = {
        series: [{
          name: '총 글수',
          data: col1
        }, {
          name: '총 조회수',
          data: col2
        }],
        chart: {
          type: 'bar',
          height: 350
        },
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: '55%',
            endingShape: 'rounded'
          },
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          show: true,
          width: 2,
          colors: ['transparent']
        },
        xaxis: {
          categories: ['자유', '출석체크', '사진', '자료', '유료 거래', '일정관리'],
        },
        yaxis: {
          title: {
            text: '횟수'
          }
        },
        fill: {
          opacity: 1
        },
        tooltip: {
          y: {
            formatter: function(val) {
              return  val 
            }
          }
        }
      }
    document.addEventListener("DOMContentLoaded", () => {
      new ApexCharts(document.querySelector("#columnChart"), threechart).render();
    });
	</script>

</html>