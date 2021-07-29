package com.example.WepApplications.controller;

import com.example.WepApplications.dto.MessageDto;
import com.example.WepApplications.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @RequestMapping(value="/inventory", method = RequestMethod.GET)
    public ResponseEntity<String> inventory(@RequestBody MessageDto msg) throws IOException, TimeoutException {
       String status =  inventoryService.doInventory(msg);
        return  new ResponseEntity<>(status,
                HttpStatus.OK);
    }

}
