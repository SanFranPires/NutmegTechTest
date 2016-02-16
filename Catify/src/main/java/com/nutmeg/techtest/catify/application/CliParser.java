package com.nutmeg.techtest.catify.application;

import java.text.MessageFormat;

public class CliParser {
	public enum Action {
		File,
		Categories,
		Fact
	}
	
	private Configuration configuration;
	
	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void initialise(Configuration configuration) {
		setConfiguration(configuration);
	}
	
	public Action parse(String[] arguments) {
		if (configuration == null || !configuration.isInitialised()) {
			throw new IllegalStateException("Configuration not initialised properly");
		}
		
		if (arguments == null) {
			throw new IllegalArgumentException(MessageFormat.format("{0} cannot be null", "arguments"));
		}
		else if (arguments.length > 1) {
			throw new IllegalArgumentException(MessageFormat.format("Wrong number of arguments. Found {0}", arguments.length));
		}
		
		if (arguments.length == 0 || arguments[0].equals(configuration.getFileCommand())) {
			return Action.File;
		}
		else if (arguments[0].equals(configuration.getCategoriesCommand())) {
			return Action.Categories;
		}
		else if (arguments[0].equals(configuration.getFactCommand())) {
			return Action.Fact;
		}
		
		throw new IllegalArgumentException(MessageFormat.format("Invalid action {0}", arguments[0]));
	}
}
