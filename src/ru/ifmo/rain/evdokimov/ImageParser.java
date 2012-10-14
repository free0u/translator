package ru.ifmo.rain.evdokimov;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ImageParser {
	public ImageParser() {

	}

	public ArrayList<String> parse(String query) {
		// typical query: http://www.google.ru/search?q=my+query&tbm=isch
		query = query.replace(' ', '+');
		final ArrayList<String> imageUrls = new ArrayList<String>();
		final String urlString = "http://www.google.ru/search?q=" + query
				+ "&tbm=isch";
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {	
					URL url = new URL(urlString);
					URLConnection connection = url.openConnection();
					connection.connect();
					InputStreamReader reader = new InputStreamReader(
							connection.getInputStream());
					StringBuilder page = new StringBuilder();
					int count = 0;
					char[] buffer = new char[50000];
					while (count >= 0) {
						count = reader.read(buffer);
						if (count > 0) {
							page.append(buffer);
						}
					}
					
					int startUrl, finishUrl;
					for (int i = 0; i < 10; i++) {
						//parsing address
						startUrl = page.indexOf("imgurl") + 7;
						finishUrl = page.indexOf("&amp", startUrl);
						imageUrls.add(page.substring(startUrl, finishUrl));
						page.delete(0, finishUrl);
						startUrl = page.indexOf("imgurl");
						page.delete(0, startUrl - 1);
					}
					reader.close();
				} catch (Exception e) {

				}

			}
		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
		
		}
		return imageUrls;
	}
}
