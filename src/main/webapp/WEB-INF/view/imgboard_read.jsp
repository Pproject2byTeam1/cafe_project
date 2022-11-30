<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>카페人중독</title>
	
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
  	<link href="assets/css/imgboard.css" rel="stylesheet">
	<link href="assets/css/comments.css" rel="stylesheet">
  	
  	<!-- sweetalert -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  	
  	<script type="text/javascript">
  	
  		$(function(){
  			
  			let email_id = '<c:out value="${member.email_id}" />';
  			let yes = '<c:out value="${yes}" />';
  			let idx = '<c:out value="${imgboard.idx}" />';
  			let b_code ='<c:out value="${imgboard.b_code}" />';
  			
  			/* 게시글 삭제 */
  			$("#imgdel").click(function(){
  				 location.href="imgboardDelete.do?idx="+idx+"&b_code="+b_code;
  			});
  			
  			/* 게시글 수정 */
  			$("#imgmodify").click(function(){
  				location.href="imgboardModifyView.do?idx="+idx+"&b_code="+b_code;
  			})
  			
	  		/* 게시물 좋아요 비동기 처리 */
	  		$("#yesbtn").click(function(){
	  			
	  		
	  	
	  			
	  			if(yes == "no"){
	  				
	  				requestdata = {"idx": idx, "email_id": email_id};
	  				
	  			  				
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
	  		
	  		/* 댓글 */
	  		function inser(data){
	  			$.ajax({
					url : "ReplyOk",
					data : data,
					dataType : "html",
					success : function(data) {
						
						list();
						swal(data);
					}
				});
	  		}
	  		
	  		function del(data2){
				$.ajax(
						{
							url: "ReplyDeleteOk",
							data: data2,
							dataType: "html",
							success: function(data){
							
								list();
								swal(data);
						}
					}
				);
			}
	  		
	  		function list(){
				const req2 = {"idx": idx};
				
					$.ajax(
							{
								url: "ReplyList",
								data: req2,
								dataType: "html",
								success: function(responseText){
								
									$("#reply").empty();
									$("#reply").append(responseText.trim());
										
							}
						}
					);
			}
	  		
	  		function replyinser(data){
	  			
	  			$.ajax({
					url : "ReplyReplyOk",
					data : data,
					dataType : "html",
					success : function(data) {
						
						list();
						swal(data);
					}
				});
	  		}
			
	  		/* 댓글 작성 버튼 클릭 */
			$("#replywritebtn").click(function(){
				const data = {"idx": idx, "content": $("#replycontent").val() };
				
				inser(data);
				
				$("#replycontent").val("");
				
			});
			
			/* 댓글 삭제 버튼 클릭 */
			$(document).on('click', '#replydel', function(){
				const tag = this.closest("div");
				
				const data2 = {"idx": $(tag).children("#co_idx").val()};
				
			
				
				del(data2);
				
			});
			$(document).on('click', '#replydel2', function(){
				const tag2 = this.closest("div");
				
				const data2 = {"idx": $(tag2).children("#co_idx2").val()};
				
		
				del(data2);
				
			});
			
			let replyreplytag = "";
			
			/* 대댓글 버튼 클릭 */
			$(document).on('click', '#replyreplywrite', function(){
				let tag = this.closest("div");
				replyreplytag = tag;
				
				html = '<div id="replyreplyreset" class="comment-write mb-2"><h5 class="card-title">대댓글</h5>';
				html += '<div class="form-floating">';
				html += '<textarea id="replyreplycontent" class="form-control"></textarea>';
				html += '<label for="floatingTextarea">댓글을 작성해 주세요</label> <input value="${imgboard.idx}" type="hidden" />';
				html += '</div>';
				html += '<nav aria-label="Page navigation example">';
				html += '<ul class="pagination justify-content-end"><div><br>';
				html += '<button type="button" id="replyreplywritebtn" class="col btn btn-outline-secondary btn-sm rounded-pill">작성하기</button>'
				html += '<button type="button" id="replyreset" class="col btn btn-outline-secondary btn-sm rounded-pill">작성취소</button>';
				html += '</div></ul></nav></div>';
				
				$(html).insertAfter(this.closest(".comment-card"));
				
			});
			
			/* 대댓글 작성 취소 */
			$(document).on('click', '#replyreset', function(){
				$("#replyreplyreset").remove();
			});
			
			/* 대댓글 작성 */
			$(document).on('click', '#replyreplywritebtn', function(){
				
				const data3 = {
						"co_idx": $(replyreplytag).children("#co_idx").val(), 
						"idx": $(replyreplytag).children("#idx").val(), 
						"content": $("#replyreplycontent").val(),
						"depth": $(replyreplytag).children("#depth").val(),
						"step":  $(replyreplytag).children("#step").val()
					};
				
				replyinser(data3);
				
			});
			
			/* 댓글 끝 */
  			
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
  	<!-- 여기서부터 작성 와랄ㄹ라  -->
  	

		<div class="pagetitle">
			<h1>IMG BOARD</h1>
			<!-- 게시판 이름 끌고오기 b_name -->
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="cafemain.do">Home</a></li>
					<li class="breadcrumb-item"><a href="img_board_list.do?b_code=${b_code}">이미지게시판</a></li>
					<li class="breadcrumb-item active">${imgboard.title}</li>
				</ol>
			</nav>
		</div>
		
		<div class="container">
			<div class="row"> 
				<div class="col-1">
				
				</div>
				
				<div class="col-10">
					<div class="park-card p-4">
						<div class="park-card-body ms-3 row">
							<div class="col-md-6 col mb-3">
								<img src="upload/${imgboard.img_name}">
							</div>
							<div class="col-md-6">
								<div class="ps-2 ms-10 row">
									<div class="col navbar-nav">
										<div class="row mt-2">
											<h3 class="col-3 nav-item mt-1"><i class="bi bi-cloud-arrow-down"></i></h3>
											<h3 class="col-3 nav-item"><i class="bi bi-exclamation-triangle"></i></h3>
											<p>조회수: ${imgboard.hits} &ensp;</p>
											<p>작성일자: ${imgboard.w_date} </p>
										</div>
									</div>
									<div class="col mt-2">
										<c:if test="${member != null}">
											<div class="row">
												<c:if test="${yes == 'no'}">
												<button class="col btn btn-outline-secondary btn-sm rounded-pill" type="button" id="yesbtn"><i class="bi bi-heart"></i></button> &nbsp;
												</c:if>
												<c:if test="${yes != 'no'}">
												<button class="col btn btn-outline-secondary btn-sm rounded-pill" type="button" id="yesbtn"><i class="bi bi-heart-fill"></i></button> &nbsp;
												</c:if>
												<c:if test="${member.email_id == imgboard.email_id}">
													<button id="imgmodify" class="col btn btn-outline-secondary btn-sm rounded-pill" type="button">수정</button> &nbsp;
													<button id="imgdel" class="col btn btn-outline-secondary btn-sm rounded-pill" type="button">삭제</button> &nbsp;
												</c:if>
											</div>
										</c:if>
										<c:if test="${member == null}">
											<button type="button" class="btn btn-outline-secondary btn-sm rounded-pill">저장</button>
										</c:if> 
									</div>
								</div>
								<div class="mt-2">
									<h2><strong>${imgboard.title}</strong></h2>
								</div>
								<div class="mt-2">
									<p>${imgboard.content}</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-1"></div>
			</div>
			
		</div>
		
		<!-- 댓글 -->
		<div class="row">
			<div class="col-1"></div>

			<div class="col-10">

				<section class="section dashboard">
					<div class="row">
						<!-- columns -->
						<div class="col-lg-12">

							<!-- 댓글 카드 섹션 -->
							<div class="ccard">
								<div class="comment-write">
									<h5 class="card-title">댓글</h5>

									<!-- 작성란 -->
									<div class="form-floating">
										<textarea id="replycontent" class="form-control"></textarea>
										<label for="floatingTextarea">댓글을 작성해 주세요</label> <input
											id="idx" value="${imgboard.idx}" type="hidden" />
									</div>
									<nav aria-label="Page navigation example">
										<ul class="pagination justify-content-end">

											<div class="col-sm-2 text-lg-end">
												<br>
												<button type="button" id="replywritebtn"
													class="btn btn-outline-secondary btn-sm rounded-pill">작성하기</button>
											</div>
										</ul>
									</nav>
									<!-- End 작성란 -->
								</div>
							</div>

							<!-- 댓글 목록 카드 섹션 시작 -->

							<div class="ccard" id="reply">
								<c:forEach var="comments" items="${comments}">
									<c:if test="${comments.depth <= 0}">
										<div class="comment-card">
											<div class="comment-box">
												<div class="row">
													<div class="col">
														<h5 class="card-title" id='replynick'>
															<img src="image/rank_icon/1.gif" alt="Profile"
																class="rounded-circle">${comments.nick}
														</h5>
													</div>
													<div id='replydate' class="col comment-date">${comments.w_date}</div>
												</div>
												<h6 class="card-text" id='replycontent'>${comments.content}</h6>
												<h6></h6>

												<div align="right" class="actions">
													<input id="co_idx" value="${comments.co_idx}" type="hidden" />
													<input id="idx" value="${comments.idx}" type="hidden" /> <input
														id="depth" value="${comments.depth}" type="hidden" /> <input
														id="step" value="${comments.step}" type="hidden" />
													<button type="button" id='replyreplywrite'
														class="btn btn-outline-secondary btn-sm rounded-pill">대댓글</button>
													<c:if test='${member.email_id eq comments.email_id } || ${member.isAdmin == "M"} || ${member.isAdmin == "S"}'>
														<button type="button" id="replydel"
															class="btn btn-outline-secondary btn-sm rounded-pill">삭제</button>
													</c:if>

												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${comments.depth > 0}">
										<div class="Recomment-box">
											<div class="row">
												<div class="col">
													<h5 class="card-title">
														<i class="bi bi-arrow-return-right"></i> <img
															src="image/rank_icon/1.gif" alt="Profile"
															class="rounded-circle"> ${comments.nick}
													</h5>
												</div>
												<div class="col comment-date">${comments.w_date}</div>
											</div>
											<h6 class="Recomment-text">${comments.content}</h6>
											<h6></h6>

											<div align="right" class="actions">
											<c:if test='${member.email_id eq comments.email_id } || ${member.isAdmin == "M"} || ${member.isAdmin == "S"}'>
												<input id="co_idx2" value="${comments.co_idx}" type="hidden" />
												<button type="button" id="replydel2"
													class="btn btn-outline-secondary btn-sm rounded-pill">삭제</button>
											</c:if>
											</div>
										</div>
									</c:if>
								</c:forEach>

								<!-- 댓글 목록 카드 섹션 끝 -->

							</div>
						</div>
				</section>
			</div>
			<div class="col-1"></div>
		</div>

		<!-- 여기까지만 작성  -->
  	</main>
	
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