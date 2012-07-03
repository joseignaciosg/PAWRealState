package ar.edu.itba.it.paw.domain;

import org.apache.wicket.injection.*;
import org.apache.wicket.model.*;
import org.apache.wicket.spring.injection.annot.*;
import org.springframework.util.*;

public class EntityModel<T> implements IModel<T> {

	private Class<T> type;
	private Integer id;

	private transient T value;
	private transient boolean attached;

	@SpringBean
	private transient EntityResolver resolver;

	public EntityModel(final Class<T> type, final Integer id) {
		super();
		Assert.notNull(type,
				"You must provide a type for entity resolver models!");
		Assert.notNull(id, "No id was provided to the entity model!");
		this.type = type;
		this.id = id;
	}

	public EntityModel(final Class<T> type, final T object) {
		super();
		Assert.notNull(type,
				"You must provide a type for entity resolver models!");
		this.type = type;
		this.id = (object == null ? null : this.resolver().getId(object));
		this.value = object;
		this.attached = true;
	}

	public EntityModel(final Class<T> type) {
		this(type, (T) null);
	}

	protected T load() {
		if (this.id == null) {
			return null;
		}
		return this.resolver().fetch(this.type, this.id);
	}

	public T getObject() {
		if (!this.attached) {
			this.value = this.load();
			this.attached = true;
		}
		return this.value;
	}

	public void setObject(final T object) {
		this.id = (object == null) ? null : this.resolver().getId(object);
		this.value = object;
		this.attached = true;
	}

	private EntityResolver resolver() {
		if (this.resolver == null) {
			Injector.get().inject(this);
			Assert.state(this.resolver != null, "Can't inject entity resolver!");
		}
		return this.resolver;
	}

	public void detach() {
		if (this.attached) {
			this.value = null;
			this.attached = false;
		}
	}
}
