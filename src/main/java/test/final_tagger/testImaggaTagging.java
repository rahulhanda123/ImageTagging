package test.final_tagger;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.mashape.unirest.http.exceptions.UnirestException;

import final_tagger.ImaggaTagging;

public class testImaggaTagging {

	@Test
	public void testGetImageTags() throws IOException, UnirestException {
		ImaggaTagging Itag = new ImaggaTagging();
		
		Scanner scanner = new Scanner(getClass().getResourceAsStream(
				"test.txt"));

		String s = new String();

		while (scanner.hasNextLine()) {

			s = s + scanner.nextLine();

			// String testString = is.toString();
		}
		assertTrue((s)
				.contains(Itag
						.getImageTags("http://media.gq.com/photos/56e867a9239f13cf5b2ba2d8/master/pass/david-beckham-gq-0416-cover-sq.jpg")));
	}

}
