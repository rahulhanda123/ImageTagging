package final_tagger;
import java.io.*;
import java.net.URL;

/**
 * Created by rohin on 3/31/2017.
 */
public class fileDownloader {

    public void downloadImage(String query, String searchquery) {

        try {
            String directory = query;
            URL url = new URL(searchquery);
            System.out.println("Insdie Image downloader "+url);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf)))
            {
                out.write(buf, 0, n);

            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();


            File file = new File(directory);
            boolean success = file.mkdirs();
            if (!success) {
                // Directory creation failed
                System.out.println("\n Not creating");
            }
            else{
                System.out.println("\n Creating");
                FileOutputStream fos = new FileOutputStream(directory+"/result.jpg");
                System.out.println(directory+"/result.jpg");
                fos.write(response);
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

