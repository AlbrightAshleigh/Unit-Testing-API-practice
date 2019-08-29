package com.ashleigh.unittesting.controller;


import com.ashleigh.unittesting.model.Item;
import com.ashleigh.unittesting.service.ItemBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemBusinessService businessService;


    @GetMapping("/dummy-item")
    public Item itemWorld(){

        return new Item(1, "Ball", 10, 100);
    }

    @GetMapping("/item-from-business-service")
    public Item itemFromService(){

        return businessService.retrieveHardCodedItem();
    }

    @GetMapping("/all-items-from-database")
    public List<Item> getAllItems(){
        return businessService.retrieveAllItems();
    }
}
