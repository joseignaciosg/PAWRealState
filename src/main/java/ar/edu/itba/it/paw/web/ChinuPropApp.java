package ar.edu.itba.it.paw.web;

import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.domain.entities.Room.RoomType;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch.Order;
import ar.edu.itba.it.paw.web.common.HibernateRequestCycleListener;
import ar.edu.itba.it.paw.web.converter.OperationConverter;
import ar.edu.itba.it.paw.web.converter.OrderConverter;
import ar.edu.itba.it.paw.web.converter.PhotoConverter;
import ar.edu.itba.it.paw.web.converter.PropertyConverter;
import ar.edu.itba.it.paw.web.converter.RoomTypeConverter;
import ar.edu.itba.it.paw.web.converter.TypeConverter;
import ar.edu.itba.it.paw.web.converter.UserConverter;

@Component
public class ChinuPropApp extends WebApplication {

	private final SessionFactory sessionFactory;

	// private final SubjectRepo subjects;

	@Autowired
	public ChinuPropApp(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		// this.subjects = subjects;
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected void init() {
		super.init();
		this.getComponentInstantiationListeners().add(
				new SpringComponentInjector(this));
		this.getRequestCycleListeners().add(
				new HibernateRequestCycleListener(this.sessionFactory));
	}

	@Override
	public Session newSession(final Request request, final Response response) {
		return new ChinuPropWicketSession(request);
	}

	@Override
	protected IConverterLocator newConverterLocator() {
		final ConverterLocator converterLocator = new ConverterLocator();
		converterLocator.set(Property.class, new PropertyConverter());
		converterLocator.set(User.class, new UserConverter());
		converterLocator.set(Photo.class, new PhotoConverter());
		converterLocator.set(Operation.class, new OperationConverter());
		converterLocator.set(Order.class, new OrderConverter());
		converterLocator.set(Type.class, new TypeConverter());
		converterLocator.set(RoomType.class, new RoomTypeConverter());
		return converterLocator;
	}
}
