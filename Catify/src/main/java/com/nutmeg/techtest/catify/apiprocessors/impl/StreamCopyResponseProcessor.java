package com.nutmeg.techtest.catify.apiprocessors.impl;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import com.nutmeg.techtest.catify.apiprocessors.ResponseProcessor;

public class StreamCopyResponseProcessor implements ResponseProcessor {
	
	private OutputStream outputStream;
	
	public StreamCopyResponseProcessor(OutputStream outputStream) {
		setOutputStream(outputStream);
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public void processResponse(InputStream inputStream) throws Exception {
		IOUtils.copy(inputStream, outputStream);
	}
}
