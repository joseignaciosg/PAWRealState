package DAOS;

import java.sql.Connection;
import java.util.List;

import model.ConnectionManager;
import model.User;

public class UserDao implements Dao<User> {

	private Connection connection = null;
	private ConnectionManager manager = null;

	public UserDao(final ConnectionManager manager) {
		this.manager = manager;
		this.connection = manager.getConnection();
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(final String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(final User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOrUpdate(final User obj) {
		// TODO Auto-generated method stub
		return true;
	}

}
