package com.steps;

import net.thucydides.core.steps.ScenarioSteps;

public class AbstractSteps extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

	/*public DictionaryPage getHeaderPage() {

		switch (System.getProperty("runPlatform")) {

		case "desktop":
			return getPages().currentPageAt(DictionaryPage.class);
		case "mobile":
			// mobile page object to be returned here
			return getPages().currentPageAt(MobileDictionaryPage.class);
		default:
			return null;
		}
	}*/
}
