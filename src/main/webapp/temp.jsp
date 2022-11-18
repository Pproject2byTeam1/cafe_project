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

      <div class="pagetitle" >
         <h1>자료 게시판</h1>
         <nav>
            <ol class="breadcrumb">
               <li class="breadcrumb-item"><a href="index.html">Home</a></li>
               <li class="breadcrumb-item active">자료게시판</li>
            </ol>
         </nav>
      </div>
      <!-- End Page Title -->
      <!--글쓰기 버튼  -->
      <div align="right">
         <button type="button" class="btn btn-success">글쓰기</button>&nbsp;
            <select class="selectpicker" data-width="75px">
                 <option>10개씩</option>
                  <option>15개씩</option>
                  <option>20개씩</option>
             </select>
      </div>

      <!--  <div><br></div>-->
      <!--리스트테이블  -->
      <div class="card">
         <div class="card-body">

            <!-- Table with hoverable rows -->
            <table class="table table-hover">
               <thead>
                  <tr>
             		<th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                  </tr>
               </thead>
               <tbody>
               
                  <tr>
                     <th scope="row"><input type="checkbox"></th>
                     <td>
                     <span class="mt-4 parent"><h3>너구리수명ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ</h3></span><br>
                     <span class="son_name">마들렌</span>
                     <span class="son_date"> 2022.11.15</span>
                     <span class="son_time"> 오후 2시 57분</span>
                     </td>
                     <td></td>
                     
                  <td class="p-5 jdc"><span class="_3Espq6" >
                     <span class="_1R-fi-">9</span><br>
                     <span>조회</span>
                     </span>
                  </td>
                  <td class="p-5 jdc"><span class="_3Espq6">
                     <span class="_1R-fi-">10</span><br>
                     <span>댓글</span>
                     </span>
                  </td>
                  <td class="p-5 jdc"><span class="_3Espq6">
                     <span class="_1R-fi-">600</span><br>
                     <span>추천
                  </td>
                  </tr>
                  
                  <tr>
                     <th scope="row"><input type="checkbox"></th>
                     <td>
                     <span class="mt-4 parent"><h3>너구리수명ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ</h3></span><br>
                     <span class="son_name">마들렌</span>
                     <span class="son_date"> 2022.11.15</span>
                     <span class="son_time"> 오후 2시 57분</span>
                     </td>
                     <td></td>
                     
                  <td class="p-5 jdc"><span class="_3Espq6" >
                     <span class="_1R-fi-">9</span><br>
                     <span>조회</span>
                     </span>
                  </td>
                  <td class="p-5 jdc"><span class="_3Espq6">
                     <span class="_1R-fi-">10</span><br>
                     <span>댓글</span>
                     </span>
                  </td>
                  <td class="p-5 jdc"><span class="_3Espq6">
                     <span class="_1R-fi-">600</span><br>
                     <span>추천
                  </td>
                  </tr>
                  
                  <tr>
                     <th scope="row"><input type="checkbox"></th>
                     <td>
                     <span class="mt-4 parent"><h3>너구리수명</h3></span><br>
                     <span class="son_name">마들렌</span>
                     <span class="son_date"> 2022.11.15</span>
                     <span class="son_time"> 오후 2시 57분</span>
                     </td>
                     <td></td>
                     
                  <td class="p-5 jdc"><span class="_3Espq6" >
                     <span class="_1R-fi-">9</span><br>
                     <span>조회</span>
                     </span>
                  </td>
                  <td class="p-5 jdc"><span class="_3Espq6">
                     <span class="_1R-fi-">10</span><br>
                     <span>댓글</span>
                     </span>
                  </td>
                  <td class="p-5 jdc"><span class="_3Espq6">
                     <span class="_1R-fi-">600</span><br>
                     <span>추천
                  </td>
                  </tr>
                  
               </tbody>
            </table>
            <!-- End Table with hoverable rows -->

         </div>

      </div>
      <!--하단 버튼  -->
      <div align="right">
         <button type="button" class="btn btn-secondary">글쓰기</button>&nbsp;
         <button type="button" class="btn btn-secondary">삭제</button>

      </div>
      
          <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                  <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><<</a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#">1</a></li>
                  <li class="page-item"><a class="page-link" href="#">2</a></li>
                  <li class="page-item"><a class="page-link" href="#">3</a></li>
                   <li class="page-item"><a class="page-link" href="#">4</a></li>
                    <li class="page-item"><a class="page-link" href="#">5</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#">>></a>
                  </li>
                </ul>
              </nav><!-- End Centered Pagination -->
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