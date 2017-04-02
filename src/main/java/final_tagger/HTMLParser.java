package final_tagger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HTMLParser {

	public Map<String, String> getImageValues(String searchQuery)
			throws UnsupportedEncodingException, IOException {

		String google = "http://www.google.com/search?q=";
		String search = URLEncoder.encode(searchQuery);
		String charset = "UTF-8";
		String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36";
		Document completePage = Jsoup
				.connect(google + search + "&source=lnms&tbm=isch")
				.userAgent(userAgent).get();
		
		String firstUrl = "";
		String imageType = "";
		Map<String, String> imageInfo = new HashMap<String, String>();
		Element firstImage = completePage.select("div.rg_meta").first();
		String imageUrl = firstImage.text();
		// String link = firstImage.text().split(",");
		// JSONObject json = new JSONObject(link);
		String[] couple = imageUrl.split(",");

		for (int i = 0; i < couple.length; i++) {
			if (couple[i].contains("ou\":")) {
				String[] items = couple[i].split(":");
				
					System.out.println(items[1]);
					firstUrl = items[1].substring(1, items[1].length())+":"
							+ items[2].substring(0, items[2].length() - 1);
					if (firstUrl.contains(".jpg")) {
						imageType = ".jpg";
					} else if (firstUrl.contains(".png")) {
						imageType = ".png";
					} else {
						imageType = items[2].substring(items[2].length() - 4,
								items[2].length() - 1);
					}
					firstUrl = firstUrl.substring(0,
							firstUrl.lastIndexOf(imageType) + 4);
					//System.out.println("url is " + firstUrl + "\nType is: "
					//		+ imageType);
					imageInfo.put("URL", firstUrl);
					imageInfo.put("type", imageType);

				}
			}

		

		return imageInfo;
	}
}
