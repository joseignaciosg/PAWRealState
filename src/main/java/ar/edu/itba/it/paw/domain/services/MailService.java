package ar.edu.itba.it.paw.domain.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	/*
	 * Send HTML mail (simple)
	 */
	public boolean sendMail(final String recipientName,
			final String recipientEmail) {
		try {
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(
					mimeMessage, "UTF-8");

			message.setSubject("Chinuprop - Petición de Contacto");
			message.setTo(recipientEmail);
			// TODO: armar mensaje
			message.setText("Hola rawr", false);

			this.mailSender.send(mimeMessage);
			return true;
		} catch (final Exception e) {
			// TODO: Improve this
			e.printStackTrace();
			return false;
		}
	}
}
