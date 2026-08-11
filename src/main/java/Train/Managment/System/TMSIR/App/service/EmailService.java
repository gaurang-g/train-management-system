package Train.Managment.System.TMSIR.App.service;

public interface EmailService {
    void sendTicketEmail(String toEmail, String pnr, String trainName,
                         String source, String destination);
}