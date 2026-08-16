package Train.Managment.System.TMSIR.App.service;
/*
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendTicketEmail(String toEmail, String pnr, String trainName, String source, String destination) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("railreservesupport1@gmail.com", "RailReserve Support");
            helper.setTo(toEmail);
            helper.setSubject("RailReserve - E-Ticket Confirmation [" + pnr + "]");

            // HTML Styled Email Body
            String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 20px; background-color: #030712; color: #f8fafc; border-radius: 12px;'>"
                    + "<h2 style='color: #06b6d4;'>RailReserve Confirmation</h2>"
                    + "<p>Your booking is confirmed!</p>"
                    + "<hr style='border: 1px solid #1e293b;' />"
                    + "<p><strong>PNR:</strong> <span style='color: #06b6d4;'>" + pnr + "</span></p>"
                    + "<p><strong>Train:</strong> " + trainName + "</p>"
                    + "<p><strong>Route:</strong> " + source + " -> " + destination + "</p>"
                    + "<p><strong>Status:</strong> <span style='color: #10b981;'>CONFIRMED</span></p>"
                    + "</div>";

            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}

 */



/*

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class EmailServiceImpl implements EmailService {

    // Reads key from application.properties or environment variables
    @Value("${resend.api.key}")
    private String resendApiKey;

    @Override
    public void sendTicketEmail(String toEmail, String pnr, String trainName, String source, String destination) {
        try {
            // Your custom HTML styled body
            String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 20px; background-color: #030712; color: #ffffff;'>"
                    + "<h2 style='color: #06b6d4;'>RailReserve Confirmation</h2>"
                    + "<p style='color: #e5e7eb;'>Your booking is confirmed!</p>"
                    + "<hr style='border: 1px solid #1e293b;' />"
                    + "<p><strong>PNR:</strong> <span style='color: #06b6d4;'>" + pnr + "</span></p>"
                    + "<p><strong>Train:</strong> " + trainName + "</p>"
                    + "<p><strong>Route:</strong> " + source + " -&gt; " + destination + "</p>"
                    + "<p><strong>Status:</strong> <span style='color: #10b981;'>CONFIRMED</span></p>"
                    + "</div>";

            // Construct JSON Payload for Resend
            // Note: Keep 'onboarding@resend.dev' for testing unless you verified a custom domain on Resend
            String jsonPayload = "{"
                    + "\"from\": \"RailReserve Support <tickets@yourdomain.com>\","
                    + "\"to\": [\"" + toEmail + "\"],"
                    + "\"subject\": \"RailReserve - E-Ticket Confirmation [" + pnr + "]\","
                    + "\"html\": \"" + htmlContent.replace("\"", "\\\"") + "\""
                    + "}";

            // Send HTTPS POST request via Port 443
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.resend.com/emails"))
                    .header("Authorization", "Bearer " + resendApiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 400) {
                throw new RuntimeException("Resend API error (" + response.statusCode() + "): " + response.body());
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to send ticket email: " + e.getMessage(), e);
        }
    }
}*/


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
            /*String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 20px; background-color: #030712; color: #ffffff;'>"
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
                """.formatted(senderEmail, toEmail, pnr, htmlContent.replace("\"", "\\\""));*/
            String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 24px; background-color: #0b0f19; color: #f8fafc; max-width: 500px; margin: 0 auto; border-radius: 12px; border: 1px solid #1e293b;'>"
                    + "<div style='text-align: center; border-bottom: 2px solid #06b6d4; padding-bottom: 16px; margin-bottom: 20px;'>"
                    + "<h2 style='color: #06b6d4; margin: 0; font-size: 24px; letter-spacing: 1px;'>RailReserve</h2>"
                    + "<span style='background-color: rgba(16, 185, 129, 0.15); color: #10b981; border: 1px solid #10b981; padding: 4px 12px; border-radius: 20px; font-weight: bold; font-size: 11px; display: inline-block; margin-top: 8px;'>&#10003; CONFIRMED</span>"
                    + "</div>"
                    + "<div style='background-color: #0f172a; border-radius: 8px; padding: 16px; text-align: center; margin-bottom: 20px; border: 1px dashed #06b6d4;'>"
                    + "<p style='font-size: 11px; color: #94a3b8; text-transform: uppercase; margin: 0 0 4px 0;'>Booking Reference (PNR)</p>"
                    + "<p style='font-size: 22px; font-weight: bold; color: #38bdf8; margin: 0; letter-spacing: 2px;'>" + pnr + "</p>"
                    + "</div>"
                    + "<div style='background-color: #0f172a; border-radius: 8px; padding: 16px; margin-bottom: 20px;'>"
                    + "<table width='100%%' style='border-collapse: collapse;'>"
                    + "<tr>"
                    + "<td style='width: 45%%;'><p style='font-size: 10px; color: #64748b; margin: 0; text-transform: uppercase;'>From</p><p style='font-size: 15px; font-weight: bold; color: #f1f5f9; margin: 4px 0 0 0;'>" + source + "</p></td>"
                    + "<td style='text-align: center; width: 10%%; color: #06b6d4; font-size: 18px;'>&#10142;</td>"
                    + "<td style='width: 45%%; text-align: right;'><p style='font-size: 10px; color: #64748b; margin: 0; text-transform: uppercase;'>To</p><p style='font-size: 15px; font-weight: bold; color: #f1f5f9; margin: 4px 0 0 0;'>" + destination + "</p></td>"
                    + "</tr>"
                    + "</table>"
                    + "</div>"
                    + "<div style='border-top: 1px solid #1e293b; padding-top: 16px; display: flex; justify-content: space-between;'>"
                    + "<p style='margin: 0; font-size: 12px; color: #94a3b8;'>Train: <strong style='color: #f8fafc;'>" + trainName + "</strong></p>"
                    + "</div>"
                    + "<p style='font-size: 11px; color: #64748b; text-align: center; margin-top: 24px; border-top: 1px solid #1e293b; padding-top: 12px;'>Have a safe journey with RailReserve!</p>"
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
