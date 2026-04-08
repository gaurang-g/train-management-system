package Train.Managment.System.TMSIR.App.Controller;

import Train.Managment.System.TMSIR.App.entity.Booking;
import Train.Managment.System.TMSIR.App.entity.train;
import Train.Managment.System.TMSIR.App.service.trainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/trains")
public class traincontroller
{

    @Autowired
    private trainServiceImpl trainService;

    @PostMapping("/add")
    public train createTrain(@RequestBody train train)
    {

        return trainService.addTrain(train);

    }
    @GetMapping("/search")

    public List<train> searchTrains(@RequestParam String source , @RequestParam String destination )
    {
        return trainService.getTrainsByRoute(source, destination);
    }
    // This is simple get all trains api
//   @GetMapping("/all")
//    public List<train> getAllTrains() {
//        return trainService.getAll(); // Implement this in your service to return repo.findAll()
//    }

    // This is get all trains api with Response Entity
    @GetMapping("/all")
    public ResponseEntity <List<train>> getAllTrains()
    {
        List<train> trains = trainService.getAll();
        return ResponseEntity.ok(trains);
    }

    @PutMapping("/{id}")

    public ResponseEntity<train>updateTrain(@PathVariable Long id , @RequestBody train trainDetails)
    {
        train updateTrain = trainService.updateTrain(id,trainDetails);
        return ResponseEntity.ok(updateTrain);
    }
   /* // Api to update Seat
    @PutMapping("/update-seats/{no}")
    public train updateSeats(@PathVariable("no") Long trainNumber ,@RequestParam Long  seats)
    {
        return trainService.updateTrain(trainNumber, seats);
    }*/

    //ApI To delete Trains

    @DeleteMapping("/remove/{no}")
        public String removeTrain(@PathVariable("no") String trainNumber)
        {
            return trainService.deleteTrain(trainNumber);
        }
@PostMapping("/book")
    public ResponseEntity<?>bookATicket(@RequestParam String trainNumber, @RequestParam String passengerName,
                                        @RequestParam int seats) {
    Booking booking = trainService.bookTrain(trainNumber, passengerName, seats);
    return ResponseEntity.ok(booking);
}


}
