<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
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

<!-- 경고창 이쁜거 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){

		let size = Number("<c:out value='${size}'/>");
		let list = "<c:out value='${list}'/>";
		let max = Number("<c:out value='${maxpoint}'/>");
		
		function ajaxlist() {
			
			$.ajax({
				type: "POST",
				url: "RankEditList",
				dataType: "TEXT",
				success: function(data){
					
					$("#ranklist").empty();
					$("#ranklist").append(data);
					
					let size = Number($(".size").val());
					let max = Number($(".max").val());
					
					}
				});
		};
		
		//신규 랭크 등록 기능 활성화
		$('#newRank').click(function newRank() {
			
			let check = $('tr:eq(' + (size+2) + ')>td:eq(3) input').val();

			if((check == "저장") || check == "등록"){
				alert("추가할 수 없습니다. 저장 또는 등록을 마무리해주세요.");
			}else{
				
			// table element 찾기
			const table = document.getElementById('ranklist');
			// 새 행(Row) 추가
			const newRow = table.insertRow();
		
			// 새 행(Row)에 Cell 추가
			const newCell1 = newRow.insertCell(0);
			const newCell2 = newRow.insertCell(1);
			const newCell3 = newRow.insertCell(2);
			const newCell4 = newRow.insertCell(3);
		
			
			// Cell에 텍스트 추가
			newRow.setAttribute("class" , size+1);
			newCell1.innerHTML = size+1
			
			newCell2.innerHTML = '<input type="text" class="form-control" placeholder="" value="" id="name"></td>';
			newCell3.innerHTML = '<input type="text" class="form-control" placeholder="" value="" id="point">';
			newCell4.innerHTML = '<input type="button" class="addRank btn btn btn-success" value="등록" style="float: right">';
			
			}	
		});
		
		//기존 랭크 수정 기능 활성화
		$(document).on('click', '.editRank', function(){
			let td = $(this).closest('tr').find('td');
			let td0 = td.eq(0).text();
			let editpoint = td.eq(2).text();

			if(td0 == "1"){
				td[1].innerHTML = '<input type="text" class="form-control" id="name" value="" required><input type="hidden" class="r_name" value="false">';
			}else {
				td[1].innerHTML = '<input type="text" class="form-control" id="name" value="" required><input type="hidden" class="r_name" value="false">';
				td[2].innerHTML = '<input type="number" class="form-control" id="point" value="" required><input type="hidden" class="r_point" value="false">';
			}
			
			td[3].innerHTML = '<input type="button" class="updateRank btn btn btn-success" value="저장" style="float: right">'; 
			
		});
		
		//기존 랭크 삭제
		$(document).on('click', '.delRank', function() {
			
			let check = $('tr:eq(' + (size+1) + ')>td:eq(3) input').val();
			let rank = $('tr:eq(' + (size+1) + ')>td:eq(0)').text();

			if((check == "저장") || check == "등록"){
				alert("삭제할 수 없습니다. 저장 또는 등록을 마무리해주세요.");
			}else{
				
			Swal.fire({
				   title: '마지막 등급을 삭제하시겠습니까?',
				   text: '다시 되돌릴 수 없습니다. 신중하세요.',
				   icon: 'warning',
				   
				   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
				   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
				   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
				   confirmButtonText: '네', // confirm 버튼 텍스트 지정
				   cancelButtonText: '취소', // cancel 버튼 텍스트 지정
				   
				   reverseButtons: true, // 버튼 순서 거꾸로
				   
				}).then(result => {
				   // 만약 Promise리턴을 받으면,
				   if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
				   	 
					  $.ajax({
							type: "POST",
							url: "RankDelete",
							data : {"rank": rank},
							dataType: "TEXT",
							success: function(data){
								
								Swal.fire( data, '', 'success');
								alert(data);

								ajaxlist();
								
								const table = document.getElementById('ranklist');
								const newRow = table.deleteRow('-1');
								
								}
					
					  });

			    	
				   }
				});
			} 
	    });
		
		
		//기존 랭크 수정 완료
		$(document).on('click', '.updateRank', function () {
			
			let td = $(this).closest('tr').find('td');
			let rank = td.eq(0).text();
			let r_name = $('#name').val();
			let r_point = $('#point').val();
						
			 var requestdata = {"rank": rank, "r_name": r_name, "r_point" : r_point};
			
			 Swal.fire({
				   title: '저장하시겠습니까?',
				   text: rank + ' 순위 ' + r_name + '의 점수를 ' + r_point + '로 수정합니다',
				   icon: 'warning',
				   
				   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
				   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
				   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
				   confirmButtonText: '네', // confirm 버튼 텍스트 지정
				   cancelButtonText: '취소', // cancel 버튼 텍스트 지정
				   
				   reverseButtons: true, // 버튼 순서 거꾸로
				   
				}).then(result => {
					if (result.isConfirmed) {
						$.ajax({
							type: "POST",
							url: "RankEdit",
							data: requestdata,
							dataType: "TEXT",
							success: function(data){
								
								alert(data);

								}
					
							});
					}
						
				ajaxlist();
			
			});
			 
		});
				
		$(document).on('click', '.addRank', function () {
			
			let td = $(this).closest('tr').find('td');
			let rank = td.eq(0).text();
			let r_name = $('#name').val();
			let r_point = $('#point').val();
						
			 var requestdata = {"rank": rank, "r_name": r_name, "r_point" : r_point};
			
			 Swal.fire({
				   title: '새로운 등급을 등록하시겠습니까?',
				   text: rank + ' 순위 ' + r_name + '의 점수를 ' + r_point + '로 등록합니다',
				   icon: 'warning',
				   
				   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
				   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
				   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
				   confirmButtonText: '네', // confirm 버튼 텍스트 지정
				   cancelButtonText: '취소', // cancel 버튼 텍스트 지정
				   
				   reverseButtons: true, // 버튼 순서 거꾸로
				   
				}).then(result => {
					if (result.isConfirmed) {
						$.ajax({
							type: "POST",
							url: "RankAdd",
							data: requestdata,
							dataType: "TEXT",
							success: function(data){
								
								alert(data);

								}
					
							});
					}
						
				ajaxlist();
			
			});
			 
		});
		
		
	});
</script>
</head>
<body>
	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">
		<jsp:include page="/common/top.jsp"></jsp:include>
	</header>
	<!-- End Header -->
	<!-- ======= Sidebar ======= -->
	<jsp:include page="/common/side.jsp"></jsp:include>
	<!-- End Sidebar -->


	<main id="main" class="main">


		<div class="pagetitle">
			<h1>등급관리</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="cafemain.do">카페관리</a></li>
					<li class="breadcrumb-item active">등급관리</li>
				</ol>
			</nav>
		</div>



		<div class="container">
			<div class="container-fluid">
				<div class="card">
				<div class="card-body">
				
				<p><p><p><p><p>
					<h3>등급 관리</h3>
				
				<form onclick="" style="cursor: pointer">
					<table class="table text-center" id='ranklist'>
						<thead class="thead-light">
							<tr>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
							</tr>

							<tr>
								<th>등급레벨</th>
								<th>등급 이름</th>
								<th>등급 포인트 기준</th>
								<th>수정/저장</th>

							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="rank" items="${list}" varStatus="status">
							
								<c:if test="${rank.rank>0}">
									<tr class="${rank.rank}">

										<td class="${rank.rank}">${rank.rank}<input type="hidden" class="r_rank" value="${rank.rank}"></td>
										<td>
										${rank.r_name}
										<input type="hidden" class="r_name" value="${rank.r_name}">
										</td>
										<td>
											<c:choose>
												<c:when test="${rank.rank == 1}">
														0 점 이상
												      </c:when>

												<c:otherwise>
													${rank.r_point} 점 이상
													<input type="hidden" class="r_point" value="${rank.r_point}">
												</c:otherwise>
											</c:choose>
										</td>
										<td>				
										<input type="button" class="editRank btn btn btn-secondary"
											value="수정" style="float: right">
										</td>

									</tr>
								</c:if>
							</c:forEach>
									
						</tbody>
					</table>
					<div style="display: inline-block; margin: 0 5px;  float: right;">
					<button type="button" class="btn btn btn-primary"
										id="newRank" value="new">추가하기</button>
					<button type="button" class="delRank btn btn btn-danger"
										id="delRank" value="del">삭제하기</button>
					</div>		
				</form>
				</div>
				</div>
			</div>

		</div>

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