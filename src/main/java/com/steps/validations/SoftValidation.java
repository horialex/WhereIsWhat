package com.steps.validations;

import jxl.common.Assert;
import jxl.common.Logger;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.steps.StepFailureException;

public class SoftValidation extends ScenarioSteps {

	private static final long serialVersionUID = 1L;
	
	final static Logger logger = Logger.getLogger(SoftValidation.class);
    static StringBuffer errorMessages;

    public SoftValidation() {
        errorMessages = new StringBuffer("\n" +"------------------ SUMMARIZING ERRORS: ------------------");
    }

    @Step
    public static void verifyStringValues(String customMessage, String expected, String actual) throws StepFailureException {
        logger.info(customMessage + ": " + expected + " ---------- " + actual);
        try{
            Assert.verify(expected.contentEquals(actual), customMessage);
        }catch(Exception e){
            errorMessages.append("\n" + "Failure: " +  e.getMessage() + " values don't match::Expected "  + expected + " ---------- "  +  "Actual " + actual);
            logger.info("Failure:" + customMessage + " values don't match::Expected " + expected + " ---------- "  +  "Actual " + actual);
        }
    }

    @Step
    public static void verifyStringValuesIgnoreCase(String customMessage, String expected, String actual) throws StepFailureException {
        logger.info(customMessage + ": " + expected + " ---------- " + actual);
        try{
            Assert.verify(expected.equalsIgnoreCase(actual), customMessage);
        }catch(Exception e){
            errorMessages.append("\n" + "Failure: " +  e.getMessage() + " values don't match::Expected "  + expected + " ---------- "  +  "Actual " + actual);
            logger.info("Failure:" + customMessage + " values don't match::Expected " + expected + " ---------- "  +  "Actual " + actual);
        }
    }

    @Step
    public static void verifyIntValues(String customMessage, int expected, int actual) throws StepFailureException {
        logger.info(customMessage + ": " + expected + " ---------- " + actual);
        try{
            Assert.verify(expected == actual, customMessage);
        }catch(Exception e){
            errorMessages.append("\n" + "Failure: " +  e.getMessage() + " values don't match::Expected "  + expected + " ---------- "  +  "Actual " + actual);
            logger.info("Failure:" + customMessage + " values don't match::Expected " + expected + " ---------- "  +  "Actual " + actual);
        }
    }

    public static void verifyObjectValues(String customMessage, boolean actual){
        try{
            Assert.verify(actual == true, customMessage);
        }catch(Exception e){
            errorMessages.append("\n" + "Failure: " +  e.getMessage() + " values don't match::::: ");
        }
    }

    @Step
    public void printErrors(){
        org.junit.Assert.assertTrue(errorMessages.toString(), !errorMessages.toString().contains("Failure"));
    }
}
