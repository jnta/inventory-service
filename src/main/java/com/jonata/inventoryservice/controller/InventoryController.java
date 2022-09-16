package com.jonata.inventoryservice.controller;

import com.jonata.inventoryservice.dto.InventoryRequest;
import com.jonata.inventoryservice.dto.InventoryResponse;
import com.jonata.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse placeProductInInventory(@RequestBody InventoryRequest inventoryRequest) {
        return inventoryService.placeProductInInventory(inventoryRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
