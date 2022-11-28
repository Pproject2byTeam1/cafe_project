<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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

<!-- sweetalert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script type="text/javascript">
     
        $(function(){
           
           let email_id = '<c:out value="${member.email_id}" />';
           let yespark = '<c:out value="${yespark}" />';
           let idx = '<c:out value="${board.idx}" />';
           let b_code = '<c:out value="${board.b_code}" />';
           let depth = '<c:out value="${board.depth}" />';
           let step = '<c:out value="${board.step}" />';
           let refer = '<c:out value="${board.refer}" />';
           
           
           
           /* 사진 크기 자동 정렬 jQuery */
			$("#text p img").removeAttr("width");
		   	$("#text p img").removeAttr("height");
		   	$("#text p img").attr("style","display: block; margin-left: auto; margin-right: auto; max-width:100%; height:auto;");
           
           /* 게시물 좋아요 비동기 처리 */
           $("#yesbtn").click(function(){
              
        	   list();
              
              console.log("hahaha");
              console.log(email_id);
              console.log("sdfs" + yespark);
              
              if(yespark == "no"){
                 
                 requestdata = {"idx": idx, "email_id": email_id};
                 
                 console.log(requestdata);
                 
                 $.ajax({
                    type: "POST",
                    url: "Yes",
                  data: requestdata,
                    dataType: "HTML",
                    success: function(data){
                       
                       yespark = email_id;
                       
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
                       yespark = "no";
                       
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
           
           
           /* 답글 작성 */
           $("#replyWrite").click(function(){
            
                let link = "/WebCafe_Project/regular_reWriteView.do?refer="+refer+"&b_code="+b_code+"&depth="+depth+"&step="+step+"&idx="+idx;
                 console.log(link);
              
              location.href=link;
              
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
		   		html += '<label for="floatingTextarea">댓글을 작성해 주세요</label> <input value="${board.idx}" type="hidden" />';
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

      <jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>

   </header>
   <!-- End Header -->

   <!-- ======= Sidebar ======= -->

   <jsp:include page="/WEB-INF/view/common/side.jsp"></jsp:include>

   <!-- End Sidebar -->

   <main id="main" class="main">
      <div class="pagetitle">
               <h1>자유 게시판</h1>
               <!-- 게시판 이름 끌고오기 b_name -->
               <nav>
                  <ol class="breadcrumb">
                     <li class="breadcrumb-item"><a href="regular_list.do?b_code=1">자유게시판</a></li>
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
                                       <p class="card-text"><img src="image/rank_icon/${user.rank}.gif">${board.nick}</p>
                                    </div>
                                    <div class="col-md-2">
                                       <p class="text-right card-text">조회수:${board.hits}</p>
                                    </div>
                                    <div class="col-md-2">
                                       <p class="text-right card-text">추천:${yes}</p>
                                    </div>
                                    <div class="col-md-3">
                                       <p class="text-right card-text">작성일자: ${board.w_date}</p>
                                    </div>
                                 </div>
                              </div>
                           


                           <hr>
                              <div class="row">
                                 <div class="col-lg-12">

                                    <text class="form-control" style="height: auto" id="text"> ${board.content}</textarea>
                                    
                                    
                                 </div>

                              </div>
                              <br>
                              <hr>
                              <div class="row">

                                 <div class="col-md-12">
                                    <div class="row">
                                    
                                    	<!-- 비어있는거 -->
                                    	<div class="col-md-4"></div>
                                    	
                                    	<!-- 좋아요 -->
                                    	<div class="col-md-4 text-center">
                                    		<c:if test="${member != null}">
	                                          <c:if test="${yespark == 'no'}">
	                                               <button class="col btn btn-outline-secondary btn-sm rounded-pill" type="button" id="yesbtn"><i class="bi bi-heart"></i></button> &nbsp;
	                                          </c:if>
	                                          <c:if test="${yespark != 'no'}">
	                                               <button class="col btn btn-outline-secondary btn-sm rounded-pill" type="button" id="yesbtn"><i class="bi bi-heart-fill"></i></button> &nbsp;
	                                          </c:if>
	                                        </c:if>
                                    	</div>
	                                   	
	                                   	<!-- 답글 수정 목록 -->
	                                   	
	                                   	
	                                   	<c:if test="${member.isAdmin == 'S' || member.isAdmin =='M'}">
	                                   	<div class="col-md-1">
	                                   		<form action="regulardelete.do?" method="get">
	                                          <input type="text" value="${board.b_code}" name="b_code" style="display: none;">
	                                          <input type="text" value="${idx}" name="idx" style="display: none;">
	                                          
	                                          <input type="submit" class="btn btn-outline-danger btn-sm rounded-pill" value="삭제">
	                                       </form>
	                                   	</div>
	                                   	</c:if>
	                                   	<c:if test="${member.email_id == board.email_id}">
                                   		<div class="col-md-1">
                                			<form action="regular_edit.do?b_code=${board.b_code}&idx=${idx}" method="post">
	                                          <input type="text" value="${board.email_id}" name="id" style="display: none;">
	                                          <input type="text" value="${idx}" name="idx" style="display: none;">
	                                          
	                                          <input type="submit" class="btn btn-outline-secondary btn-sm rounded-pill" value="수정">
	                                       </form>
	                                   	</div>
	                                   	</c:if>
	                                   	
	                                   	<div class="col-md-1">
	                                   		<input type="button" class="btn btn-outline-secondary btn-sm rounded-pill" id="replyWrite" value="답글">
	                                   	</div>
	                                   	
                                   		<div class="col-md-1">
                                   			<input type="button" class="btn btn-outline-secondary btn-sm rounded-pill" onClick="history.go(-1)" value="목록">
	                                   	</div>
		                                       
                                    </div>
                                 </div>

                              </div>
                           </div>



                           <!-- 댓글 끝 -->
                        </div>
                     </div>
                  </div>

               
<!--					 여기까지 게시글!!					 -->
<!--					 여기까지 게시글!!					 -->
<!--					 여기까지 게시글!!					 -->





               
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
														id="idx" value="${board.idx}" type="hidden" />
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
																<c:if test='${member.email_id eq comments.email_id }'>
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
														<c:if test='${member.email_id eq comments.email_id }'>
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
               </div>


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
   
   <script type="text/javascript">
   
  
   

   
   
   </script>


</body>

</html>