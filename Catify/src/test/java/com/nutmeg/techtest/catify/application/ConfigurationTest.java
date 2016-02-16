package com.nutmeg.techtest.catify.application;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigurationTest {
	private static final String FileKey = "cli.action.file";
	private static final String CategoriesKey = "cli.action.categories";
	private static final String FactKey = "cli.action.fact";
	private static final String CatApiKeyQueryParameterKey = "catapi.key.queryparameter";
	private static final String CatApiFileUrlKey = "catapi.file.url";
	private static final String CatApiCategoriesUrlKey = "catapi.categories.url";
	private static final String CatFactsUrlKey = "catfacts.url";
	
	private static final String FileValue = "file";
	private static final String CategoriesValue = "categories";
	private static final String FactValue = "fact";
	private static final String CatApiKeyQueryParameterValue = "api_key=NjY5NDI";
	private static final String CatApiFileUrlValue = "http://thecatapi.com/api/images/get?format=xml&results_per_page=1";
	private static final String CatApiCategoriesUrlValue = "http://thecatapi.com/api/categories/list";
	private static final String CatFactsUrlValue = "http://catfacts-api.appspot.com/api/facts?number=1";
	
	private static final String PropertiesFile = "config.properties";
	

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testFileConfigurationMissingThrowsException() {
		initialiseConfiguration(new String[][]{
			{ CategoriesKey, CategoriesValue },
			{ FactKey, FactValue },
			{ CatApiKeyQueryParameterKey, CatApiKeyQueryParameterValue },
			{ CatApiFileUrlKey, CatApiFileUrlValue },
			{ CatApiCategoriesUrlKey, CatApiCategoriesUrlValue },
			{ CatFactsUrlKey, CatFactsUrlValue }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCategoriesConfigurationMissingThrowsException() {
		initialiseConfiguration(new String[][]{
			{ FileKey, FileValue },
			{ FactKey, FactValue },
			{ CatApiKeyQueryParameterKey, CatApiKeyQueryParameterValue },
			{ CatApiFileUrlKey, CatApiFileUrlValue },
			{ CatApiCategoriesUrlKey, CatApiCategoriesUrlValue },
			{ CatFactsUrlKey, CatFactsUrlValue }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testFactConfigurationMissingThrowsException() {
		initialiseConfiguration(new String[][]{
			{ FileKey, FileValue },
			{ CategoriesKey, CategoriesValue },
			{ CatApiKeyQueryParameterKey, CatApiKeyQueryParameterValue },
			{ CatApiFileUrlKey, CatApiFileUrlValue },
			{ CatApiCategoriesUrlKey, CatApiCategoriesUrlValue },
			{ CatFactsUrlKey, CatFactsUrlValue }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCatApiKeyConfigurationMissingThrowsException() {
		initialiseConfiguration(new String[][]{
			{ FileKey, FileValue },
			{ CategoriesKey, CategoriesValue },
			{ FactKey, FactValue },
			{ CatApiFileUrlKey, CatApiFileUrlValue },
			{ CatApiCategoriesUrlKey, CatApiCategoriesUrlValue },
			{ CatFactsUrlKey, CatFactsUrlValue }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCatFileUrlConfigurationMissingThrowsException() {
		initialiseConfiguration(new String[][]{
			{ FileKey, FileValue },
			{ CategoriesKey, CategoriesValue },
			{ FactKey, FactValue },
			{ CatApiKeyQueryParameterKey, CatApiKeyQueryParameterValue },
			{ CatApiCategoriesUrlKey, CatApiCategoriesUrlValue },
			{ CatFactsUrlKey, CatFactsUrlValue }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCatCategoriesUrlConfigurationMissingThrowsException() {
		initialiseConfiguration(new String[][]{
			{ FileKey, FileValue },
			{ CategoriesKey, CategoriesValue },
			{ FactKey, FactValue },
			{ CatApiKeyQueryParameterKey, CatApiKeyQueryParameterValue },
			{ CatApiFileUrlKey, CatApiFileUrlValue },
			{ CatFactsUrlKey, CatFactsUrlValue }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCatFactsUrlConfigurationMissingThrowsException() {
		initialiseConfiguration(new String[][]{
			{ FileKey, FileValue },
			{ CategoriesKey, CategoriesValue },
			{ FactKey, FactValue },
			{ CatApiKeyQueryParameterKey, CatApiKeyQueryParameterValue },
			{ CatApiFileUrlKey, CatApiFileUrlValue },
			{ CatApiCategoriesUrlKey, CatApiCategoriesUrlValue }
		});
	}
	
	@Test
	public void testConfigurationReadingCorrectKeys() {
		Configuration configuration = initialiseConfiguration(new String[][]{
			{ FileKey, FileValue },
			{ CategoriesKey, CategoriesValue },
			{ FactKey, FactValue },
			{ CatApiKeyQueryParameterKey, CatApiKeyQueryParameterValue },
			{ CatApiFileUrlKey, CatApiFileUrlValue },
			{ CatApiCategoriesUrlKey, CatApiCategoriesUrlValue },
			{ CatFactsUrlKey, CatFactsUrlValue }
		});
		
		Assert.assertEquals(configuration.getFileCommand(), FileValue);
		Assert.assertEquals(configuration.getCategoriesCommand(), CategoriesValue);
		Assert.assertEquals(configuration.getFactCommand(), FactValue);
		Assert.assertEquals(configuration.getCatApiKeyQueryParameter(), CatApiKeyQueryParameterValue);
		Assert.assertEquals(configuration.getCatFileUrl(), CatApiFileUrlValue);
		Assert.assertEquals(configuration.getCatCategoryUrl(), CatApiCategoriesUrlValue);
		Assert.assertEquals(configuration.getCatFactUrl(), CatFactsUrlValue);
		
		Assert.assertTrue(configuration.isInitialised());
	}
	
	@Test
	public void testConfigurationCreatingCorrectAuthenticatedCatFileUrl() {
		Configuration configuration = new Configuration();
		configuration.setCatFileUrl("leftSide");
		configuration.setCatApiKeyQueryParameter("rightSide");
		
		Assert.assertEquals(
				configuration.getCatFileUrlWithApiKey(),
				configuration.getCatFileUrl() + "&" + configuration.getCatApiKeyQueryParameter());
	}

	@Test(expectedExceptions = IOException.class)
	public void testConfigurationLoadingNonExistingPropertiesFileThrowsException() throws IOException {
		Configuration configuration = new Configuration();
		try {
			configuration.loadFromResourceFile("nonexisting." + PropertiesFile);
		} catch (IOException ex) {
			Assert.assertFalse(configuration.isInitialised());
			throw ex;
		}
	}
	
	@Test
	public void testConfigurationLoadingBundledPropertiesFile() throws IOException {
		Configuration configuration = new Configuration();
		configuration.loadFromResourceFile();
		configuration.initialise();
		
		Assert.assertEquals(configuration.getFileCommand(), FileValue);
		Assert.assertEquals(configuration.getCategoriesCommand(), CategoriesValue);
		Assert.assertEquals(configuration.getFactCommand(), FactValue);
		Assert.assertEquals(configuration.getCatApiKeyQueryParameter(), CatApiKeyQueryParameterValue);
		Assert.assertEquals(configuration.getCatFileUrl(), CatApiFileUrlValue);
		Assert.assertEquals(configuration.getCatCategoryUrl(), CatApiCategoriesUrlValue);
		Assert.assertEquals(configuration.getCatFactUrl(), CatFactsUrlValue);
		
		Assert.assertTrue(configuration.isInitialised());
	}
	
	private Configuration initialiseConfiguration(String[][] keyValues) {
		Configuration configuration = new Configuration();
		for (String[] keyValue : keyValues) {
			configuration.setProperty(keyValue[0], keyValue[1]);
		}
		try {
			configuration.initialise();
		} catch (IllegalArgumentException ex) {
			Assert.assertFalse(configuration.isInitialised());
			throw ex;
		}
		Assert.assertTrue(configuration.isInitialised());
		return configuration;
	}
}
