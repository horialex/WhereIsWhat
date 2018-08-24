package com.steps.api;

import java.util.ArrayList;
import java.util.List;

import com.jayway.restassured.path.json.JsonPath;
import com.tools.constants.ApiUrlConstants;
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Item;
import com.tools.entities.ItemCsv;
import com.tools.factories.ItemCSVFactory;
import com.tools.factories.ItemFactory;
import com.tools.utils.CSVWriters;
import com.tools.utils.InstanceUtils;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;

public class ApiItemSteps extends AbstractApiSteps {

	private static final long serialVersionUID = 1L;

    @Step
    public void createItem() {

        Item itemRequest = ItemFactory.geItemInstance();
        Item itemResponse = createResource(ApiUrlConstants.ITEMS, itemRequest, Item.class);

        itemRequest = (Item) InstanceUtils.mergeObjects(itemRequest, itemResponse);
        SerenitySessionUtils.saveObjectListInSerenitySession(SerenityKeyConstants.ITEMS, itemRequest);
    }

    @Step
	public void createItemCSV() throws Exception {
    	AbstractApiSteps.extraHeaders.put("Entity-Type", "item");
    	AbstractApiSteps.extraHeaders.put("Content-Type", "multipart/form-data");
    	
    	Item item = ItemFactory.geItemCSVInstance();
    	CSVWriters.writeCsv(item, EnvironmentConstants.CSV_RESOURCES + EnvironmentConstants.CSV_FILE);
    	String fileName = uploadCSVResource(ApiUrlConstants.CSV_UPLOAD, EnvironmentConstants.CSV_RESOURCES, EnvironmentConstants.CSV_FILE);
		JsonPath jsonPath = new JsonPath(fileName);
		String fileProcessed = jsonPath.get("file");
		createItemFromCSV(ApiUrlConstants.PROCESS_CSV_FILE + "?filename=" + fileProcessed + "&" + "category_id=" + null);
		
		AbstractApiSteps.extraHeaders.remove("Entity-Type");
		AbstractApiSteps.extraHeaders.remove("Content-Type");
		SerenitySessionUtils.saveObjectListInSerenitySession(SerenityKeyConstants.ITEMS, item);
	}
    
    @Step
    public void createMultipleItemsCSV(List<Item> items) throws Exception{
    	AbstractApiSteps.extraHeaders.put("Entity-Type", "item");
    	AbstractApiSteps.extraHeaders.put("Content-Type", "multipart/form-data");
    	
    	CSVWriters.writeCsv(items, EnvironmentConstants.CSV_RESOURCES + EnvironmentConstants.CSV_FILE);
    	
    	String fileNameOnDisk = uploadCSVResource(ApiUrlConstants.CSV_UPLOAD, EnvironmentConstants.CSV_RESOURCES, EnvironmentConstants.CSV_FILE);
		JsonPath jsonPath = new JsonPath(fileNameOnDisk);
		String fileNameProcessed = jsonPath.get("file");
		
		
		createItemFromCSV(ApiUrlConstants.PROCESS_CSV_FILE + "?filename=" + fileNameProcessed + "&" + "category_id=" + null);
		
		for(Item item : items) {
			//TODO search and hyedarte objects with id's using merge objects
			List<Item> itemsResponse = getResources(ApiUrlConstants.ITEMS +"?title_or_code=" + item.getTitle() + "&category_id=" + item.getCategoryId() , Item.class);
			item = (Item) InstanceUtils.mergeObjects(item, itemsResponse.get(0));
			SerenitySessionUtils.saveObjectListInSerenitySession(SerenityKeyConstants.ITEMS, item);
		}
		
		
    	AbstractApiSteps.extraHeaders.remove("Entity-Type");
		AbstractApiSteps.extraHeaders.remove("Content-Type");
    }
    
}
