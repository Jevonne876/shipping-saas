package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.PlatformUser;
import com.shipping.saas.shippingSaas.domain.dto.PlatformUserDTO;
import com.shipping.saas.shippingSaas.exceptions.BadRequestException;
import com.shipping.saas.shippingSaas.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/platform-users")
@AllArgsConstructor
public class PlatformUsersResources {


    private final UserServiceImpl userService;

    @PostMapping("")
    public ResponseEntity<PlatformUser> createNewUser(@RequestBody PlatformUserDTO platformUser) throws Exception {

        return new ResponseEntity<>(userService.save(platformUser), OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PlatformUser> updateUser(@PathVariable UUID id, @RequestBody PlatformUserDTO platformUser) throws Exception {

        return new ResponseEntity<>(userService.update(id, platformUser), OK);
    }

    @GetMapping
    public ResponseEntity<List<PlatformUser>> getAllUsers() {

        return new ResponseEntity<>(userService.findAll(), OK);
    }
}
