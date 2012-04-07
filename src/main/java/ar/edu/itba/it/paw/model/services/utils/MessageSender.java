package ar.edu.itba.it.paw.model.services.utils;

import javax.mail.Message;

/**
 * Interface for sending a message though SMTP for example.
 * 
 * @author cris
 */
public interface MessageSender {
	public boolean send(final Message m);
}
