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
  	<link href="assets/css/calendar.css" rel="stylesheet">
  	<link href="assets/css/comments.css" rel="stylesheet">
  	
  	 <!--fullcalendar css-->
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.css">
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/locales-all.js"></script>
    <script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script>
	<script src="https://unpkg.com/tippy.js@6"></script>
	
	<!-- sweetalert -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  	
  	<script type="text/javascript">
  	
  	document.addEventListener('DOMContentLoaded', function() {
  		
  		let today = new Date();

  		let idx = "";
  		
  		//일정 읽기 사리게 하기
  		$("#read_reset").click(function() {
  			ReadView();
  		});

  		//일정 추가 사라지게 하기
  		$("#add_reset").click(function() {
  			AddView();
  		});
  		
  		$("#modify_reset").click(function(){
  			ModifyView();
  		});

  		//일정 추가 버튼 클릭
  		$("#add_btn").click(function() {
  			
  			let b_code = "<c:out value='${b_code}'/>";
  	  		let email_id = "<c:out value='${member.email_id}'/>";
  	  		let nick = "<c:out value='${member.nick}'/>";
  			
  			let requestdata3 = {
  					"title": $("#caltitle").val(),
  					"start_date": $("#calstart_date").val(),
  					"end_date": $("#calend_date").val(),
  					"content": $("#calcontent").val(),
  					"finish": $("#addSelect option:selected").val(),
  					"email_id": email_id,
  					"nick": nick,
  					"b_code": b_code
  			};
  			
  			$.ajax({
  				type: "POST",
  				url: "AddCalender",
  				data: requestdata3,
  				dataType: "HTML",
  				success: function(data) {
  					
  					swal(data);

  					document.getElementById("add").className += " d-none";
  					$("#caltitle").val('');
  					$("#calstart_date").val('');
  					$("#calend_date").val('');
  					$("#calcontent").val('');
  					$('#addSelect').val("");

  					loadlist();
  				},
  				beforeSend: function(){
  					$('.wrap-load').removeClass('display-none');
  				},
  				complete: function(){
  					$('.wrap-loading').addClass('display-none');
  				}
  			});

  		});
  		
  		//일정 삭제
  		$("#deletebtn").click(function(){
  			const html = this.closest("div").closest("form").closest("div");
  			let modifyidx = $(html).find($("#read-idx")).val();
  			
  			requestdata5 = {"idx": modifyidx};
  			
  			$.ajax({
  				type: "POST",
  				url: "DeleteCalender",
  				data: requestdata5,
  				dataType: "HTML",
  				success: function(data) {
  					swal(data);
  					ReadView();
  					loadlist();
  				}
  			});
  		});
  		
  		//일정 수정
  		$("#modifybtn").click(function(){
  			
  			ModifyView();
  			
  			const html = this.closest("div").closest("form").closest("div");
  			
  			let sel = $(html).find($("#readSelect option:selected")).val();
  			
  			if(sel == 3){
  				$("#modifySelect").val("3").prop("selected", true);
  			}else if(sel == 2){
  				$("#modifySelect").val("2").prop("selected", true);
  			}else{
  				$("#modifySelect").val("1").prop("selected", true);
  			}
  			
  			$("#modifytitle").val($(html).find($("#readcaltitle")).html());
  			$("#modifystart_date").val($(html).find($("#read-startdate")).val());
  			$("#modifyend_date").val($(html).find($("#read-enddate")).val());
  			$("#modifycontent").val($(html).find($("#readcalcontent")).html());
  			
  			$('#modify_btn').click(function(){
  				let modifyidx = $(html).find($("#read-idx")).val()
  				
  				modifyCal(modifyidx);
  				
  				setTimeout(function() {
  					loadlist();
  				}, 700);
  				
  			});
  			
  		});
  		
  		function loadlist() { //달력 생성
  			let b_code = "<c:out value='${b_code}'/>";
  	  		let member = "<c:out value='${member}'/>";
  			
  			const requestdata = { "b_code": b_code, "email_id": member.email_id};
  			
			$.ajax({
				type: "POST",
				url: "CalendarList",
				data: requestdata,
				dataType: "JSON",
				success: function(data) {

					let datelist = new Array();

					$(data[0]).each(function() {
						const date = { title: this.title, start: this.start_date, end: this.end_date };

						datelist.push(date);
					});

					let calendarEl = document.getElementById('calendar');

					let calendar = new FullCalendar.Calendar(calendarEl, {
						customButtons: {
							parkCustomButton: {
								text: '추가',
								click: function() {	//일정 추가
									if(member != null && member != ""){
										AddView();
									}else{
										swal('로그인이 필요한 기능입니다.');
									}
									
								}
							}
						},
						themeSystem: 'bootstrap5',
						initialView: 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
						headerToolbar: { // 헤더에 표시할 툴 바
							start: 'parkCustomButton today',
							center: 'title',
							end: 'prev,next dayGridMonth,dayGridWeek,dayGridDay'
						},
						titleFormat: function(date) {
							return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
						},
						dateClick: function(info) {

							AddView();
							$("#calstart_date").val(info.dateStr);
							$("#calend_date").val(info.dateStr);

						},
						nowIndicator: true, // 현재 시간 마크
						locale: 'ko', // 한국어 설정
						dayMaxEventRows: true,
						events: datelist,
						eventClick: function(info) {//이벤트 클릭시 
							calEventClick(info, data[0], data[1]);
						},
					});

					calendar.render();

				},
				error: function() {
					alert("error");
				}, 
				beforeSend: function(){
					$('.wrap-load').removeClass('display-none');
				},
				complete: function(){
					$('.wrap-loading').addClass('display-none');
				}
			});
		}
  		
  		loadlist();
  	});
  	
  		//해당 일정의 참석 인원 얻기
  		function getCountYes(idx){
  			$.ajax({
  				type: "POST",
				url : "GetCountYes",
				data : {"idx": idx},
				dataType : "html",
				success : function(data) {
					$('#yescount').text('');
  					$('#yescount').text(data + "  : 참석인원");
				}
			});
  		}
  	
  		
  		//일정 읽기
  		function calEventClick(info, mydateList, yesList) {
  			
  			let b_code = "<c:out value='${b_code}'/>";
  	  		let member = "<c:out value='${member.email_id}'/>";

  			$('#readcaltitle').text(info.event.title);

  			$(mydateList).each(function() {
  				if (this.title == info.event.title) {
  					
  					$('#readcalcontent').text(this.content);
  					$('#read-startdate').val(this.start_date);
  					$('#read-enddate').val(this.end_date);
  					
  					idx = this.idx;
  					
  					let realidx = this.idx;
  					
  					getCountYes(realidx);
  					
  					$('#read-idx').val(idx);

  					if (this.finish == "F") {
  						$("#readSelect").val("2").prop("disabled", true);
  					} else if (this.finish == "T") {
  						$("#readSelect").val("3").prop("disabled", true);
  					} else {
  						$("#readSelect").val("1").prop("disabled", true);
  					}

  					if(member == this.email_id){
  						$("#modifybtn").removeClass('visually-hidden');
  						$("#deletebtn").removeClass("visually-hidden");
  					}
  					
  					$(yesList).each(function() {
  						if (this.idx == realidx) {
  							$("#gridCheck2").prop("checked", true);
  						}
  					});
  	  				const requestdata2 = { "idx": idx };
  	  			
  	  				//그 일정에 대한 댓글 불러오기
  	  				calcomment(requestdata2);
  				}
  				
  			});

  			ReadView();

  			//참석 여부
  			yes();

  			/* 댓글시작 */
  			list();
  			
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
  			/* 대댓글 삭제 버튼 클릭 */
			$(document).on('click', '#replydel2', function(){
				const tag2 = this.closest("div");
				
				const data2 = {"idx": $(tag2).children("#co_idx2").val()};
				
				
				
				del(data2);
				
			});
  			
			let replyreplytag = "";
			
			/* 대댓글 작성 버튼 클릭 */
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
  		}
  		
  		/* 댓글 생성 */
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
  		/* 댓글 삭제 */
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
  		/* 댓글 목록 불러오기 */
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
  		/* 대댓글 작성 */
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
  		
  		//그 일정에 대한 댓글 불러오기
  		function calcomment(requestdata2) {
  			$.ajax({
  				type: "POST",
  				url: "LoadCalenderComments",
  				data: requestdata2,
  				dataType: "JSON",
  				success: function(data) {

  					if(data == null){
  					
  					}else{
  						$(data).each(function() {
  							html = '<div class="row mb-5">';
  							html += '<img id="profile" class="col-3 rounded-circle" src="image/rank_icon/1.gif" alt="Profile">';
  							html += '<div class="col row">';
  							html += this.nick + ' (' + this.w_date + ')';
  							html += '<p class="text-justify">' + this.content + '</p>';
  							html += '</div>';
  							html += '</div>';
  		
  							$("#comment").append(html);
  						});	
  					}

  				}, 
  				beforeSend: function(){
  					$('.wrap-loading').removeClass('d-none');
  				},
  				complete: function(){
  					$('.wrap-loading').addClass('d-none');
  				}
  			});
  		}
  		
  		//참석 여부
  		function yes() {
  			let b_code = "<c:out value='${b_code}'/>";
  	  		let member = "<c:out value='${member.email_id}'/>";
  			
  			const requestdata1 = { "email_id": member, "idx": idx };
  			
  		
  			
  			$(document).on('click', '#gridCheck2', function(){
  				if ($('#gridCheck2').is(':checked')) {
  					yesadd(requestdata1);
  				} else {
  					yesremove(requestdata1);
  				}
  			});
  			
  		}
  		//참석 지우기
  		function yesremove(requestdata1) {
			$.ajax({
				type: "POST",
				url: "YesRemove",
				data: requestdata1,
				dataType: "HTML",
				success: function(data) {
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
  		//참석 하기
  		function yesadd(requestdata1) {
  			$.ajax({
  				type: "POST",
  				url: "Yes",
  				data: requestdata1,
  				dataType: "HTML",
  				success: function(data) {
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
  		
  		//일정 수정
  		function modifyCal(modifyidx){
  			
  			let b_code = "<c:out value='${b_code}'/>";
  	  		let member = "<c:out value='${member}'/>";
  			
  			let requestdata4 = {
  						"idx" : modifyidx,
	  					"title": $("#modifytitle").val(),
	  					"start_date": $("#modifystart_date").val(),
	  					"end_date": $("#modifyend_date").val(),
	  					"content": $("#modifycontent").val(),
	  					"finish": $("#modifycontent option:selected").text(),
	  					"b_code": b_code
	  		};
  			  
			$.ajax({
				type: "POST",
				url: "ModifyCalender",
				data: requestdata4,
				dataType: "HTML",
				success: function(data){
					swal(data);
					ModifyView();
				},
				beforeSend: function(){
  					$('.wrap-load').removeClass('display-none');
  				},
  				complete: function(){
  					$('.wrap-loading').addClass('display-none');
  				}
			});
		}
  	
  		//일정 추가 보이게 하기
  		function AddView() {
  			if (document.getElementById("read").classList.item(4) == null) {
  				document.getElementById("read").className += " d-none";
  			}
  			if (document.getElementById("modify").classList.item(4) == null) {
  				document.getElementById("modify").className += " d-none";
  			}

  			if (document.getElementById("add").classList.item(4) == null) { //사라지게 하기
  				document.getElementById("add").className += " d-none";
  			} else {
  				document.getElementById("add").classList.remove("d-none"); //나타내기
  			}
  		}
  		
  		//일정 읽기 보이게 하기
  		function ReadView(){
  			if (document.getElementById("add").classList.item(4) == null) {
  				document.getElementById("add").className += " d-none";
  			}
  			if (document.getElementById("modify").classList.item(4) == null) {
  				document.getElementById("modify").className += " d-none";
  			}

  			if (document.getElementById("read").classList.item(4) == null) {
  				document.getElementById("read").className += " d-none";
  			} else {
  				document.getElementById("read").classList.remove("d-none");
  			}
  			
  			$("#comment").empty();
  		}
  		//수정 보이게 하기
  		function ModifyView(){
  			if (document.getElementById("add").classList.item(4) == null) {
  				document.getElementById("add").className += " d-none";
  			}
  			if (document.getElementById("read").classList.item(4) == null) {
  				document.getElementById("read").className += " d-none";
  			}

  			if (document.getElementById("modify").classList.item(4) == null) {
  				document.getElementById("modify").className += " d-none";
  			} else {
  				document.getElementById("modify").classList.remove("d-none");
  			}
  			
  			
  		}
  	
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
  		<div class="park-card">
  			<div class="park-card-body">
  			
				<div class="pagetitle">
					<h1>Calendar</h1>
					<!-- 게시판 이름 끌고오기 b_name -->
					<nav>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="cafemain.do">Home</a></li>
							<c:forEach var="boardinfo" items="infolist">
								<li class="breadcrumb-item active">Calendar</li>
							</c:forEach>
						</ol>
					</nav>
				</div>

				<div class="container">
					<div class="row">
						<div id="calendar" class="col mb-5"></div>

						<div class="wrap-loading d-none">
							<div>
								<img src="assets/img/loading1.gif" />
							</div>
						</div>

						<!-- 일정 추가 -->
						<div id="add" class="mt-10 col-md-6 park-card p-4 d-none">
							<div class="park-card-body row">
								<div class="card">
									<div class="card-body">

										<div class="row mt-2">
											<h4 class="col card-title">
												<strong>일정 추가</strong>
											</h4>
											<div class="col mt-3">
												<select class="form-select" id="addSelect">
													<option selected>Not Started</option>
													<option value="1">In progress</option>
													<option value="2">Done</option>
												</select>
											</div>
										</div>

										<!-- Floating Labels Form -->
										<form class="row g-3 mt-2">
											<div class="col-md-12">
												<div class="form-floating">
													<input type="text" class="form-control" id="caltitle"
														placeholder="Title"> <label for="floatingName">일정</label>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-floating">
													<div class="row mb-3">
														<label for="inputDate" class="col-sm-4 col-form-label">시작일</label>
														<div class="col-sm-8">
															<input type="date" id="calstart_date"
																class="form-control">
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-floating">
													<div class="row mb-3">
														<label for="inputDate" class="col-sm-4 col-form-label">종료일</label>
														<div class="col-sm-8">
															<input type="date" id="calend_date" class="form-control">
														</div>
													</div>
												</div>
											</div>
											<div class="col-12">
												<div class="form-floating">
													<textarea class="form-control" placeholder="Content"
														id="calcontent" style="height: 100px;"></textarea>
													<label for="floatingTextarea">상세 일정</label>
												</div>
											</div>
											<div class="text-center">
												<c:if test="${member != null}">
													<button type="button" id="add_btn" class="btn btn-primary">확인</button>
												</c:if>
												<button type="reset" id="add_reset"
													class="btn btn-secondary">취소</button>
											</div>
										</form>
										<!-- End floating Labels Form -->

									</div>
								</div>
							</div>
						</div>
						
						<!-- 일정 수정 -->
						<div id="modify" class="mt-10 col-md-6 park-card p-4 d-none">
							<div class="park-card-body row">
								<div class="card">
									<div class="card-body">

										<div class="row mt-2">
											<h4 class="col card-title">
												<strong>일정 수정</strong>
											</h4>
											<div class="col mt-3">
												<select class="form-select" id="modifySelect">
													<option value="1" selected>Not Started</option>
													<option value="2">In progress</option>
													<option value="3">Done</option>
												</select>
											</div>
										</div>

										<!-- Floating Labels Form -->
										<form class="row g-3 mt-2">
											<div class="col-md-12">
												<div class="form-floating">
													<input type="text" class="form-control" id="modifytitle"
														placeholder="Title"> <label for="floatingName">일정</label>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-floating">
													<div class="row mb-3">
														<label for="inputDate" class="col-sm-4 col-form-label">시작일</label>
														<div class="col-sm-8">
															<input type="date" id="modifystart_date"
																class="form-control">
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-floating">
													<div class="row mb-3">
														<label for="inputDate" class="col-sm-4 col-form-label">종료일</label>
														<div class="col-sm-8">
															<input type="date" id="modifyend_date" class="form-control">
														</div>
													</div>
												</div>
											</div>
											<div class="col-12">
												<div class="form-floating">
													<textarea class="form-control" placeholder="Content"
														id="modifycontent" style="height: 100px;"></textarea>
													<label for="floatingTextarea">상세 일정</label>
												</div>
											</div>
											<div class="text-center">
												<button type="button" id="modify_btn" class="btn btn-primary">확인</button>
												<button type="reset" id="modify_reset"
													class="btn btn-secondary">취소</button>
											</div>
										</form>
										<!-- End floating Labels Form -->

									</div>
								</div>
							</div>
						</div>

						<!-- 일정 내용 보기 -->
						<div id="read" class="mt-10 col-md-6 park-card p-4 d-none">
							<div class="park-card-body row">
								<div class="card">
									<div class="card-body">

										<div class="row mt-2">
											<h4 class="col card-title">
												<strong>일정</strong>
											</h4>
											<div class="col mt-3">
												<select class="form-select" id="readSelect">
													<option value="1">Not Started</option>
													<option value="2">In progress</option>
													<option value="3">Done</option>
												</select>
											</div>
										</div>

										<!-- Floating Labels Form -->
										<form class="row g-3 mt-2">
											<div class="col-md-12">
												<div id="titlediv" class="form-floating">
													<h4 id="readcaltitle"></h4>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-floating">
													<div class="row mb-3">
														<label for="inputDate" class="col-sm-4 col-form-label">시작일</label>
														<div class="col-sm-8">
															<input type="date" id="read-startdate"
																class="form-control" readonly>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-floating">
													<div class="row mb-3">
														<label for="inputDate" class="col-sm-4 col-form-label">종료일</label>
														<div class="col-sm-8">
															<input type="date" id="read-enddate" class="form-control"
																readonly>
															<input id="read-idx" type="hidden" />
														</div>
													</div>
												</div>
											</div>
											<div class="col-12">
												<div class="form-floating">
													<p id="readcalcontent"></p>
												</div>
											</div>
											<div class="col-md-4"></div>
											<div class="col-md-4"></div>
											<div class="col-md-4">
												<c:if test="${member != null}">
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														id="gridCheck2"> <label class="form-check-label"
														for="gridCheck2">참석 여부</label>
												</div>
												<div id="yescount"></div>
												</c:if>
											</div>
											<div class="text-center">
												<c:if test="${member != null}">
													<button type="button" id="modifybtn" class="btn btn-primary visually-hidden">수정</button>
													<button type="button" id="deletebtn" class="btn btn-warning visually-hidden">삭제</button>
												</c:if>
												<button type="reset" id="read_reset"
													class="btn btn-secondary">닫기</button>
											</div>
										</form>
										<!-- End floating Labels Form -->

									</div>
								</div>
							</div>


							<!-- 댓글 -->
							
							<div class="row mx-1">

								<section class="section dashboard">
									<!-- columns -->

									<!-- 댓글 카드 섹션 -->
									<div class=" ccard">
										<div class="comment-write">
											<h5 class="card-title">댓글</h5>

											<!-- 작성란 -->
											<div class="row">
												<div class="col-9 form-floating">
													<textarea id="replycontent" class="form-control"></textarea>
													<label for="floatingTextarea">댓글을 작성해 주세요</label> <input
														id="idx" value="${imgboard.idx}" type="hidden" />
												</div>
												<div class="col-3">
													<button type="button" id="replywritebtn"
														class="btn btn-outline-secondary btn-sm rounded-pill">작성하기</button>
												</div>
											</div>

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
								</section>
							</div>


						</div>
					</div>
				</div>

			</div>
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