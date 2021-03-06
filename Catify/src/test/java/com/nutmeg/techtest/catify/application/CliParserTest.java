package com.nutmeg.techtest.catify.application;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nutmeg.techtest.catify.application.CliParser.Action;

public class CliParserTest {
	
	private Configuration defaultConfiguration;
	
	@BeforeClass
	public void setupTestClass() throws IOException {
		defaultConfiguration = new Configuration();
		defaultConfiguration.loadFromResourceFile();
		defaultConfiguration.initialise();
	}
	
	@Test(expectedExceptions = IllegalStateException.class)
	public void testParseWithNullConfigurationThrowsException() {
		CliParser parser = initialiseParser(null);
		parser.parse(new String[] { "file" });
	}

	@Test(expectedExceptions = IllegalStateException.class)
	public void testParseWithUninitialisedConfigurationThrowsException() {
		CliParser parser = initialiseParser(new Configuration());
		Assert.assertFalse(parser.getConfiguration().isInitialised());
		parser.parse(new String[] { "file" });
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testParseWithNullArgumentsThrowsException() {
		CliParser parser = initialiseParser(defaultConfiguration);
		parser.parse(null);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testParseWithInvalidArgumentThrowsException() {
		CliParser parser = initialiseParser(defaultConfiguration);
		parser.parse(new String[] { "non-existent-action" });
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testParseWithTwoOrMoreArgumentsThrowsException() {
		CliParser parser = initialiseParser(defaultConfiguration);
		parser.parse(new String[] {
				defaultConfiguration.getFileCommand(),
				defaultConfiguration.getCategoriesCommand()
		});
	}
	
	@Test
	public void testParseWithZeroArgumentsReturnsFileAction() {
		CliParser parser = initialiseParser(defaultConfiguration);
		Action action = parser.parse(new String[0]);
		Assert.assertEquals(action, Action.File);
	}
	
	@Test
	public void testParseWithFileArgumentReturnsFileAction() {
		CliParser parser = initialiseParser(defaultConfiguration);
		Action action = parser.parse(new String[] { defaultConfiguration.getFileCommand() });
		Assert.assertEquals(action, Action.File);
	}
	
	@Test
	public void testParseWithCategoriesArgumentReturnsCategoriesAction() {
		CliParser parser = initialiseParser(defaultConfiguration);
		Action action = parser.parse(new String[] { defaultConfiguration.getCategoriesCommand() });
		Assert.assertEquals(action, Action.Categories);
	}
	
	@Test
	public void testParseWithFactArgumentReturnsFactAction() {
		CliParser parser = initialiseParser(defaultConfiguration);
		Action action = parser.parse(new String[] { defaultConfiguration.getFactCommand() });
		Assert.assertEquals(action, Action.Fact);
	}
	
	private CliParser initialiseParser(Configuration configuration) {
		CliParser parser = new CliParser();
		parser.initialise(configuration);
		return parser;
	}
}
