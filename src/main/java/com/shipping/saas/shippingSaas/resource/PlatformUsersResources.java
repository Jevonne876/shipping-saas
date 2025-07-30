package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.PlatformUser;
import com.shipping.saas.shippingSaas.domain.dto.PlatformUserDTO;
import com.shipping.saas.shippingSaas.exceptions.PlatformUserNotFoundException;
import com.shipping.saas.shippingSaas.repository.PlatformUserRepository;
import com.shipping.saas.shippingSaas.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

/**
 * The type Platform users resources.
 */
@RestController
@RequestMapping("/api/platform-users")
@AllArgsConstructor
@Slf4j
public class PlatformUsersResources {


    private final UserServiceImpl userService;
    private final PlatformUserRepository platformUserRepository;

    /**
     * Create new user response entity.
     *
     * @param platformUser the platform user
     * @return the response entity
     * @throws Exception the exception
     */
    @PostMapping("")
    public ResponseEntity<PlatformUser> createNewUser(@RequestBody PlatformUserDTO platformUser) throws Exception {
        log.info("creating new platform user");
        return new ResponseEntity<>(userService.save(platformUser), OK);

    }

    /**
     * Update user response entity.
     *
     * @param id           the id
     * @param platformUser the platform user
     * @return the response entity
     * @throws Exception the exception
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<PlatformUser> updateUser(@PathVariable UUID id, @RequestBody PlatformUserDTO platformUser) throws Exception {
        log.info("updating existing platform user with id {}", id);
        return new ResponseEntity<>(userService.update(id, platformUser), OK);
    }

    /**
     * Find user by id response entity.
     *
     * @param id the id
     * @return the response entity
     * @throws Exception the exception
     */
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<PlatformUser> findUserById(@PathVariable UUID id) throws Exception {
        log.info("find existing platform user with id {}", id);
        return new ResponseEntity<>(userService.findById(id).orElseThrow(() -> new PlatformUserNotFoundException("User Not Found")), OK);
    }

    /**
     * Find user by email response entity.
     *
     * @param email the email
     * @return the response entity
     * @throws Exception the exception
     */
    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<PlatformUser> findUserByEmail(@PathVariable String email) throws Exception {
        log.info("find existing platform user with email {}", email);
        return new ResponseEntity<>(userService.findByEmail(email).orElseThrow(() -> new PlatformUserNotFoundException("User Not Found")), OK);
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @GetMapping
    public ResponseEntity<List<PlatformUser>> getAllUsers() {

        return new ResponseEntity<>(userService.findAll(), OK);
    }

    /**
     * Deactivate response entity.
     *
     * @param id the id
     * @return the response entity
     * @throws PlatformUserNotFoundException the platform user not found exception
     */
    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable UUID id) throws PlatformUserNotFoundException {
        log.info("deactivating existing platform user with id {}", id);
        PlatformUser platformUser = userService.findById(id).orElseThrow(() -> new PlatformUserNotFoundException("User Not Found"));
        platformUser.setActive(false);
        platformUserRepository.save(platformUser);
        return new ResponseEntity<>(OK);
    }

    /**
     * Delete user response entity.
     *
     * @param id the id
     * @return the response entity
     * @throws PlatformUserNotFoundException the platform user not found exception
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_PLATFORM_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) throws PlatformUserNotFoundException {
        log.info("deleting existing platform user with id {}", id);
        //todo fix issue with user not having role above and still able to delete other users.
        platformUserRepository.delete(userService.findById(id).orElseThrow(() -> new PlatformUserNotFoundException("User Not Found")));
        return new ResponseEntity<>(OK);
    }
}
