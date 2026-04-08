package Train.Managment.System.TMSIR.App.TrainRepository;

import Train.Managment.System.TMSIR.App.entity.train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface trainRepo extends JpaRepository<train, Long>

{
    // custom Query for to find Trains By Routes
    List<train> findBySourceContainingIgnoreCaseAndDestinationContainingIgnoreCase(String source , String destination);

    train findByTrainNumber(String trainNumber);

List<train>findByDaysOfOperationContaining(String day);


}
