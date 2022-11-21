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

  <style type="text/css">
  .comment-reply {
  	left : 20px;
  }
  </style>

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  <!-- coments CSS Files -->
  <link href="assets/css/comments.css" rel="stylesheet">
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
      <h1>댓글</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
          <li class="breadcrumb-item active">게시판 하단에 들어갈 댓글</li>
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
                  <h5 class="card-title">댓글 <span>/ 4개</span></h5>

					<!-- 작성란 -->
	              <div class="quill-editor-default">
	                <p>댓글 내용을 입력하세요</p>
	              </div>
	              <nav aria-label="Page navigation example">
	              <ul class="pagination justify-content-end">
	              	
	              	<div class="col-sm-2 text-lg-end">
	              	<br>
	              	<!-- Basic Modal -->
	              	<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#basicModal" >
	                	댓글 작성
	              	</button>
	              	</div>
	              	<div class="modal fade" id="basicModal" tabindex="-1">
	                	<div class="modal-dialog">
	                  	<div class="modal-content">
	                    	<div class="modal-body">
	                    	내용을 입력하세요.
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
	      	<div class="card-body">
	      		<h5 class="card-title">댓글 <span>/ 4개</span></h5>
	      			
				<div>
					<img src="image/rank_icon/1.gif" alt="Profile" class="rounded-circle"> USER_NICK <span id="marketB_Text.ns">(22.11.18 12:10)</span>
					<p class="text-justify">ㅊㅊㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ</p>
					<div class="text-end">
					<a href="#" class="btn btn-primary">답글</a><a href="#" class="btn btn-warning">수정</a><a href="#" class="btn btn-danger">삭제</a>
					<hr>
					</div>
				</div>
				

				<div>
					<img src="image/rank_icon/1.gif" alt="Profile" class="rounded-circle"> USER_NICK <span id="marketB_Text.ns">(22.11.18 12:10)</span>
					<p class="text-justify">답글 들여쓰기 구현해야함....</p>
					<div class="text-end">
					<a href="#" class="btn btn-primary">답글</a><a href="#" class="btn btn-warning">수정</a><a href="#" class="btn btn-danger">삭제</a>
					<hr>
					</div>
				</div>
		

				

			</div>
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