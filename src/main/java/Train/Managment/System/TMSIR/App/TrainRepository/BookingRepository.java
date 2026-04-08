package Train.Managment.System.TMSIR.App.TrainRepository;

import Train.Managment.System.TMSIR.App.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long>
{

}

