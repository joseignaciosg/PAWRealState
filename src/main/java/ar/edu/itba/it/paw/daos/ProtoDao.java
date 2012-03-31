package DAOS;

import java.util.List;

import model.Photo;

public class ProtoDao implements Dao<Photo> {

	/*
	 * Esto para mi no deber’a sea un dao por que siempre que voy a pedir una
	 * foto la voy a perdir relacionada con una propiedad o un usuario. Las
	 * fotos deberian ser atributos de property y usuarios.
	 */

	@Override
	public List<Photo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Photo getById(final String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(final Photo obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOrUpdate(final Photo obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
