package com.zshopping.inventoryservice;

import com.zshopping.inventoryservice.model.Inventory;
import com.zshopping.inventoryservice.service.impl.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryService inventoryService){
		return args -> {
			Inventory inventory = Inventory.builder()
					.skuCode(getRandomSkuCode())
					.quantity(getRandomQuantity())
					.build();

			Inventory inventory2 = Inventory.builder()
					.skuCode(getRandomSkuCode())
					.quantity(getRandomQuantity())
					.build();

			Inventory inventory3 = Inventory.builder()
					.skuCode(getRandomSkuCode())
					.quantity(getRandomQuantity())
					.build();

			inventoryService.save(inventory);
			inventoryService.save(inventory2);
			inventoryService.save(inventory3);
		};
	}

	private String getRandomSkuCode(){
		List<String> skuCodeList = List.of(
				"SKU_A_001","SKU_A_002","SKU_A_003","SKU_B_001","SKU_B_002","SKU_B_003","SKU_C_001","SKU_C_002",
				"SKU_C_003","SKU_D_001","SKU_D_002","SKU_D_003");
		int randomNo = (int) (Math.random() * 11);
		return skuCodeList.get(randomNo);
	}

	private int getRandomQuantity(){
		return (int) (Math.random() * 100);
	}

}
