package com.nutmeg.techtest.catify.apiprocessors.impl;

import java.io.InputStream;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.nutmeg.techtest.catify.apiprocessors.ResponseProcessor;
import com.nutmeg.techtest.catify.apiprocessors.WebGetRequestProcessor;

public class WebGetRequestProcessorTest {

	private static class WebGetResponseProcessorInTest implements ResponseProcessor {
		private boolean called = false;

		@Override
		public void processResponse(InputStream inputStream) throws Exception {
			setCalled(true);
		}

		public boolean isCalled() {
			return called;
		}

		public void setCalled(boolean called) {
			this.called = called;
		}
		
	}
	
	@Test
	public void testProcessorIsCalled() throws Exception {
		WebGetRequestProcessor requestProcessor = new WebGetRequestProcessor("http://www.google.com");
		WebGetResponseProcessorInTest responseProcessor = new WebGetResponseProcessorInTest();
		requestProcessor.executeRequest(responseProcessor);
		
		Assert.assertTrue(responseProcessor.isCalled());
	}
}
