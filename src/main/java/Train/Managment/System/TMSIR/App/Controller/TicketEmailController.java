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
    public ResponseEntity<?> sendTicketEmail(@RequestBody Map<String, String> request) {
        String recipientEmail = request.get("email");

        if (recipientEmail == null || recipientEmail.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email is required"));
        }

        String pnr = request.getOrDefault("pnr", "RR-882190");
        String trainName = request.getOrDefault("trainName", "Express Train");
        String source = request.getOrDefault("source", "Origin");
        String destination = request.getOrDefault("destination", "Destination");

        // Send email to whichever recipient email was passed from the UI
        emailService.sendTicketEmail(recipientEmail, pnr, trainName, source, destination);

        return ResponseEntity.ok(Map.of("message", "Ticket successfully emailed to " + recipientEmail));
    }
}