package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;

import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Comments;
import kr.or.kosa.dto.DataBoard;

public class DataBoardDao {
   DataSource ds = null;

   public DataBoardDao() throws NamingException {
      Context context = new InitialContext();
      ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
   }
   
   //board 에서 특정한 글조회
   public Board getBoard_data( int idx) {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      Board board = new Board();
      
      
      try {
         conn = ds.getConnection();
         
         String sql ="select  title,nick,content ,to_char(w_date,'yyyy-MM-dd') as w_date from board where b_code =? and idx=?";
         pstmt=conn.prepareStatement(sql);
         

         pstmt.setInt(1, idx);
         rs = pstmt.executeQuery();
         if (rs.next()) {

         board.setTitle(rs.getString("title"));
         board.setNick(rs.getString("nick"));
         board.setContent(rs.getString("content"));
         board.setW_date(rs.getString("w_date"));
      
      
      
         
         } else {
            System.out.println("조회 데이터 없음");
         }
      } catch (SQLException e) {
      
         System.out.println(e.getMessage());
      }finally {
         try {
            conn .close();
            rs.close();
            pstmt.close();
         } catch (Exception e2) {
            System.out.println(e2.getMessage());
         }
      }
   
      return board;
      
   }

   // 자료 게시판 특정 글 조회
   public DataBoard getData_BoardByIdx(int idx) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      DataBoard board = new DataBoard(); 

   try {
         
         conn = ds.getConnection();
         String sql = "select b.idx, b.title, b.nick, b.content, b.hits, to_char(b.w_date, 'YYYY-MM-dd') w_date, b.report_count, b.notic, b.email_id, b.b_code, i.ori_name, i.save_name, i.volume, i.refer, i.depth, i.step "
                  + "from board b join data_board i "
                  + "on b.idx = i.idx "
                  + "where b.idx = ?";
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, idx);
         
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            
            board.setIdx(rs.getInt("idx"));
            board.setTitle(rs.getString("title"));
            board.setNick(rs.getString("nick"));
            board.setContent(rs.getString("content"));
            board.setHits(rs.getInt("hits"));
            board.setW_date(rs.getString("w_date"));
            board.setEmail_id(rs.getString("email_id"));
            board.setReport_count(rs.getInt("report_count"));
            board.setNotic(rs.getString("notic"));
            board.setEmail_id(rs.getString("email_id"));
            board.setOri_name(rs.getString("ori_name"));
            board.setSave_name(rs.getString("save_name"));
            board.setVolume(rs.getInt("volume"));
            board.setRefer(rs.getInt("refer"));
            board.setDepth(rs.getInt("depth"));
            board.setStep(rs.getInt("step"));
            
         }else {
            System.out.println("조회 데이터 없음");
         }
         
      } catch (Exception e) {
         System.out.println(e.getMessage());
      } finally {
         try {
            rs.close();
            pstmt.close();
            conn.close();
         } catch (Exception e2) {
            System.out.println(e2.getMessage());
         }
      }
      
      return board;
   }


   // 전체 자료게시판 조회
   public List<Board> getAllDatalist(int b_code, int cpage, int pagesize) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<Board> datalist = null;

      try {

         conn = ds.getConnection();
         String sql = "select *" + "from (select rownum rn, b.idx, b.title, b.nick, b.content, b.hits, b.w_date,d.depth,d.step,d.refer "
               + "        from board b join data_board d" + "        on b.idx = d.idx" + "        where b_code=? "
               + "        order by refer desc, step desc) where rn <= ? and rn >= ?";
         pstmt = conn.prepareStatement(sql);
         
         datalist = new ArrayList<Board>();
         int start = cpage * pagesize - (pagesize - 1);
         int end = cpage * pagesize;
         pstmt.setInt(1, b_code);
         pstmt.setInt(2, end);
         pstmt.setInt(3, start);

         rs = pstmt.executeQuery();
         
         datalist = new ArrayList<Board>();

         while (rs.next()) {
            DataBoard data = new DataBoard();

            data.setIdx(rs.getInt("idx"));
            data.setTitle(rs.getString("title"));
            data.setNick(rs.getString("nick"));
            data.setContent(rs.getString("content"));
            data.setHits(rs.getInt("hits"));
            data.setW_date(rs.getString("w_date"));

            // 계층형

            data.setRefer(rs.getInt("refer"));
            data.setStep(rs.getInt("step"));
            data.setDepth(rs.getInt("depth"));

            System.out.println(data.getTitle());
            
            datalist.add(data);

         }

      } catch (Exception e) {
         System.out.println(e.getMessage());
      } finally {
         try {
            rs.close();
            pstmt.close();
            conn.close();
         } catch (Exception e2) {
            System.out.println(e2.getMessage());
         }
      }

      return datalist;
   }
   
   // 좋아요 개수
   public int getYes(int idx) {

      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      int yes_max = 0;

      try {
         conn = ds.getConnection();
         String sql = "select  count(*) as cnt from yes where idx=?";
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1,idx);
         
         rs = pstmt.executeQuery();
         
         if (rs.next()) {
            yes_max = rs.getInt("cnt");
         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      } finally {
         try {
            pstmt.close();
            rs.close();
            conn.close();
         } catch (Exception e) {

         }
      }

      return yes_max;

   }

   // 댓글개수

    public int getComments(int idx1) {

      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      int co_max = 0;

      try {
         conn = ds.getConnection();
         String sql = "select count(*) as cnt from comments where idx=?";
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, idx1);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            co_max = rs.getInt("cnt");
         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      } finally {
         try {
            pstmt.close();
            rs.close();
            conn.close();
         } catch (Exception e) {

         }
      }

      return co_max;

   }
    
    //데이터 게시판 답글 삽입
    public int insertData_BoardReply(DataBoard databoard) {
       
       Connection conn = null;
       PreparedStatement pstmt = null;
       PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
       int row = 0;
      
       try {
          
          conn = ds.getConnection();
          conn.setAutoCommit(false);
          
          String sql = "INSERT ALL "
                   + "INTO board (idx, title, nick, content, email_id, b_code) "
                   + "VALUES (IDX_SEQ.nextval, ?, ?, ?, ?, ?) "
                   + "INTO data_board (b_idx, idx, ori_name, save_name, volume, refer, depth, step) "
                   + "VALUES (DATA_IDX_SEQ.nextval, IDX_SEQ.currval, ?, ?, ?, ?, ?, ?) "
                   + "select * from dual";
          pstmt = conn.prepareStatement(sql);
          
          pstmt.setString(1, databoard.getTitle());
          pstmt.setString(2, databoard.getNick());
          pstmt.setString(3, databoard.getContent());
          pstmt.setString(4, databoard.getEmail_id());
          pstmt.setInt(5, databoard.getB_code());
          pstmt.setString(6, databoard.getOri_name());
          pstmt.setString(7, databoard.getSave_name());
          pstmt.setInt(8, databoard.getVolume());
          pstmt.setInt(9, databoard.getRefer());
          pstmt.setInt(10, databoard.getDepth()+1);
          pstmt.setInt(11, databoard.getStep()+1);
          
          row = pstmt.executeUpdate();
          
			if (row <= 0) {
				throw new Exception("board 삽입 실패");
			}
			
			String sql2 = "UPDATE user_details SET re_count = nvl(re_count + 1, 0) WHERE email_id=?";
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setString(1, databoard.getEmail_id());
			
			row = pstmt2.executeUpdate();
			
			if(row <= 0) {
				throw new Exception("user_detals update 실패");
			}
			
			String sql3 = "UPDATE data_board set step = nvl(step + 1, 0) where refer=?";
			pstmt3 = conn.prepareStatement(sql3);
			
			pstmt3.setInt(1, databoard.getRefer());
			
			row = pstmt3.executeUpdate();
			
			if(row <= 0) {
				throw new Exception("comments depth, step update 실패");
			}else {
				conn.commit();
			}
			
      } catch (Throwable e) {
			if(conn != null) {
				try {
					conn.rollback(); // 트랜잭션 실행 이전 상태로 돌리기
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		} finally {
			try {
				conn.setAutoCommit(true);
				pstmt2.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
      
       
       return row;
    }
   
}