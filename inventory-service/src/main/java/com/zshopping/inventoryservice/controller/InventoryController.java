package com.zshopping.inventoryservice.controller;

import com.zshopping.inventoryservice.dto.InventoryResponse;
import com.zshopping.inventoryservice.service.IInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@AllArgsConstructor
public class InventoryController {

    private final IInventoryService inventoryService;

    @GetMapping
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
