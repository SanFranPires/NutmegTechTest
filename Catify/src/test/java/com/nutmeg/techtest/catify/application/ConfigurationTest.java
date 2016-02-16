package com.nutmeg.techtest.catify.application;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigurationTest {
	private static final String FileKey = "cli.action.file";
	private static final String CategoriesKey = "cli.action.categories";
	private static final String FactKey = "cli.action.fact";
	
	private static final String FileValue = "file";
	private static final String CategoriesValue = "categories";
	private static final String FactValue = "fact";
	
	private static final String PropertiesFile = "config.properties";
	

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testFileConfigurationMissingThrowsException() {
		initialiseConfiguration(new String[][]{
			{ CategoriesKey, CategoriesValue },
			{ FactKey, FactValue }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCategoriesConfigurationMissingThrowsException() {
		initialiseConfiguration(new String[][]{
			{ FileKey, FileValue },
			{ FactKey, FactValue }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testFactConfigurationMissingThrowsException() {
		initialiseConfiguration(new String[][]{
			{ FileKey, FileValue },
			{ CategoriesKey, CategoriesValue }
		});
	}
	
	@Test
	public void testConfigurationReadingCorrectKeys() {
		Configuration configuration = initialiseConfiguration(new String[][]{
			{ FileKey, FileValue },
			{ CategoriesKey, CategoriesValue },
			{ FactKey, FactValue }
		});
		
		Assert.assertEquals(configuration.getFileCommand(), FileValue);
		Assert.assertEquals(configuration.getCategoriesCommand(), CategoriesValue);
		Assert.assertEquals(configuration.getFactCommand(), FactValue);
	}

	@Test(expectedExceptions = IOException.class)
	public void testConfigurationLoadingNonExistingPropertiesFileThrowsException() throws IOException {
		Configuration configuration = new Configuration();
		configuration.loadFromResourceFile("nonexisting." + PropertiesFile);
	}
	
	@Test
	public void testConfigurationLoadingBundledPropertiesFile() throws IOException {
		Configuration configuration = new Configuration();
		configuration.loadFromResourceFile();
		configuration.initialise();
		
		Assert.assertEquals(configuration.getFileCommand(), FileValue);
		Assert.assertEquals(configuration.getCategoriesCommand(), CategoriesValue);
		Assert.assertEquals(configuration.getFactCommand(), FactValue);
	}
	
	private Configuration initialiseConfiguration(String[][] keyValues) {
		Configuration configuration = new Configuration();
		for (String[] keyValue : keyValues) {
			configuration.setProperty(keyValue[0], keyValue[1]);
		}
		configuration.initialise();
		return configuration;
	}
}
