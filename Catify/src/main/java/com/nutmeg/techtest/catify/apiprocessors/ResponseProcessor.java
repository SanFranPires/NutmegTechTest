package com.nutmeg.techtest.catify.apiprocessors;

import java.io.InputStream;

public interface ResponseProcessor {
	void processResponse(InputStream inputStream) throws Exception;
}
