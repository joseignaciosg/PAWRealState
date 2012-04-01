package ar.edu.itba.it.paw.daos.api;

import java.util.List;

import ar.edu.itba.it.paw.model.entities.User;

public interface UserDao extends Dao<User> {

	public List<User> getAll();

	public User getUser(String username);
}
