package com.jonata.inventoryservice.controller;

import com.jonata.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public boolean isInStock(@PathVariable(value = "sku-code") String skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
