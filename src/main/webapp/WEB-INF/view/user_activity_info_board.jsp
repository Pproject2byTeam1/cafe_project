<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>카페人중독</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

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
  
  <!-- 테이블 정렬 -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">


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

    <div class="pagetitle">
      <h1>User</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
          <li class="breadcrumb-item active">User 활동 내역</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section profile">
      <div class="row d-flex flex-column align-items-center">

        <div class="col-xl-12">

          <div class="card">
            <div class="card-body pt-3">
            <h5 class="card-title">유저 활동 내역</h5>
            대충 닉네임
            
              <!-- Bordered Tabs -->
              <ul class="nav nav-tabs nav-tabs-bordered">

                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#write_board">작성 글 목록</button>
                </li>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#write_comments">댓글 목록</button>
                </li>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#liked_boards">좋아요 & 참여한 일정</button>
                </li>

              </ul>
              <div class="tab-content pt-2">

                <div class="tab-pane fade show active profile-overview table-responsive"  id="write_board">
                	<!-- Table with hoverable rows -->
		              <table class="table table-hover" id="dataTable1" >
		                <thead>
		                  <tr>
		                    <th scope="col">글 번호</th>
		                    <th scope="col">종류</th>
		                    <th scope="col">Title</th>
		                    <th scope="col">Write Date</th>
		                    <th scope="col">Hits</th>
		                    <th scope="col">Like</th>
		                  </tr>
		                </thead>
		                <tbody>
		                <c:forEach var="list" items="${writeboardlist}">
		                  <tr class="listrow">
		                    <th scope="row" class="idx">${list.idx}</th>
		                    <td>${list.b_name}<span class="d-none">${list.b_code}</span></td>
		                    <td>${list.title}</td>
		                    <td class="w_date">${list.w_date}</td>
		                    <td>${list.hits}</td>
		                    <td>${list.like}</td>
		                  </tr>
		                </c:forEach>
		                </tbody>
		              </table>
		              <!-- End Table with hoverable rows -->
                </div>

                <div class="tab-pane fade table-responsive" id="write_comments">
                	<!-- Table with hoverable rows -->
		              <table class="table table-hover" id="dataTable2" >
		                <thead>
		                  <tr>
		                    <th scope="col">글 번호</th>
		                    <th scope="col">원글 Title</th>
		                    <th scope="col">Comment Text</th>
		                    <th scope="col">Write Date</th>
		                  </tr>
		                </thead>
		                <tbody>
		                <c:forEach var="list" items="${commnetlist}">
		                  <tr class="listrow">
		                    <th scope="row" class="idx">${list.idx}</th><!-- setEmail_id <= title, setDepth <= c_count -->
		                    <td scope="row">${list.email_id}<span class="badge bg-primary rounded-pill">${list.depth}</span></td>
		                    <td>${list.content}<span class="d-none">${list.refer}</span></td>
		                    <td class="w_date">${list.w_date}</td>
		                  </tr>
		                </c:forEach>
		                </tbody>
		              </table>
		              <!-- End Table with hoverable rows -->
                </div>

                <div class="tab-pane fade table-responsive" id="liked_boards">
                	<!-- Table with hoverable rows -->
		              <table class="table table-hover" id="dataTable3" >
		                <thead>
		                  <tr>
		                    <th scope="col">글 번호</th>
		                    <th scope="col">종류</th>
		                    <th scope="col">Title</th>
		                    <th scope="col">Writer</th>
		                    <th scope="col">Write Date</th>
		                  </tr>
		                </thead>
		                <tbody>
		                <c:forEach var="list" items="${likeboardlist}">
		                  <tr class="listrow">
		                    <th scope="row" class="idx">${list.idx}</th>
		                    <td>${list.b_name}<span class="d-none">${list.b_code}</span></td>
		                    <td scope="row">${list.title}<span class="badge bg-primary rounded-pill">${list.c_count}</span></td>
		                    <td scope="row">${list.nick}</td>
		                    <td class="w_date">${list.w_date}</td>
		                  </tr>
		                </c:forEach>
		                </tbody>
		              </table>
		              <!-- End Table with hoverable rows -->
                </div>

              </div><!-- End Bordered Tabs -->

            </div>
          </div>

        </div>
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
  
  <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.js"></script>
  <!-- Page level plugins -->
  <script src="vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
  
  <script type="text/javascript">
  	$(function(){
  		$('#dataTable1').DataTable();
  		$('#dataTable2').DataTable();
  		$('#dataTable3').DataTable();
  		
  		$('.page-link ').click(function(){
  			clickLocation();
  		});
  		$('.paginate_button').click(function(){
  			clickLocation();
  		});
  		$('.sorting ').click(function(){
  			clickLocation();
  		});
  		clickLocation();
  		
  	});
  	function clickLocation(){
  		$('.listrow').click(function(){
  			const idx = $(this).children('.idx').text();
  			const b_code = $(this).children().children('.d-none').text();
  			const w_date = $(this).children(".w_date").text();
  			window.location.href = 'boardContent.do?idx='+idx+'&b_code='+b_code+'&w_date='+w_date;
  		});
  	}
  </script>

</body>

</html>