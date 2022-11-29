<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
    <div class="d-flex align-items-center justify-content-between">
      <a href="index.jsp" class="logo d-flex align-items-center">
        <img src="assets/img/logo.png" alt="">
        <span class="d-none d-lg-block">카페人중독</span>
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
      
      <c:if test="${member != null}">

        <li class="nav-item d-block d-lg-none"></li><!-- End Search Icon-->
        
		<c:if test="${member.isAdmin eq 'M'}">
       		<ul class="header-nav ms-auto">
        	   <li class="nav-item dropdown">
            	   <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i>메인</a>
            	   <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
            	       <li><a class="dropdown-item" href="adminmain_edit.jsp">메인페이지 관리</a></li>
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
           		<li class="nav-item dropdown">
               		<a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i>통계</a>
               		<ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                   		<li><a class="dropdown-item" href="#!">활동 분석</a></li>
                   		<li><a class="dropdown-item" href="chart.do">회원 분석</a></li>
               		</ul>
           		</li>
       		</ul>
       </c:if>

        <li class="nav-item dropdown">

          <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
            <i class="bi bi-bell"></i>
            <span class="badge bg-primary badge-number">4</span>
          </a><!-- End Notification Icon -->

          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow notifications">
            <li class="dropdown-header">
              You have 4 new notifications
              <a href="#"><span class="badge rounded-pill bg-primary p-2 ms-2">View all</span></a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="notification-item">
              <i class="bi bi-exclamation-circle text-warning"></i>
              <div>
                <h4>좋아요!</h4>
                <p>USER_NICK님이 회원님의 글에 좋아요를 남겼어요.</p>
                <p>30 min. ago</p>
              </div>
            </li>

            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="notification-item">
              <i class="bi bi-x-circle text-danger"></i>
              <div>
                <h4>답글</h4>
                <p>USER_NICK님이 회원님의 글에 답글을 남겼어요.</p>
                <p>1 hr. ago</p>
              </div>
            </li>

            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="notification-item">
              <i class="bi bi-check-circle text-success"></i>
              <div>
                <h4>대댓글</h4>
                <p>USER_NICK님이 회원님의 댓글에 댓글을 남겼어요.</p>
                <p>2 hrs. ago</p>
              </div>
            </li>

            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="notification-item">
              <i class="bi bi-info-circle text-primary"></i>
              <div>
                <h4>등급업!</h4>
                <p>RANK_NAME에서 RANK_NAME으로 등급업!</p>
                <p>4 hrs. ago</p>
              </div>
            </li>

            <li>
              <hr class="dropdown-divider">
            </li>
            <li class="dropdown-footer">
              <a href="#">뺄 기능</a>
            </li>

          </ul><!-- End Notification Dropdown Items -->

        </li><!-- End Notification Nav -->
        </c:if>
        
		<c:if test="${member != null}">
        <li class="nav-item dropdown">

          <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
            <i class="bi bi-chat-left-text"></i>
            <span class="badge bg-success badge-number">3</span> <!-- 총 송신자id 셀렉트해서 쪽지개수 카운트 -->
          </a><!-- End Messages Icon -->

          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow messages">
            <li class="dropdown-header">
              USER_NICK 님의 최근 3개 쪽지
              <a href="#"><span class="badge rounded-pill bg-primary p-2 ms-2">전체 보기</span></a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="message-item">
              <a href="#">
                <img src="assets/img/messages-1.jpg" alt="" class="rounded-circle">
                <div>
                  <h4>Maria Hudson</h4>
                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                  <p>4 hrs. ago</p>
                </div>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="message-item">
              <a href="#">
                <img src="assets/img/messages-2.jpg" alt="" class="rounded-circle">
                <div>
                  <h4>Anna Nelson</h4>
                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                  <p>6 hrs. ago</p>
                </div>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="message-item">
              <a href="#">
                <img src="assets/img/messages-3.jpg" alt="" class="rounded-circle">
                <div>
                  <h4>David Muldon</h4>
                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                  <p>8 hrs. ago</p>
                </div>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li class="dropdown-footer">
              <a href="#">Show all messages</a>
            </li>

          </ul><!-- End Messages Dropdown Items -->

        </li><!-- End Messages Nav -->
		</c:if>


		<c:if test="${member != null}">        
        <li class="nav-item dropdown pe-3">

          <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
            <img src="image/rank_icon/${member.rank}.gif" alt="Profile" class="">
            <span class="d-none d-md-block dropdown-toggle ps-2">${member.nick}</span>
          </a><!-- End Profile Iamge Icon -->

          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
            <li class="dropdown-header">
              <h6><img src="image/rank_icon/${member.rank}.gif" alt="Profile" class="">${member.nick }</h6>
              <span>RANK_NAME (POINT)</span>
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
        		<p class="col-6"><a href="snsLogin.do">JOIN</a></p>
    			<p class="col-6"><a href="login_view.do">LOGIN</a></p>
        	</div>
        
        </c:if>

      </ul>
    </nav><!-- End Icons Navigation -->