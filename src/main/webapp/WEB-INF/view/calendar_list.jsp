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

  		let year = today.getFullYear();
  		let month = today.getMonth() + 1;

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
  					console.log(data);
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
  			
  			const requestdata = { "b_code": b_code, "year": year, "month": month, "email_id": member.email_id};
  			
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
						selectable: true, // 달력 일자 드래그 설정가능
						unselectAuto: true, //드래그 후 다른 곳 클릭 시 드래그 지우기
						droppable: true,
						editable: true,
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
  						if (this.idx == idx) {
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
  						console.log("데이터 없음");
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
  	  		let member = "<c:out value='${member}'/>";
  			
  			const requestdata1 = { "email_id": member.email_id, "idx": idx };
  			
  			$('#gridCheck2').click(function() {
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
				dataType: "JSON",
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
							<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
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

								<!-- 댓글 -->
								<div class="card">
									<div class="card-body row ms-2 mt-2">
										<h5 class="card-title">댓글</h5>
										<div id="comment"></div>

									</div>
								</div>
								<!-- 댓글 끝 -->

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