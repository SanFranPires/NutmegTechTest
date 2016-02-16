package com.nutmeg.techtest.catify.application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

@SuppressWarnings("serial")
public class Configuration extends Properties {
	private String fileCommand;
	private String categoriesCommand;
	private String factCommand;
	
	private String catApiKeyQueryParameter;
	private String catFileUrl;
	private String catCategoryUrl;
	private String catFactUrl;

	private boolean initialised = false;

	public String getFileCommand() {
		return fileCommand;
	}

	public void setFileCommand(String fileCommand) {
		this.fileCommand = fileCommand;
	}

	public String getCategoriesCommand() {
		return categoriesCommand;
	}

	public void setCategoriesCommand(String categoriesCommand) {
		this.categoriesCommand = categoriesCommand;
	}

	public String getFactCommand() {
		return factCommand;
	}

	public void setFactCommand(String factCommand) {
		this.factCommand = factCommand;
	}
	
	public String getCatApiKeyQueryParameter() {
		return catApiKeyQueryParameter;
	}

	public void setCatApiKeyQueryParameter(String catApiKeyQueryParameter) {
		this.catApiKeyQueryParameter = catApiKeyQueryParameter;
	}

	public String getCatFileUrl() {
		return catFileUrl;
	}
	
	public String getCatFileUrlWithApiKey() {
		return getCatFileUrl() + "&" + getCatApiKeyQueryParameter();
	}

	public void setCatFileUrl(String catFileUrl) {
		this.catFileUrl = catFileUrl;
	}

	public String getCatCategoryUrl() {
		return catCategoryUrl;
	}

	public void setCatCategoryUrl(String catCategoryUrl) {
		this.catCategoryUrl = catCategoryUrl;
	}

	public String getCatFactUrl() {
		return catFactUrl;
	}

	public void setCatFactUrl(String catFactUrl) {
		this.catFactUrl = catFactUrl;
	}

	public boolean isInitialised() {
		return initialised;
	}
	
	public void loadFromResourceFile() throws IOException {
		loadFromResourceFile("config.properties");
	}
	
	public void loadFromResourceFile(String resourceFile) throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(resourceFile);
		try {
			if (is == null) {
				throw new FileNotFoundException(MessageFormat.format("Resource file {0} could not be found", resourceFile));
			}
			load(is);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}
	
	public void initialise() {
		fileCommand = getProperty("cli.action.file");
		categoriesCommand = getProperty("cli.action.categories");
		factCommand = getProperty("cli.action.fact");
		
		catApiKeyQueryParameter = getProperty("catapi.key.queryparameter");
		catFileUrl = getProperty("catapi.file.url");
		catCategoryUrl = getProperty("catapi.categories.url");
		catFactUrl = getProperty("catfacts.url");
		
		initialised = true;
	}
	
	public String getProperty(String propertyName) {
		String property = super.getProperty(propertyName);
		if (property == null) {
			throw new IllegalArgumentException(MessageFormat.format("Missing value for property {0}", propertyName));
		}
		return property;
	}
}
