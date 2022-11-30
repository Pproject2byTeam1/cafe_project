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
			selectBoard();
			boardUtilizationRate();
			
			
			/* 변수 값 버리는 temp */
			let splicetResult;
			
			
			
			/* 기간별 게시판 조회수 */
			
			let Bhits = []; 
			let Bname = [];
			
			$("#BURstartDate").change(boardUtilizationRate);
			
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
  						
  						console.log(Bhits)
  						console.log(Bname)
  						
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
			/* 전체 게시판 조회수 상위 TOP 종료 */
			
			
			
			
			/* 기간별 글 종류별 글 생성, 조회수  */
			let RPrankpoint = []; 
			let RPtitle = [];
			
			$("#RankPointSelect").change(selectBoard);
			
			function selectBoard(){
				$("#columnChart").empty();
				
				let startDate = $("#startDate").val();
				let endDate = $("#endDate").val();
				let RPnum = $('#RankPointSelect').val();
				
				
				const RPnumber = {"startDate": startDate, "endDate": endDate, "number": RPnum, "chart": "rankpointselect"};
				
				$.ajax({
  					type: "POST",	
  					url: "ChartList",
  					data: RPnumber,
  					dataType: "JSON",
  					success: function(data){
  							
  						splicetResult = RPrankpoint.splice(0);
  						splicetResult = RPtitle.splice(0);
  						
  						$(data).each(function(){
  							RPrankpoint.push(this.rankpoint);
							RPtitle.push(this.title);
  						});
  					
  						
  						new ApexCharts(document.querySelector("#columnChart"), {
  		                    series: [{
  		                      name: 'Net Profit',
  		                      data: [44, 55, 57, 56, 61, 58, 63, 60, 66]
  		                    }, {
  		                      name: 'Revenue',
  		                      data: [76, 85, 101, 98, 87, 105, 91, 114, 94]
  		                    }, {
  		                      name: 'Free Cash Flow',
  		                      data: [35, 41, 36, 26, 45, 48, 52, 53, 41]
  		                    }],
  		                    chart: {
  		                      type: 'bar',
  		                      height: 350
  		                    },
  		                    plotOptions: {
  		                      bar: {
  		                        horizontal: false,
  		                        columnWidth: '55%',
  		                        endingShape: 'rounded'
  		                      },
  		                    },
  		                    dataLabels: {
  		                      enabled: false
  		                    },
  		                    stroke: {
  		                      show: true,
  		                      width: 2,
  		                      colors: ['transparent']
  		                    },
  		                    xaxis: {
  		                      categories: ['ㅁㅁㅁㅁㅁㅁㅁㅁㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct'],
  		                    },
  		                    yaxis: {
  		                      title: {
  		                        text: 'rankpoint'
  		                      }
  		                    },
  		                    fill: {
  		                      opacity: 1
  		                    },
  		                    tooltip: {
  		                      y: {
  		                        formatter: function(val) {
  		                          return " " + val + " point"
  		                        }
  		                      }
  		                    }
  		                  }).render();
					}
 				});
			}
				
				
		
				
			
			
			
			
			/* 전체 게시판 조회수 상위 TOP */
			
			let hits = []; 
			let title = [];
			
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
  						
  						$(data).each(function(){
							hits.push(this.hits);
							title.push(this.title);
  						});
  						
						new ApexCharts(document.querySelector("#barChart"), {
		                    series: [{
		                      name: '전체게시판',
		                      data: hits
		                    }],
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
		                    },
		                    dataLabels: {
		                      enabled: true
		                    },
		                    xaxis: {
		                      categories: title
		                    },
   		                    fill: {
   		                      opacity: 1
   		                    },
   		                    tooltip: {
   		                      y: {
   		                        formatter: function(val) {
   		                          return " " + val + " point"
   		                        }
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
          <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
          <li class="breadcrumb-item">통계</li>
          <li class="breadcrumb-item active">회원분석</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    	
		
    <section class="section">
      <div class="row">

        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
              <div class="col-md-12">
         			<h5 class="card-title">RankPoint TOP</h5>
         		</div>
         			<div class="row">
         				<div class="col-md-5 d-flex justify-content-end align-items-center">
         					<input type="date" id="startDate" class="form-control" value="2022-11-01" />
         				</div>
         				<div class="col-md-5 d-flex justify-content-end align-items-center">
         					<input type="date" id="endDate" class="form-control" value="2022-12-01" />
         				</div>
	            		<div class="col-md-2 d-flex justify-content-end align-items-center">
	            			<select name="number" id="RankPointSelect" class="form-select">
								<option value=5>5개</option>
								<option value=10>10개</option>
								<option value=15>15개</option>
								<option value=20>20개</option>
							</select>
	            		</div>
            		
            		</div>

              <!-- Column Chart -->
              <div id="columnChart"></div>
              <!-- End Column Chart -->

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

      

        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
              <div class="col-md-12">
         			<h5 class="card-title">기간별 게시판 조회수</h5>
         		</div>
         			<div class="row">
         				<div class="col-md-5 d-flex justify-content-end align-items-center">
         					<input type="date" id="BURstartDate" class="form-control" value="2022-11-01" />
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