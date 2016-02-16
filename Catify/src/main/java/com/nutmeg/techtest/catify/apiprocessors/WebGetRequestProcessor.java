package com.nutmeg.techtest.catify.apiprocessors;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class WebGetRequestProcessor {

	private String requestUrl;

	public WebGetRequestProcessor(String requestUrl) {
		setRequestUrl(requestUrl);
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public void executeRequest(ResponseProcessor responseProcessor) throws Exception {
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			HttpGet request = new HttpGet(requestUrl);
			try (CloseableHttpResponse response = client.execute(request)) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try (InputStream inputStream = entity.getContent()) {
						responseProcessor.processResponse(inputStream);
					}
				}
			}
		}
	}

}