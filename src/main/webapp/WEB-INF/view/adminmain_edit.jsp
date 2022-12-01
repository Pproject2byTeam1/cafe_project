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

.draggable{
	cursor: move;
}

.draggable.dragging {
 	opacity: 0.5;
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
 		  	if (event.target.className === "inner") {
 		    	event.target.style.background = "#616161";
 		  	}
 		});

 		document.addEventListener("dragover", (event) => {
 		  	event.preventDefault();
 		});

 		document.addEventListener("dragleave", (event) => {
 		  	event.preventDefault();
 		  	if (event.target.className === "inner") {
 		    	event.target.style.background = "#3a3a3a";
 		  	}
 		});

 		document.addEventListener("drop", (event) => {
 		  	event.preventDefault();
 		  	
 		  	if (event.target.className === "inner") { //배너 이미지
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
			  const formData = new FormData();
			  
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
 			 		formData.append('cafe_img', file);
 			 });
 			 
 			 
 			 bannerUpload(formData);
 		};
 		
 		function bannerUpload(formData){
 			$.ajax({
 		    	type: "POST",
 		    	url: "BannerUpload",
 		    	data: formData,
 		    	contentType:false,
 		    	processData:false,
 		    	dataType: "HTML",
 		    	success: function(data){
 		    		
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
 		
 		function board1load(main1){
 			if(main1 != null && main1 != ""){
 				let html1 = "";
	 			for(let i=0; i<main1.length; i++){
	 				html1 += "<tr>";
	 					html1 += "<td>" + main1[i].title + "</td>";
	 					html1 += "<td>" + main1[i].nick + "</td>";
	 					html1 += "<td>" + main1[i].w_date + "</td>";
	 				html1 += "</tr>";
	 			}	
	 			
	 			$("#boardlist1").empty();
	 			$("#boardlist1").append(html1);
 			}
 		}
 		function board2load(main2){
 			if(main2 != null && main2 != ""){
	 			let html2 = "";
	 			for(let i=0; i<main2.length; i++){
	 				html2 += "<tr>";
	 					html2 += "<td>" + main2[i].title + "</td>";
	 					html2 += "<td>" + main2[i].nick + "</td>";
	 					html2 += "<td>" + main2[i].w_date + "</td>";
	 				html2 += "</tr>";
	 			}
	 			$("#boardlist2").empty();
	 			$("#boardlist2").append(html2);
 			}
 		}
 		function board3load(main3){
 			if(main3 != null && main3 != ""){
	 			let html3 = "";
	 			for(let i=0; i<main3.length; i++){
	 				html3 += "<tr>";
	 					html3 += "<td>" + main3[i].title + "</td>";
	 					html3 += "<td>" + main3[i].nick + "</td>";
	 					html3 += "<td>" + main3[i].w_date + "</td>";
	 				html3 += "</tr>";
	 			}	
	 			
	 			$("#boardlist3").empty();
	 			$("#boardlist3").append(html3);
 			}
 		}
 		function board4laod(main4){
 			if(main4 != null && main4 != ""){
	 			let html4 = "";
	 			for(let i=0; i<main4.length; i++){
	 				html4 += "<tr>";
	 					html4 += "<td>" + main4[i].title + "</td>";
	 					html4 += "<td>" + main4[i].nick + "</td>";
	 					html4 += "<td>" + main4[i].w_date + "</td>";
	 				html4 += "</tr>";
	 			}	
	 			
	 			$("#boardlist4").empty();
	 			$("#boardlist4").append(html4);
 			}
 		}
 		
 		function boardload(){
 			
 			$.ajax({
 				type: "POST",
 		    	url: "MainBoadLoad",
 		    	dataType: "JSON",
 		    	success: function(data){
 		    		
 		    		board1load(data[0].main1);
 		    		board2load(data[1].main2);
 		    		board3load(data[2].main3);
 		    		board4laod(data[3].main4);
 		    	}
 			});
 		}
 		
 		function mainindexupload(selectedvalue, index){
			$.ajax({
				type: "POST",
 		    	url: "MainIndexUpload",
 		    	data: {"main_idx": index, "b_code": selectedvalue},
 		    	dataType: "HTML",
 		    	success: function(data){
 		    		swal(data);
 		 			boardload();
 		    	}
			});
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
 		
 		/* 게시판 출력해주기 끝 */
 		
 		/* top 시작 */
 		
 		$("#topreset").click(function(){
 			$("#inputname").val("");
 			$("#iconfile").val("");
 		});
 		
 		$("#topedit").click(function(){
 			let cafename = $("#inputname").val();
 			let cafeicon = $("#iconfile").val().substr(12, );
 			
 			
 			const formData = new FormData();
 			const data = $("#iconfile");
 				
 			formData.append('cafe_icon', data[0].files[0]);
 			formData.append('cafe_name', cafename);
 			
 			$.ajax({
 				type: "POST",
 	 		   	url: "CafeInfoUpload",
 	 		   	data: formData,
 	 		   	dataType: "HTML",
 	 		   	contentType:false,
 	 		   	processData:false,
 	 		   	success: function(data){
 	 		   		swal(data);
 	 		   	}
 			});
 			
 		});
 		
 		/* top 끝 */
 		
 		/* 게시판 추가 삭제 버튼 시작 */
 		
 		function cateload(){
 			
 			$.ajax({
 				type: "POST",
 				url: "BoardInfoLoad",
 				dataType: "HTML",
 				success: function(data){
 					$("#infoboard").empty();
 					$("#infoboard").append(data);
 					$("#cateinsertview").empty();
 					dragsidebar();
 				}
 			});
 		}
 		
 		function catesave(requestdata) {
 			$.ajax({
 				type: "POST",
 				url: "BoardInfoInsert",
 				data: requestdata,
 				dataType: "HTML",
 				success: function(data){
 					swal(data);
 					cateload();
 				}
 			});
 		}
 		
 		function cateremove(requestdata1){
 			$.ajax({
 				type: "POST",
 				url: "BoardInfoRemove",
 				data: requestdata1,
 				dataType: "HTML",
 				success: function(data){
 					swal(data);
 					cateload();
 				}
 			});
 		}
 		
 		$("#addview_btn").click(function(){
 			let html2 = '<div class="maincard">';
 			html2 += '<input type="text" class="form-control" id="catename" placeholder="카테고리 이름을 입력해 주세요" required></input><br>';
 			html2 += '<select class="form-select" aria-label="Default select example" id="btypeselected">';
 				html2 += '<option value="b1">자유게시판</option>';
 				html2 += '<option value="b2">출석게시판</option>';
 				html2 += '<option value="b3">사진게시판</option>';
 				html2 += '<option value="b4">자료게시판</option>';
 				html2 += '<option value="b5">거래게시판</option>';
 				html2 += '<option value="b6">일정관리게시판</option>';
 			html2 += '</select><br>';
 			html2 += '<textarea class="form-control" placeholder="게시글 작성 견본을 작성해보세요" id="addform" style="height: 100px"></textarea>';
 			html2 += '<div align="center" class="col-md-12 mt-2"><button type="submit" id="addviewsave" class="btn btn-outline-secondary btn-sm rounded-pill">저장</button>';
 			html2 += '<button type="button" id="addviewreset" class="btn btn-outline-secondary btn-sm rounded-pill">취소</button>';
 			html2 += '</div></div>';
 			
 			$("#cateinsertview").append(html2);
 		});
 		
 		$(document).on('click', '#addviewreset', function(){
 			$("#cateinsertview").empty();
 		});
 		
 		$(document).on('click', '#addviewsave', function(){
 			
 			let check = $("#btypeselected option:selected").val();
 			let requestdata = {"b_name": $("#catename").val(), "form": $("#addform").val(), "b_type": check};
 			catesave(requestdata);
 		});
 		
 		$(document).on('click', '.trash', function(){
 			let divtag = this.closest(".maincard");
 			let children1 = $(divtag).children();
 			let ch = $(children1).children().eq(0);
 			
 			let requestdata1 = {"b_code": $(ch).val()};
 			
 			
 			cateremove(requestdata1);
 		});
 		
 		/* 게시판 추가 삭제 버튼 끝 */
 		
 		/* 게시판 정보 수정 시작 */
 		
 		function boardinfobyb_code(divtag){
 			
 			let children1 = $(divtag).children();
 			let ch = $(children1).children().eq(0);
 			let requestdata2 = {"b_code": $(ch).val()};
 			
 			$.ajax({
 				type: "POST",
 				url: "BoardInfoByB_code",
 				data: requestdata2,
 				dataType: "JSON",
 				success: function(data){
 					
 					let html3 = '<div id="mytag" class="maincard">';
 					html3 += '<input type="text" class="form-control" id="catename2" value="' + data.b_name + '" required></input><input id="parkb_code" value="' + data.b_code + '" type="hidden" /><br>';
 					html3 += '쓰기 등급 제한 선택<select id="addviewswriteselect" class="form-select" aria-label="Default select example">';
 						for(let j=0; j<data.ranklist.length; j++){
 							if(data.rank.w_rank == data.ranklist[j].rank){
 								html3 += '<option value="' + data.ranklist[j].rank + '" selected>' + data.ranklist[j].rank + '. ' + data.ranklist[j].r_name + '</option>';
 							}else{
 								html3 += '<option value="' + data.ranklist[j].rank + '">' + data.ranklist[j].rank + '. ' + data.ranklist[j].r_name + '</option>';
 							}
 						}
 					html3 += '</select><br>';
 					html3 += '읽기 등급 제한 선택<select id="addviewsreadselect" class="form-select" aria-label="Default select example">';
						for(let k=0; k<data.ranklist.length; k++){
							if(data.rank.re_rank == data.ranklist[k].rank){
								html3 += '<option value="' + data.ranklist[k].rank + '" selected>' + data.ranklist[k].rank + '. ' + data.ranklist[k].r_name + '</option>';
							}else{
								html3 += '<option value="' + data.ranklist[k].rank + '">' + data.ranklist[k].rank + '. ' + data.ranklist[k].r_name + '</option>';
							}
 						}
					html3 += '</select><br>';
 					if(data.form == "" || data.form == null){
 						html3 += '<textarea class="form-control" placeholder="게시글 작성 견본을 작성해보세요" id="addform2" style="height: 100px"></textarea>';
 					}else{
 						html3 += '<textarea class="form-control" id="addform2" style="height: 100px">' + data.form + '</textarea>';
 					}
 					
 					html3 += '<div align="center" class="col-md-12 mt-2"><button type="submit" id="addviewsave2" class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>';
 					html3 += '<button type="button" id="addviewreset2" class="btn btn-outline-secondary btn-sm rounded-pill">취소</button>';
 					html3 += '</div></div>';
 					
 					$(divtag).after(html3);
 				}
 			});
 		};
 		
 		function boardinfoupdatebyb_code(requestdata3){
 			$.ajax({
 				type: "POST",
 				url: "UpdateInfoByB_code",
 				data: requestdata3,
 				dataType: "HTML",
 				success: function(data){
 					swal(data);
 					cateload();
 				}
 			});
 		}
 		
 		$(document).on('click', '.moreinfo', function(){
 			
 			let divtag = this.closest(".maincard");
 			boardinfobyb_code(divtag);
 			
 		});
 		
 		$(document).on('click', '#addviewreset2', function(){
 			$("#mytag").remove();
 		});
 		
 		$(document).on('click', '#addviewsave2', function(){
 			
 			requestdata3 = {
 					"b_name": $("#catename2").val(), 
 					"form": $("#addform2").val(), 
 					"b_code": $("#parkb_code").val(),
 					"w_rank": $("#addviewswriteselect option:selected").val(),
 					"re_rank": $("#addviewsreadselect option:selected").val()
 			};
 
 			boardinfoupdatebyb_code(requestdata3)
 			
 		});
 		
 		/* 게시판 정보 수정 끝 */
 		
 		/* side_idx 수정 시작 */
 		
 		function dragsidebar(){
 			const draggables = document.querySelectorAll(".draggable");
 			const containers = document.querySelectorAll(".con");
 		
	 		draggables.forEach(draggable => {
	 			draggable.addEventListener("dragstart", () => {
	 				draggable.classList.add("dragging");
	 			});
	
	 			draggable.addEventListener("dragend", () => {
	 				draggable.classList.remove("dragging");
	 			});
	 		});
	 		
		 	containers.forEach(container => {
	 			container.addEventListener("dragover", e => {
	 				e.preventDefault();
	 			    const afterElement = getDragAfterElement(container, e.clientY);
	 			    const draggable = document.querySelector(".dragging");
	 			    if (afterElement === undefined) {
	 			    	container.appendChild(draggable);
	 			    } else {
	 			    	container.insertBefore(draggable, afterElement);
	 			    }
	 			});
	 		});
		 	
		 	document.addEventListener("dragend", (event) => {
	 		  	event.preventDefault();
	 		  	
	 		  	if(event.target.className === "maincard row m-2 draggable"){ //사이드 바
	 		  		let target = $(event.target);
	 		  		let children1 = $(target).children();
	 	 			let ch = $(children1).children().eq(0);
	 		  		
	 		  		let supertag = target.closest("#infoboard");
	 		  		let childtag = $(supertag).children();
	 		  		
	 		  		let arr = [];
	 		  		let num = 0;
	 		  		$(childtag).each(function(){
	 		  			
	 		  			let parkfor = $(this).children();
	 		  			
	 		  			$(parkfor).each(function(){
	 		  				let ch2 = $(this).children().eq(0);
	 		  				let ch3 = $(ch2).children().eq(0);
	 		  				let ch4 = $(ch3)[0];
	 		  				
	 		  				arr.push($(ch4).val());
	 		  				
	 		  				num += 1;
	 		  				
	 		  			});
	 		  			
	 		  		});
	 		  		
	 		  		let requestdata4 = { ...arr, "max": num };
	 		  	
					sideuplaod(requestdata4);
	 		  	}
	 		});
	 		
 		}
 		
 		function getDragAfterElement(container, y) {
 			const draggableElements = [
 				...container.querySelectorAll(".draggable:not(.dragging)"),
 			];

 			return draggableElements.reduce(
 				(closest, child) => {
 				const box = child.getBoundingClientRect();
 			    const offset = y - box.top - box.bottom / 2;
 			    
 			    if (offset < 0 && offset > closest.offset) {
 			    	return { offset: offset, element: child };
 			    } else {
 			    	return closest;
 			    }
 			}, { offset: Number.NEGATIVE_INFINITY }, ).element;
 		}
 		
 		function sideuplaod(requestdata4){
 			$.ajax({
 				type: "POST",
 				url: "SideUpload",
 				data: requestdata4,
 				dataType: "HTML",
 				success: function(data){
 					swal(data);
 					cateload();
 				}
 			});
 		}
 		
 		dragsidebar();
 		/* side_idx 수정 끝 */
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
		
		<div class="pagetitle">
		
	
		</div>
		<!-- End Page Title -->
	
   
		<!-- 관리자 메인페이지 관리 시작 -->
		

		<div class="row">
			<div class="container-fluid">
				<div class="wrap1">
					<div class="col-lg-12">
						<div class="maincard">
							<div class="row">
								<div class="col-md-5">
									<div class="ms-5 ps-5 pt-4">
										<a class="logo d-flex align-items-center"> 
											<img src="assets/img/logo.png" alt="">
											<input type="text" class="form-control" id="inputname" value="${cafebanner.cafe_name}"></input>
										</a> 
										<span id="size"></span>
									</div>
								</div>

								<div class="col-md-7">
									<div class="row">
										<label for="form-control" class="col"> 
											<strong>아이콘 : ${cafebanner.cafe_icon}</strong> 
											<input type="file" class="form-control" accept="image/*" id="iconfile" required>
										</label>
									</div>
								</div>
							</div>
							<hr />
							<div align="center" class="col-md-12 mt-2">
								<button type="submit" id="topedit" 
									class="btn btn-outline-secondary btn-sm rounded-pill">수정</button>
								<button type="button" id="topreset" 
									class="btn btn-outline-secondary btn-sm rounded-pill">취소</button>
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
									<button type="button" id="addview_btn"
										class="btn btn-lg btn-outline-secondary rounded-pill">게시판 추가</button>
								</div>
							</div>
						</div>
						<div id="cateinsertview">
						
						</div>
						
						<div id="infoboard" class="maincard">
							<c:forEach var="infolist" items="${infolist}">
								<div class="con">
									<div class="maincard row m-2 draggable" draggable="true">
										<div class="col-md-10 pt-1">
											<input id="b_code" value="${infolist.b_code}" type="hidden" />
											<br> <h5>${infolist.b_name}</h5>
										</div>
										<div align="right" class="col-md-2 pt-2">
											<h4><i class="bi bi-trash3 trash"></i></h4>
											<h4><i class="bi bi-arrow-down-square moreinfo"></i></h4>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="col-md-8">
						<div class="maincard">
							<div class="col-md-12">
								<!-- 배너 이미지 미리보기 -->
								<label class="label" id="label" for="input">
									<div class="preview" id="preview">
									<img width="100%" height="350px" src="upload/${cafebanner.cafe_img}">
									</div>
									<div align="center"><span class="inner" id="inner">이미지를 드래그 또는 클릭하여 추가</span></div>
								</label> <input id="input" class="input" accept="image/*" type="file"
									required="true" hidden="true">
							</div>
						</div>

						<div class="row">
							<div class="col-md-6">
								<div class="maincard">
									<select id="boardselect1" class="form-select"
										aria-label="Default select example">
										<option selected>게시판 선택</option>
										<c:forEach var="infoname" items="${infolist}">
											<c:if test="${infoname.main_idx == -1}">
												<option value="${infoname.b_code}">${infoname.b_name}</option>
											</c:if>
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
									<select id="boardselect2" class="form-select"
										aria-label="Default select example">
										<option selected>게시판 선택</option>
										<c:forEach var="infoname" items="${infolist}">
											<c:if test="${infoname.main_idx == -1}">
												<option value="${infoname.b_code}">${infoname.b_name}</option>
											</c:if>
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
									<select id="boardselect3" class="form-select"
										aria-label="Default select example">
										<option selected>게시판 선택</option>
										<c:forEach var="infoname" items="${infolist}">
											<c:if test="${infoname.main_idx == -1}">
												<option value="${infoname.b_code}">${infoname.b_name}</option>
											</c:if>
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
									<select id="boardselect4" class="form-select"
										aria-label="Default select example">
										<option selected>게시판 선택</option>
										<c:forEach var="infoname" items="${infolist}">
											<c:if test="${infoname.main_idx == -1}">
												<option value="${infoname.b_code}">${infoname.b_name}</option>
											</c:if>
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
				</div>
			</div>
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

<!-- 
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
 -->
</body>

</html>