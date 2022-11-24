package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	// 자료 게시판 특정 글 조회
	public DataBoard getData_BoardByIdx(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataBoard data_board = new DataBoard();

		try {

			conn = ds.getConnection();
			String sql = "select b_idx, idx, ori_name, save_name, volume, refer, depth, step from Data_Board where idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				data_board.setB_idx(rs.getInt("b_idx"));
				data_board.setIdx(rs.getInt(rs.getInt("idx")));
				data_board.setOri_name(rs.getString("ori_name"));
				data_board.setSave_name(rs.getString("save_name"));
				data_board.setVolume(rs.getInt("volume"));
				data_board.setRefer(rs.getInt("refer"));
				data_board.setDepth(rs.getInt("depth"));
				data_board.setStep(rs.getInt("step"));

			} else {
				System.out.println("조회 데이터 없음");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return data_board;
	}

	// 자료 게시판 특정 글 삽입
	public int insertData_Board(DataBoard data_board) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {

			conn = ds.getConnection();
			String sql = "insert into data_board(ori_name, save_name, volume, refer, depth, step) values( ?, ?, 0,0,0,0)";
			pstmt = conn.prepareStatement(sql);

	//		pstmt.setInt(1, data_board.getIdx());
			pstmt.setString(1, data_board.getOri_name());
			pstmt.setString(2, data_board.getOri_name());
			//pstmt.setInt(4, data_board.getVolume());
			pstmt.setInt(3, data_board.getRefer());
			pstmt.setInt(4, data_board.getDepth());
			pstmt.setInt(5, data_board.getStep());
			
			
			/*
			 * int refermax = getMaxRefer(); int refer = refermax+1; pstmt.setInt(8,refer);
			 */
			System.out.println("1."+row);
			
			row = pstmt.executeUpdate();
			System.out.println("2."+row);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return row;
	}
	//board 에 자료게시판 글 삽입
	public int insertBoard(Board board,int bcode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			
			conn = ds.getConnection();
			String sql = "insert into Board(b_code,title, nick, content, email_id, b_code,to_char(w_date, 'yyyy-MM-dd')) values(?,?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_code());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getNick());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getEmail_id());
			pstmt.setInt(6, board.getB_code());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return row;
	}
	
	
	
	
	// 자료 게시판 특정 글 수정
	public int updateData_Board(DataBoard data_board) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {

			conn = ds.getConnection();
			String sql = "update Data_Board set ori_name=?, save_name=? volume=? where idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, data_board.getOri_name());
			pstmt.setString(2, data_board.getSave_name());
			pstmt.setInt(3, data_board.getVolume());
			pstmt.setInt(4, data_board.getIdx());

			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return row;
	}
	public int boardEdit(MultipartRequest boarddata) {
		
		String idx= boarddata.getParameter("idx");
		String ori_name=boarddata.getParameter("ori_name");
		String save_name = boarddata.getParameter("save_name");
		String volume =boarddata.getParameter("volume");
		
		
		Enumeration filenames = boarddata.getFileNames();
		
		String file1 = (String) filenames.nextElement();
		String filename1 = boarddata.getFilesystemName(file1);
		
		if(filename1 == null) {
			filename1 = boarddata.getParameter("orifile");
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql_idx = "select idx  from data_board where idx=? ";
			String sql_udpate = "update databoard set ori_name=?, save_name=? volume=? where idx=?";
			pstmt = conn.prepareStatement(sql_idx);
			pstmt.setString(1, idx);
	
			
			rs = pstmt.executeQuery();
			//판단 (데이터 있다며 : 수정가능 , 없다면 : 수정불가
			if(rs.next()) {
				//경고
				pstmt.close();
				//업데이트
				pstmt = conn.prepareStatement(sql_udpate);
				pstmt.setString(1,filename1);
				pstmt.setString(2,filename1);
				pstmt.setString(3, volume);
			
				pstmt.setString(4, idx);
				row = pstmt.executeUpdate();
				//System.out.println("row : " + row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			} catch (Exception e2) {
				
			}
		}
	
		return row;
	}
	// 자료 게시판 특정 글 삭제
	public int deleteData_Board(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {

			conn = ds.getConnection();
			String sql = "delete from Data_Board where idx=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, idx);

			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return row;
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

	// 글쓰기 refer 값 생성하기
	private int getMaxRefer() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int refer_max = 0;
		try {
			conn = ds.getConnection();
			String sql = "select nvl(max(refer),0) from data_board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				refer_max = rs.getInt(1);
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

		return refer_max;

	}

	// 게시물 총 건수 구하기;
	public int totaldataBoard() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalcount = 0;
		try {
			conn = ds.getConnection();
			String sql = "select count(*) cnt from data_board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalcount = rs.getInt("cnt");
			}
		} catch (Exception e) {

		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e) {

			}
		}

		return totalcount;
	}
	
//계층형 답글	
	public List<Comments> getComment(int b_code,int idx, int cpage, int pagesize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Comments> comlist = null;

		try {

			conn = ds.getConnection();
			String sql = "select * from"
					+ "(select rownum rn, b.idx,c.co_idx,c.content, c.refer, c.depth, c.step"
					+ "from board b join comments c"
					+ "on c.idx = b.idx"
					+ "where b.b_code=?  and b.idx=?"
					+ "order by refer desc , step desc ) where rn <=? and rn >=?";
			pstmt = conn.prepareStatement(sql);
			
			comlist = new ArrayList<Comments>();
			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;
			
			pstmt.setInt(1, b_code);
			pstmt.setInt(2, idx);
			pstmt.setInt(3, end);
			pstmt.setInt(4, start);
			
			rs = pstmt.executeQuery();
			
			comlist = new ArrayList<Comments>();

			while (rs.next()) {
				Comments com = new Comments();

				com.setIdx(rs.getInt("idx"));
				com.setContent(rs.getString("content"));
				
			
				// 계층형

				com.setRefer(rs.getInt("refer"));
				com.setStep(rs.getInt("step"));
				com.setDepth(rs.getInt("depth"));

				
				
				comlist.add(com);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return comlist;
	}
	
	
	
}
