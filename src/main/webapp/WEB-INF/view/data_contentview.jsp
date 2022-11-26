<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

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
<link rel="stylesheet" type="text/css" href="assets/css/free.css">
<!-- coments CSS Files -->
<link href="assets/css/comments.css" rel="stylesheet">
<link href="assets/css/marketboard_read.css" rel="stylesheet">

<!-- 경고창 이쁜거 -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js" >

</script>

<script type="text/javascript">
	$(function(){
		
		let email_id = '<c:out value="${member.email_id}" />';
        let yes = '<c:out value="${yes}" />';
        let idx = '<c:out value="${board.idx}" />';
        let b_code = '<c:out value="${board.b_code}" />';
        let depth = '<c:out value="${board.depth}" />';
        let step = '<c:out value="${board.depth}" />';

        /* 게시물 좋아요 비동기 처리 */
        $("#yesbtn").click(function(){
           
           if(yes == "no"){
              
              requestdata = {"idx": idx, "email_id": email_id};
              
              console.log(requestdata);
              
              $.ajax({
                 type: "POST",
                 url: "Yes",
               data: requestdata,
                 dataType: "HTML",
                 success: function(data){
                    
                    yes = email_id;
                    
                    $("#yesbtn").empty();
                    $("#yesbtn").append('<i class="bi bi-heart-fill"></i>');
                    swal(data);
                 },
                 beforeSend: function(){
                  $('.wrap-load').removeClass('display-none');
               },
               complete: function(){
                  $('.wrap-loading').addClass('display-none');
               }
              });
              
           }else{
              
              requestdata = {"idx": idx, "email_id": email_id};
              
              $.ajax({
                 type: "POST",
                 url: "YesRemove",
               data: requestdata,
                 dataType: "HTML",
                 success: function(data){
                    yes = "no";
                    
                    $("#yesbtn").empty();
                    $("#yesbtn").append('<i class="bi bi-heart"></i>');
                    swal(data);
                 },
                 beforeSend: function(){
                  $('.wrap-load').removeClass('display-none');
               },
               complete: function(){
                  $('.wrap-loading').addClass('display-none');
               }
              });
              
           }
           
           
        });

        $("#delete").click(function(){
			let idx = "<c:out value='${board.idx}'/>";
			let b_code = "<c:out value='${board.b_code}'/>";
			console.log(idx);
			console.log(b_code);
			
 			location.href="board_delete.do?idx=" + ${board.idx};
 			
 			if(idx == ${board.idx}){
 				alert("삭제됩니다!");
 				
 			}
			
		});
        
        /* 답글 작성 */
        $("#replyWrite").click(function(){
     	 
     	  	let link = "/WebCafe_Project/replyWriteDataBoardView.do?idx="+idx+"&b_code="+b_code+"&depth="+depth+"&step="+step;
     	   	console.log(link);
     	   
     		location.href=link;
     	   
        });
        
	});
</script>

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
               <h1>자료게시판</h1>
               <!-- 게시판 이름 끌고오기 b_name -->
               <nav>
                  <ol class="breadcrumb">
                     <li class="breadcrumb-item"><a href="databoard_list.do?b_code=6">자료게시판</a></li>
                     <li class="breadcrumb-item active">${board.title}</li>
                  </ol>
               </nav>
            </div>


		<div class="container-fluid">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">


					<div class="col-md-12">
						<div class="marketcard">
							<div class="marketcard-body">

								<div class="row">
									<!-- 상단부 2/3으로 나눠 글 내용 시작 -->
									<div class="col-md-12">
										<div class="col-md-12">
											<h1 id="marketB_Title">${board.title}</h1><br>
										</div>
										
										<div class="col-md-12">
											<div class="row">
												<div class="col-md-5">
													<p class="card-text"><img src="./image/rank_icon/${rank.rank}.gif">${board.nick}</p>
												</div>
												<div class="col-md-2">
													<p class="text-right card-text">조회수:10</p>
												</div>
												<div class="col-md-2">
													<p class="text-right card-text">추천:${board.hits}</p>
												</div>
												<div class="col-md-3">
													<p class="text-right card-text">작성일자: ${board.w_date}</p>
												</div>
											</div>
										</div>
									


									<hr>
										<div class="row">
											<div class="col-lg-12">

												<text class="form-control" style="height: 500px" readonly> ${board.content}</textarea>

											</div>

										</div>
										<br>
										<hr>
										<div class="row">

											<div align="right" class="col-md-12">
												<div>
													<c:if test="${yes == 'no'}">
                                    					<button class="col btn btn-outline-secondary btn-sm rounded-pill" type="button" id="yesbtn"><i class="bi bi-heart"></i></button> &nbsp;
                                    				</c:if>
                                    				<c:if test="${yes != 'no'}">
                                    					<button class="col btn btn-outline-secondary btn-sm rounded-pill" type="button" id="yesbtn"><i class="bi bi-heart-fill"></i></button> &nbsp;
                                    				</c:if>
													<button type="button" id="replyWrite"
														class="btn btn-outline-secondary btn-sm rounded-pill">답글</button>
														<c:if test="${board.email_id==member.email_id || member.isAdmin == 'S' || member.isAdmin =='M'}">
													<button type="button" id="delete"
														class="btn btn-outline-secondary btn-sm rounded-pill" >삭제</button>	
													<button type="button" id="List"
														class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
														</c:if>
													<button type="button" id="Top"
														class="btn btn-outline-secondary btn-sm rounded-pill">목록</button>
												</div>
											</div>

										</div>
									</div>



									<!-- 댓글 끝 -->
								</div>
							</div>
						</div>

					</div>








					<section class="section dashboard">
						<div class="row">
							<!-- columns -->
							<div class="col-lg-12">

								<!-- 댓글 카드 섹션 -->
								<div class="ccard">
									<div class="comment-write">
										<h5 class="card-title">
											댓글 <span>/ 4개</span>
										</h5>

										<!-- 작성란 -->
										<div class="quill-editor-default">
											<p>댓글 내용을 입력하세요</p>
										</div>
										<nav aria-label="Page navigation example">
											<ul class="pagination justify-content-end">

												<div class="col-sm-2 text-lg-end">
													<br>
													<button type="button"
														class="btn btn-outline-secondary btn-sm rounded-pill">작성하기</button>
												</div>
												<div class="modal fade" id="basicModal" tabindex="-1">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-body">내용을 입력하세요.</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-secondary"
																	data-bs-dismiss="modal">Close</button>
															</div>
														</div>
													</div>
												</div>
											</ul>
										</nav>
										<!-- End 작성란 -->
									</div>
								</div>

								<!-- 댓글 목록 카드 섹션 시작 -->
								<div class="ccard">
									<div class="comment-card">
										<div class="comment-box"><!--  댓글박스 -->
											<div class="row">
												<div class="col">
													<h5 class="card-title">
														<img src="image/rank_icon/1.gif" alt="Profile"
															class="rounded-circle"> USER_NICK
													</h5>
												</div>
												<div class="col comment-date">22.11.21 12:10</div>
											</div>
											<h6 class="card-text">댓글의 내용~~~</h6>
											<h6></h6>

											<div align="right" class="actions">
												<button type="button"
													class="btn btn-outline-secondary btn-sm rounded-pill">대댓글</button>
											
												<button type="button"
													class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
												<form action ="board_delete.do?idx=${idx}" method="GET">
												<button type="submit"
													class="btn btn-outline-secondary btn-sm rounded-pill">삭제</button>
												</form>
											
											</div>
										</div>
									</div>

									<div class="Recomment-box">
										<div class="row">
											<div class="col">
												<h5 class="card-title">
													<i class="bi bi-arrow-return-right"></i> <img
														src="image/rank_icon/1.gif" alt="Profile"
														class="rounded-circle"> USER_NICK
												</h5>
											</div>
											<div class="col comment-date">22.11.21 12:10</div>
										</div>
										<h6 class="Recomment-text">대댓글 대댓글</h6>
										<h6></h6>

										<div align="right" class="actions">
											<button type="button"
												class="btn btn-outline-secondary btn-sm rounded-pill">대댓글</button>
											<button type="button"
												class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
											<button type="button"
												class="btn btn-outline-secondary btn-sm rounded-pill">삭제</button>
										</div>
									</div>

									<div class="comment-card">
										<div class="comment-box">
											<div class="row">
												<div class="col">
													<h5 class="card-title">
														<img src="image/rank_icon/1.gif" alt="Profile"
															class="rounded-circle"> USER_NICK
													</h5>
												</div>
												<div class="col comment-date">22.11.21 12:10</div>
											</div>
											<h6 class="card-text">댓글의 내용~~~</h6>
											<h6></h6>

											<div align="right" class="actions">
												<button type="button"
													class="btn btn-outline-secondary btn-sm rounded-pill">대댓글</button>
												<button type="button"
													class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
												<form action="regular_write.do?b_code=${b_code}" method="GET">
					<input type="text" value="${b_code}" name="b_code" style="display: none;">
					<input type="submit" class="btn btn-secondary float-right" value="글쓰기">
					</form>
											</div>
										</div>

									</div>
									<!-- 댓글 목록 카드 섹션 끝 -->

								</div>
							</div>
					</section>


				</div>
				<div class="col-md-1"></div>
			</div>
		</div>






		<!-- Page Title -->


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
</body>

</html>