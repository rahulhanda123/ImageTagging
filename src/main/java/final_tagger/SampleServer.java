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
		if (searchQuery.isEmpty()) {
			PrintWriter writer = response.getWriter();
			String htmlRespone = "<html>";
			htmlRespone += "<h2>" + "The search query cannot be empty."
					+ "</h2><br/>";
			htmlRespone += "</html>";
			// return response
			writer.println(htmlRespone);
		} else {
			// get first image url
			Map<String, String> url = parser.getImageValues(searchQuery);
			String urlName = url.get("URL");

			String jsonObject = "";
			try {
				jsonObject = tagger.getImageTags(url.get("URL"));
				downloader.downloadImage(searchQuery, urlName, jsonObject);
			} catch (UnirestException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// get response writer
			PrintWriter writer = response.getWriter();
			String htmlRespone = "<html>";
			htmlRespone += "<h2>"
					+ "The first google image for given query is :"
					+ "</h2><br/>";
			htmlRespone += "<img src='" + "/final_tagger/image?imagequery="
					+ searchQuery + "' height=\"300\" width=\"300\"/><br/>";
			htmlRespone += "<div> <h3>The Tags for the above image are :</h3></br><h4>"
					+ jsonObject + "</h4></div>";
			htmlRespone += "</html>";

			// return response
			writer.println(htmlRespone);
		}
	}

}