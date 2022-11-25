<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  
  <!-- 무한 스크롤 -->
  <script src="https://unpkg.com/jscroll/dist/jquery.jscroll.min.js"></script>
  
  <!-- 무한 스크롤 js 코드 -->
	<script type="text/javascript">
	
		$(document).ready(function(){
			
			$('#autoScroll').jscroll({
				autoTrigger: true,
				loadingHtml: '<div class="spinner-border" role="status"><span class="visually-hidden">Loading...</span></div>',
				nextSelector: 'a.nextPage:last',
				contentSelector: '#columns',
				padding: 20
			});
			
		});
  	
  	</script>

</head>

<body>

  <main id="main" class="main">
    <section class="section dashboard">
      <div class="row">
		<!-- columns -->
        <div class="col-md-10">
          <div class="row">
          	  <!-- 쪽지리스트 -->
	          <div class="card">
	            <div class="card-body">
	              <h5 class="card-title">쪽지함</h5>
	              <div class="filter">
	                  <a class="icon" href="#" id = "close"><i class="bi bi-x-lg"></i></a>
                </div>
                <hr>
                <div class="row" ><!-- 쪽지 페이지 1면 -->
                <div id="memolist">
                <c:if test="${messagelist == null || fn:length(messagelist) == 0}">
					<p>보관중인 쪽지가 없습니다</p>
				</c:if>
  				<c:forEach var="list" items="${messagelist}" varStatus="status">
	        		<div class="col-sm-12">
                	  <div class="form-check">
                	      <input class="form-check-input" type="checkbox" value="${list.m_idx}">
			              <table>
				             <tr>
					             <td><img src="image/rank_icon/${list.send_rank}.gif"></td>
					             <td>
					             <a data-bs-toggle="dropdown"><h5 class="card-title">${list.send_nick}</h5></a>
					             	<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
				                    	<li class="anw_memo"><a class="dropdown-item sendToMemo" href="write_memo.do?sender_id=${list.send_id}" >답장 보내기</a></li>
				                    </ul>
					             </td>
					             <td>${list.m_date}</td>
				             </tr>
				             <tr>
				             	<td colspan="3"><p>${list.m_content}</p></td>
				             </tr>
				          </table>
		              </div>
		            </div>
		            <hr>
  				</c:forEach>
  				</div>
		            <!-- 기능버튼들 나중에 작성 -->
		            <div class="col-sm-9 ">
		            	 <a href="write_memo.do"><button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#disablebackdrop" id="writememo">
			               쪽지 작성하기
			              </button></a>
		            </div>
		            <div  class="col-sm-3 text-lg-end">
	              		<!-- Disabled Backdrop Modal -->
			              <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#disablebackdrop">
			                삭제
			              </button>
			              <div class="modal fade" id="disablebackdrop" tabindex="-1" data-bs-backdrop="false">
			                <div class="modal-dialog">
			                  <div class="modal-content">
			                    <div class="modal-header">
			                      <h5 class="modal-title">경고</h5>
			                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			                    </div>
			                    <div class="modal-body">
			                      정말로 삭제하시겠습니까?
			                    </div>
			                    <div class="modal-footer">
			                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
			                      <button type="button" class="btn btn-primary" id="deleteMemo" data-bs-dismiss="modal">확인</button>
			                    </div>
			                  </div>
			                </div>
			              </div><!-- End Disabled Backdrop Modal-->
	              	</div>
                </div>
	          </div><!-- End 쪽지리스트 -->
          </div>
         </div>
         <!-- columns -->
      </div>
      </div>
    </section>

  </main><!-- End #main -->

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
  
  <script type="text/javascript">
  $(function(){
	  $("#close").click(function(){
	    window.close();
	  });
	  
	  $(document).on('click', '#deleteMemo', function(){//삭제시 재갱신s
		  var chk_arr = [];
		  $('.form-check-input:checked').each(function(){
			  chk_arr.push($(this).val());
		  })
		  const data = {
			  "idxarr" : chk_arr,
			  "delCheckNo": chk_arr.length
			  } 
		  console.log(chk_arr);
		  /*console.log(chk_arr.length); */
		  $.post("delete_memo.do", data, function(responsedata){
                //응답이 왔고 정상 건 이라면
                console.log(responsedata);
			  	
                $('#memolist').empty();
                $('#memolist').append(responsedata);
             });
	  	 });
	});
  </script>

</body>

</html>