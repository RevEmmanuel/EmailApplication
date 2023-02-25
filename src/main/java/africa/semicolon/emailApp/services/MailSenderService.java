package africa.semicolon.emailApp.services;

public interface MailSenderService {

    public void sendEmail(String to, String subject, String body);
}
