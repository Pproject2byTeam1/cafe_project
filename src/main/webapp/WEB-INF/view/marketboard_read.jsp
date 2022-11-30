<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>marketboard_read</title>

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">
</script>

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
<!-- sweetalert -->
   <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


<!-- 거래게시판 CSS 시작 -->
<link href="assets/css/marketboard_read.css" rel="stylesheet">
<!-- 거래게시판 CSS 끝 -->
<link href="assets/css/comments.css" rel="stylesheet">

<script type="text/javascript">

	$(document).ready(function(){
		let b_code = "<c:out value='${b_code}'/>";
		let cp = "<c:out value='${cpage}'/>";
		let ps = "<c:out value='${pagesize}'/>";	
		
			//글쓰기
			$("#Write").click(function(){
				console.log("write 클릭");
				location.href="marketboard_write.do?b_code=" + b_code;
				
			});
			
			
	});
			
</script>

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">

  	<script type="text/javascript">
  	
  	$(document).ready(function(){
		
  		/* 글삭제 */
		$("#delete").click(function(){
			let idx = "<c:out value='${list.idx}'/>";
			let b_code = "<c:out value='${b_code}'/>";
			console.log(idx);
			console.log(b_code);
 			location.href="marketboard_delete.do?b_code=" + b_code + "&idx=" + ${list.idx};
			
		});
  		
		//글수정
		$("#edit").click(function(){
			
			let b_code = "<c:out value='${b_code}'/>";
			let idx = "<c:out value='${list.idx}'/>";
			console.log("edit 클릭");
			location.href="marketboardEdit.do?b_code="  + b_code + "&idx=" + ${list.idx};
			
		});
		
		let email_id = '<c:out value="${member.email_id}" />';
        let yes = '<c:out value="${yes2}" />';
        let idx = '<c:out value="${list.idx}" />'
        
        /* 게시물 좋아요 비동기 처리 */
        $("#yesbtn").click(function(){
           
           console.log("hahaha");
           console.log(email_id);
           console.log("sdfs" + yes);
           
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
			
			console.log(data2);
			
			del(data2);
			
		});
		$(document).on('click', '#replydel2', function(){
			const tag2 = this.closest("div");
			
			const data2 = {"idx": $(tag2).children("#co_idx2").val()};
			
			console.log(data2);
			
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
		
		list();
		
		/* 댓글 끝 */
		
        	/*신고  */
		function rep(data7){

			$.ajax({
				url:"RepCount",
				data:data7,
				dataType:"html",
				success:function(responsetxt){
				
					swal("신고","신고되었습니다","success");
				}
				
		});
				
		};

		$(document).on('click', '#report', function(){
		
			const data7 ={"idx":idx};
			
			console.log(data7);
			rep(data7);
			
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

		<!-- Page Title -->
		<div class="pagetitle">
			<h1>거래 게시판</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">전체글 : 000</a></li>
					<li class="breadcrumb-item active">판매중 : 000</li>
					<li class="breadcrumb-item">판매완료 : 000</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<!-- Contents -->
		<div class="container">
			<div class="row">

				<div class="col-md-12">
					<div class="marketcard">
						<div class="marketcard-body">

							<div class="row">
								<!-- 상단부 2/3으로 나눠 글 내용 시작 -->
								<div class="col-md-8">
									<span id="marketB_Title">${list.idx}) ${list.title}</span>
									<p>
									<hr>
									<div class="row">
										<div class="col-lg-9">
											<img class="marketB_img" src="upload/${list.img_name}" onerror="this.onerror=null; this.src='https://via.placeholder.com/500X500?text=No+Image'">
										</div>
										<div align="center" class="col-lg-3">
											<div class="info-body price">
												<span id="marketB_Price">${list.price}</span>
											</div>
											<div class="info-body">
												<span id="marketB_Text.ns">${list.m_mode}|${list.sold}|${list.cate}</span>
											</div>
											<div class="info-body">
												<span id="marketB_Text.ns">조회수:${list.hits} 댓글:${commentscount} 찜:${yes}</span>
											</div>
											<div class="info-body">
												<img src="image/rank_icon/1.gif" alt="Profile"
													style="width:15px" class="rounded-circle"><a data-bs-toggle="dropdown">${list.nick}</a>
												<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
													<li class="anw_memo"><a class="dropdown-item sendToMemo" href="write_memo.do?sender_id=${board.email_id}" >답장 보내기</a></li>
													<li class="view_user_activity"><a class="dropdown-item viewActivity" href="user_activity.do?email_id=${list.email_id}" >활동 내역 보기</a></li>
												</ul>
											</div>
											<div class="info-body">
												<span id="marketB_Text.ns">${list.w_date}</span>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12" style="word-break:break-all;">
											<hr>
											${list.content}
											<p>
										</div>
										<hr>
										<div align="right" class="col-md-12">
											<div>
											<input type="submit" class="btn btn-outline-danger btn-sm rounded-pill "   id="report"  value="신고"> 
												<button type="button" id="Write" 
													class="btn btn-outline-secondary btn-sm rounded-pill">글쓰기</button>
												<!-- 본인확인 -->
												<c:if test="${list.email_id==member.email_id || member.isAdmin == 'S' || member.isAdmin =='M'}">
												<button type="button" id="edit" 
													class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
												<button type="button" id="delete" 
													class="btn btn-outline-secondary btn-sm rounded-pill">삭제</button>
												</c:if>
												<!-- 본인확인 -->
												<button type="button" id="List" 
													class="btn btn-outline-secondary btn-sm rounded-pill" onclick="history.go(-1)">목록</button>
												<c:if test="${member != null}">
													<c:if test="${yes2 == 'no'}">
	                                    				<button class="btn btn-outline-secondary btn-sm rounded-pill" type="button" id="yesbtn"><i class="bi bi-heart"></i></button> &nbsp;
	                                    			</c:if>
	                                    			<c:if test="${yes2 != 'no'}">
	                                    				<button class="btn btn-outline-secondary btn-sm rounded-pill" type="button" id="yesbtn"><i class="bi bi-heart-fill"></i></button> &nbsp;
	                                    			</c:if>	
												</c:if>
											</div>
										</div>

									</div>
								</div>
								<!-- 상단부 1/3으로 나눠 댓글시작 -->
								<div class="col-md-4">
									<br>

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

																		<div align="right" class="actions">
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
																				<input id="co_idx" value="${comments.co_idx}"
																					type="hidden" /> <input id="idx"
																					value="${comments.idx}" type="hidden" /> <input
																					id="depth" value="${comments.depth}" type="hidden" />
																				<input id="step" value="${comments.step}"
																					type="hidden" />
																				<button type="button" id='replyreplywrite'
																					class="btn btn-outline-secondary btn-sm rounded-pill">대댓글</button>
																				<c:if test='${member.email_id eq comments.email_id } || ${member.isAdmin == "M"} || ${member.isAdmin == "S"}'>
																					<button type="button" id="replydel"
																						class="btn btn-outline-secondary btn-sm rounded-pill">삭제</button>
																				</c:if>																																							</div>
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
																				<input id="co_idx2" value="${comments.co_idx}"
																					type="hidden" />
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

									<!-- 댓글 끝 -->
								</div>
								
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>


		<!-- 보드 리스트 출력 시작 -->

		<!-- 보드 페이지 시작 -->

	

		<!-- 보드 페이지 끝 -->
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