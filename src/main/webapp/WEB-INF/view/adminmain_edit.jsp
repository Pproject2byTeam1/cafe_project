<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>카페人중독</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<!-- bootstrab -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
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
<link href="assets/css/marketboard_read.css" rel="stylesheet">
<link href="assets/css/draganddropfile.css" rel="stylesheet">

<!-- sweetalert -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<style type="text/css">
.maincard {
	background-color: white;
	margin-bottom: 30px;
	border-radius: 1em;
	box-shadow: 0px 0 30px rgba(1, 41, 112, 0.1);
	padding: 15px;
}

#wrap1::after {
	content: "";
	display: block;
	clear: both;
}

.imgbox {
	position: relative;
	width: 100%;
	height: 200px;
	background-color: #e0e0e0;
}

.imgbox.wide {
	width: 100%;
	height: 200px;
}

.ex {
	margin: 20px 0;
	display: flex;
	flex-wrap: wrap;
	flex-direction: row;
}

.ex-imgbox {
	margin-right: 20px;
	margin-bottom: 20px;
}

.ex-imgbox:last-child {
	margin: 0;
}

@media ( max-width : 100%;) {
	.flex {
		flex-direction: column;
	}
}
</style>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

<script type="text/javascript">
  	
 	$(function(){
 		
 		if(${member.isAdmin != 'M'}){
 			location.href="login_view.do";
 		}
 		
 		/* 배너 이미지 파일 드래그앤 드랍 업로드 시작 */
 		
 		var input = document.getElementById("input");
 		var initLabel = document.getElementById("label");

 		input.addEventListener("change", (event) => {
 			const files = changeEvent(event);
 		  	handleUpdate(files);
 		});

 		initLabel.addEventListener("mouseover", (event) => {
 		  	event.preventDefault();
 		  	const label = document.getElementById("label");
 		  	label?.classList.add("label--hover");
 		});

 		initLabel.addEventListener("mouseout", (event) => {
 		  	event.preventDefault();
 		  	const label = document.getElementById("label");
 		  	label?.classList.remove("label--hover");
 		});

 		document.addEventListener("dragenter", (event) => {
 		  	event.preventDefault();
 		  	console.log("dragenter");
 		  	if (event.target.className === "inner") {
 		    	event.target.style.background = "#616161";
 		  	}
 		});

 		document.addEventListener("dragover", (event) => {
 		  	console.log("dragover");
 		  	event.preventDefault();
 		});

 		document.addEventListener("dragleave", (event) => {
 		  	event.preventDefault();
 		  	console.log("dragleave");
 		  	if (event.target.className === "inner") {
 		    	event.target.style.background = "#3a3a3a";
 		  	}
 		});

 		document.addEventListener("drop", (event) => {
 		  	event.preventDefault();
 		  	console.log("drop");
 		  	if (event.target.className === "inner") {
 		  		const files = event.dataTransfer?.files;
 		    	event.target.style.background = "#3a3a3a";
 		    	handleUpdate([...files]);
 		  	}
 		});

 		function changeEvent(event){
 		  	const { target } = event;
 		  	return [...target.files];
 		};

 		function handleUpdate(fileList){
 			  const preview = document.getElementById("preview");

 			  fileList.forEach((file) => {
 			    	const reader = new FileReader();
 			    	reader.addEventListener("load", (event) => {
 			      		const img = el("img", {
 			        		className: "embed-img",
 			        		src: event.target?.result,
 			      		});
 			      		const imgContainer = el("div", { className: "container-img" }, img);
 			      		$("#preview").empty();
 			      		preview.append(imgContainer);
 			    	});
 			 		reader.readAsDataURL(file);
 			 });
 			  
 			 bannerUpload(fileList[0].name);
 		};
 		
 		function bannerUpload(name){
 			console.log(name);
 		
 			$.ajax({
 		    	type: "POST",
 		    	url: "BannerUpload",
 		    	data: {"name": name },
 		    	dataType: "HTML",
 		    	success: function(data){
 		    		console.log(data);
 		    		swal(data);
 		    	}
 		    });
 		}

 		function el(nodeName, attributes, ...children) {
 		  	const node =
 		    	nodeName === "fragment"
 		      			? document.createDocumentFragment()
 		      				: document.createElement(nodeName);

 		  	Object.entries(attributes).forEach(([key, value]) => {
 		    	if (key === "events") {
 		      		Object.entries(value).forEach(([type, listener]) => {
 		        		node.addEventListener(type, listener);
 		      		});
 		    	} else if (key in node) {
 		      		try {
 		        		node[key] = value;
 		      		} catch (err) {
 		        		node.setAttribute(key, value);
 		      		}
 		    	} else {
 		      		node.setAttribute(key, value);
 		    	}
 		  	});

 		  	children.forEach((childNode) => {
 		    	if (typeof childNode === "string") {
 		      		node.appendChild(document.createTextNode(childNode));
 		    	} else {
 		      		node.appendChild(childNode);
 		    	}
 		  	});

 		  	return node;
 			}
 		
 		/* 배너 이미지 파일 드래그앤 드랍 업로드 끝 */
 		
 		/* 게시판 출력해주기 시작 */
 		
 		function boardload(){
 			let html1 = "";
 			for(let i=0; i<${main1.main1}.length; i++){
 				html1 += "<tr>";
 					html1 += "<td>" + ${main1.main1}[i].title + "</td>";
 					html1 += "<td>" + ${main1.main1}[i].nick + "</td>";
 					html1 += "<td>" + ${main1.main1}[i].w_date + "</td>";
 				html1 += "</tr>";
 			}	
 			
 			$("#boardlist1").empty();
 			$("#boardlist1").append(html1);
 			
 			let html2 = "";
 			for(let i=0; i<${main2.main2}.length; i++){
 				html2 += "<tr>";
 					html2 += "<td>" + ${main2.main2}[i].title + "</td>";
 					html2 += "<td>" + ${main2.main2}[i].nick + "</td>";
 					html2 += "<td>" + ${main2.main2}[i].w_date + "</td>";
 				html2 += "</tr>";
 			}	
 			
 			$("#boardlist2").empty();
 			$("#boardlist2").append(html2);
 			
 			let html3 = "";
 			for(let i=0; i<${main3.main3}.length; i++){
 				html3 += "<tr>";
 					html3 += "<td>" + ${main3.main3}[i].title + "</td>";
 					html3 += "<td>" + ${main3.main3}[i].nick + "</td>";
 					html3 += "<td>" + ${main3.main3}[i].w_date + "</td>";
 				html3 += "</tr>";
 			}	
 			
 			$("#boardlist3").empty();
 			$("#boardlist3").append(html3);
 			
 			let html4 = "";
 			for(let i=0; i<${main4.main4}.length; i++){
 				html4 += "<tr>";
 					html4 += "<td>" + ${main4.main4}[i].title + "</td>";
 					html4 += "<td>" + ${main4.main4}[i].nick + "</td>";
 					html4 += "<td>" + ${main4.main4}[i].w_date + "</td>";
 				html4 += "</tr>";
 			}	
 			
 			$("#boardlist4").empty();
 			$("#boardlist4").append(html4);
 			
 		}
 		
 		boardload();
 		
 		$("#boardselect1").change(function(){
 			let selected = $("#boardselect1 option:selected").text();
 			let selectedvalue = $("#boardselect1 option:selected").val();
 			let index = 1;
 			mainindexupload(selectedvalue, index);
 		});
 			
		$("#boardselect2").change(function(){
			let selected = $("#boardselect2 option:selected").text();
 			let selectedvalue = $("#boardselect2 option:selected").val();
 			let index = 2;
 			mainindexupload(selectedvalue, index);
 		});
		
		$("#boardselect3").change(function(){
			let selected = $("#boardselect3 option:selected").text();
 			let selectedvalue = $("#boardselect3 option:selected").val();
 			let index = 3;
 			mainindexupload(selectedvalue, index);
 		});
 		
		$("#boardselect4").change(function(){
			let selected = $("#boardselect4 option:selected").text();
 			let selectedvalue = $("#boardselect4 option:selected").val();
 			let index = 4;
 			mainindexupload(selectedvalue, index);
		});
		
		function mainindexupload(selectedvalue, index){
			$.ajax(function(){
				type: "POST",
 		    	url: "MainIndexUpload",
 		    	data: {"main_idx": index, "b_code": selectedvalue},
 		    	dataType: "HTML",
 		    	success: function(data){
 		    		console.log(data);
 		    		swal(data);
 		    	}
			});
		}
 		
 		/* 게시판 출력해주기 시작 */
 		
 	});
  	
  	
 </script>

</head>

<body class="toggle-sidebar">

	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">
		<c:import url="/WEB-INF/view/common/admintop.jsp" />
	</header>
	<!-- End Header -->

	<main id="main" class="main">

		<div class="pagetitle"></div>
		<!-- End Page Title -->

		<!-- 관리자 메인페이지 관리 시작 -->


		<div class="row">
			<div class="col-md-12">
				<div align="right" class="col-md-12">
					<button type="submit" id="edit"
						class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
					<button type="button" id="cancel"
						class="btn btn-outline-secondary btn-sm rounded-pill">취소</button>
					<hr>
					<!-- 본인확인 -->

				</div>
			</div>
			<form name="bbs" action=".do" method="POST"
				enctype="multipart/form-data">
				<div class="container-fluid">
					<div class="wrap1">
						<div class="col-lg-12">
							<div class="maincard">
								<div class="row">
									<div class="col-md-5">
										<div>
											<a class="logo d-flex align-items-center"> <img
												src="assets/img/logo.png" alt=""> <span id="cafename"
												class=""><input type="text" name="cafe_name"
													id="inputname" value="${cafebanner.cafe_name}" rows="1"
													cols="10"></input></span>
											</a> <span id="size"></span>
										</div>
									</div>

									<div class="col-md-7">
										<div class="row">
											<label for="form-control" class="col">아이콘 :
												${cafebanner.cafe_icon} <input type="file" name="cafe_icon"
												class="form-control" placeholder="카페 아이콘" accept="image/*"
												id="getfile" onchange="seticon(event)" required>
											</label> <label for="form-control" class="col">메인 배너 :
												${cafebanner.cafe_img} <input type="file" name="cafe_img"
												class="form-control" placeholder="카페 메인 배너" accept="image/*"
												id="getfile" onchange="setBanner(event)" required>
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- 게시판 리스트 기능 -->
					<div class="row">
						<div class="col-md-4">
							<div class="maincard">
								<div class="row">
									<div align="center" class="col">
										<button type="button" id="add_btn"
											class="btn btn-outline-secondary  rounded-pill">게시판
											추가</button>
										<button type="button" id="del"
											class="btn btn-outline-secondary  rounded-pill">휴지통</button>
									</div>
								</div>
							</div>
							<div class="maincard">
								<c:forEach var="infolist" items="${infolist}">
									<div class="maincard">${infolist.b_name}</div>
								</c:forEach>
							</div>
						</div>
						<div class="col-md-8">
							<div class="maincard">
								<div class="col-md-12">
									<!-- 배너 이미지 미리보기 -->
									<label class="label" id="label" for="input">
										<div class="inner" id="inner">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
											&emsp;&emsp;&emsp;&emsp;&emsp;&ensp; 드래그하거나 클릭해서 업로드</div>
									</label> <input id="input" class="input" accept="image/*" type="file"
										required="true" hidden="true">
								</div>
								<div class="col-md-12">
									<p class="preview-title"></p>
									<div class="preview" id="preview">
										<img width="200" src="upload/${cafebanner.cafe_img}">
									</div>
								</div>
								<div class="col-md-12">
									<hr>
									<textarea rows="" cols=""></textarea>
								</div>

							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="maincard">
										<select id="boardselect1" class="form-select" aria-label="Default select example">
											<option selected>게시판 선택</option>
											<c:forEach var="infoname" items="${infolist}">
												<option value="${infoname.b_code}">${infoname.b_name}</option>
											</c:forEach>
										</select>
										<table class="table table-striped" style="text-align: center;">
											<thead>
												<tr>
													<th scope="col">제목</th>
													<th scope="col">작성자</th>
													<th scope="col">작성일</th>
												</tr>
											</thead>
											<tbody id="boardlist1">
											</tbody>
										</table>
									</div>
								</div>
								<div class="col-md-6">
									<div class="maincard">
										<select id="boardselect2" class="form-select" aria-label="Default select example">
											<option selected>게시판 선택</option>
											<c:forEach var="infoname" items="${infolist}">
												<option value="${infoname.b_code}">${infoname.b_name}</option>
											</c:forEach>
										</select>
										<table class="table table-striped" style="text-align: center;">
											<thead>
												<tr>
													<th scope="col">제목</th>
													<th scope="col">작성자</th>
													<th scope="col">작성일</th>
												</tr>
											</thead>
											<tbody id="boardlist2">
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="maincard">
										<select id="boardselect3" class="form-select" aria-label="Default select example">
											<option selected>게시판 선택</option>
											<c:forEach var="infoname" items="${infolist}">
												<option value="${infoname.b_code}">${infoname.b_name}</option>
											</c:forEach>
										</select>
										<table class="table table-striped" style="text-align: center;">
											<thead>
												<tr>
													<th scope="col">제목</th>
													<th scope="col">작성자</th>
													<th scope="col">작성일</th>
												</tr>
											</thead>
											<tbody id="boardlist3">
											</tbody>
										</table>
									</div>
								</div>
								<div class="col-md-6">
									<div class="maincard">
										<select id="boardselect4" class="form-select" aria-label="Default select example">
											<option selected>게시판 선택</option>
											<c:forEach var="infoname" items="${infolist}">
												<option value="${infoname.b_code}">${infoname.b_name}</option>
											</c:forEach>
										</select>
										<table class="table table-striped" style="text-align: center;">
											<thead>
												<tr>
													<th scope="col">제목</th>
													<th scope="col">작성자</th>
													<th scope="col">작성일</th>
												</tr>
											</thead>
											<tbody id="boardlist4">
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
			</form>
		</div>



	</main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->


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
  

	  function setBanner(event) {
	      var reader = new FileReader();
	
	      reader.onload = function(event) {
	        var img = document.createElement("img");
	        img.setAttribute("src", event.target.result);
	       
	        
	        document.querySelector("div#image-thumbnail").appendChild(img);
	      };
	
	      reader.readAsDataURL(event.target.files[0]);
	    }
	  
	  function setIcon(event) {
	      var reader = new FileReader();
	
	      reader.onload = function(event) {
	        var img = document.createElement("img");
	        img.setAttribute("src", event.target.result);
	        img.setAttribute("style", "width=50px; height=50px;");
	       
	        document.querySelector("div#image-icon").appendChild(img);
	      };
	
	      reader.readAsDataURL(event.target.files[0]);
	    }
	  
	  $('#inputname').keyup(function(){
			
			var inputLength = $(this).val().length; //입력한 값의 글자수
			var remain = 9-inputLength;
			var value = document.getElementById("inputname").value;
		   
			
			$("#cafename").html(value);
			$("#size").html("남은 글자수 : " + remain);
		
			if(remain<1){
				alert("카페 이름이 너무 깁니다.");
			}
		});

	  </script>


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

</body>

</html>