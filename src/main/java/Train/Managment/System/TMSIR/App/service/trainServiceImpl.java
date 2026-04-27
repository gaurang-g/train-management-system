package Train.Managment.System.TMSIR.App.service;

import Train.Managment.System.TMSIR.App.TrainRepository.BookingRepository;
import Train.Managment.System.TMSIR.App.TrainRepository.trainRepo;
import Train.Managment.System.TMSIR.App.entity.Booking;
import Train.Managment.System.TMSIR.App.entity.train;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class trainServiceImpl {
    @Autowired
    private trainRepo trainRepo;


    public train addTrain(train train) {
        //public Train addTrain(Train train) {
        System.out.println("Received Train Destination: " + train.getDestination());
        //    return trainRepo.save(train);
        //}
        return trainRepo.save(train);


    }

    public List<train> getTrainsByRoute(String source, String destination) {
        System.out.println("Searching for: [" + source + "] to [" + destination + "]");
        return trainRepo.findBySourceContainingIgnoreCaseAndDestinationContainingIgnoreCase(source, destination);

    }

    public List<train> getAll() {
        return trainRepo.findAll();

    }

    public train updateTrain(Long id, train trainDetails) {
        train existingTrain = trainRepo.findById(id).
                orElseThrow(() -> new RuntimeException("Train Not Found with Id :" + id));

        existingTrain.setSource(trainDetails.getSource());
        existingTrain.setDestination(trainDetails.getDestination());
        existingTrain.setTrainName(trainDetails.getTrainName());
        existingTrain.setTotalSeats(trainDetails.getTotalSeats());
        existingTrain.setDaysOfOperation(trainDetails.getDaysOfOperation());
        existingTrain.setArrivalTime(trainDetails.getArrivalTime());
        existingTrain.setDepartureTime(trainDetails.getDepartureTime());

        return trainRepo.save(existingTrain);
    }


    public String deleteTrain(String trainNumber) {
        train t = trainRepo.findByTrainNumber(trainNumber);
        trainRepo.delete(t);
        return "Train " + trainNumber + " deleted successfully";


    }

    public List<train> getTrainsByDay(String day) {
        return trainRepo.findByDaysOfOperationContaining(day);
    }


    @Autowired
    private BookingRepository bookingRepository;
@Transactional
    public Booking bookTrain(String trainNumber, String passengerName, int seats,String source , String destination) {
        train t = trainRepo.findByTrainNumber(trainNumber);

        if (t != null && t.getTotalSeats() >= seats) {
            // Update the train's available seats
            t.setTotalSeats(t.getTotalSeats() - seats);
            trainRepo.save(t);

            // Populate the booking details
            Booking newBooking = new Booking();
            newBooking.setTrainNumber(trainNumber);
            newBooking.setPassengerName(passengerName);
            newBooking.setSeatsBooked(seats);
            newBooking.setSource(source);
            newBooking.setDestination(destination);

            // If you have a date field, set it here:
             newBooking.setBookingDate(LocalDate.now());

            // Save and return the booking record
            return bookingRepository.save(newBooking);
        } else {
            // You can return null or throw a custom exception here
            throw new RuntimeException("Booking failed: Insufficient seats or train not found.");
        }
    }


    public train findByNumber(String trainNumber)
    {
        return trainRepo.findByTrainNumber(trainNumber);
    }
}