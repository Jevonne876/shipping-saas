package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.dto.WarehouseDTO;
import com.shipping.saas.shippingSaas.service.impl.WareHouseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouse-address/")
public class WarehouseAddressResource {

    private WareHouseServiceImpl wareHouseServiceImpl;

    @PostMapping
    public ResponseEntity<WarehouseDTO> save(@RequestBody WarehouseDTO warehouseAddress) {
        log.info("save warehouse address {}", warehouseAddress);
        return new ResponseEntity<>(wareHouseServiceImpl.create(warehouseAddress), OK);
    }
}
