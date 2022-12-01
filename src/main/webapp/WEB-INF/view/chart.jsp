<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Charts / ApexCharts - NiceAdmin Bootstrap Template</title>
  <meta content="" name="description">
  <meta content="" name="keywords">
	
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

  <!-- =======================================================
  * Template Name: NiceAdmin - v2.4.1
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
  
  <script type="text/javascript">
		$(document).ready(function(){
			allBoardTopView();
			boardUtilizationRate();
			boardCount();
			monthBoardWrite();
			monthBoardView();
			
			/* 변수 값 버리는 temp */
			let splicetResult;
			
			
			
			
			
			/* 게시판별 조회수 */
			
			let BC_bname = []; 
			let BC_hitcount = [];
			
			
						/* 함수시작 */
			function boardCount(){
				$("#boardCount").empty();
				
				const BCboard = { "chart": "boardCount"};
				
				
					
				$.ajax({
  					type: "POST",
  					url: "ChartList",
  					data: BCboard,
  					dataType: "JSON",
  					success: function(data){
  						
  						splicetResult = BC_bname.splice(0);
  						splicetResult = BC_hitcount.splice(0);
  						
  						$(data).each(function(){
  							BC_bname.push(this.b_name);
  							BC_hitcount.push(this.hit_count);
  						});
  						
  						
  						
  						
  						new ApexCharts(document.querySelector("#boardCount"), {
  		                    series: BC_hitcount,
  		                    chart: {
  		                      height: 350,
  		                      type: 'pie',
  		                      toolbar: {
  		                        show: true
  		                      }
  		                    },
  		                    labels: BC_bname
  		                  }).render();
  						
  					}
 				});
			}
			/* 게시판별 조회수 종료 */
			
			
			
			
			/* 기간별 게시판 조회수 */
			
			let Bhits = []; 
			let Bname = [];
			
			$("#BURstartDate").change(boardUtilizationRate);
			$("#BURendDate").change(boardUtilizationRate);
			
						/* 함수시작 */
			function boardUtilizationRate(){
				$("#boardUtilizationRate").empty();
				
				let BURstartDate = $("#BURstartDate").val();
				let BURendDate = $("#BURendDate").val();
				
				
				const BURnumber = {"BURstartDate": BURstartDate, "BURendDate": BURendDate, "chart": "boardUtilizationRate"};
				
				
					
				$.ajax({
  					type: "POST",
  					url: "ChartList",
  					data: BURnumber,
  					dataType: "JSON",
  					success: function(data){
  						
  							
  						splicetResult = Bhits.splice(0);
  						splicetResult = Bname.splice(0);
  						
  						$(data).each(function(){
  							Bhits.push(this.hit_count);
							Bname.push(this.b_name);
  						});
  						
  						
  						new ApexCharts(document.querySelector("#boardUtilizationRate"), {
  		                    series: Bhits,
  		                    chart: {
  		                      type: 'polarArea',
  		                      height: 350,
  		                      toolbar: {
  		                        show: true
  		                      }
  		                    },
  		                    labels: Bname,
  		                    
  		                    stroke: {
  		                      colors: ['#fff']
  		                    },
  		                    fill: {
  		                      opacity: 0.8
  		                    }
  		                  }).render();
  						
  					}
 				});
			}
			/* 기간별 게시판 조회수 종료 */
			
			
				
		
			/* 게시판별 월별 글수 TOP */
			
			let MBWmonth = []; 
			let MBWcount = [];
		
			
			
			$("#MBWmonth").change(monthBoardWrite);
			
						/* 함수시작 */
			function monthBoardWrite(){
				$("#monthBoardWrite").empty();
				
				
				let month = $('#MBWmonth').val();
				const MBWnumber = {"MBWmonth": month,"chart": "monthBoardWrite"};
				
				$.ajax({
  					type: "POST",
  					url: "ChartList",
  					data: MBWnumber,
  					dataType: "JSON",
  					success: function(data){
						
  				
  						
  						splicetResult = MBWmonth.splice(0);
  						splicetResult = MBWcount.splice(0);
  						
  						$(data).each(function(){
  							MBWmonth.push(this.month);
							MBWcount.push(this.count);
  						});
  						
  						
  						
  						
  						new ApexCharts(document.querySelector("#monthBoardWrite"), {
  		                    series: [{
  		                      name: data[0].b_name,
  		                      data: MBWcount
  		                    }],
  		                    chart: {
  		                    	
    		                  colors: ['#E91E63', '#E91E63', '#9C27B0'],
  		                      height: 350,
  		                      type: 'line',
  		                      zoom: {
  		                        enabled: true
  		                      }
  		                    },
  		                    dataLabels: {
  		                      enabled: true
  		                    },
  		                    stroke: {
  		                      curve: 'smooth'
  		                    },
  		                    grid: {
  		                      row: {
  		                        opacity: 0.5
  		                      },
  		                    },
  		                    xaxis: {
  		                        categories: MBWmonth,
  		                      },
  		                    yaxis: {
  			                      title: {
  			                        text: 'write'
  			                      }
  		                    },
  		                    fill: {
  			                      opacity: 1
  		                    },
  		                    tooltip: {
  			                      y: {
  			                        formatter: function(val) {
  			                          return " " + val + " write"
  			                        }
  			                      }
  			                    }
  		                    
  		                  }).render();
					}
 				});
				
			}
			/* 게시판별 월별 글수 종료 */	
			
			
			
			/* 게시판별 월별 조회수 TOP */
			
			let MBVmonth = []; 
			let MBVcount = [];
		
			
			
			$("#MBVmonth").change(monthBoardView);
			
						/* 함수시작 */
			function monthBoardView(){
				$("#monthBoardView").empty();
				
				
				let month = $('#MBVmonth').val();
				const MBVnumber = {"MBVmonth": month,"chart": "monthBoardView"};
				
				$.ajax({
  					type: "POST",
  					url: "ChartList",
  					data: MBVnumber,
  					dataType: "JSON",
  					success: function(data){
						
  				
  						
  						splicetResult = MBVmonth.splice(0);
  						splicetResult = MBVcount.splice(0);
  						
  						$(data).each(function(){
  							MBVmonth.push(this.month);
							MBVcount.push(this.count);
  						});
  						
  						
  						
  						new ApexCharts(document.querySelector("#monthBoardView"), {
  		                    series: [{
  		                      name: data[0].b_name,
  		                      data: MBVcount
  		                    }],
  		                    chart: {
  		                      height: 350,
  		                      type: 'line',
  		                      zoom: {
  		                        enabled: true
  		                      },colors: ['#fffff', 'transparent']
  		                    },
  		                    dataLabels: {
  		                      enabled: true
  		                    },
  		                    stroke: {
  		                      curve: 'smooth'
  		                    },
  		                    xaxis: {
  		                        categories: MBVmonth,
  		                      },
  		                    yaxis: {
  			                      title: {
  			                        text: 'view'
  			                      }
  		                    },
  		                    fill: {
  			                      opacity: 1
  		                    },
  		                    tooltip: {
  			                      y: {
  			                        formatter: function(val) {
  			                          return " " + val + " view"
  			                        }
  			                      }
  			                    }
  		                  }).render();
					}
 				});
				
			}
			/* 게시판별 월별 글수 종료 */	
			
			
			/* 전체 게시판 조회수 상위 TOP */
			
			let hits = []; 
			let title = [];
			let b_name = [];
			
			
			$("#allBoardTopView").change(allBoardTopView);
			
						/* 함수시작 */
			function allBoardTopView(){
				$("#barChart").empty();
				
				let BTnum = $('#allBoardTopView').val();
				const BTnumber = {"number": BTnum,"chart": "allboardtopview"};
				
				$.ajax({
  					type: "POST",
  					url: "ChartList",
  					data: BTnumber,
  					dataType: "JSON",
  					success: function(data){
						
  						splicetResult = hits.splice(0);
  						splicetResult = title.splice(0);
  						
  						$(data[0]).each(function(){
							hits.push(this.hits);
							title.push(this.title);
  						});
  						
  						
						new ApexCharts(document.querySelector("#barChart"), {
		                    series: data[1],
		                    chart: {
		                      type: 'bar',
		                      height: 350
		                    },
		                    labels: title
		                    ,
		                    plotOptions: {
		                      bar: {
		                        borderRadius: 4,
		                        horizontal: true,
		                      }
		                    }
		                  }).render();
					}
 				});
				
			}
			/* 전체 게시판 조회수 상위 TOP 종료 */
			
		});
  
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
  


    <div class="pagetitle">
      <h1>회원 분석</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="cafemain.do">Home</a></li>
          <li class="breadcrumb-item">통계</li>
          <li class="breadcrumb-item active">회원분석</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    	
		
    <section class="section">
      <div class="row">

        

       

      
      	<div class="col-lg-6">
          <div class="card">
            <div class="card-body">
              <div class="row">
            		<div class="col-md-7">
            			<h5 class="card-title">게시판별 월별 글수</h5>
            		</div>
            		<div class="col-md-5 d-flex justify-content-end align-items-center">
            			<select id="MBWmonth" class="form-select">
            				<c:forEach var="boardlist" items="${boardlist}">
            					<option value="${boardlist.b_code}" >${boardlist.b_name}</option>
            				</c:forEach>
						</select>
            		</div>
            		
            	</div>

              <!-- Line Chart -->
              <div id="monthBoardWrite"></div>
              <!-- End Line Chart -->

            </div>
          </div>
        </div>
        
        <div class="col-lg-6">
          <div class="card">
            <div class="card-body">
              <div class="row">
            		<div class="col-md-7">
            			<h5 class="card-title">게시판별 월별 조회수</h5>
            		</div>
            		<div class="col-md-5 d-flex justify-content-end align-items-center">
            			<select id="MBVmonth" class="form-select">
            				<c:forEach var="boardlist" items="${boardlist}">
            					<option value="${boardlist.b_code}" >${boardlist.b_name}</option>
            				</c:forEach>
						</select>
            		</div>
            		
            	</div>

              <!-- Line Chart -->
              <div id="monthBoardView"></div>
              <!-- End Line Chart -->

            </div>
          </div>
        </div>
      
      
      

        <div class="col-lg-6">
          <div class="card">
            <div class="card-body">
              <div class="col-md-12">
         			<h5 class="card-title">기간별 게시판 조회수</h5>
         		</div>
         			<div class="row">
         				<div class="col-md-5 d-flex justify-content-end align-items-center">
         					<input type="date" id="BURstartDate" class="form-control" value="2022-05-01" />
         				</div>
         				<div class="col-md-5 d-flex justify-content-end align-items-center">
         					<input type="date" id="BURendDate" class="form-control" value="2022-12-01" />
         				</div>
	            		<div class="col-md-2 d-flex justify-content-end align-items-center">
	            			
	            		</div>
            		
            		</div>

              <!-- Polar Area Chart -->
              <div id="boardUtilizationRate"></div>
              <!-- End Polar Area Chart -->

            </div>
          </div>
        </div>
        
        
        <div class="col-lg-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">게시판 별 조회수</h5>
              <br>

              <!-- Pie Chart -->
              <div id="boardCount"></div>
              <!-- End Pie Chart -->

            </div>
          </div>
        </div>
        
        
         <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
            	<div class="row">
            		<div class="col-md-9">
            			<h5 class="card-title">전체 게시판 조회수 상위 TOP</h5>
            		</div>
            		<div class="col-md-3 d-flex justify-content-end align-items-center">
            			<select name="number" id="allBoardTopView" class="form-select">
							<option value=5>5개</option>
							<option value=10>10개</option>
							<option value=15>15개</option>
							<option value=20>20개</option>
						</select>
            		</div>
            		
            	</div>
              
				
              <!-- Bar Chart -->
             	 <div id="barChart"></div>
              <!-- End Bar Chart -->

            </div>
          </div>
        </div>

       
       
       
      </div>
    </section>

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer" class="footer">
    <div class="copyright">
      &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved
    </div>
    <div class="credits">
      <!-- All the links in the footer should remain intact. -->
      <!-- You can delete the links only if you purchased the pro version. -->
      <!-- Licensing information: https://bootstrapmade.com/license/ -->
      <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
      Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

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