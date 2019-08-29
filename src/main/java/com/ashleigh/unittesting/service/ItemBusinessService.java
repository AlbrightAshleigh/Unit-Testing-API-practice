package com.ashleigh.unittesting.service;

import com.ashleigh.unittesting.model.Item;
import com.ashleigh.unittesting.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class ItemBusinessService {

    @Autowired
    private ItemRepository repository;

    public Item retrieveHardCodedItem() {

        return new Item(2, "Pony", 1000, 8);
    }

    public List<Item> retrieveAllItems(){

        List<Item> items = repository.findAll();
        for(Item item:items){
            item.setValue(item.getPrice()* item.getQuantity());
        }
        return items;
    }
}
