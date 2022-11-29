package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.kosa.dao.CafeBannerDao;
import kr.or.kosa.dao.Calender_Dao;
import kr.or.kosa.dto.Calender;
import kr.or.kosa.dto.User;

@WebServlet("/BannerUpload")
public class BannerUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BannerUpload() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	try {  		
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("member");
		    
		    String msg = "";
		    
		    if(user == null) {
		    	msg = "로그인이 필요한 기능입니다.";
		    }else {
		    	
		    	if(!user.getIsAdmin().equals("M")) {
		    		
		    		msg = "관리자 권한이 필요한 기능입니다.";
		    		
		    	}else {
		    		
		    		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		    		int size = 1024 * 1024 * 10;

		    		MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8",
							new DefaultFileRenamePolicy());
			    	
		    		Enumeration filenames = multi.getFileNames();
			    	String file1 = (String) filenames.nextElement();
			    	String filename1 = multi.getFilesystemName(file1);
			    	
			    	CafeBannerDao cafedao = new CafeBannerDao();
			    	int row = cafedao.UploadCafeBanner(filename1);
					
					if(row > 0) {
						msg = "카페 배너가 업로드 되었습니다.";
					}else {
						msg = "배너 이미지 업로드에 실패하였습니다.";
					}
		    	}
		    }
			
			out.print(msg);
			
    		
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
