package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.CustomUserDetails;
import com.shipping.saas.shippingSaas.domain.PlatformUser;
import com.shipping.saas.shippingSaas.domain.dto.AuthRequest;
import com.shipping.saas.shippingSaas.domain.dto.AuthResponse;
import com.shipping.saas.shippingSaas.jwt.JwtUtil;
import com.shipping.saas.shippingSaas.service.impl.ClientUserServiceImpl;
import com.shipping.saas.shippingSaas.service.impl.PlatformUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;


    private final PlatformUserService platformUserService;

    private final ClientUserServiceImpl clientUserServiceImpl;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        CustomUserDetails userDetails = null;

        try {
            userDetails = (CustomUserDetails) platformUserService.loadUserByUsername(authRequest.getUsername());
        } catch (UsernameNotFoundException ex) {
            try {
                userDetails = (CustomUserDetails) clientUserServiceImpl.loadUserByUsername(authRequest.getUsername());
            } catch (UsernameNotFoundException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        }

        // Manually check password
        if (!passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        String jwt = jwtUtil.generateToken(
            userDetails.getUsername(),
            userDetails.getAuthorities().toString(),
            userDetails.getUserType(),
            userDetails.getClientCode()
        );

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @GetMapping("/me")
    public ResponseEntity<PlatformUser> getCurrentUser(Authentication auth) {
        String email = auth.getName(); // comes from JWT
        PlatformUser user = platformUserService.findByEmail(email);
        return ResponseEntity.ok(user);
    }


}
