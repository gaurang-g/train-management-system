package Train.Managment.System.TMSIR.App.service;

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
