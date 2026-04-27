package Train.Managment.System.TMSIR.App.TrainRepository;

import Train.Managment.System.TMSIR.App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Long>
    {
        Optional<User>findByUsername(String username);
    }

