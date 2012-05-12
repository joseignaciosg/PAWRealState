package ar.edu.itba.it.paw.domain;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Type;

public class PropertyTest {

	@Test
	public void toggleVisibilityTest() {
		final Property prop = new Property();
		final Property prop2 = new Property(Type.APARTMENT, Operation.RENT,
				"bla", "bla", 1000, 3, 200, 200, 23, null, "nice", null);

		Assert.assertTrue(!prop.getVisible());
		Assert.assertTrue(!prop2.getVisible());

		prop.toggleVisibility();
		prop2.toggleVisibility();

		Assert.assertTrue(prop.getVisible());
		Assert.assertTrue(prop2.getVisible());

	}
}
