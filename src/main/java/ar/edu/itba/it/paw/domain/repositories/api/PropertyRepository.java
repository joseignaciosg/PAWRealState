package ar.edu.itba.it.paw.domain.repositories.api;

import java.util.List;

import ar.edu.itba.it.paw.domain.entities.ContactRequest;
import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.User;

public interface PropertyRepository {

	public List<Property> getAll();

	public List<Property> getAll(PropertySearch search);

	public List<Property> getByUser(User user);

	public Photo getPhotoById(Integer id);

	public boolean sendContactRequest(ContactRequest request);
}
