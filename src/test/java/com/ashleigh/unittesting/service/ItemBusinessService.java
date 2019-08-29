package com.ashleigh.unittesting.service;

import com.ashleigh.unittesting.model.Item;
import com.ashleigh.unittesting.repository.ItemRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ItemBusinessService {

    @InjectMocks
    ItemBusinessService businessService;

    @Mock
    ItemRepository itemRepository;

    @Test
    public void retrieveAllItems(){

        List<Item> listOfItems= new ArrayList<>();
        listOfItems.add(new Item(2, "Pony", 1000, 8));
        listOfItems.add(new Item(10002,"Item", 5, 12));

        when(itemRepository.findAll()).thenReturn(listOfItems);
        businessService.retrieveAllItems();
//        assertEquals(listOfItems.size(), actual.size());
//        assertEquals(8000, actual.get(0).getValue());
    }



}
