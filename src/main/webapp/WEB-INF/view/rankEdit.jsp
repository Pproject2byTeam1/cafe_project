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
<script type="text/javascript">
	$(document).ready(function(){

		let size = Number("<c:out value='${size}'/>");
		let list = "<c:out value='${list}'/>";
		let max = Number("<c:out value='${maxpoint}'/>");
		
		function ajaxlist() {
			
			$.ajax({
				type: "POST",
				url: "MarketSearch",
				dataType: "TEXT",
				success: function(data){
					
					$("#ranklist").empty();
					$("#ranklist").append(data);
					
					let sizeajx = $(".size").val();
					let maxajx = $(".max").val();
					
					}
				});
		}
		

		$('#newRank').click(function addRank() {
			
			
			// table element 찾기
			const table = document.getElementById('ranklist');
			// 새 행(Row) 추가
			const newRow = table.insertRow();
		
			// 새 행(Row)에 Cell 추가
			const newCell1 = newRow.insertCell(0);
			const newCell2 = newRow.insertCell(1);
			const newCell3 = newRow.insertCell(2);
			const newCell4 = newRow.insertCell(3);
			size += 1;
			max += 1;
		
			// Cell에 텍스트 추가
			newRow.setAttribute("class" , size);
			newCell1.innerHTML = size;
			
			newCell2.innerHTML = '<input type="text" class="form-control" placeholder="" value="" id="' + size + 'name"></td>';
			newCell3.innerHTML = '<input type="text" class="form-control" placeholder="' + max + '점 이상" id="' + size + 'point">';
			newCell4.innerHTML = '<button type="button" class="saveRank btn btn btn-success" value="edit" style="float: right">저장하기</button>';

			
		});
		
		$('.editRank').click(function editRank(){
			
			let td = $(this).closest('tr').find('td');
			let td0 = td.eq(0).text();
			
			if(td0 == "1"){
				td[1].innerHTML = '<input type="text" class="form-control" id="name"><input type="hidden" class="isReg" value="false">';
			}else {
				td[1].innerHTML = '<input type="text" class="form-control" id="point"><input type="hidden" class="isReg" value="false">';
				td[2].innerHTML = '<input type="text" class="form-control" id="name"><input type="hidden" class="isReg" value="false">';
			}
			
			td[3].innerHTML = '<button type="button" class="saveRank btn btn btn-success" value="save" style="float: right">저장하기</button>';
			
		});
		
		$('.delRank').click(function delRank() {
			
			const table = document.getElementById('ranklist');
	    	  
	    	  // 행(Row) 삭제
	    	const newRow = table.deleteRow(-1);
    	
		    	
	    });
		
		$('.saveRank').click(function saveRank(){
			
			$.ajax({
				type: "POST",
				url: "RankEdit",
				dataType: "TEXT",
				success: function(data){
					
					alert("data");

					
					ajaxlist();
					
					}
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
				<form onclick="" style="cursor: pointer">
					
					<table class="table text-center" id='ranklist'>
						<thead class="thead-light">
							<tr>
								<th></th>
								<th></th>
								<th></th>
								<th>
								<button type="button" class="btn float-right btn-success"
										id="saveRank" value="save" style="float: right">저장하기</button>	
								</th>
							</tr>

							<tr>
								<th>등급레벨</th>
								<th>등급 이름</th>
								<th>등급 포인트 기준</th>
								<th>수정/삭제</th>

							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="rank" items="${list}" varStatus="status">
							
								<c:if test="${rank.rank>0}">
									<tr class="${rank.rank}">

										<td class="${rank.rank}">
										${rank.rank}
										<input type="hidden" class="r_rank" value="${rank.rank}">
										</td>
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
													${rank.r_point}
													<input type="hidden" class="r_point" value="${rank.r_point}">
												</c:otherwise>
											</c:choose>
										</td>
										<td>				
										<input type="button" class="editRank btn btn btn-secondary"
											value="수정하기" style="float: right">
										</td>

									</tr>
								</c:if>
							</c:forEach>
									
						</tbody>
					</table>
					<div style="display: inline-block; margin: 0 5px;  float: right;">
					<button type="button" class="btn btn btn-primary"
										id="newRank" value="new">추가하기</button>
					<button type="button" class="btn btn btn-danger"
										id="delRank" value="new">삭제하기</button>
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