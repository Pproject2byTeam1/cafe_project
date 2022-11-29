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
 <script type="text/javascript">

 $(function)(){

	 
	 function insert(data){
		 
		 
		 $.ajax({
			
			 url : "RankOk",
			 data :data,
			 dataType :"html";
			success : function(data){
	
				list();
					
	
			}
			 

		 });
		 
 
	 }
	 
	function list(){
		
		
		const req2 = {"rank" : rank};
		
		$.ajax({
			
				url :"RankList";
				data : req,
				dataType :"html",
				success : function(responseText){
					
					$("#ranklist").empty();
					$("#ranklist").append(responseText.trim());
					
				}
							
			
			
			
			
		});

	} 
	 
	 
	 
	 /*추가하기*/
	 $("#RankNew").click(function(){
		 
	  const data = {"rank" : rank , "r_name" :$("#r_rank").val() , "r_point" :$("#r_point").val()};
	  insert(data);
	  $("#r_rank").val("");
	 
	 
	 });
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
 }
 
 
 </script>
</head>
<body>
  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">
     <jsp:include page="/common/top.jsp"></jsp:include>
  </header><!-- End Header -->
  <!-- ======= Sidebar ======= -->
      <jsp:include page="/common/side.jsp"></jsp:include>
  <!-- End Sidebar -->
  
  
  
  <main id="main" class="main">
  
		
		<div class="pagetitle" >
			<h1>등급관리</h1>
			<nav>
			   <ol class="breadcrumb">
			      <li class="breadcrumb-item"><a href="index.html">카페관리</a></li>
			      <li class="breadcrumb-item active">등급관리</li>
			   </ol>
			</nav>
		</div>

	<div class="container">
		<div class="card">
			<div class="card-body">
			
				
				<div class="container-fluid">
			         <div class="row">
			            <div class="col-md-2"></div>
			            <div class="col-md-8">
			            	
			            	<div class="row">
			            		<br><br>
			            	</div>
			            	<form  onclick="location.href='rankeditboard.do?b_code=6&rank=${rank}&cp=${cpage}&ps=${pagesize}'" style="cursor:pointer">
			            	<button type="button" class="btn float-right btn-success" style="float:right"> 
									저장하기
							</button>
			                </form>
			                
			             	  <br><br>
			              
			
			               <table class="table text-center">
			                  <thead class="thead-light">
			                     <tr>
			                        <th>
			                           등급레벨
			                        </th>
			                        <th>
			                           등급 이름 변경
			                        </th>
			                        <th>
			                           등급 기준 설정
			                        </th>
			                        <th>
			                           삭제
			                        </th>
			                     </tr>
			                  </thead>
			                  <tbody>
			                     
			                     <tr>
											                     
			                        <td>
			                           1
			                        </td>
			                        <td>
			                           <input type="text" class="form-control" id="기입하세용" placeholder="준회원">
			                        </td>
			                        <td>
			                           <input type="text" class="form-control" id="기입하세용" placeholder="0점 이상" readonly>
			                        </td>
			                        <td>
			                           
			                        </td>
			                        
			                     </tr>
			                 
			                     <c:forEach var="rank" items="${list}"   varStatus="status">
			                 	    <c:if test="${rank.rank>1}">    
			                     <tr>
			                     	
			                        <td>
			                           ${rank.rank}
			                        </td>
			                        <td>
			                           <input type="text" class="form-control" id="기입하세용" placeholder="${rank.r_name}" value="${rank.r_name}" id="r_rank">
			                          
			                        </td>
			                        <td>
			                           <input type="text" class="form-control" id="기입하세용" placeholder="${rank.r_point}점 이상" id="r_point">
			                          
			                        </td>
			                        <td>
			                           <input type="button" class="btn btn-danger" onclick="alert('삭제되었습니다!')" value="삭제">
			                        </td>
			                       
			                     </tr>
			                        </c:if>
			                      </c:forEach>
			          
			                 
			                     <tr>
			                        <td>
			                        </td>
			                        <td>
			                        </td>
			                        <td>
			                           <button type="button" class="btn btn btn-secondary" id="RankNew"> 추가하기 </button>
			                        </td>
			                        <td>
			                        </td>
			                     </tr>
			                     
			                  </tbody>
			               </table>
			            </div>
			            
			         </div>
			      </div>
			  <!-- 여기까지만 작성  -->
			
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