package com.pages;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class AbstractPage extends PageObject {

	public void waitForElementsByCssLocator(String cssLocator) {
		(new WebDriverWait(getDriver(), 20))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(cssLocator)));
	}
	
	public <T> void verifyListOfObjects(List<T> list1, List<T> list2, String matchElement)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		List<T> localList2 = new ArrayList<T>(list2);

		Assert.assertTrue("The lists do not have the same size", list1.size() == list2.size());

		for (T item : list1) {
			T itemInList2 = getObjectByMatchingElement(localList2, matchElement,
					PropertyUtils.getProperty(item, matchElement).toString());
			for (Field field : item.getClass().getDeclaredFields()) {

				field.setAccessible(true);

				if (field.get(item) == null) {
					Assert.assertNull(field.get(itemInList2));

				} else {
					Assert.assertTrue(
							"<< " + field.getName() + " >> doesn't match !! Expected : " + field.get(item) + " Actual "
									+ field.get(itemInList2),
							(String.valueOf(field.get(itemInList2)).contains(String.valueOf(field.get(item)))));
				}
			}
			localList2.remove(itemInList2);
		}
	}

	protected <T> T getObjectByMatchingElement(List<T> list, String matchElement, String matchValue)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		for (T obj : list) {
			Object value = PropertyUtils.getProperty(obj, matchElement);
			if (((String) value).contains(matchValue)) {
				return obj;
			}
		}
		Assert.assertTrue("The object with type " + matchElement + " wasn't found", false);
		return null;
	}

	// TODO bagati soft assert aici de vreti
	public <T> void verifyObjects(T obj1, T obj2)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		for (Field field : obj1.getClass().getDeclaredFields()) {

			field.setAccessible(true);

			if (field.get(obj1) == null) {
				Assert.assertNull(field.get(obj2));

			} else {
				Assert.assertTrue(
						"<< " + field.getName() + " >> doesn't match !! Expected : " + field.get(obj1) + " Actual "
								+ field.get(obj2),
						((String) (field.get(obj2))).contentEquals((String) field.get(obj1)));
			}
		}
	}

	// TODO bagati soft assert aici de vreti
	public <T> void verifyObjectsIgnoreNull(T obj1, T obj2)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		for (Field field : obj1.getClass().getDeclaredFields()) {

			field.setAccessible(true);

			if (!(field.get(obj1) == null && field.get(obj2) == null)) {
				Assert.assertTrue("<< " + field.getName() + " >> doesn't match !! Expected : " + field.get(obj1)
						+ " Actual " + field.get(obj2),
						((String) (field.get(obj2))).contains((String) field.get(obj1)));
			}
		}
	}

	public void scrollToElementByName(WebElementFacade e) {

		((JavascriptExecutor) getDriver())
				.executeScript("" + e + ".scrollTo(0, " + e +".scrollHeight)");
	}
}
