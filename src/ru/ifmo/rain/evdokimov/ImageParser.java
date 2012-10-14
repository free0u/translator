package ru.ifmo.rain.evdokimov;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;

public class ImageParser {
	public ImageParser() {

	}

	public String[] Parse(String query) throws IOException {
		// http://www.google.ru/search?q=my+query&tbm=isch/
		query = query.replace(' ', '+');
		final String[] imageUrls = new String[10];
		final String urlString = "http://www.google.ru/search?q=" + query
				+ "&tbm=isch";
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					URL url = new URL(urlString);
					URLConnection connection = url.openConnection();
					Log.i("TIME", "GG");
					connection.connect();
					Log.i("TIME", "GGG");
					InputStreamReader reader = new InputStreamReader(
							connection.getInputStream());
					Log.i("TIME", "GGGG");
					StringBuilder page = new StringBuilder();
					//reader.skip(140000); // fix!
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
						startUrl = page.indexOf("imgurl") + 7;
						finishUrl = page.indexOf("&amp", startUrl);
						imageUrls[i] = page.substring(startUrl, finishUrl);
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
