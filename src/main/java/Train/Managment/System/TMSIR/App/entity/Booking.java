package Train.Managment.System.TMSIR.App.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@Table(name = "Reservation")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private String passengerName;
    private String trainNumber;
    private int  seatsBooked;
    private LocalDate bookingDate;

    private String  Source ;
    private String Destination;



}
