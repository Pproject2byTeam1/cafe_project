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


  <!-- Template Main CSS File -->

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>  
  
  <!-- sweetalert -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  
</head>
<body>
  
  
   <main id="main" class="main" style="background-color: #f6f9ff; border-radius: 25px;">
   
   
	<div class="container">
	<br>
		<div class="card">
			<div class="card-body">
				<div class="pagetitle" >
				
				
				
				 <!-- 여기서부터 작성 와랄ㄹ라  -->
			         <p><h1>&nbsp;회원 조회</h1></p>
			         
			         <nav>
			            <ol class="breadcrumb">
			            </ol>
			         </nav>
     			 </div>
				<!-- 서블릿 아래 action에다가 적어주세용 -->
      <form action="user_edit.do" method="post" id="myForm">
         <div class="container">
            <div class="row col-md-12">  
               <div>
                  <div class="mb-3 row">
                     <label for="staticEmail" class="col-sm-2 col-form-label">이메일</label>
                     <div class="col-sm-10">
                       <input type="text" class="form-control-plaintext" name="id" id="staticEmail" value="${userlist.email_id}" readonly>
                     </div>
                  </div>
                  <hr>
                  <div class="mb-3 row">
                     <label for="inputPassword" class="col-sm-2 col-form-label">비밀번호</label>
                     <div class="col-sm-8">
                       <input type="password" class="form-control" name="password" id="inputPassword" value="${userlist.password}">
                     </div>
                     <div class="col-sm-2">
                        <input type="button" class="form-control" value="show" onclick="HidenShow()">
                      </div>
                  </div>
                  <hr>
                  <div class="mb-3 row">
                     <label for="inputNickname" class="col-sm-2 col-form-label">별명</label>
                     <div class="col-sm-8">
                       <input type="text" class="form-control" name="nick" id="inputNickname" value="${userlist.name}">
                     </div>
                  </div>
                  <hr>
                  <div class="mb-3 row">
                     <label for="inputNickname" class="col-sm-2 col-form-label">관리등급</label>
                     <div class="col-sm-8">
                       <select class="form-select" name="isAdmin" form="myForm">
                       <c:choose>
							<c:when test="${userlist.isAdmin eq 'F'}">
							   <option value="F" selected>F</option>
							   <option value="S">S</option>
							   <option value="X">X</option>
						    </c:when>
						    <c:when test="${userlist.isAdmin eq 'S'}">
							   <option value="F" >F</option>
							   <option value="S" selected>S</option>
							   <option value="X">X</option>
						    </c:when>
						    <c:when test="${userlist.isAdmin eq 'X'}">
							   <option value="F" >F</option>
							   <option value="S">S</option>
							   <option value="X" selected>X</option>
						    </c:when>
					   </c:choose>
	   					</select>
                     </div>
                  </div>
                  <hr>
               </div> 
            </div>
         </div>
   
   
         <br><br>
   
   
         <div class="pagetitle" >
            
            <p><h1>&nbsp;부가설정</h1></p>
            <nav>
               <ol class="breadcrumb">
               </ol>
            </nav>
         </div>
   
         
         
   
   
         <div class="container">
            <div class="row col-md-12">  
               <div>
                  <div class="mb-5 row">
                     <label for="inputPassword" class="col-sm-2 col-form-label text-center pt-5">포인트</label>
   
                     <div class="col-sm-10">
                        <div class="row">
                           <div class="col-sm-3">
                              <label class="col-form-label">현재포인트:</label>
                           </div>
                           <div class="col-sm-5">
                              <!-- 여기 id="userpoint" 의 value값을 바꾸면 id="inputUserPoint"도 같이 바뀌도록 설정해놓음 -->
                              <input type="text" class="form-control-plaintext text-left" id="userpoint" value="${userlist.point}" readonly>
                           </div>
                        </div>
                        <div class="row">
                           <div class="col-sm-5">
                              <input type="text" class="form-control" name="point" id="inputUserPoint">
                           </div>
   
                           <div class="col-sm-5">
                              <select class="form-select" id="selectPoint">
                                 <option value="0">준회원(0p)</option>
                                 <option value="100">정회원(100p)</option>
                                 <option value="200">우수회원(200p)</option>
                                 <option value="300">특별회원(300p)</option>
                              </select>
                           </div>
                           
                        </div>
                        
                        
                       <br>
   
                        <input type="button" class="btn btn-danger btn-sm" value="-100" id="minus100">
                        <input type="button" class="btn btn-danger btn-sm" value="-50" id="minus50">
                        <input type="button" class="btn btn-danger btn-sm" value="-10" id="minus10">
   
                        <input type="button" class="btn btn-info btn-sm" value="+10" id="plus10">
                        <input type="button" class="btn btn-info btn-sm" value="+50" id="plus50">
                        <input type="button" class="btn btn-info btn-sm" value="+100" id="plus100">
                        
             
   
                     </div>
                  </div>
                  <hr>
                  <div class="d-grid gap-2">
                     <button class="btn btn-primary" type="submit" id="change">바꾸기</button>
                  </div>
               </div> 
            </div>
         </div>
      </form>
      
      
  <!-- 여기까지만 작성  -->
			
			
			</div>
		</div>
		<br>
	</div>
 
      


  </main>



</body>

<script>

   /* 회원 비밀번호 보이기 닫기 */
   function HidenShow() {
   let x = document.getElementById("inputPassword");
      if (x.type === "password") {
         x.type = "text";
      } else {
         x.type = "password";
      }
   }
   
	
   /* 회원의 포인트값 text쪽에 넣어주기 */
   document.getElementById("inputUserPoint").value = document.getElementById('userpoint').value
   
   
   
   /* 회원의 포인트값 지정 */
   	document.getElementById("minus100").onclick = () =>{
       document.getElementById("inputUserPoint").value -= 100;
    }
    document.getElementById("minus50").onclick = () =>{
       document.getElementById("inputUserPoint").value -= 50;
    }
    document.getElementById("minus10").onclick = () =>{
       document.getElementById("inputUserPoint").value -= 10;
    }

    document.getElementById("plus100").onclick = () =>{
       let a = document.getElementById("inputUserPoint").value
       document.getElementById("inputUserPoint").value = Number(a) + Number(100);
    }
    document.getElementById("plus50").onclick = () =>{
       let a = document.getElementById("inputUserPoint").value
       document.getElementById("inputUserPoint").value = Number(a) + Number(50);
    }
    document.getElementById("plus10").onclick = () =>{
       let a = document.getElementById("inputUserPoint").value
       document.getElementById("inputUserPoint").value = Number(a) + Number(10);
    }

    document.getElementById("selectPoint").addEventListener("click", ()=>{
       document.getElementById("inputUserPoint").value = document.getElementById("selectPoint").value
    });
    
    
    
     document.getElementById("change").onclick = () =>{
    	swal("완료","정보가 변경되었습니다.",'success');
        //window.close();
        
     } 
    

 </script>
</html>
