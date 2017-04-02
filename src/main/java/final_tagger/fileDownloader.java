package final_tagger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class fileDownloader {

	public String downloadImage(String query, String searchUrl, String jsonTags) {
		String saveInDirectory = "";
		try {
			String directory = query;
			
			searchUrl = URLDecoder.decode(searchUrl, "UTF-8");
			System.out.println("Decode url is "+searchUrl);
			URL url = new URL(searchUrl);
			System.out.println("Inside Image downloader " + url);

			saveInDirectory = "final_tagger/src/Images/" + directory;
			// System.out.println("Working Directory = " +
			// System.getProperty("user.dir"));
			File file = new File(saveInDirectory);
			boolean success = file.mkdirs();

			if (!success) {
				// Directory creation failed
				System.out.println("\n Not creating");
			} else {

				System.out.println("Using new code to save \n");
				InputStream in = url.openStream();
				Files.copy(in, Paths.get(saveInDirectory + "/result.jpg"));

				// write tags
				File tagsFile = new File(saveInDirectory + "/tags.txt");
				FileWriter fileWriter = new FileWriter(tagsFile);
				fileWriter.write(jsonTags);
				fileWriter.flush();
				fileWriter.close();

				// fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return saveInDirectory;
	}
}
