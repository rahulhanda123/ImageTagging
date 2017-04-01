package final_tagger;
import java.io.*;
import java.net.URL;


public class fileDownloader {
	

    public String downloadImage(String query, String searchUrl, String jsonTags) {
    	String saveInDirectory ="";
        try {
            String directory = query;
            URL url = new URL(searchUrl);
            System.out.println("Inside Image downloader "+url);
            InputStream in = new BufferedInputStream(url.openStream());
            
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

            saveInDirectory = "final_tagger/src/Images/"+directory;
            System.out.println("Working Directory = " +
                    System.getProperty("user.dir"));
            File file = new File(saveInDirectory);
            boolean success = file.mkdirs();
            if (!success) {
                // Directory creation failed
                System.out.println("\n Not creating");
            }
            else{
                System.out.println("\n Creating");
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

