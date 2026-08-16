package Train.Managment.System.TMSIR.App.Controller;

import Train.Managment.System.TMSIR.App.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketEmailController {

    @Autowired
    private EmailService emailService;

    /*@PostMapping("/send-email")
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
}*/

   /* @PostMapping("/send-email")
    public ResponseEntity<?> sendTicketEmail(@RequestBody Map<String, Object> request) {
        try {
            // Safely extract email regardless of nulls
            String recipientEmail = request.get("email") != null ? request.get("email").toString().trim() : null;

            if (recipientEmail == null || recipientEmail.isBlank()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Email is required"));
            }

            // Safely parse other fields with default fallbacks
            String pnr = request.getOrDefault("pnr", "RR-882190").toString();
            String trainName = request.getOrDefault("trainName", "Express Train").toString();
            String source = request.getOrDefault("source", "Origin").toString();
            String destination = request.getOrDefault("destination", "Destination").toString();

            // Call your email service
            emailService.sendTicketEmail(recipientEmail, pnr, trainName, source, destination);

            return ResponseEntity.ok(Map.of("message", "Ticket successfully emailed to " + recipientEmail));

        } catch (Exception e) {
            e.printStackTrace(); // Check your terminal console for the exact stack trace!
            return ResponseEntity.status(500).body(Map.of("error", "Failed to send email: " + e.getMessage()));
        }
    }}*/


    @Service
    public class EmailService {

        // Replace with your actual Resend API Key or load via @Value("${resend.api.key}")
        private static final String RESEND_API_KEY = "re_A21trF3Z_KqfWdwNP7G1Pm4GkFTLrYZTx";

        public void sendTicketEmail(String toEmail, String pnr, String trainName, String source, String destination) {
            try {
                String jsonPayload = """
                        {
                          "from": "Train Tickets <onboarding@resend.dev>",
                          "to": ["%s"],
                          "subject": "Booking Confirmation - PNR %s",
                          "html": "<h3>Ticket Details</h3><p>Train: <strong>%s</strong></p><p>Route: %s to %s</p><p>PNR: <strong>%s</strong></p>"
                        }
                        """.formatted(toEmail, pnr, trainName, source, destination, pnr);

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.resend.com/emails"))
                        .header("Authorization", "Bearer " + RESEND_API_KEY)
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() >= 400) {
                    throw new RuntimeException("Resend API error (" + response.statusCode() + "): " + response.body());
                }
            } catch (Exception e) {
                throw new RuntimeException("Email delivery failed: " + e.getMessage(), e);
            }
        }
    }
}