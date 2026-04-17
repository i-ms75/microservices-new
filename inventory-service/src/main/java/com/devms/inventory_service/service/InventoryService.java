package com.devms.inventory_service.service;

import com.devms.inventory_service.dto.InventoryResponse;
import com.devms.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService
{
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) throws InterruptedException {
//        log.info("Wait time started");
//        Thread.sleep(10000);
//        log.info("Wait is over");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .inStock(inventory.getQuantity()>0)
                            .build()
                ).toList();
    }
}
