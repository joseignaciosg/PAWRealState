package ar.edu.itba.it.paw.domain.services;

import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.*;

import ar.edu.itba.it.paw.domain.entities.*;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	/*
	 * Send HTML mail (simple)
	 */
	public boolean sendMail(final ContactRequest request) {
		try {
			final Property property = request.getPropRefered();
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(
					mimeMessage, "UTF-8");

			message.setSubject("Chinuprop - Petición de Contacto");
			message.setTo(property.getOwner().getMail());

			final StringBuilder sb = new StringBuilder();

			sb.append(
					"Usted tiene una nueva solicitud de contacto para su propiedad en "
							+ property.getAddress() + ", "
							+ property.getNeighborhood() + ".").append("\n");

			sb.append("Datos del contacto:").append("\n");

			sb.append("Nombre:").append(request.getName()).append("\n");

			if (request.getEmail() != null) {
				sb.append("Email:").append(request.getEmail()).append("\n");
			}

			if (request.getTelephone() != null) {
				sb.append("Telefono:").append(request.getTelephone())
						.append("\n");
			}

			if (request.getDescription() != null) {
				sb.append("Descripcion:").append(request.getDescription())
						.append("\n");
			}

			message.setText(sb.toString(), false);

			this.mailSender.send(mimeMessage);
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
