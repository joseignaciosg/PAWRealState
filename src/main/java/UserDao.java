import java.util.List;

import ar.edu.itba.it.paw.daos.Dao;

public interface UserDao extends Dao<User> {

	public List<T> getAll();

}
