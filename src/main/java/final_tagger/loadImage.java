package final_tagger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/image")
public class loadImage extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HTMLParser parser = new HTMLParser();
	ImaggaTagging tagger = new ImaggaTagging();
	fileDownloader downloader = new fileDownloader();
	String searchQuery = "";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		String searchQuery = req.getParameter("imagequery");
		String URLAfterWebDomain = req.getRequestURI();
		resp.setContentType("image/jpg");
		ServletOutputStream out;  
	    out = resp.getOutputStream();
	    String path = System.getProperty("user.dir")+"/" ;
	    System.out.println(path);
	    FileInputStream fin = new FileInputStream(path+"/final_tagger/src/Images/"+ searchQuery +"/result.jpg");  
	      
	    BufferedInputStream bin = new BufferedInputStream(fin);  
	    BufferedOutputStream bout = new BufferedOutputStream(out);  
	    int ch =0; ;  
	    while((ch=bin.read())!=-1)  
	    {  
	    bout.write(ch);  
	    }  
	      
	    bin.close();  
	    fin.close();  
	    bout.close();  
	    out.close();  
	}
}