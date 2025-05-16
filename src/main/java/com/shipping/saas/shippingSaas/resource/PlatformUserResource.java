package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.PlatformUser;
import com.shipping.saas.shippingSaas.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/platform-users")
public class PlatformUserResource {


    private final UserServiceImpl userService;

    public PlatformUserResource(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createPlatformUser(@RequestBody PlatformUser newUser) {

        PlatformUser savedUser = userService.save(newUser);

        URI location = URI.create("/register/" + savedUser.getId());

        return ResponseEntity.created(location).body(savedUser);

    }
}
