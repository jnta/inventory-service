package com.jonata.inventoryservice.service;

import com.jonata.inventoryservice.dto.InventoryRequest;
import com.jonata.inventoryservice.dto.InventoryResponse;
import com.jonata.inventoryservice.model.Inventory;
import com.jonata.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public InventoryResponse placeProductInInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setSkuCode(inventoryRequest.getSkuCode());
        inventory.setQuantity(inventoryRequest.getQuantity());

        inventoryRepository.save(inventory);
        return new InventoryResponse(inventory);
    }

    public List<InventoryResponse> isInStock(List<String> skuCode) {
        var products = inventoryRepository.findBySkuCodeIn(skuCode).stream().map(product ->
                InventoryResponse.builder()
                        .skuCode(product.getSkuCode())
                        .inStock(product.getQuantity() > 0).build()
        ).toList();

        return products;
    }

}
