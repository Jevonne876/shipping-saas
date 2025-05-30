package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.PlatformUser;
import com.shipping.saas.shippingSaas.domain.dto.PlatformUserDTO;
import com.shipping.saas.shippingSaas.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
