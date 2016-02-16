package com.nutmeg.techtest.catify.apiprocessors.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StreamCopyResponseProcessorTest {
	private final String TestInputString = "String to copy";
	
	@Test
	public void testStreamCopy() throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamCopyResponseProcessor processor = new StreamCopyResponseProcessor(outputStream);
		processor.processResponse(new ByteArrayInputStream(TestInputString.getBytes()));
		
		Assert.assertEquals(outputStream.toString(), TestInputString);
	}
	
}
