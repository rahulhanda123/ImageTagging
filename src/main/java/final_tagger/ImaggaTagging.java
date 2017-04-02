package final_tagger;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ImaggaTagging {

	public String getTags(String json) {

		 System.out.println(json);
		if (json.isEmpty()){
			return json;
		}
		if(json.indexOf("{\"confidence")== -1){
			return json;
		}
		String s = json.substring(json.indexOf("{\"confidence"),
				json.indexOf("}]}]}"));
		String output;
		String[] sp = s.split("},");

		for (int i = 0; i < sp.length; i++) {
			sp[i] = sp[i].substring(sp[i].indexOf("tag"));
			sp[i] = sp[i].substring(6, sp[i].length() - 1);

		}
		output = sp[0];
		for (int j = 1; j < sp.length; j++)
			output = output + ", " + sp[j];
		return output;
	}

	public String getImageTags(String imageUrl) throws UnirestException {

		// imageUrl =
		// "http://docs.imagga.com/static/images/docs/sample/japan-605234_1280.jpg",
		String apiKey = "acc_2b3f0aa26c30bc3", apiSecret = "0f1e523f089c49f16ff07595a343416f";

		// These code snippets use an open-source library.
		// http://unirest.io/java
		HttpResponse response = Unirest
				.get("https://api.imagga.com/v1/tagging")
				.queryString("url", imageUrl).basicAuth(apiKey, apiSecret)
				.header("Accept", "application/json").asJson();

		// Object jsonResponse = response.getBody().getObject();
		return getTags(response.getBody().toString());
	}

}
