package com.steps.frontend;

import com.pages.HeaderPage;
import com.steps.AbstractSteps;
import net.thucydides.core.annotations.Step;

public class HeaderSteps extends AbstractSteps {
   
	private static final long serialVersionUID = 1L;
	
	HeaderPage headerPage;

    @Step
    public void selectHeader(String header){
        headerPage.selectHeader(header);
    }
}
