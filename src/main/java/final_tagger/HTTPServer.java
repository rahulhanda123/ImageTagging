package final_tagger;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Scanner;

import com.mashape.unirest.http.exceptions.UnirestException;


public class HTTPServer {
	public static void main(String args[]) throws UnirestException, UnsupportedEncodingException, IOException{
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the search query : ");
		String query =in.nextLine();
		HTMLParser parser = new HTMLParser();
		
		Map<String, String> valueMap = parser.getImageValues(query);
		ImaggaTagging i = new ImaggaTagging();
		i.getImageTags(valueMap.get("URL"));
	}
}
