package com.tools.constants;

import com.tools.persistence.PropertyFileReader;

public class EnvironmentConstants {

	public static final String BASE_URL = PropertyFileReader.getEnvConstantsItem("BASE_URL");
	public static final String USER = PropertyFileReader.getEnvConstantsItem("USER");
	public static final String PASS = PropertyFileReader.getEnvConstantsItem("PASS");
	public static final String USER_NAME = PropertyFileReader.getEnvConstantsItem("USER_NAME");
	public static final String CSV_RESOURCES = "src/test/resources/csv/";
	public static final String CSV_FILE = "item.csv";
}
