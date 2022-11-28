<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>카페人중독</title>
  <meta content="" name="description">
  <meta content="" name="keywords">
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysdate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set> 
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

</head>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">

  <jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>

  </header><!-- End Header -->

  <!-- ======= Sidebar ======= -->
  
   <jsp:include page="/WEB-INF/view/common/side.jsp"></jsp:include>
  
  <!-- End Sidebar -->

  <main id="main" class="main">

    <div class="pagetitle">
      <h1>출석게시판</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
          <li class="breadcrumb-item active">출석부</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section dashboard">
      <div class="row">
		<!-- columns -->
        <div class="col-lg-12">
          <div class="row">
          <!-- 카드 -->
          	<div class="col-12">
              <div class="card">
                <div class="card-body">
                  <h5 class="card-title">출석부 <span>/ Today</span></h5>
                  <hr>
					<button type="button" class="btn btn-outline-info rounded-pill bi bi-caret-left-fill"></button>
					<span>2022 년 11 월</span>
					<button type="button" class="btn btn-outline-info rounded-pill bi bi-caret-right-fill"></button>
					<br>
					<br>
					<ul class="list-group list-group-horizontal list-inline nav">
					<c:forEach begin="1" end="${days}" step="1" var="day" varStatus="status">
						<c:choose>
						<c:when test="${status.count == nowday2}">
						<li><a href="#" class="btn btn-default btn-outline-success"><b>${status.count}</b></a></li>
						</c:when>
						<c:otherwise>
						<li><a href="checkBoard.do?inputdate=2022-${month}-${status.count}" class="btn btn-default">${status.count}</a></li>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					</ul>
					<!-- 작성란 -->
					<c:if test="${userid != '' && sysdate == date5}"><!-- 로그인 상태와 오늘 날짜여야만 보임 -->
					  <div class="insertCheckToday">
			              <div class="quill-editor-default">
			                <p>출석 체크</p>
			              </div><!-- 서버시간 기준으로 오늘날짜가 아닌 경우 안보이게 처리-->
			              <nav aria-label="Page navigation example">
			              <ul class="pagination justify-content-end">
		              	  <div class="col-sm-10 align-self-center text-md-start">오늘의 출석인 수: ${count}명</div>
		              	  <div  class="col-sm-2 text-lg-end">
		              	  <!-- Basic Modal -->
			              	  <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#basicModal" >
			                	출석하기
			              	  </button>
			              	 </div>
			              </ul>
			              </nav>
			              <div class="modal fade" id="basicModal" tabindex="-1">
			                <div class="modal-dialog">
			                	
			                  <div class="modal-content">
			                    <div class="modal-body">
			                    하루에 한번만 출석 가능
			                     </div>
			                    <div class="modal-footer">
			                      	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
			                    </div>
			                  </div>
			                </div>
			              </div><!-- End Basic Modal-->
		              </div>
		              </c:if><!-- End 작성란 -->
                </div>
              </div>
            </div><!-- End 카드 -->
            <!-- 댓글 -->
            <div class="col-lg-12">
	          <div class="card">
	            <h5 class="card-title">&nbsp;&nbsp;&nbsp;출석명단</h5>
	            <c:forEach var="list" items="${boardlist}" varStatus="status">
	            <!-- 1round -->
	            <div class="card-body row">
	            <hr>
	            <div class="col-sm-2">
	            	<h5 class="card-title"><img src="image/rank_icon/${list.hits}.gif" alt="랭크 아이콘">${list.nick}</h5>
	            </div>
	              <div class="col-sm-7 align-self-center"><p class="text-justify ">${list.content}</p></div>
	              <div class="col-sm-3 align-self-center text-lg-end">
	              <nav style="--bs-breadcrumb-divider: '|';">
	                <ol class="breadcrumb">
	                  <li class="breadcrumb-item">${list.w_date}</li>
	                  <c:if test="${list.email_id == userId}">
	                  <li class="breadcrumb-item active"><a href="#" class="btn btn-warning">삭제</a></li>
	                  </c:if>
	                </ol>
	              </nav>
	              </div>
	              </div>
	              <!-- 1round -->
	              </c:forEach>
	            <hr>
				<ul class="pagination justify-content-center">
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#">1</a></li>
                  <li class="page-item"><a class="page-link" href="#">2</a></li>
                  <li class="page-item"><a class="page-link" href="#">3</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </ul>	            
	          </div>

	        </div>
            <!-- End 댓글 -->
          </div>
         </div>
         <!-- columns -->
      </div>
    </section>

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer" class="footer">
    <div class="copyright">
      &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved
    </div>
    <div class="credits">
      <!-- All the links in the footer should remain intact. -->
      <!-- You can delete the links only if you purchased the pro version. -->
      <!-- Licensing information: https://bootstrapmade.com/license/ -->
      <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
      Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

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