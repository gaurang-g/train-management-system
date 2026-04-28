package Train.Managment.System.TMSIR.App.Controller;

import Train.Managment.System.TMSIR.App.TrainRepository.AuthService;
import Train.Managment.System.TMSIR.App.TrainRepository.UserRepo;
import Train.Managment.System.TMSIR.App.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")

public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
       /* return userRepo.findByUsername(username)
                .map(user -> {
                    if (user.getPassword().equals(password)) {
                        return ResponseEntity.ok("{\"status\": \"success\"}");
                    }
                    return ResponseEntity.status(401).body("Invalid Password");
                })
                .orElse(ResponseEntity.status(404).body("User not found"));*/
        String result = authService.loginUser(username, password);

        if ("SUCCESS".equals(result)) {
            return ResponseEntity.ok("{\"status\": \"success\"}");
        } else if ("INVALID_PASSWORD".equals(result)) {
            return ResponseEntity.status(401).body("{\"message\": \"Invalid Password\"}");
        } else {
            return ResponseEntity.status(404).body("{\"message\": \"User not found\"}");
        }

    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        try {
            // We call the method name exactly as it appears in your ServiceImpl
            User savedUser = authService.registerNewUser(newUser);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            // This catches the "Username is already taken!" error and sends it to the UI
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    }

