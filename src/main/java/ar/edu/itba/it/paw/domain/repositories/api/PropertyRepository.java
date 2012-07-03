package ar.edu.itba.it.paw.domain.repositories.api;

import java.util.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.exceptions.*;

public interface PropertyRepository {

	public List<Property> getAll();

	public List<Property> getAll(PropertySearch search);

	public List<Property> getByUser(User user);

	public Photo getPhotoById(Integer id) throws NoSuchEntityException;

	public void delete(Property property);

	public boolean sendContactRequest(ContactRequest request);
}
