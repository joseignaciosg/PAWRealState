package ar.edu.itba.it.paw.web.session;

import ar.edu.itba.it.paw.model.entities.User;

public interface UserManager {
	public User getCurrentUser();

	public void setCurrentUser(User user);
}
