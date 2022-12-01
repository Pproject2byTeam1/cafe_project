package kr.or.kosa.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.service.AdminMainEditService;
import kr.or.kosa.service.BoardContentService;
import kr.or.kosa.service.CafeMain;
import kr.or.kosa.service.Calender_Board_List_Service;
import kr.or.kosa.service.ChartService;
import kr.or.kosa.service.CheckBoardService;
import kr.or.kosa.service.DataBoardEditOkService;
import kr.or.kosa.service.DataBoardEditService;
import kr.or.kosa.service.DataBoardListService;
import kr.or.kosa.service.DataBoardWriteService;
import kr.or.kosa.service.DataBoardWriteViewService;
import kr.or.kosa.service.Data_Board_Post_Service;
import kr.or.kosa.service.DeleteAttendanceService;
import kr.or.kosa.service.DeleteOkService;
import kr.or.kosa.service.ImgBoardDeleteService;
import kr.or.kosa.service.ImgBoardModifyService;
import kr.or.kosa.service.ImgBoardModifyServiceOk;
import kr.or.kosa.service.ImgBoardWriteService;
import kr.or.kosa.service.ImgBoardWriteViewService;
import kr.or.kosa.service.Img_Board_List_Service;
import kr.or.kosa.service.Img_Board_Read_Service;
import kr.or.kosa.service.InsertAttendanceService;
import kr.or.kosa.service.LoginCheckService;
import kr.or.kosa.service.Login_Service;
import kr.or.kosa.service.Login_View_Service;
import kr.or.kosa.service.Logout_Service;
import kr.or.kosa.service.MarketBoardDeleteService;
import kr.or.kosa.service.MarketBoardEditOkService;
import kr.or.kosa.service.MarketBoardEditService;
import kr.or.kosa.service.MarketBoardListService;
import kr.or.kosa.service.MarketBoardReadService;
import kr.or.kosa.service.MarketBoardWriteService;
import kr.or.kosa.service.MarketBoardWriteViewService;
import kr.or.kosa.service.MessageDeleteService;
import kr.or.kosa.service.MessageListService;
import kr.or.kosa.service.MessageWriteService;
import kr.or.kosa.service.Message_Add_Service;
import kr.or.kosa.service.RankEditListService;
import kr.or.kosa.service.RankInfoService;
import kr.or.kosa.service.RapportListService;
import kr.or.kosa.service.RegularBoardDeleteService;
import kr.or.kosa.service.RegularBoardEditOkService;
import kr.or.kosa.service.RegularBoardEditService;
import kr.or.kosa.service.RegularBoardWriteOkService;
import kr.or.kosa.service.RegularBoardWriteService;
import kr.or.kosa.service.Regular_Board_List_Service;
import kr.or.kosa.service.Regular_Board_Post_Service;
import kr.or.kosa.service.ReplyDataWriteService;
import kr.or.kosa.service.ReplyDataWriteViewService;
import kr.or.kosa.service.ReplyRegularWriteService;
import kr.or.kosa.service.ReplyRegularWriteViewService;
import kr.or.kosa.service.SnsCheckService;
import kr.or.kosa.service.UpdatePwdService;
import kr.or.kosa.service.UserActivityService;
import kr.or.kosa.service.UserDeleteService;
import kr.or.kosa.service.UserInfoService;
import kr.or.kosa.service.UserKick;
import kr.or.kosa.service.UserListService;
import kr.or.kosa.service.UserUpdateService;
import kr.or.kosa.service.User_Edit;
import kr.or.kosa.service.User_details;
import kr.or.kosa.service.adminInfoService;
import kr.or.kosa.service.adminUpdateService;
import kr.or.kosa.service.getBoardList;
import kr.or.kosa.service.idVerification;
import kr.or.kosa.service.nickVerification;
import kr.or.kosa.service.userVerification;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlcommand = requestURI.substring(contextPath.length());

		Action action = null;
		ActionForward forward = null;

		// 회원가입, 로그인
		if (urlcommand.equals("/register.do")) { // 회원가입

			action = null;// 일단 내버려 둠..
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/login_view.do")) { // 로그인 화면 뿌리기

			action = new Login_View_Service();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/loginok.do")) { // 로그인

			action = new Login_Service();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/snsLogin.do")) { // sns 로그인 (+회원가입 페이지) 이동

			action = new SnsCheckService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/registerok.do")) { // sns회원 등록

			action = new LoginCheckService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/logout.do")) { // 로그아웃

			action = new Logout_Service();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/deleteUser.do")) { // 탈퇴

			action = new UserDeleteService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/user_list.do")) { // 유저 정보들 보기

			action = new UserListService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/user_details.do")) { // 유저 세부정보
			action = new User_details();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/adminInfo.do")) { // 관리자 정보 페이지

			action = new adminInfoService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/adminUpdate.do")) { // 관리자 정보 변경

			action = new adminUpdateService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/user_edit.do")) { // 유저 정보 수정 (관리자페이지-범종)
			action = new User_Edit();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/rapport_list.do")) { // 신고리스트 (관리자 페이지)

			action = new RapportListService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/userupdate.do")) { // 유저정보 수정 (개인정보수정 -태호)

			action = new UserUpdateService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/user_activity.do")) { // 유저활동내역 페이지 이동

			action = new UserActivityService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/userinfo.do")) { // 유저정보 페이지 이동
			action = new UserInfoService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/userVerification.do")) { // 유저정보 검증

			action = new userVerification();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/nickVerification.do")) { // 유저닉네임 검증

			action = new nickVerification();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/boardContent.do")) { // 사이드바 링크 글 내용 보기 중간 터널

			action = new BoardContentService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/img_board_list.do")) { // 이미지 게시판 목록

			action = new Img_Board_List_Service();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/img_board_read.do")) { // 이미지 게시판 읽기

			action = new Img_Board_Read_Service();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/imgboardWriteView.do")) { // 이미지 게시판 글 쓰기 view

			action = new ImgBoardWriteViewService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/board_writeok.do")) { // 이미지 게시판 글쓰기 ok

			action = new ImgBoardWriteService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/imgboardDelete.do")) { // 이미지 게시판 글 삭제

			action = new ImgBoardDeleteService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/imgboardModifyView.do")) { // 이미지 게시판 글 수정 view

			action = new ImgBoardModifyService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/imgboardmodify.do")) { // 이미지 게시판 글 수정 로직

			action = new ImgBoardModifyServiceOk();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/calendar_list.do")) { // 달력

			action = new Calender_Board_List_Service();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/memo_list.do")) {// 메모 리스트 보기

			action = new MessageListService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/delete_memo.do")) {// 메모 삭제

			action = new MessageDeleteService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/write_memo.do")) {// 메모 작성화면 ㄱㄱ

			action = new MessageWriteService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/databoard_list.do")) { // 데이터 게시판 리스트

			action = new DataBoardListService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/write_memo_ok.do")) {// 메모 작성 실행

			action = new Message_Add_Service();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/regular_edit.do")) {// 자유 게시판 수정

			action = new RegularBoardEditService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/regular_editok.do")) {// 자유 게시판 수정완료

			action = new RegularBoardEditOkService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/regular_reWriteView.do")) { // 자유 게시판 답글 작성 페이지 이동

			action = new ReplyRegularWriteViewService();
			forward = action.execute(request, response);
		} else if (urlcommand.equals("/regular_rewriteok.do")) { // 자유 게시판 답글 작성

			action = new ReplyRegularWriteService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/regular_post.do")) { // 자유게시판 검색

			action = new Regular_Board_Post_Service();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/regular_list.do")) { // 자유게시판 목록
			action = new Regular_Board_List_Service();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/regular_write.do")) { // 자유게시판 글쓰기

			action = new RegularBoardWriteService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/marketboard_list.do")) { // 거래 게시판 리스트

			action = new MarketBoardListService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/marketboard_read.do")) { // 거래 게시판 읽기

			action = new MarketBoardReadService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/marketboard_delete.do")) { // 거래 게시판 삭제

			action = new MarketBoardDeleteService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/marketboard_writeok.do")) { // 거래 게시판 쓰기

			action = new MarketBoardWriteService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/marketboard_write.do")) { // 거래 게시판 쓰기 페이지 가기

			action = new MarketBoardWriteViewService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/databoard_read.do")) { // 자료 게시판 읽기

			action = new Data_Board_Post_Service();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/data_delete.do")) {// 자료 게시판 삭제하기

			action = new DeleteOkService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/databoard_write.do")) {// 자료 게시판 작성페이지 가기

			action = new DataBoardWriteViewService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/databoard_writeok.do")) {// 자료 게시판 작성하기

			action = new DataBoardWriteService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/databoard_rewrite.do")) { // 자료 게시판 답글 작성 페이지 이동

			action = new ReplyDataWriteViewService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/data_rewriteok.do")) { // 자료 게시판 답글 작성

			action = new ReplyDataWriteService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/databoard_edit.do")) { // 자료 게시판 수정

			action = new DataBoardEditService();
			
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/databoard_editok.do")) { // 자료 게시판 수정

			action = new DataBoardEditOkService();
			forward = action.execute(request, response);
		} else if (urlcommand.equals("/checkBoard.do")) { // 출석 게시판 이동

			action = new CheckBoardService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/regularwriteok.do")) { // 자유게시판 글쓰기

			action = new RegularBoardWriteOkService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/userkick.do")) { // 자유게시판 글쓰기

			action = new UserKick();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/windowclose.do")) { // 게시판 글 작성

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/view/windowClose.jsp");

		} else if (urlcommand.equals("/regulardelete.do")) { // 자유게시판 글쓰기

			action = new RegularBoardDeleteService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/insertAttendance.do")) { // 출석게시판 글쓰기

			action = new InsertAttendanceService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/deleteAttendance.do")) { // 출석게시판 글삭제

			action = new DeleteAttendanceService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/rankedit.do")) { // 랭크변환리스트

			action = new RankEditListService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/cafemain.do")) { // 카페 메인 화면

			action = new CafeMain();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/marketboardEdit.do")) { // 거래 게시판 읽기

			action = new MarketBoardEditService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/marketboardEditOk.do")) { // 거래 게시판 읽기

			action = new MarketBoardEditOkService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/adminmaineditservice.do")) { // 관리자 메인 관리 페이지

			action = new AdminMainEditService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/chart.do")) { // 관리자 메인 관리 페이지

			action = new ChartService();
			forward = action.execute(request, response);

		}  else if (urlcommand.equals("/userUpdatePwd.do")) { // 사용자 비밀번호 변경

			action = new UpdatePwdService();
			forward = action.execute(request, response);

		}  else if (urlcommand.equals("/idVerification.do")) { // 아이디 검증

			action = new idVerification();
			forward = action.execute(request, response);

		}  else if (urlcommand.equals("/getBoardList.do")) { // 아이디 검증

			action = new getBoardList();
			forward = action.execute(request, response);
			int b_code = Integer.parseInt(request.getParameter("b_code"));
			
		} else if (urlcommand.equals("/inforank.do")) { // 등급안내페이지

			action = new RankInfoService();
			forward = action.execute(request, response);

		} 
		
		if (forward != null) {
			if (forward.isRedirect()) { // true 페이지 재 요청 (location.href="페이지"
				response.sendRedirect(forward.getPath());
			} else { // 기본적으로 forward ....
						// 1. UI 전달된 경우
						// 2. UI + 로직
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}