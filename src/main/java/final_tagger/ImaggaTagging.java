package final_tagger;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class ImaggaTagging {

	public String getImageTags(String imageUrl) throws UnirestException{
		// TODO Auto-generated method stub

		
		// imageUrl = "http://docs.imagga.com/static/images/docs/sample/japan-605234_1280.jpg",
		String	apiKey = "acc_2b3f0aa26c30bc3",
				apiSecret = "0f1e523f089c49f16ff07595a343416f";

				// These code snippets use an open-source library. http://unirest.io/java
				HttpResponse response = Unirest.get("https://api.imagga.com/v1/tagging")
				    .queryString("url", imageUrl)
				    .basicAuth(apiKey, apiSecret)
				    .header("Accept", "application/json")
				    .asJson();

				    //Object jsonResponse = response.getBody().getObject();
					//System.out.println("Inside gettags ;"+response.getBody().toString());
				    return response.getBody().toString();
	}

}
