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
					<a href="checkBoard.do?inputdate=${year}-${month-1}-01">
					<button type="button" class="btn btn-outline-info rounded-pill bi bi-caret-left-fill"></button></a>
					<span>${year} 년 ${month} 월</span>
					<a href="checkBoard.do?inputdate=${year}-${month+1}-01">
					<button type="button" class="btn btn-outline-info rounded-pill bi bi-caret-right-fill"></button></a>
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
					<br>
					
		              <br>
		              <div class="col-sm-10 align-self-center text-md-start" id = "count">오늘의 출석인 수: ${count}명</div>
                </div>
              </div>
            </div><!-- End 카드 -->
            <!-- 작성란 -->
<c:if test="${userId != null && sysdate == date5}"><!-- 로그인 상태와 오늘 날짜여야만 보임 -->
		<div class="container">
			<div class="row">
				<div class="col-1"></div>
				<form name="bbs" action="insertAttendance.do" method="POST" enctype="multipart/form-data">
					<div class="col-12">
						<div class="park-card p-4">
							<div class="park-card-body row">
								<textarea name="content" required> 
        			 				글을 작성해주세요.
        						</textarea>
							</div>
							<div class="col-2">
								<button type="submit" id="btn" class="btn btn-primary">출석하기</button>
							</div>
						</div>
					</div>
				</form>
			</div>

			<div class="col-1"></div>
		</div>
</c:if><!-- End 작성란 -->
            
            <!-- 작성글 -->
            <div class="col-lg-12">
	          <div class="card">
	            <h5 class="card-title">&nbsp;&nbsp;&nbsp;출석명단</h5>
	            <div id = "boardlist">
	            <c:forEach var="list" items="${boardlist}" varStatus="status">
	            <!-- 1round -->
	            <div class="card-body row">
	              <hr>
	              <div class="col-sm-2">
	            	  <h5 class="card-title"><img src="image/rank_icon/${list.hits}.gif" alt="랭크 아이콘">${list.nick}</h5>
	              </div>
	              <input type="text" hidden="" value="${list.idx}" class="idx">
	              <div class="col-sm-7 align-self-center"><p class="text-justify ">${list.content}</p></div>
	              <div class="col-sm-3 align-self-center text-lg-end">
		              <nav style="--bs-breadcrumb-divider: '|';">
			                <ol class="breadcrumb">
				                  <li class="breadcrumb-item">${list.w_date}</li>
				                  <c:if test="${list.email_id == userId}">
				                  <li class="breadcrumb-item active"><a class="btn btn-warning" id="attendanceDelete">삭제</a></li>
				                  </c:if>
			                </ol>
		               </nav>
	               </div>
	              </div>
	              <!-- 1round -->
	              </c:forEach>
	              </div>
	            <hr>
				<!-- 보드 페이지 시작 -->
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
					
			               <c:if test="${cpage > 1}">
			                 <li class="page-item">
			                   <a class="page-link" href="checkBoard.do?cp=${cpage-1}&ps=${pagesize}" tabindex="-1" aria-disabled="true">&laquo;</a>
			                 </li>
			                  </c:if>
			                  	
			                  <c:forEach var="i" begin="1" end="${pagecount}" step="1">
			                  	<c:choose>
									<c:when test="${cpage==i}">
											<li class="page-item"><a class="page-link active" >${i}</a></li>
									</c:when>
									<c:otherwise>
				                 			<li class="page-item"><a class="page-link" href="checkBoard.do?cp=${i}&ps=${pagesize}">${i}</a></li>
									</c:otherwise>
								</c:choose>
			                  </c:forEach>
			                  
			                <c:if test="${cpage < pagecount}">
		                  	<li class="page-item">
								<a class="page-link" href="checkBoard.do?cp=${cpage+1}&ps=${pagesize}">&raquo;</a>
							</li>
						    </c:if>
					</ul>
				</nav>	            
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
<script>
		tinymce
				.init({
					selector : 'textarea',
					plugins : 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage tinycomments tableofcontents footnotes mergetags autocorrect',
					toolbar : 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
					tinycomments_mode : 'embedded',
					tinycomments_author : 'Author name',
					mergetags_list : [ {
						value : 'First.Name',
						title : 'First Name'
					}, {
						value : 'Email',
						title : 'Email'
					}, ]
				});
	</script>
	<script type="text/javascript">
		$(function(){
			//삭제
			$('#attendanceDelete').click(function(){
				const idx = $(this).parent().parent().parent().parent().parent().children('.idx').val();
				console.log(idx);
				location.href="deleteAttendance.do?idx="+idx;
			});
		});
	</script>
</body>

</html>