package Train.Managment.System.TMSIR.App.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name = "trains")
@Data

@NoArgsConstructor
@AllArgsConstructor
public class train
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique = true)
     private String trainNumber;

    @Column(nullable = false)
     private String trainName;

     private String source;

     @Column(nullable = false)
   //  @JsonProperty("destination")
     private String destination ;

     @Column(nullable = false)
     private Integer totalSeats;

     @Column(name ="DOO")
     private  String daysOfOperation ;

     @Column(name ="Departure")
     @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime departureTime;

     @Column(name ="Arrival")
     @JsonFormat(pattern ="HH:mm:ss")
     private LocalTime arrivalTime;

}
