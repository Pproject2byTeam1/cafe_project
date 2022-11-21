<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>카페人중독</title>
<meta content="" name="description">
<meta content="" name="keywords">

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
<link href="assets/css/free.css" rel="stylesheet">
<style>
/* .btn-success btn-lg {
   float: right;
} */
.selectpicker{
width:75px;height:35px; border-radius: 5px;
}
._3Espq6{
width :38px;
height:22px;
font-size:0.9rem;
 text-align:center;
}
.parent{
   width :500px;
 display: flex;
  flex-direction: column;
}
.name{
font-size:0.9rem;
  margin-top:auto;

vertical-align :bottom;

}
._1R-fi-{
margin-bottom: 0.5rem;
    color: #2b2d36;
    line-height: 1.5;
    font-weight: 700;
    font-size: 1.5rem;
    letter-spacing: -0.01875rem;
    margin-bottom: 0;
    color: var(--gray-600);
    text-align: center;
}


.jdc{
text-align: center;
}
 
.son_name{
   font-size: 13px; font-weight:bold;
}
.son_date{
   font-size: 13px;
}
.son_time{
   font-size: 13px; 
}
 
</style>
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
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-1">
			</div>
			<div class="col-md-10">
			  <div class="card">
            <div class="card-body">
              <h5 class="card-title">정모하는 날</h5>
			<div>
				시작일 &emsp;  &emsp;  &emsp; &emsp; &emsp; &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;종료일
			
			
			</div>
              <!-- Floating Labels Form -->
              <form class="row g-3">
                <div class="col-md-3">
                  <div class="form-floating">
                    <input type="date" class="form-control" id="floatingName">
                
                  </div>
                </div><br>
                
                <div class="col-md-3">
                  <div class="form-floating">
                    <input type="date" class="form-control" id="floatingEmail">
                   
                  </div>
                </div>
               <div class="col-md-4">
                  <div class="form-floating mb-3">
                    <select class="form-select" id="floatingSelect" aria-label="State">
                      <option >참여</option>
                      <option >미참여</option>
                   
                    </select>
                 
                  </div>
                </div>
                <hr>
                <div class="col-10">
                  <div class="form-floating">
                    <textarea class="form-control" placeholder="내용" id="floatingTextarea" style="height: 100px;"></textarea>
                   
                  </div>
                </div>
                <hr>
                     <div class="col-lg-12">
	          <div class="card">
	            <div class="card-body row">
	            <h5 class="card-title">댓글</h5>
	            <hr><br>
	            <div>
	            <div class="form-floating col-lg-10 ">
	            <div class="row">
                      <textarea class="form-control" placeholder="답글을 작성해주세요" style="height: 50px;"></textarea>
                      
                      
                </div>
	             <div class="insertbutton col-lg-2 ">
                    <button type="submit" class="btn btn-primary">작성</button>
                  </div>
                  </div>
              
	            </div>
	            <div class="col-sm-2">
	              <table>
		             <tr>
			          	<td> <i class="ri-account-circle-fill" ></i></td>
			             <td><h5 class="card-title">닉네임1</h5></td>
		             </tr>
	              </table>
	            </div>
	
	              <div class="col-sm-6 align-self-center"><p class="text-justify ">ㅊㅊ</p></div>
	              <div class="col-sm-3 align-self-center text-lg-end">11 / 25 | 01 : 10 &nbsp;&nbsp;&nbsp;<a href="#" class="btn btn-warning">삭제</a></div>
	              <hr>
	            </div>
	            <div class="card-body row">
	            <div class="col-sm-2">
	              <table>
		             <tr>
			            <td> <i class="ri-account-circle-fill" ></i></td>
			             <td><h5 class="card-title">닉네임2</h5></td>
		             </tr>
	              </table>
	            </div>
	             
	              <div class="col-sm-6 align-self-center"><p class="text-justify"></div>
	          <div class="col-sm-3 align-self-center text-lg-end">11 / 25 | 01 : 10 &nbsp;&nbsp;&nbsp;<a href="#" class="btn btn-warning">삭제</a></div>
	              <hr>
	            </div>
				<ul class="pagination justify-content-center">
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#">1</a></li>
                  <li class="page-item"><a class="page-link" href="#">2</a></li>
                  <li class="page-item"><a class="page-link" href="#">3</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </ul>	            
	          </div>

	        </div>
            <!-- End 댓글 -->
                
              </form><!-- End floating Labels Form -->

            </div>
          </div>

        </div>
			
			
			
			
			
			
			</div>
			<div class="col-md-1">
			</div>
		</div>
	</div>
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