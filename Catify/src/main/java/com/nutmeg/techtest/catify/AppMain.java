package com.nutmeg.techtest.catify;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import com.nutmeg.techtest.catify.apiprocessors.WebGetRequestProcessor;
import com.nutmeg.techtest.catify.apiprocessors.impl.CatCategoriesResponseProcessor;
import com.nutmeg.techtest.catify.apiprocessors.impl.CatFactResponseProcessor;
import com.nutmeg.techtest.catify.apiprocessors.impl.CatFileResponseProcessor;
import com.nutmeg.techtest.catify.apiprocessors.impl.StreamCopyResponseProcessor;
import com.nutmeg.techtest.catify.application.CliParser;
import com.nutmeg.techtest.catify.application.CliParser.Action;
import com.nutmeg.techtest.catify.application.Configuration;

public class AppMain {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		configuration.loadFromResourceFile();
		configuration.initialise();
		
		CliParser parser = new CliParser();
		parser.initialise(configuration);
		Action action;
		try {
			action = parser.parse(args);
		} catch (Exception ex) {
			action = null;
		}
		
		if (action != null) {
			switch (action) {
			case File:
				runFileMode(configuration);
				break;
			case Categories:
				runCategoriesMode(configuration);
				break;
			case Fact:
				runFactMode(configuration);
				break;
			default:
				break;
			}
		} else {
			runHelpMode(configuration);
		}
	}
	
	private static void runFileMode(Configuration configuration) throws Exception {
		WebGetRequestProcessor fileRequest = new WebGetRequestProcessor(configuration.getCatFileUrlWithApiKey());
		CatFileResponseProcessor fileResponse = new CatFileResponseProcessor();
		fileRequest.executeRequest(fileResponse);
		
		WebGetRequestProcessor imageRequest = new WebGetRequestProcessor(fileResponse.getImageUrl());
		OutputStream outputStream = getImageOutputStream(fileResponse.getImageUrl());
		StreamCopyResponseProcessor imageResponse = new StreamCopyResponseProcessor(outputStream);
		imageRequest.executeRequest(imageResponse);
		
		System.out.println(fileResponse.getImageUrl());
	}
	
	private static void runCategoriesMode(Configuration configuration) throws Exception {
		WebGetRequestProcessor categoriesRequest = new WebGetRequestProcessor(configuration.getCatCategoryUrl());
		CatCategoriesResponseProcessor categoriesResponse = new CatCategoriesResponseProcessor();
		categoriesRequest.executeRequest(categoriesResponse);
		
		for (String category : categoriesResponse.getCategories()) {
			System.out.println(category);
		}
	}

	private static void runFactMode(Configuration configuration) throws Exception {
		WebGetRequestProcessor factRequest = new WebGetRequestProcessor(configuration.getCatFactUrl());
		CatFactResponseProcessor factResponse = new CatFactResponseProcessor();
		factRequest.executeRequest(factResponse);
		
		System.out.println(factResponse.getFact());
	}
	
	private static void runHelpMode(Configuration configuration) {
		StringBuilder helpBuilder = new StringBuilder("Usage: java -jar <package_name> [ ");
		helpBuilder.append(configuration.getFileCommand());
		helpBuilder.append(" | ");
		helpBuilder.append(configuration.getCategoriesCommand());
		helpBuilder.append(" | ");
		helpBuilder.append(configuration.getFactCommand());
		helpBuilder.append(" ]");
		
		System.out.println(helpBuilder.toString());
	}
	
	private static OutputStream getImageOutputStream(String imageUrl) throws FileNotFoundException {
		String[] urlParts = imageUrl.split("/");
		String fileName = urlParts[urlParts.length - 1];
		File file = new File(System.getProperty("java.io.tmpdir"), fileName);
		return new FileOutputStream(file);
	}
}
