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

  <main id="main" class="main">
    <section class="section dashboard">
      <div class="row">
		<!-- columns -->
        <div class="col-lg-10">
          <div class="row">
          	  <!-- 일정상세 -->
	          <div class="card">
	            <div class="card-body">
	              <h5 class="card-title">일정제목</h5>
	              <div class="filter">
	                  <a class="icon" href="#" data-bs-toggle="dropdown">진행상황</a>
	                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
	                    <li class="dropdown-header text-start">
	                      <h6>상태</h6>
	                    </li>
	
	                    <li><a class="dropdown-item" href="#">진행 전</a></li>
	                    <li><a class="dropdown-item" href="#">진행 중</a></li>
	                    <li><a class="dropdown-item" href="#">진행 완료</a></li>
	                  </ul>
                </div>
                <div class="row mb-3">
                   <div class="col-lg-3 col-md-4 label">시작일자</div>
                   <div class="col-lg-9 col-md-8">1998.05.27</div>
                 </div>
                 <div class="row mb-3">
                   <div class="col-lg-3 col-md-4 label">종료일자</div>
                   <div class="col-lg-9 col-md-8">1998.05.27</div>
                 </div>
                 <div class="row">
                   <div class="col-lg-3 col-md-4">조회 수 : 3</div>
                 </div>
                  <div class="row">
                   <div class="col-lg-3 col-md-4">작성자: 닉네임</div>
                 </div>
                 <hr>
                <h4>
                내용입니다 아무거나 작성하는 공간
                </h4>
                <br>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="gridCheck">
                    <label class="form-check-label" for="gridCheck">
                      참석하기
                    </label>
                 </div>
                 <div>
                  <nav style="--bs-breadcrumb-divider: '/';">
	                <ol class="breadcrumb">
	                  참석자 명단: 
	                  <li class="breadcrumb-item">ㅁ씨</li>
	                  <li class="breadcrumb-item">ㄱ씨</li>
	                  <li class="breadcrumb-item">ㅅ씨</li>
	                </ol>
	              </nav>
	             </div>
          		<hr>
          		<div>
	          		<div class="quill-editor-default">
	          		<p>여기다가 작성</p>
	          		</div>
	          		<nav aria-label="Page navigation example">
		              <ul class="pagination justify-content-end">
		              	<button type="button" class="btn btn-success">작성하기</button>
		              </ul>
		            </nav>
		        </div>
				<div class="col-sm-12">
	              <table>
		             <tr>
			             <td rowspan="2"><img src="assets/img/messages-2.jpg" alt="" class="rounded-circle w-75 p-3"></td>
			             <td><h5 class="card-title">닉네임1</h5></td>
			             <td>
			                <nav style="--bs-breadcrumb-divider: '|';">
			                <ol class="breadcrumb">
			                  <li class="breadcrumb-item">11 / 25</li>
			                  <li class="breadcrumb-item">01 : 02</li>
			                </ol>
			               </nav>
			             </td>
		             </tr>
		             <tr>
		             	<td colspan="2"><p>ㅊㅊgdhdhsgjfkhgkfgkhdjhdhfdghfdffffffffffffffffffffff</p></td>
		             </tr>
		             <tr><td colspan="3"><a href="#"  class="btn btn-warning">삭제</a></td></tr>
	              </table>
	            </div>
	              <hr>
	            <div class="col-sm-12">
	              <table>
		             <tr>
			             <td rowspan="2"><img src="assets/img/messages-1.jpg" alt="" class="rounded-circle w-75 p-3"></td>
			             <td><h5 class="card-title">닉네임2</h5></td>
			             <td>
			                <nav style="--bs-breadcrumb-divider: '|';">
			                <ol class="breadcrumb">
			                  <li class="breadcrumb-item">11 / 25</li>
			                  <li class="breadcrumb-item">01 : 02</li>
			                </ol>
			               </nav>
			             </td>
		             </tr>
		             <tr>
		             	<td colspan="2"><p>아절이시ㅏㅓㅑ어렺들쟈ㅑ를자ㅔ,ㅑㄷ츠쟈ㅐㅣ므리망</p></td>
		             </tr>
		             <tr><td colspan="3"><a href="#"  class="btn btn-warning">삭제</a></td></tr>
	              </table>
	            </div>
	            <hr>
	          </div><!-- End 일정상세 -->
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