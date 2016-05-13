package com.kyawn.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class ValidateEmail {
	public ClientResponse SendSimpleMessage(String email, String token , String fileRootPath) throws IOException {

		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(MultiPartWriter.class);
		Client client = Client.create(cc);

		client.addFilter(new HTTPBasicAuthFilter("api", "key-a8df1d5edbeb620f0446ec5acc58eedb"));
		WebResource webResource = client.resource("https://api.mailgun.net/v3/vp0.org" + "/messages");

		FormDataMultiPart form = new FormDataMultiPart();
		form.field("from", "KevinHsiun<admin@vp0.org>");
		form.field("to", email);
		form.field("subject", "Hello New User!");
		form.field("html", generateHTML(email, token,fileRootPath));
		return webResource.type(MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class, form);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String generateHTML(String email, String token, String fileRootPath) throws IOException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setDirectoryForTemplateLoading(new File(fileRootPath + "WEB-INF/"));

		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

		String url = "https://vp0.org/account/verify?email=" + email + "&token=" + token;
		Map root = new HashMap();
		root.put("verifyurl", url);
		Template temp = cfg.getTemplate("mail.ftl");

		StringWriter sw = new StringWriter();
		try {
			temp.process(root, sw);
		} catch (TemplateException e) {
			e.printStackTrace();
		}

		return sw.toString();
	}

}
