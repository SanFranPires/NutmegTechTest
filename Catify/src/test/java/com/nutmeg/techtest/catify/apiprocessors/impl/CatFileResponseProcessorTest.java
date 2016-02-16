package com.nutmeg.techtest.catify.apiprocessors.impl;

import java.io.ByteArrayInputStream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CatFileResponseProcessorTest {
	private final String TestInputXml =
			"<response>" +
				"<data>" +
					"<images>" +
						"<image>" +
							"<url>http://server.com/image.jpg</url>" +
						"</image>" +
					"</images>" +
				"</data>" +
			"</response>";
	
	@Test
	public void testParseFile() throws Exception {
		CatFileResponseProcessor processor = new CatFileResponseProcessor();
		processor.processResponse(new ByteArrayInputStream(TestInputXml.getBytes()));
		
		Assert.assertEquals(processor.getImageUrl(), "http://server.com/image.jpg");
	}

}
