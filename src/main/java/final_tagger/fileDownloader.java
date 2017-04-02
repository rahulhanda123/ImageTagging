package final_tagger;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class fileDownloader {
	

    public String downloadImage(String query, String searchUrl, String jsonTags) {
    	String saveInDirectory ="";
        try {
            String directory = query;
            URL url = new URL(searchUrl);
            
            saveInDirectory = "final_tagger/src/Images/"+directory;
            File file = new File(saveInDirectory);
            boolean success = file.mkdirs();
            
            if (!success) {
                // Directory creation failed
                System.out.println("\n Not creating");
            }
            else{


            	HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
                httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");

                InputStream in = httpcon.getInputStream();
            	//InputStream in = new BufferedInputStream(url.openStream());
                
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[8192];
                int n = 0;
                while (-1!=(n=in.read(buf)))
                {
                    out.write(buf, 0, n);

                }
                out.close();
                in.close();
                byte[] response = out.toByteArray();
                
                FileOutputStream fos = new FileOutputStream(saveInDirectory+"/result.jpg");
                System.out.println(saveInDirectory+"/result.jpg");
                fos.write(response);
                
                //write tags
                File tagsFile = new File(saveInDirectory+"/tags.txt");
                FileWriter fileWriter = new FileWriter(tagsFile);
        		fileWriter.write(jsonTags);
        		fileWriter.flush();
        		fileWriter.close();
                
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saveInDirectory;   
    }
}
