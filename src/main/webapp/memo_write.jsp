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

  <main id="main" class="main">
    <section class="section dashboard">
      <div class="row">
		<!-- columns -->
        <div class="col-md-10">
          <div class="row">
          	  <!-- 쪽지리스트 -->
	          <div class="card">
	            <div class="card-body">
	              <h5 class="card-title">쪽지함</h5>
	              <div class="filter">
	                  <a class="icon" href="#"><i class="bi bi-x-lg"></i></a>
                </div>
                <!-- No Labels Form -->
              <form class="row g-3">
                <div class="col-md-9">
                  <input type="email" class="form-control" placeholder="보내는 사람" value="${아이디 넣는곳}" readonly="readonly">
                </div>
                <div class="col-md-3">
                  <input class="form-check-input" type="checkbox">
                  <label class="form-check-label" for="gridCheck">내게쓰기</label>
                </div>
                <div class="col-md-9">
                  <input type="email" class="form-control" placeholder="받는 사람">
                </div>
                <div class="col-md-3">
                  <button type="button" class="btn btn-success">보내기</button>
                </div>
                <div class="col-12">
                  <div class="form-floating">
                    <textarea class="form-control" placeholder="필수 입력사항을 입력하세요" id="floatingTextarea" style="height: 300px;"></textarea>
                    <label for="floatingTextarea">필수 입력사항을 입력하세요</label>
                  </div>
                </div>
              </form><!-- End No Labels Form -->
	          </div><!-- End 쪽지리스트 -->
          </div>
         </div>
         <!-- columns -->
      </div>
      </div>
    </section>

  </main><!-- End #main -->

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