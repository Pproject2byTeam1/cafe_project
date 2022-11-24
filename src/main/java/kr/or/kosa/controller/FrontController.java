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
import kr.or.kosa.service.DataBoardListService;
import kr.or.kosa.service.DataWriteService;
import kr.or.kosa.service.Img_Board_List_Service;
import kr.or.kosa.service.Img_Board_Read_Service;
import kr.or.kosa.service.MarketBoardListService;
import kr.or.kosa.service.MarketBoardReadService;
import kr.or.kosa.service.MessageDeleteService;
import kr.or.kosa.service.MessageListService;
import kr.or.kosa.service.MessageWriteService;
import kr.or.kosa.service.Message_Add_Service;
import kr.or.kosa.service.RapportListService;
import kr.or.kosa.service.Regular_Board_List_Service;
import kr.or.kosa.service.UserListService;
import kr.or.kosa.service.User_Edit;
import kr.or.kosa.service.User_details;


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

		if(urlcommand.equals("/img_board_list.do")) { //이미지 게시판 목록
			
			action = new Img_Board_List_Service();
			forward = action.execute(request, response);
			
		} else if(urlcommand.equals("/img_board_read.do")) {
			
			action = new Img_Board_Read_Service();
			forward = action.execute(request, response);
			
		} else if(urlcommand.equals("/calendar_list.do")) {
			
			forward = new ActionForward();
	        forward.setRedirect(false);
	        forward.setPath("/WEB-INF/view/calendar_list.jsp");
	        
		} else if(urlcommand.equals("/memo_list.do")) {//메모 리스트 보기
			
			action = new MessageListService();
			forward = action.execute(request, response);
			
		} else if(urlcommand.equals("/delete_memo.do")) {//메모 삭제
			
			action = new MessageDeleteService();
			forward = action.execute(request, response);
			
		} else if(urlcommand.equals("/user_list.do")) { //유저 정보들 보기
			
			action = new UserListService();
			forward = action.execute(request, response);
			
		} else if(urlcommand.equals("/databoard_list.do")){ // 데이터 게시판 리스트
			
			action = new DataBoardListService();
			forward = action.execute(request, response);
			
		}else if(urlcommand.equals("/write_memo.do")) {//메모 작성화면 ㄱㄱ
			
			action = new MessageWriteService();
			forward = action.execute(request, response);
			
		}else if(urlcommand.equals("/write_memo_ok.do")) {//메모 작성 실행
			
			action = new Message_Add_Service();
			forward = action.execute(request, response);
			
		}else if(urlcommand.equals("/user_details.do")) {
			action = new User_details();
			forward = action.execute(request, response);
			
		}else if(urlcommand.equals("/user_edit.do")) {
			action = new User_Edit();
			forward = action.execute(request, response);
			
		}else if(urlcommand.equals("/regular_list.do")) {
			action = new Regular_Board_List_Service();
			forward = action.execute(request, response);
			
		}else if(urlcommand.equals("/rapport_list.do")){ // 신고리스트
		
			action = new RapportListService();
			forward = action.execute(request, response);
		}else if(urlcommand.equals("/data_content.do")) {
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/data_content.jsp");
			
		}else if(urlcommand.equals("/board_datacontentright.do")) {
			
			action = new DataWriteService();
			forward = action.execute(request, response);
			
		}else if(urlcommand.equals("/marketboard_list.do")){ // 데이터 게시판 리스트
			
			action = new MarketBoardListService();
			forward = action.execute(request, response);
			
		}else if(urlcommand.equals("/marketboard_read.do")){ // 데이터 게시판 리스트
			
			action = new MarketBoardReadService();
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