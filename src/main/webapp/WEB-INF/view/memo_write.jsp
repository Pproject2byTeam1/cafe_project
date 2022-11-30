<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">
     <c:import url="/WEB-INF/view/common/top.jsp" />
  </header><!-- End Header -->
  
  <!-- ======= Sidebar ======= -->
  <c:import url="/WEB-INF/view/common/side.jsp" />
  <!-- End Sidebar -->
  
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

<!-- 경고창 이쁜거 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

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
	                  <a class="icon" id="close"><i class="bi bi-x-lg"></i></a>
                </div>
                <!-- No Labels Form -->
              <form class="row g-3" name="memoboard" action="write_memo_ok.do" method="post">
                <div class="col-md-9">
                  <input type="email" id="writer" name="writer" class="form-control" placeholder="보내는 사람" value="${userId}" readonly="readonly">
                </div>
                <div class="col-md-3">
                	<input class="form-check-input" type="checkbox" id="sendtoMe">
                  <label class="form-check-label" for="gridCheck">내게쓰기</label>
                </div>
                
                <div class="col-md-9">
                <c:choose>
	                <c:when test="${responde_Id != null}">
	                  <input type="email" name="reader" class="form-control" placeholder="받는 사람" id="respond_Id" value="${responde_Id }">
	                </c:when>
	                <c:otherwise>
	                <input type="email" name="reader" class="form-control" placeholder="받는 사람" id="respond_Id">
	                </c:otherwise>
                </c:choose>
                </div>
                <c:if test="${member.rank == -1}">
                <div class="col-md-3">
                	<input class="form-check-input" type="checkbox" id="allSend">
                  <label class="form-check-label" for="allSend">모두에게 보내기</label>
                </div>
                <div class="col-md-9"></div>
                </c:if>
                <div class="col-md-3">
                  <button type="button" class="btn btn-success" id="sendMemo">보내기</button>
                </div>
                <div class="col-12">
                  <div class="form-floating">
                    <textarea class="form-control" name="content" placeholder="필수 입력사항을 입력하세요" id="floatingTextarea" style="height: 200px;"></textarea>
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
  <script type="text/javascript">
  $(function () {
	  $("#close").click(function(){
		  window.close();
		  });
	  $("#sendtoMe").click(function(){
		  if($(this).is (':checked')){
			  $('#respond_Id').attr('value',$('#writer').val());
		  }else {
			  $('#respond_Id').attr('value', "");
		  }
	  })
	  
	  $("#allSend").click(function(){
		  if($(this).is (':checked')){
			  $('#respond_Id').attr('value','ALL');
			  $('#respond_Id').attr('readonly',true);
		  }else {
			  $('#respond_Id').attr('value', "");
		  }
	  })

	  $("#sendMemo").click(function(){//쪽지 보내기(글 작성)
		  check();
	  });
	  window,addEventListener("keydown",function(event){
		  if(event.defaultPrevented){
			  return;
		  }
		  var handled = false;
		  if(event.keyCode == 123)
			  handled = true;
		  if(handled){
			  swal("특정 행위를 감지","F12키를 누르지 마십시오",'error');
			  event.preventDefault();
		  }
	  },true);
	  
		function check() {
			if (!memoboard.writer.value) {
				Swal.fire("경고","보내는 사람을 입력하세요","warning");
				memoboard.writer.focus();
				return false;
			}
			if(!memoboard.content.value){            
				Swal.fire("경고","글 내용을 입력하세요","warning");
			     memoboard.content.focus();
			     return false;
			 }
			Swal.fire({
				title:"성공!",
				text: "전송 성공",
				type:"success"}).then(function(){
				document.memoboard.submit();
			});
		}
	
  });
  </script>

</body>

</html>