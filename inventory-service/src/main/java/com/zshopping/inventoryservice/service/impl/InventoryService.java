package com.zshopping.inventoryservice.service.impl;

import com.zshopping.inventoryservice.dto.InventoryResponse;
import com.zshopping.inventoryservice.model.Inventory;
import com.zshopping.inventoryservice.repository.InventoryRepository;
import com.zshopping.inventoryservice.service.IInventoryService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryService implements IInventoryService {

    private final InventoryRepository inventoryRepository;

    @SneakyThrows
    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCodeList) {
        log.info("Start Checking Inventory");
        log.info("Finished Checking Inventory");
        return inventoryRepository.findBySkuCodeIn(skuCodeList).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }

    @Override
    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }
}
