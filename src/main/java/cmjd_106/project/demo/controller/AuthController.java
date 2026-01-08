package cmjd_106.project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import cmjd_106.project.demo.dto.LoginDto;
import cmjd_106.project.demo.dto.SignupDto;
import cmjd_106.project.demo.dto.JwtResponse;
import cmjd_106.project.demo.entity.User;
import cmjd_106.project.demo.security.jwt.JwtUtils;
import cmjd_106.project.demo.service.UserService;
import cmjd_106.project.demo.repository.UserRepository;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired 
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        // Fetch user to get email
        User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
        
        JwtResponse response = new JwtResponse(
            jwt,
            userDetails.getUsername(),
            user != null ? user.getEmail() : ""
        );
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignupDto signupDto) {
        // Check if username exists
        if (userRepository.findByUsername(signupDto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Username already exists!");
        }
        
        // Check if email exists
        boolean emailExists = userService.getAllUsers().stream()
                .anyMatch(u -> u.getEmail().equals(signupDto.getEmail()));
        
        if (emailExists) {
            return ResponseEntity.badRequest().body("Error: Email already in use!");
        }
        
        // Create new user
        User user = new User();
        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setPassword(signupDto.getPassword());
        
        userService.createUser(user);
        
        return ResponseEntity.ok("User registered successfully!");
    }
}