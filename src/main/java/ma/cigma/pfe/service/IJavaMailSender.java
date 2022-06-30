package ma.cigma.pfe.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/30/2022 8:30 PM
 */
public interface IJavaMailSender {

    void sendEmail(String to, String patient, LocalDate dateVisite, LocalTime heureVisite) throws MessagingException, UnsupportedEncodingException;
}
