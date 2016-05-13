package com.kyawn.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;

public class MailTest {

	public static ClientResponse SendSimpleMessage() throws IOException {

		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(MultiPartWriter.class);
		Client client = Client.create(cc);

		client.addFilter(new HTTPBasicAuthFilter("api", "key-a8df1d5edbeb620f0446ec5acc58eedb"));
		WebResource webResource = client.resource("https://api.mailgun.net/v3/vp0.org" + "/messages");

		/*
		 * String token = RandomStringUtils.randomAlphanumeric(50); String email
		 * = "xiongkaiya@gmail.com"; String url =
		 * "https://vp0.org/account/verify?email=" + email + "&token=" + token;
		 * String htmlContent = "<html>Thank you for your registeration.<a
		 * href='" + url + "'>Click This to finish it!</a></html>";
		 */

		FormDataMultiPart form = new FormDataMultiPart();
		form.field("from", "KevinHsiun<admin@vp0.org>");
		form.field("to", "xiongkaiya@gmail.com");
		form.field("subject", "Hello New User! loginfull.html");
		form.field("html", readHtmlFile("D:/login.html"));
		return webResource.type(MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class, form);
	}

	public static String readHtmlFile(String filePath) throws IOException {
		File file = new File(filePath);
		Reader reader = null;
		reader = new InputStreamReader(new FileInputStream(file));
		int tempchar;
		StringBuffer sBuffer = new StringBuffer();
		while ((tempchar = reader.read()) != -1) {
			// 对于windows下，\r\n这两个字符在一起时，表示一个换行。
			// 但如果这两个字符分开显示时，会换两次行。
			// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
			if (((char) tempchar) != '\r') {
				sBuffer.append((char) tempchar);
			}
		}
		reader.close();
		return sBuffer.toString();
	}

	public static void main(String[] args) throws IOException {
		System.out.println(SendSimpleMessage().getStatus());
	}

}
