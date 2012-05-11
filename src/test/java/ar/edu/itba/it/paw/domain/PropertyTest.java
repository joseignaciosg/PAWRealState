package ar.edu.itba.it.paw.domain;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Type;

public class PropertyTest {

	@Test
	public void toggleVisibilityTest() {
		final Property prop = new Property(Type.HOUSE, Operation.RENT,
				"Retiro", "Marcelo T. de Alvear 1270", 100000, 4, 400, 200, 15,
				new ArrayList<String>(), "pretty nice", null);

		Assert.assertTrue(prop.getVisible());

		prop.toggleVisibility();

		Assert.assertFalse(prop.getVisible());
	}
}
