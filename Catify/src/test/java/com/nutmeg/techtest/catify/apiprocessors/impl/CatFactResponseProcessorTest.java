package com.nutmeg.techtest.catify.apiprocessors.impl;

import java.io.ByteArrayInputStream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CatFactResponseProcessorTest {
	private final String TestInputJSON = "{ \"facts\": [\"this is a cat fact\"] }";
	
	@Test
	public void testParseFact() throws Exception {
		CatFactResponseProcessor processor = new CatFactResponseProcessor();
		processor.processResponse(new ByteArrayInputStream(TestInputJSON.getBytes()));
		
		Assert.assertEquals(processor.getFact(), "this is a cat fact");
	}
}
