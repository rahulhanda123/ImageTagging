package final_tagger;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

import com.mashape.unirest.http.exceptions.UnirestException;
@WebServlet("/login")
public class SampleServer extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HTMLParser parser = new HTMLParser();
	ImaggaTagging tagger = new ImaggaTagging();
	fileDownloader downloader = new fileDownloader();
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
         
        // read form fields
        String searchQuery = request.getParameter("imagequery");
       // String password = request.getParameter("password");
         
        System.out.println("username: " + searchQuery);
       // System.out.println("password: " + password);
 
        // do some processing here...
        
        Map<String, String> url = parser.getImageValues(searchQuery);
        String urlName = url.get("URL");
        try {
			String jsonObject = tagger.getImageTags(url.get("URL"));
			downloader.downloadImage(searchQuery, urlName);
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // get response writer
        PrintWriter writer = response.getWriter();
         
        // build HTML code
        String htmlRespone = "<html>";
        htmlRespone += "<h2>Your urlquery is: " + urlName + "<br/>";      
        //htmlRespone += "Your password is: " + password + "</h2>";    
        htmlRespone += "</html>";
         
        // return response
        writer.println(htmlRespone);
         
    }
 
}