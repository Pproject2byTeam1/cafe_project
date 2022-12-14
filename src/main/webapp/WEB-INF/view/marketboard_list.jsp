<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>marketboard_list</title>

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
<!-- 거래게시판 CSS 시작 -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/marketboard_list.css" rel="stylesheet">
<!-- 거래게시판 CSS 끝 -->
<script type="text/javascript">
	$(document).ready(function(){
		let b_code = "<c:out value='${b_code}'/>";
		let cp = "<c:out value='${cpage}'/>";
		let ps = "<c:out value='${pagesize}'/>";
		let sold = "";
			
			
			//글쓰기
			$("#Write").click(function(){
			
				location.href="marketboard_write.do?b_code=" + b_code;
				
			});
			
			
			//목록 건수 변경
			
						
			$("#listselectbtn").click(function(){				
			    
			    var sold = $("#sold option:selected").val();
			    var ps = $("#listselect option:selected").val();
			    var search = $("#sarchInput").val();
			    var requestdata = {"b_code": b_code, "sold": sold, "search" : search, "ps" : ps};	
			  
			    
				/* 검색 비동기 */					
					$.ajax({
						type: "POST",
						url: "MarketSearch",
						data: requestdata,
						dataType: "JSON",
						success: function(data){
														
							$(".list").empty();
							$(".page").empty();
						
							
							
							for(let index in data.list){
								var str = data.list[index].content;
								var result = str.substr(0, 20) + "...";
								html = '<div class="mcard" onclick="location.href=' + "'marketboard_read.do?b_code=" + data.list[index].b_code + '&idx=' + data.list[index].idx + '&cp=' + cp + "'" + ';">';
								html += '<div class="mimg">'
								html += '<img src="upload/' + data.list[index].img_name + '" id="mimg"/>';
								html += '</div>'
								html += '<span id="marketB_Text.ns">' + data.list[index].idx + ' | ' + data.list[index].m_mode + ' | ' + data.list[index].sold + ' | ' + data.list[index].cate + '</span>';
								html += '<p>';
								html += '<span id="marketB_Title">' + data.list[index].title + '</span>';
								html += '<br>';
								html += '<span id="marketB_Text">' + result + '</span>';
								html += '<div id="marketB_Price">' + data.list[index].price + ' 원</div>';
								html += '<p><p>'
								html += '<span id="marketB_info">조회수 : ' + data.list[index].hits + ' | 댓글 : ' + data.commentcountlist[index] + ' | 찜 : ' + data.yescountlist[index]  + '</span>';
								html += '<br>';
									html += '<span id="marketB_Text.ns"><img src="image/rank_icon/' + data.ranklist[index]  + '.gif" alt="Profile"'
										html += 'class="rounded-circle"> ' + data.list[index].nick + ' | ' + data.list[index].w_date + '</span>';
								html += '</div>';
								
								$('.list').append(html);
								
								
							}; 
							
							
							
							
						}
					});
				});
		
			
		});

	
</script>
<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<script type="text/javascript">

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
			<h1>${boardinfo.b_name}</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="cafemain.do">전체글 : ${totalboardcount}</a></li>
					<li class="breadcrumb-item active">판매중 : ${soldcount}</li>
					<li class="breadcrumb-item">판매완료 : ${totalboardcount-soldcount}</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<!-- Contents -->
		<section class="section">
			<div class="col-md-12">
			<div class="marketcard">
				<div class="card-body">
				<span class="card-title">거래 게시판에서 발생하는 모든 문제는 본인에게 있습니다.</span>
				<div align="right" class="col-md-12">
					<div>
						<button type="button" id="Write" 
							class="btn btn-outline-secondary btn-sm rounded-pill">글쓰기</button>
						</div>
					</div>
					
					<!-- Bordered Tabs -->
					<div class="seach">
					<ul class="nav nav-tabs nav-tabs-bordered" id="searchTab"
						role="tablist">
						<li class="nav-item" role="presentation">
							<select id="sold" class="form-select" aria-label="Default select example">
								<option value="all" selected>전체조회</option>
								<option value="판매중">판매중</option>
							</select>
						</li>
						<li class="nav-item" role="presentation">
							<select id="listselect" class="form-select" aria-label="Default select example">
								<option value="8" selected>게시물 건수</option>
								<option value="10">최근 10건</option>
								<option value="20">최근 20건</option>
								<option value="30">최근 30건</option>
								<option value="50">최근 50건</option>
								<option value="100">최근 100건</option>
							</select>
						</li>
						<li class="nav-item" role="presentation">
							<input class="flex-fill form-control" type="text" id="searchInput" placeholder="Search" title="Enter search keyword">
						</li>
						<li class="nav-item" role="presentation">
						  &nbsp&nbsp<button type="button" id="listselectbtn" 
							class="btn btn-outline-secondary btn-sm rounded-pill">검색</button>
						</li>
					</ul>
					</div>
				
					<div class="tab-content pt-2" id="borderedTabContent">
						<div class="tab-pane fade show active" id="bordered-home"
							role="tabpanel" aria-labelledby="home-tab"></div>
						<div class="tab-pane fade" id="bordered-profile" role="tabpanel"
							aria-labelledby="profile-tab"></div>
						<div class="tab-pane fade" id="bordered-contact" role="tabpanel"
							aria-labelledby="contact-tab"></div>
						<div></div>
					</div>
					<!-- End Bordered Tabs -->
				
				<c:if test="${list == null}">
  					<p>데이터가 없습니다</p>
  				</c:if>
					<!-- 보드	리스트 출력 시작 -->
					<div class="list container container__content--flow">
						<c:forEach var="list" items="${list}" varStatus="status">
						<div class="mcard" onclick="location.href='marketboard_read.do?b_code=${list.b_code}&idx=${list.idx}&cp=${cpage}';">
							<div class="mimg">
							<img src="upload/${list.img_name}" id="mimg" onerror="this.onerror=null; this.src='https://via.placeholder.com/500X500?text=No+Image'">
							</div>
							<span id="marketB_Text.ns">${list.idx} | ${list.m_mode} | ${list.sold} | ${list.cate}</span>
							<p>
							<span id="marketB_Title">${list.title}</span>
							<br>
							<span id="marketB_Text">
							<c:choose>
									<c:when test="${list.content != null && fn:length(list.content) > 20}">
										${fn:substring(list.content,0,20)}...
									</c:when>
									<c:otherwise>
										${list.content}
									</c:otherwise>
							</c:choose>
							</span>
							<div id="marketB_Price">${list.price} 원</div>
							<p><p>
							<span id="marketB_info">조회수 : ${list.hits} | 댓글 : ${comments[status.index]} | 찜 : ${yes[status.index]}</span>
							<br>
								<span id="marketB_Text.ns"><img src="image/rank_icon/${rank[status.index]}.gif" alt="Profile"
									class="rounded-circle"> ${list.nick} | ${list.w_date}</span>
						</div>
						</c:forEach>					
					</div>
					<!-- 보드 페이지 시작 -->
	<div class="page">					
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
			
	               <c:if test="${cpage > 1}">
	                 <li class="page-item">
	                 <div class="1">
	                   <a class="page-link" href="marketboard_list.do?b_code=${b_code}&cp=${cpage-1}&ps=${pagesize}" tabindex="-1" aria-disabled="true"><<</a>
	                 </div>
	                 </li>
	                  </c:if>
	                  	
	                  <c:forEach var="i" begin="1" end="${pagecount}" step="1">
	                  	<c:choose>
						<c:when test="${cpage==i}">
								<li class="page-item"><a class="page-link active" >${i}</a></li>
						</c:when>
						<c:otherwise>
								<div class="2">
	                 			<li class="page-item"><a class="page-link" href="marketboard_list.do?b_code=${b_code}&cp=${i}&ps=${pagesize}">${i}</a></li>
	                 			</div>
						</c:otherwise>
					</c:choose>
	                  </c:forEach>
	                  
	                  <c:if test="${cpage < pagecount}">
	                  	<li class="page-item">
	                  	<div class="3">
							<a class="page-link" href="marketboard_list.do?b_code=${b_code}&cp=${cpage+1}&ps=${pagesize}">>></a>
						</div>
					</li>

				</c:if>
			</ul>
		</nav>
		</div>
	</div>
		</div>
		</div>
		</section>
		<!-- End Centered Pagination -->
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



</body>

</html>