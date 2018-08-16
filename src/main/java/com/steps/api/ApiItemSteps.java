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
    	
    	Item item = ItemFactory.geItemInstance();
    	ItemCsv itemCSV = ItemCSVFactory.geItemCSVInstance();
    	itemCSV.setCategoryTitle(item.getCategory().getName());
    	itemCSV.setName(item.getTitle());
    	
    	CSVWriters.writeCsv(itemCSV, EnvironmentConstants.CSV_RESOURCES + EnvironmentConstants.CSV_FILE);
    	String fileName = uploadCSVResource(ApiUrlConstants.CSV_UPLOAD, EnvironmentConstants.CSV_RESOURCES, EnvironmentConstants.CSV_FILE);
		JsonPath jsonPath = new JsonPath(fileName);
		String fileProcessed = jsonPath.get("file");
		createItemFromCSV(ApiUrlConstants.PROCESS_CSV_FILE + "?filename=" + fileProcessed + "&" + "category_id=" + null);
		AbstractApiSteps.extraHeaders.remove("Entity-Type");
		AbstractApiSteps.extraHeaders.remove("Content-Type");
		
		SerenitySessionUtils.saveObjectListInSerenitySession(SerenityKeyConstants.ITEMS, item);
	}
    
    @Step
    public void createMultipleItemsCSV() throws Exception{
    	AbstractApiSteps.extraHeaders.put("Entity-Type", "item");
    	AbstractApiSteps.extraHeaders.put("Content-Type", "multipart/form-data");
    	List<ItemCsv> csvRows = new ArrayList<ItemCsv>();
    	
    	for(int i=0; i<10; i++) {
    		Item item = ItemFactory.geItemInstance();
        	ItemCsv itemCSV = ItemCSVFactory.geItemCSVInstance();
        	itemCSV.setCategoryTitle(item.getCategory().getName());
        	itemCSV.setName(item.getTitle());
        	csvRows.add(itemCSV);
        	SerenitySessionUtils.saveObjectListInSerenitySession(SerenityKeyConstants.ITEMS, item);
    	}
    	CSVWriters.writeCsv(csvRows, EnvironmentConstants.CSV_RESOURCES + EnvironmentConstants.CSV_FILE);
    	String fileName = uploadCSVResource(ApiUrlConstants.CSV_UPLOAD, EnvironmentConstants.CSV_RESOURCES, EnvironmentConstants.CSV_FILE);
		JsonPath jsonPath = new JsonPath(fileName);
		String fileProcessed = jsonPath.get("file");
		createItemFromCSV(ApiUrlConstants.PROCESS_CSV_FILE + "?filename=" + fileProcessed + "&" + "category_id=" + null);
    	
    	AbstractApiSteps.extraHeaders.remove("Entity-Type");
		AbstractApiSteps.extraHeaders.remove("Content-Type");
    }
    
}
