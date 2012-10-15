package ru.ifmo.rain.evdokimov;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Translate {
	static String res = null;

	public static String getTranslate(String s) {

		s = s.replace(' ', '+');
		final String urlString = "http://translate.yandex.net/api/v1/tr/translate?lang=en-ru&text="
				+ s;
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					URL url = new URL(urlString);
					BufferedReader in = new BufferedReader(
							new InputStreamReader(url.openStream()));
					String inputLine;
					String ans = new String();
					while ((inputLine = in.readLine()) != null) {
						ans += inputLine;
					}
					if (ans.length() > 0) {
						res = ans;
					}

				} catch (Exception e) {

				}

			}
		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {

		}
		if (res == null) {
			return "Перевод не найден";
		} else {
			
			try {
				Document doc = loadXMLFromString(res);
				NodeList texts = doc.getElementsByTagName("text");
				String translated = new String();
				for (int i = 0; i < texts.getLength(); i++) {
					Node child = texts.item(i);
					translated += child.getTextContent();
				}
				return translated;
			} catch (Exception e) {
				return "Перевод не найден";
			}
			
		}
	}

	private static Document loadXMLFromString(String xml) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

}
