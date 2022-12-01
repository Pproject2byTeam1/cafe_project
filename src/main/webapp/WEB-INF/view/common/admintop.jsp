<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
    <div class="d-flex align-items-center justify-content-between">
      <a href="cafemain.do" class="logo d-flex align-items-center">
        <img src="upload/${banner.cafe_icon}" alt="icon" onerror="this.onerror=null; this.src='assets/img/logo.png'">
        <span class="d-none d-lg-block">${banner.cafe_name}</span>
      </a>
      <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->



    <nav class="header-nav ms-auto">
      <ul class="d-flex align-items-center">

        <li class="nav-item d-block d-lg-none">
        </li><!-- End Search Icon-->
	</ul>
	</nav>	

    <nav class="header-nav ms-auto">
      <ul class="d-flex align-items-center">
      

        <li class="nav-item d-block d-lg-none"></li><!-- End Search Icon-->
        
		<c:if test="${member.isAdmin eq 'M'}">
       		<ul class="header-nav ms-auto">
        	   <li class="nav-item dropdown">
            	   <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i>메인</a>
            	   <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
            	       <li><a class="dropdown-item" href="adminmaineditservice.do">메인페이지 관리</a></li>
            	   </ul>
           		</li>
       		</ul>
       </c:if>
       <c:if test="${member.isAdmin eq 'S' || member.isAdmin eq 'M'}">
       <ul class="header-nav ms-auto">
           <li class="nav-item dropdown">
               <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i>회원</a>
               <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                   <li><a class="dropdown-item" href="user_list.do">회원 조회</a></li>
                   <li><a class="dropdown-item" href="rapport_list.do">신고 현황</a></li>
               </ul>
           </li>
       </ul>
       </c:if>
       <c:if test="${member.isAdmin eq 'M'}">
       		<ul class="header-nav ms-auto">
           		
       		</ul>
       </c:if>

        


		<c:if test="${member != null}">        
        <li class="nav-item dropdown pe-3 ps-3">

          <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
            <img src="image/rank_icon/${member.rank}.gif" alt="Profile" class="">
            <span class="d-none d-md-block dropdown-toggle ps-2">${member.nick}</span>
          </a><!-- End Profile Iamge Icon -->

          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
            <li class="dropdown-header">
              <h6><img src="image/rank_icon/${member.rank}.gif" alt="Profile" class="">&nbsp ${member.nick }</h6>
              <span>POINT: ${member.point }</span>
            </li>	
            <li>
              <hr class="dropdown-divider">
            </li>

            <li>
              <a class="dropdown-item d-flex align-items-center" href="user_activity.do">
                <i class="bi bi-person"></i>
                <span>나의 활동 내역</span>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>
            
            <li>
              <a class="dropdown-item d-flex align-items-center" href="memo_list.do">
                <i class="ri-mail-line"></i>
                <span>나의 쪽지함</span>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li>
              <a class="dropdown-item d-flex align-items-center" href="userinfo.do">
                <i class="bi bi-gear"></i>
                <span>나의 정보</span>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li>
              <a class="dropdown-item d-flex align-items-center" href="pages-faq.html">
                <i class="bi bi-question-circle"></i>
                <span>예비용</span>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li>
              <a class="dropdown-item d-flex align-items-center" href="logout.do">
                <i class="bi bi-box-arrow-right"></i>
                <span>로그아웃</span>
              </a>
            </li>

          </ul><!-- End Profile Dropdown Items -->
        </li><!-- End Profile Nav -->
        </c:if>
        
        <c:if test="${member == null}">
        
        	<div class="row m-4 pt-3">
        		<p class="col-6"><a href="snsLogin.do">Register</a></p>
    			<p class="col-6"><a href="login_view.do">LOGIN</a></p>
        	</div>
        
        </c:if>

      </ul>
    </nav><!-- End Icons Navigation -->