package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dao.Board_Info_Dao;
import kr.or.kosa.dao.CommentsDao;
import kr.or.kosa.dao.MarketBoardDao;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dao.Yes_Dao;
import kr.or.kosa.dto.Board_Info;
import kr.or.kosa.dto.MarketBoard;
import kr.or.kosa.dto.User;
import net.sf.json.JSONObject;

@WebServlet("/MarketSearch")
public class MarketSearch extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MarketSearch() {
      super();
   }

   private void doProcess(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      try {

         // 사이드 바
         Board_Info_Dao infodao = new Board_Info_Dao();
         List<Board_Info> infolist = infodao.getSideBoardList();

         String search = request.getParameter("search");
         String sold = request.getParameter("sold");
         int b_code = Integer.parseInt(request.getParameter("b_code"));
         System.out.println("1" + search);
         if (search == null || search.trim().equals("")) {
            search = "no";
         }
         if (sold == null || sold.trim().equals("")) {
            sold = "all";
         }
         System.out.println("2" + search);
         MarketBoardDao market_dao = new MarketBoardDao();
         Yes_Dao ydao = new Yes_Dao();
         CommentsDao cdao = new CommentsDao();
         UserDao udao = new UserDao();

         // 상세보기 >> 다시 LIST 넘어올때 >> 현재 페이지 설정
         String ps = request.getParameter("ps");
         String cp = request.getParameter("cp");
         
         if (ps == null || ps.trim().equals("")) {
            ps = "8"; // List 페이지 처음 호출 경우 -> 8개씩
         }

         if (cp == null || cp.trim().equals("")) {
            cp = "1"; // default 값 설정 -> 1번째 페이지 보겠다
         }

         int pagesize = Integer.parseInt(ps);
         int cpage = Integer.parseInt(cp);
         int pagecount = 0;
         int soldcount = market_dao.countSoldF(b_code);
         int totalboardcount = 0;
         // 게시물 총 건수
         if (sold.equals("판매중")) {
            totalboardcount = soldcount;
         } else {
            totalboardcount = market_dao.countMarket(b_code);
         }

         if (totalboardcount % pagesize == 0) {
            pagecount = totalboardcount / pagesize;
         } else {
            pagecount = (totalboardcount / pagesize) + 1;
         }

         List<MarketBoard> list = market_dao.searchMarket(b_code, cpage, pagesize, sold, search);
         
     	 //yes, 댓글수
         List<Integer> yescountlist = new ArrayList<Integer>();
         List<Integer> commentcountlist = new ArrayList<Integer>();
         List<Integer> ranklist = new ArrayList<Integer>();
         
         for (MarketBoard mb : list) {
            int idx = mb.getIdx();
            String email_id = mb.getEmail_id();
            int yescount = ydao.getYesCountBy_idx(idx);
            int commentcount = cdao.getCommentCountBy_idx(idx);
            User user = udao.selectUserById(email_id);
            int rank = user.getRank();
            
            yescountlist.add(yescount);
            commentcountlist.add(commentcount);
            ranklist.add(rank);
            
         }
         
         JSONObject json = new JSONObject();
         
         json.put("yescountlist", yescountlist);
         json.put("commentcountlist", commentcountlist);
         json.put("ranklist", ranklist);
         
         // page.put("pager", pager);
         json.put("page", pagesize);
         json.put("cpage", cpage);
         json.put("pagecount", pagecount);
         json.put("totalboardcount", totalboardcount);
         json.put("sold", sold);
         json.put("search", search);
         json.put("soldcount", soldcount);
         json.put("infolist", infolist);
         
         json.put("list", list);
         
         out.print(json);

      } catch (Exception e) {
    	  System.out.println("marketsearch오류");
         System.out.println(e.getMessage());
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