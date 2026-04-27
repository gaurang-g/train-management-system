package Train.Managment.System.TMSIR.App.service.impl;

import Train.Managment.System.TMSIR.App.TrainRepository.AuthService;
import Train.Managment.System.TMSIR.App.TrainRepository.UserRepo;
import Train.Managment.System.TMSIR.App.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service

public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public String loginUser(String username, String password) {
        // 1. Logic: Check if user exists
        return userRepo.findByUsername(username)
                .map(user -> {
                    // 2. Logic: Compare passwords
                    if (user.getPassword().equals(password)) {
                        return "SUCCESS";
                    } else {
                        return "INVALID_PASSWORD";
                    }
                })
                .orElse("USER_NOT_FOUND");
    }

    public User registerNewUser(User newUser) {
        // 1. Check if the username already exists using your repo method
        if (userRepo.findByUsername(newUser.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken!");
        }

        // 2. If unique, save and return the full user object (including its new ID)
        return userRepo.save(newUser);
    }


}