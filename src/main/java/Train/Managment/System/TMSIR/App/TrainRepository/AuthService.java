package Train.Managment.System.TMSIR.App.TrainRepository;

import Train.Managment.System.TMSIR.App.entity.User;

public interface AuthService
    {
        String loginUser(String username , String password);


        User registerNewUser(User newUser);
    }
