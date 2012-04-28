package ar.edu.itba.it.paw.daos;

import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.daos.impl.sql.SQLContactRequestDao;
import ar.edu.itba.it.paw.daos.impl.sql.SQLPhotoDao;
import ar.edu.itba.it.paw.daos.impl.sql.SQLPropertyDao;
import ar.edu.itba.it.paw.daos.impl.sql.SQLUserDao;
import ar.edu.itba.it.paw.db.ConnectionProvider;

@Component
public class SQLDaoProvider extends DaoProvider {
	public SQLDaoProvider() {
		this(ConnectionProvider.getProvider());
	}

	public SQLDaoProvider(final ConnectionProvider provider) {
		this.userDao = new SQLUserDao(provider);
		this.photoDao = new SQLPhotoDao(provider);
		this.propertyDao = new SQLPropertyDao(provider, this.userDao,
				this.photoDao);

		this.photoDao = new SQLPhotoDao(provider);
		this.contactrequestDao = new SQLContactRequestDao(provider,
				this.propertyDao);

		((SQLUserDao) this.userDao).setPropertyDao(this.propertyDao);

	}
}
