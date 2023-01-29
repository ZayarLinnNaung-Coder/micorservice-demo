package com.zshopping.inventoryservice.service;

import com.zshopping.inventoryservice.dto.InventoryResponse;
import com.zshopping.inventoryservice.model.Inventory;

import java.util.List;

public interface IInventoryService {
    List<InventoryResponse> isInStock(List<String> skuCodeList);

    void save(Inventory inventory);
}
