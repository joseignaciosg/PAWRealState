package ar.edu.itba.it.paw.repositories;

import java.util.*;

import junit.framework.Assert;

import org.hibernate.*;
import org.junit.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.*;

import ar.edu.itba.it.paw.*;
import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.domain.entities.Room.RoomType;
import ar.edu.itba.it.paw.domain.repositories.api.*;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch.Order;
import ar.edu.itba.it.paw.domain.repositories.impl.*;

public class HibernatePropertyRepositoryTest extends BaseTest {
	@Autowired
	HibernateUserRepository userRepository;

	@Autowired
	HibernatePropertyRepository propertyRepository;

	@Autowired
	SessionFactory factory;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void searchTest() {

		final User u = new User("name", "username", "bla", "bla", "bla", "bla");
		this.userRepository.save(u);

		final Property property = new Property(Type.APARTMENT, Operation.RENT,
				"flores", "somewhere", 1000, 4, 120, 120, 10,
				(List) Collections.emptyList(), (List) Collections.emptyList(),
				"salida", u, null);

		this.userRepository.save(property);

		// Visibility

		PropertySearch propSearch = new PropertySearch(null, null, null, null,
				null, null, Order.ASC, null, null, true, null, null);

		List<Property> result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(result.contains(property));

		// Operation type

		propSearch = new PropertySearch(Operation.RENT, null, null, null, null,
				null, Order.ASC, null, null, true, null, null);

		result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(result.contains(property));

		// Sell type

		propSearch = new PropertySearch(Operation.RENT, Type.APARTMENT, null,
				null, null, null, Order.ASC, null, null, true, null, null);

		result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(result.contains(property));

		// Negative test

		propSearch = new PropertySearch(Operation.RENT, Type.HOUSE, null, null,
				null, null, Order.ASC, null, null, true, null, null);

		result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(!result.contains(property));

		propSearch = new PropertySearch(Operation.SELL, Type.HOUSE, null, null,
				null, null, Order.ASC, null, null, false, null, null);

		result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(!result.contains(property));

		propSearch = new PropertySearch(Operation.SELL, Type.APARTMENT, null,
				null, null, null, Order.ASC, null, null, false, null, null);

		result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(!result.contains(property));

		// Price high search

		propSearch = new PropertySearch(Operation.RENT, Type.APARTMENT, null,
				1100, null, null, Order.ASC, null, null, true, null, null);

		result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(result.contains(property));

		// Price low search

		propSearch = new PropertySearch(Operation.RENT, Type.APARTMENT, 900,
				1100, null, null, Order.ASC, null, null, true, null, null);

		result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(result.contains(property));

		// Price invalid

		propSearch = new PropertySearch(Operation.RENT, Type.APARTMENT, 900,
				950, null, null, Order.ASC, null, null, true, null, null);

		result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(!result.contains(property));

		// By user

		propSearch = new PropertySearch(Operation.RENT, Type.APARTMENT, 900,
				null, null, null, Order.ASC, null, null, true, null, u);

		result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(result.contains(property));

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void userSearchTest() {

		final User u = new User("name", "username", "bla", "bla", "bla", "bla");
		this.userRepository.save(u);

		final Property property = new Property(Type.APARTMENT, Operation.RENT,
				"flores", "somewhere", 1000, 4, 120, 120, 10,
				(List) Collections.emptyList(), (List) Collections.emptyList(),
				"salida", u, null);

		final Property property2 = new Property(Type.APARTMENT, Operation.RENT,
				"Caballito", "Caballito", 1500, 4, 120, 120, 10,
				(List) Collections.emptyList(), (List) Collections.emptyList(),
				"salida", u, null);

		this.userRepository.save(property);

		final PropertySearch propSearch = new PropertySearch(null, null, null,
				null, null, null, Order.ASC, null, null, true, null, u);

		List<Property> list = this.propertyRepository.getAll(propSearch);

		Assert.assertEquals(1, list.size());

		this.userRepository.save(property2);

		list = this.propertyRepository.getAll(propSearch);

		Assert.assertEquals(2, list.size());

	}

	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void roomSearchTest() {
		final User u = new User("name", "username", "bla", "bla", "bla", "bla");
		this.userRepository.save(u);

		@SuppressWarnings("rawtypes")
		final Property property = new Property(Type.APARTMENT, Operation.RENT,
				"flores", "somewhere", 1000, 4, 120, 120, 10,
				(List) Collections.emptyList(), new ArrayList<Room>(),
				"salida", u, null);

		this.userRepository.save(property);

		final Room room = new Room(RoomType.BATHROOM, 10, 1, property);

		final Room room2 = new Room(RoomType.DORM, 20, 1, property);

		property.getRooms().add(room);
		property.getRooms().add(room2);

		this.userRepository.save(property);

		RoomSearch roomSearch = new RoomSearch(RoomType.BATHROOM, 20, 30);
		RoomSearch roomSearch2 = new RoomSearch(RoomType.DORM, 20, 30);

		PropertySearch propSearch = new PropertySearch(null, null, null, null,
				null, null, Order.ASC, null,
				Arrays.asList(new RoomSearch[] { roomSearch }), true, null,
				null);

		List<Property> result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(!result.contains(property));

		roomSearch = new RoomSearch(RoomType.BATHROOM, 5, 15);
		roomSearch2 = new RoomSearch(RoomType.DORM, 15, 30);

		propSearch = new PropertySearch(Operation.RENT, Type.APARTMENT, 900,
				null, null, null, Order.ASC, null,
				Arrays.asList(new RoomSearch[] { roomSearch, roomSearch2 }),
				true, null, u);

		result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(result.contains(property));

		roomSearch2 = new RoomSearch(RoomType.DORM, 25, 30);

		propSearch = new PropertySearch(Operation.RENT, Type.APARTMENT, 900,
				null, null, null, Order.ASC, null,
				Arrays.asList(new RoomSearch[] { roomSearch, roomSearch2 }),
				true, null, u);

		result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(!result.contains(property));

	}

	@Test
	public void serviceSearchTest() {
		final User u = new User("name", "username", "bla", "bla", "bla", "bla");
		this.userRepository.save(u);

		final Property property = new Property(Type.APARTMENT, Operation.RENT,
				"flores", "somewhere", 1000, 4, 120, 120, 10,
				new ArrayList<Service>(), new ArrayList<Room>(), "salida", u,
				null);

		this.userRepository.save(property);

		property.getServices().add(Service.CABLE);
		property.getServices().add(Service.PADDLE);

		this.userRepository.save(property);

		PropertySearch propSearch = new PropertySearch(null, null, null, null,
				null, null, Order.ASC,
				Arrays.asList(new Service[] { Service.LAUNDRY }), null, true,
				null, null);

		List<Property> result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(!result.contains(property));

		propSearch = new PropertySearch(Operation.RENT, Type.APARTMENT, 900,
				null, null, null, Order.ASC, Arrays.asList(new Service[] {
						Service.CABLE, Service.PADDLE }), null, true, null, u);

		result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(result.contains(property));

		propSearch = new PropertySearch(Operation.RENT, Type.APARTMENT, 900,
				null, null, null, Order.ASC, Arrays.asList(new Service[] {
						Service.CABLE, Service.QUINCHO }), null, true, null, u);

		result = this.propertyRepository.getAll(propSearch);

		Assert.assertTrue(!result.contains(property));

	}
}
