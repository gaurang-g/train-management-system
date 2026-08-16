package Train.Managment.System.TMSIR.App.Controller;

import Train.Managment.System.TMSIR.App.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketEmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendTicketEmail(@RequestBody Map<String,String> request) {
        try {
            emailService.sendTicketEmail(
                    request.get("email"),
                    request.get("PNR"),
                    request.get("TrainName"),
                    request.get("Source"),
                    request.get("Destination")
            );
            return ResponseEntity.ok("Ticket email sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
        }
    }
}