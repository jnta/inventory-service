package com.jonata.inventoryservice.dto;

import com.jonata.inventoryservice.model.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InventoryResponse {
    private String skuCode;
    private boolean inStock;

    public InventoryResponse(Inventory inventory) {
        this.skuCode = inventory.getSkuCode();
        if (inventory.getQuantity() > 0) {
            this.inStock = true;
        }
    }
}
