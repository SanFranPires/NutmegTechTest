package com.nutmeg.techtest.catify.apiprocessors.impl;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.nutmeg.techtest.catify.apiprocessors.ResponseProcessor;

public class CatFactResponseProcessor implements ResponseProcessor {
	
	private String fact;

	public String getFact() {
		return fact;
	}

	public void setFact(String fact) {
		this.fact = fact;
	}

	@Override
	public void processResponse(InputStream inputStream) throws Exception {
		JSONObject rootObject = (JSONObject) new JSONParser().parse(new InputStreamReader(inputStream));
		JSONArray factsArray = (JSONArray) rootObject.get("facts");
		fact = factsArray.get(0).toString();
	}

}
