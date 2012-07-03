package ar.edu.itba.it.paw.domain;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Type;

public class PropertyTest {

	@Test
	public void toggleVisibilityTest() {
		final Property prop = new Property(Type.APARTMENT, Operation.RENT,
				"bla", "bla", 1000, 3, 200, 200, 23, null, null, "nice", null, null);
		final Property prop2 = new Property(Type.APARTMENT, Operation.RENT,
				"bla", "bla", 1000, 3, 200, 200, 23, null, null, "nice", null, null);

		Assert.assertTrue(prop.getVisible());
		Assert.assertTrue(prop2.getVisible());

		prop.toggleVisibility();
		prop2.toggleVisibility();

		Assert.assertTrue(!prop.getVisible());
		Assert.assertTrue(!prop2.getVisible());
	}

	@Test
	public void updateVisitCountTest() {
		final Property prop = new Property(Type.APARTMENT, Operation.RENT,
				"bla", "bla", 1000, 3, 200, 200, 23, null, null, "nice", null, null);
		Assert.assertTrue(prop.getVisitCount() == 0);
		prop.updateVisitCount();
		Assert.assertTrue(prop.getVisitCount() == 1);
	}

	@Test
	public void reserveTest() {
		final Property prop = new Property(Type.APARTMENT, Operation.RENT,
				"bla", "bla", 1000, 3, 200, 200, 23, null, null, "nice", null, null);
		Assert.assertFalse(prop.isReserved());
		prop.reserve();
		Assert.assertTrue(prop.isReserved());
		prop.unreserve();
		Assert.assertFalse(prop.isReserved());
	}
}
