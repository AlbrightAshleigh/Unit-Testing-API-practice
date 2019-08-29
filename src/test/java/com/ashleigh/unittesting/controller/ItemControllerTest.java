package com.ashleigh.unittesting.controller;

import com.ashleigh.unittesting.model.Item;
import com.ashleigh.unittesting.service.ItemBusinessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ItemBusinessService businessService;

    @Test
    public void itemWorld() throws Exception {

        // call
        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                //will not fail if missing a json item
                .andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
                .andReturn();
    }

    @Test
    public void itemfromBusiness() throws Exception {

        when(businessService.retrieveHardCodedItem()).thenReturn(new Item(2, "Pony", 1000, 8));

        // call
        RequestBuilder request = MockMvcRequestBuilders
                .get("/item-from-business-service")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                //will not fail if missing a json item
                .andExpect(content().json("{\"id\":2,\"name\":\"Pony\",\"price\":1000,\"quantity\":8}"))
                .andReturn();
    }

    @Test
    public void allItemsTest() throws Exception {

        List<Item> listOfItems= new ArrayList<>();
        listOfItems.add(new Item(2, "Pony", 1000, 8));
        listOfItems.add(new Item(10002,"Item", 5, 12));


                when(businessService.retrieveAllItems()).thenReturn(listOfItems);


        // call
        RequestBuilder request = MockMvcRequestBuilders
                .get("/all-items-from-database")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                //will not fail if missing a json item
                .andExpect(content().json("[{\"id\":2,\"name\":\"Pony\",\"price\":1000,\"quantity\":8,\"value\":0},{\"id\":10002,\"name\":\"Item\",\"price\":5,\"quantity\":12,\"value\":0}]"))
                .andReturn();
    }
}
