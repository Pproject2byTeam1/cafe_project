<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

  <jsp:include page="/common/top.jsp"></jsp:include>

  </header><!-- End Header -->

  <!-- ======= Sidebar ======= -->
  
   <jsp:include page="/common/side.jsp"></jsp:include>
  
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
						<li><a href="#" class="btn btn-default">1</a></li>
						<li><a href="#" class="btn btn-default">2</a></li>
						<li><a href="#" class="btn btn-default">3</a></li>
						<li><a href="#" class="btn btn-default">4</a></li>
						<li><a href="#" class="btn btn-default">5</a></li>
						<li><a href="#" class="btn btn-default">6</a></li>
						<li><a href="#" class="btn btn-default">7</a></li>
						<li><a href="#" class="btn btn-default">8</a></li>
						<li><a href="#" class="btn btn-default">9</a></li>
						<li><a href="#" class="btn btn-default">10</a></li>
						<li><a href="#" class="btn btn-default">11</a></li>
						<li><a href="#" class="btn btn-default">12</a></li>
						<li><a href="#" class="btn btn-default">13</a></li>
						<li><a href="#" class="btn btn-default">14</a></li>
						<li><a href="#" class="btn btn-default">15</a></li>
						<li><a href="#" class="btn btn-default">16</a></li>
						<li><a href="#"class="btn btn-default">17</a></li>
						<li><a href="#"class="btn btn-default">18</a></li>
						<li><a href="#"class="btn btn-default">19</a></li>
						<li><a href="#"class="btn btn-default">20</a></li>
						<li><a href="#"class="btn btn-default">21</a></li>
						<li><a href="#"class="btn btn-default">22</a></li>
						<li><a href="#"class="btn btn-default">23</a></li>
						<li><a href="#"class="btn btn-default">24</a></li>
						<li><a href="#" class="btn btn-default btn-outline-success"><b>25</b></a></li>
						<li><a href="#"class="btn btn-default">26</a></li>
						<li><a href="#"class="btn btn-default">27</a></li>
						<li><a href="#"class="btn btn-default">28</a></li>
						<li><a href="#"class="btn btn-default">29</a></li>
						<li><a href="#"class="btn btn-default">30</a></li>
					</ul>
					<!-- 작성란 -->
	              <div class="quill-editor-default">
	                <p>출석 체크</p>
	              </div>
	              <nav aria-label="Page navigation example">
	              <ul class="pagination justify-content-end">
	              	<div class="col-sm-10 align-self-center text-md-start">오늘의 출석인 수: 2명</div>
	              	<div  class="col-sm-2 text-lg-end">
	              	<button type="button" class="btn btn-success" hidden>출석하기</button>
	              	<!-- Basic Modal -->
	              	<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#basicModal" >
	                	출석하기
	              	</button>
	              	</div>
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
	              </ul>
	              </nav>
	              <!-- End 작성란 -->
                </div>
              </div>
            </div><!-- End 카드 -->
            <!-- 댓글 -->
            <div class="col-lg-12">
	          <div class="card">
	            <div class="card-body row">
	            <h5 class="card-title">&nbsp;&nbsp;&nbsp;출석명단</h5>
	            <hr>
	            <div class="col-sm-2">
	              <table>
		             <tr>
			             <td><img src="assets/img/messages-2.jpg" alt="" class="rounded-circle w-75 p-3"></td>
			             <td><h5 class="card-title">닉네임1</h5></td>
		             </tr>
	              </table>
	            </div>
	              <div class="col-sm-1 align-self-center text-lg-end"><em class="rank2">2등</em></div>
	              <div class="col-sm-6 align-self-center"><p class="text-justify ">ㅊㅊ</p></div>
	              <div class="col-sm-3 align-self-center text-lg-end">
	              <nav style="--bs-breadcrumb-divider: '|';">
	                <ol class="breadcrumb">
	                  <li class="breadcrumb-item">11 / 25</li>
	                  <li class="breadcrumb-item">01 : 02</li>
	                  <li class="breadcrumb-item active"><a href="#" class="btn btn-warning">삭제</a></li>
	                </ol>
	              </nav>
	              </div>
	              <hr>
	            </div>
	            <div class="card-body row">
	            <div class="col-sm-2">
	              <table>
		             <tr>
			             <td><img src="assets/img/messages-1.jpg" alt="" class="rounded-circle w-75 p-3"></td>
			             <td><h5 class="card-title">닉네임2</h5></td>
		             </tr>
	              </table>
	            </div>
	              <div class="col-sm-1 align-self-center text-lg-end"><em class="rank1">1등</em></div>
	              <div class="col-sm-6 align-self-center"><p class="text-justify">ㅊㅊㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ</p></div>
	              <div class="col-sm-3 align-self-center text-lg-end">
	              <nav style="--bs-breadcrumb-divider: '|';">
	                <ol class="breadcrumb">
	                  <li class="breadcrumb-item">11 / 25</li>
	                  <li class="breadcrumb-item">01 : 02</li>
	                  <!-- <li class="breadcrumb-item active"><a href="#" hidden="" class="btn btn-warning">삭제</a></li> -->
	                </ol>
	              </nav>
	              <hr>
	            </div>
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