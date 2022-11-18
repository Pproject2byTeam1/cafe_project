<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>marketboard_list</title>
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
  <!-- 거래게시판 CSS 시작 -->
  <style type="text/css">
   #marketB_Box {
   margin-top: 30px;
   margin-left: 10px;
   margin-right: 10px;
   margin-bottom: 30px;
   border: none;
   border-radius: 5px;
   box-shadow: 0px 0 30px rgba(1, 41, 112, 0.1);
   padding:15px 15px 15px 15px;
   }
   #marketB_img{
   width: 200px;
   height: 200px;
   }
   #YES{
   width: 50px;
   }
   #marketB_inBox{
   height: 150px;
   }
   #marketB_Title {
   font-size: 18px;
   color: #012970;
   font-family: "Poppins", sans-serif;
   }
   #marketB_Text {
   font-size: 14px;
   font-weight: 500;
   color: #012970;
   font-family: "Poppins", sans-serif;
   }
   .ns {
   color:red;
   }
   #marketB_Price {
   font-size:18px;
   color:orange;
   font-family: "Poppins", sans-serif, bold;
   }
  <!-- 거래게시판 CSS 끝 -->
  </style>
  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">

  <jsp:include page="/common/top.jsp"></jsp:include>

  </header><!-- End Header -->

  <!-- ======= Sidebar ======= -->
  
   <jsp:include page="/common/side.jsp"></jsp:include>
  
  <!-- ======= End Sidebar ======= -->
    <main id="main" class="main">
    
    <!-- Page Title -->
        <div class="pagetitle">
      <h1>거래 게시판</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">전체글 : 000</a></li>
          <li class="breadcrumb-item active">판매중 : 000</li>
          <li class="breadcrumb-item">판매완료 : 000</li>
        </ol>
      </nav>
    </div>
    <!-- End Page Title -->
    
    <!-- Contents -->
    <section class="section">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">거래 게시판에서 발생하는 모든 문제는 본인에게 있습니다.</h5>
			  <!-- Bordered Tabs -->
              <ul class="nav nav-tabs nav-tabs-bordered" id="borderedTab" role="tablist">
                <li class="nav-item" role="presentation">
                  <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#bordered-home" type="button" role="tab" aria-controls="home" aria-selected="true">전체보기</button>
                </li>
                <li class="nav-item" role="presentation">
                  <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#bordered-profile" type="button" role="tab" aria-controls="profile" aria-selected="false">판매중</button>
                </li>
                <li class="nav-item" role="presentation">
                  <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#bordered-contact" type="button" role="tab" aria-controls="contact" aria-selected="false">나의 찜</button>
                </li>
              </ul>
              <div class="tab-content pt-2" id="borderedTabContent">
                <div class="tab-pane fade show active" id="bordered-home" role="tabpanel" aria-labelledby="home-tab">
                  
                </div>
                <div class="tab-pane fade" id="bordered-profile" role="tabpanel" aria-labelledby="profile-tab">
                  
                </div>
                <div class="tab-pane fade" id="bordered-contact" role="tabpanel" aria-labelledby="contact-tab">
                  
                </div>
              </div>
              <!-- End Bordered Tabs -->
              <!-- 보드 리스트 출력 시작 -->
<div class="container-fluid">
	<div class="row">
		<div id="marketB_Box" class="col-md-5 col-sm-5 col-5">
			<div class="row">
				<div id="marketB_img" class="col-md-2">
					<img src="image/marketboard_img/1.jpg" class="img-fluid rounded" alt="..."><p>
					<img src="image/marketboard_img/YES.jpg" id="YES" class="img-fluid rounded" alt="...">
				</div>
				<div class="col-md-8">
					<span id="marketB_Title">맥북 팝니다!</span><br>
	                <span id="marketB_Text">직거래는 혜화에서 가능...3개월정도 사용했고 상태 좋습니다. 직거래는 혜화에서 가능...</span><br>
	                <span id="marketB_Price">1,300,000</span><p>
	                <span id="marketB_Text.ns">직거래</span> |<span id="marketB_Text.ns">판매중</span><br>
	               	<h6><img src="image/rank_icon/1.gif" alt="Profile" class="rounded-circle"> USER_NICK</h6>
	                <span id="marketB_Text.ns">22.11.18 12:10</span><p>
	                <span id="marketB_Text.ns">조회수 : 120</span>
	                <span id="marketB_Text.ns">찜 : 3</span>
				</div>
			</div>
		</div>
		<div id="marketB_Box" class="col-md-5 col-sm-5 col-5">
			<div class="row">
				<div id="marketB_img" class="col-md-2">
					<img src="image/marketboard_img/2.jpeg" class="img-fluid rounded" alt="..."><p>
					<img src="image/marketboard_img/YES.jpg" id="YES" class="img-fluid rounded" alt="...">
				</div>
				<div class="col-md-8">
					<span id="marketB_Title">맥북 팝니다!</span><br>
	                <span id="marketB_Text">직거래는 혜화에서 가능...3개월정도 사용했고 상태 좋습니다. 직거래는 혜화에서 가능...</span><br>
	                <span id="marketB_Price">1,300,000</span><p>
	                <span id="marketB_Text.ns">직거래</span> |<span id="marketB_Text.ns">판매중</span><br>
	               	<h6><img src="image/rank_icon/1.gif" alt="Profile" class="rounded-circle"> USER_NICK</h6>
	                <span id="marketB_Text.ns">22.11.18 12:10</span><p>
	                <span id="marketB_Text.ns">조회수 : 120</span>
	                <span id="marketB_Text.ns">찜 : 3</span>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row">
		<div id="marketB_Box" class="col-md-5 col-sm-5 col-5">
			<div class="row">
				<div id="marketB_img" class="col-md-2">
					<img src="image/marketboard_img/3.png" class="img-fluid rounded" alt="..."><p>
					<img src="image/marketboard_img/YES.jpg" id="YES" class="img-fluid rounded" alt="...">
				</div>
				<div class="col-md-8">
					<span id="marketB_Title">맥북 팝니다!</span><br>
	                <span id="marketB_Text">직거래는 혜화에서 가능...3개월정도 사용했고 상태 좋습니다. 직거래는 혜화에서 가능...</span><br>
	                <span id="marketB_Price">1,300,000</span><p>
	                <span id="marketB_Text.ns">직거래</span> |<span id="marketB_Text.ns">판매중</span><br>
	               	<h6><img src="image/rank_icon/1.gif" alt="Profile" class="rounded-circle"> USER_NICK</h6>
	                <span id="marketB_Text.ns">22.11.18 12:10</span><p>
	                <span id="marketB_Text.ns">조회수 : 120</span>
	                <span id="marketB_Text.ns">찜 : 3</span>
				</div>
			</div>
		</div>
		<div id="marketB_Box" class="col-md-5 col-sm-5 col-5">
			<div class="row">
				<div id="marketB_img" class="col-md-2">
					<img src="image/marketboard_img/4.jpg" class="img-fluid rounded" alt="..."><p>
					<img src="image/marketboard_img/YES.jpg" id="YES" class="img-fluid rounded" alt="...">
				</div>
				<div class="col-md-8">
					<span id="marketB_Title">맥북 팝니다!</span><br>
	                <span id="marketB_Text">직거래는 혜화에서 가능...3개월정도 사용했고 상태 좋습니다. 직거래는 혜화에서 가능...</span><br>
	                <span id="marketB_Price">1,300,000</span><p>
	                <span id="marketB_Text.ns">직거래</span> |<span id="marketB_Text.ns">판매중</span><br>
	               	<h6><img src="image/rank_icon/1.gif" alt="Profile" class="rounded-circle"> USER_NICK</h6>
	                <span id="marketB_Text.ns">22.11.18 12:10</span><p>
	                <span id="marketB_Text.ns">조회수 : 120</span>
	                <span id="marketB_Text.ns">찜 : 3</span>
				</div>
			</div>
		</div>
	</div>
</div>

            <!-- 보드 리스트 출력 끝 -->
                
             
			
			<!-- 보드 페이지 시작 -->

              <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#">1</a></li>
                  <li class="page-item"><a class="page-link" href="#">2</a></li>
                  <li class="page-item"><a class="page-link" href="#">3</a></li>
				  <li class="page-item"><a class="page-link" href="#">4</a></li>
                  <li class="page-item"><a class="page-link" href="#">5</a></li>
                  <li class="page-item"><a class="page-link" href="#">6</a></li>
                  <li class="page-item"><a class="page-link" href="#">7</a></li>
                  <li class="page-item"><a class="page-link" href="#">8</a></li>
                  <li class="page-item"><a class="page-link" href="#">9</a></li>
                  <li class="page-item"><a class="page-link" href="#">10</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </ul>
              </nav>
             </div>
              <!-- 보드 페이지 끝 -->

      </section>
   </main>
     <!-- End Contents -->
</body>
</html>