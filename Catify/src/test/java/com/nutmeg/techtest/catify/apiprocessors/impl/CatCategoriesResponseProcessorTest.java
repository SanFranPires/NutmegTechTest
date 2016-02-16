package com.nutmeg.techtest.catify.apiprocessors.impl;

import java.io.ByteArrayInputStream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CatCategoriesResponseProcessorTest {
	private final String TestInputXml =
			"<response>" +
				"<data>" +
					"<categories>" +
						"<category>" +
							"<name>cat1</name>" +
						"</category>" +
						"<category>" +
							"<name>cat2</name>" +
						"</category>" +
						"<category>" +
							"<name>cat3</name>" +
						"</category>" +
					"</categories>" +
				"</data>" +
			"</response>";
	
	@Test
	public void testParseCategories() throws Exception {
		CatCategoriesResponseProcessor processor = new CatCategoriesResponseProcessor();
		processor.processResponse(new ByteArrayInputStream(TestInputXml.getBytes()));
		
		Assert.assertEquals(processor.getCategories().size(), 3);
		Assert.assertTrue(processor.getCategories().contains("cat1"));
		Assert.assertTrue(processor.getCategories().contains("cat2"));
		Assert.assertTrue(processor.getCategories().contains("cat3"));
	}
}
