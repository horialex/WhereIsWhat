package com.steps.api;

import com.tools.constants.ApiUrlConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Item;
import com.tools.factories.ItemFactory;
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
    
}
