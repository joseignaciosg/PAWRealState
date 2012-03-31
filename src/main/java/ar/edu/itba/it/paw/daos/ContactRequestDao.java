package DAOS;

import java.sql.Connection;
import java.util.List;

import model.ConnectionManager;
import model.ContactRequest;

public class ContactRequestDao implements Dao<ContactRequest> {

	private Connection connection = null;
	private ConnectionManager manager = null;

	public ContactRequestDao(final ConnectionManager manager) {
		this.manager = manager;
		this.connection = manager.getConnection();
	}

	@Override
	public List<ContactRequest> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactRequest getById(final String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(final ContactRequest obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOrUpdate(final ContactRequest obj) {
		// TODO Auto-generated method stub
		return true;
	}

}
