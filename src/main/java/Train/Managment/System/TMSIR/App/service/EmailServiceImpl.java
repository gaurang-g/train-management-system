package Train.Managment.System.TMSIR.App.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${brevo.api.key}")
    private String brevoApiKey;

    @Value("${brevo.sender.email}")
    private String senderEmail;

    @Override
    public void sendTicketEmail(String toEmail, String pnr, String trainName, String source, String destination) {
        try {
            String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 20px; background-color: #030712; color: #ffffff;'>"
                    + "<h2 style='color: #06b6d4;'>RailReserve Confirmation</h2>"
                    + "<p>Your booking is confirmed!</p>"
                    + "<hr style='border: 1px solid #1e293b;' />"
                    + "<p><strong>PNR:</strong> <span style='color: #06b6d4;'>" + pnr + "</span></p>"
                    + "<p><strong>Train:</strong> " + trainName + "</p>"
                    + "<p><strong>Route:</strong> " + source + " -&gt; " + destination + "</p>"
                    + "<p><strong>Status:</strong> <span style='color: #10b981;'>CONFIRMED</span></p>"
                    + "</div>";

            String jsonPayload = """
                {
                  "sender": {"name": "RailReserve Support", "email": "%s"},
                  "to": [{"email": "%s"}],
                  "subject": "RailReserve - E-Ticket Confirmation [%s]",
                  "htmlContent": "%s"
                }
                """.formatted(senderEmail, toEmail, pnr, htmlContent.replace("\"", "\\\""));

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.brevo.com/v3/smtp/email"))
                    .header("api-key", brevoApiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 400) {
                throw new RuntimeException("Brevo API Error (" + response.statusCode() + "): " + response.body());
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to send ticket email: " + e.getMessage(), e);
        }
    }
}
