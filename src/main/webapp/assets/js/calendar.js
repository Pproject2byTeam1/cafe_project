
//https://amadoo.tistory.com/89 캘린더 이벤트 관련 사이트

document.addEventListener('DOMContentLoaded', function() {

	let today = new Date();

	let year = today.getFullYear();
	let month = today.getMonth() + 1;

	let idx = "";
	
	function ReadView(){
		if (document.getElementById("add").classList.item(4) == null) {
			document.getElementById("add").className += " d-none";
		}

		if (document.getElementById("read").classList.item(4) == null) {
			document.getElementById("read").className += " d-none";
		} else {
			document.getElementById("read").classList.remove("d-none");
		}
		
		$("#comment").empty();
	}

	function AddView() {
		if (document.getElementById("read").classList.item(4) == null) {
			document.getElementById("read").className += " d-none";
		}

		if (document.getElementById("add").classList.item(4) == null) { //사라지게 하기
			document.getElementById("add").className += " d-none";
		} else {
			document.getElementById("add").classList.remove("d-none"); //나타내기
		}
	}
	
	//일정 읽기 사리게 하기
	$("#read_reset").click(function() {
		ReadView();
	});

	//일정 추가 사라지게 하기
	$("#add_reset").click(function() {
		AddView();
	});

	//일정 추가 버튼 클릭
	$("#add_btn").click(function() {

		let requestdata3 = {
			"title": $("#caltitle").val(),
			"start_date": $("#calstart_date").val(),
			"end_date": $("#calend_date").val(),
			"content": $("#calcontent").val(),
			"finish": $("#addSelect option:selected").val(),
			"email_id": "T1@naver.com",
			"nick": "치츠스콘",
			"b_code": 3
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

	//일정 읽기
	function calEventClick(info, mydateList, yesList) {
		
		console.log(mydateList);

		$('#readcaltitle').text(info.event.title);

		$(mydateList).each(function() {
			if (this.title == info.event.title) {
				
				console.log(this);

				$('#readcaltitle').text(this.content);
				$('#read-startdate').val(this.start_date);
				$('#read-enddate').val(this.end_date);
				idx = this.idx;

				if (this.finish == "F") {
					$("#readSelect").val("2").prop("disabled", true);
				} else if (this.finish == "T") {
					$("#readSelect").val("3").prop("disabled", true);
				} else {
					$("#readSelect").val("1").prop("disabled", true);
				}


				$(yesList).each(function() {
					if (this.idx == idx) {
						$("#gridCheck2").prop("checked", true);
					}
				});
			}

			const requestdata2 = { "idx": idx };

			//그 일정에 대한 댓글 불러오기
			calcomment(requestdata2);
			
			return false;
		});

		ReadView();

		//나중에 member 페이지 만들면 email_id 수정해야함
		const requestdata1 = { "email_id": "T1@naver.com", "idx": idx };

		//참석 여부
		yes(requestdata1);

	}

	//그 일정에 대한 댓글 불러오기
	function calcomment(requestdata2) {
		$.ajax({
			type: "POST",
			url: "LoadCalenderComments",
			data: requestdata2,
			dataType: "JSON",
			success: function(data) {

				console.log(data);

				$("#comment").empty();


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
	function yes(requestdata1) {
		$('#gridCheck2').click(function() {
			if ($('#gridCheck2').is(':checked')) {
				yesadd(requestdata1);
			} else {
				yesremove(requestdata1);
			}
		});
	}

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

	//나중에 member 페이지 만들면 email_id 수정해야함
	const requestdata = { "b_code": 3, "year": year, "month": month, "email_id": "T1@naver.com" };

	function loadlist() { //달력 생성
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
								AddView();
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
						alert('Clicked on : ' + info.dateStr);//클릭한 날짜



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
