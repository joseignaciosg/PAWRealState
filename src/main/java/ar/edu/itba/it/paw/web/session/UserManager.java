package ar.edu.itba.it.paw.web.session;

import ar.edu.itba.it.paw.domain.entities.User;

public interface UserManager {

	public User getCurrentUser();

	public void setCurrentUser(User user);

	public void forgetCurrentUser();
}
