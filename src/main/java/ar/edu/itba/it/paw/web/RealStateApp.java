package ar.edu.itba.it.paw.web;

import org.apache.wicket.*;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.*;
import org.apache.wicket.request.*;
import org.apache.wicket.request.resource.*;
import org.apache.wicket.settings.*;
import org.apache.wicket.spring.injection.annot.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.impl.*;
import ar.edu.itba.it.paw.web.common.*;
import ar.edu.itba.it.paw.web.converters.*;
import ar.edu.itba.it.paw.web.photos.*;

import com.google.code.jqwicket.*;

@Component
public class RealStateApp extends WebApplication {

	public static final PackageResourceReference NO_PROP_PICTURE = new PackageResourceReference(
			RealStateApp.class, "realstate-no-picture.jpg");

	public static final ImageResourceReference imageReference = new ImageResourceReference();

	private SessionFactory sessionFactory;
	private HibernatePropertyRepository repo;

	@Autowired
	public RealStateApp(final SessionFactory sessionFactory,
			final HibernatePropertyRepository repo) {
		this.sessionFactory = sessionFactory;
		this.repo = repo;
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	public Session newSession(final Request request, final Response response) {
		return new RealStateSession(request, response);
	}

	@Override
	protected void init() {
		super.init();

		this.getComponentInstantiationListeners().add(
				new SpringComponentInjector(this));
		this.getRequestCycleListeners().add(
				new HibernateRequestCycleListener(this.sessionFactory));
		this.getMarkupSettings().setStripWicketTags(true);

		this.getDebugSettings().setAjaxDebugModeEnabled(false);

		final JQContributionConfig config = new JQContributionConfig()
				.withDefaultJQueryUi();
		this.getComponentPreOnBeforeRenderListeners().add(
				new JQComponentOnBeforeRenderListener(config));

		this.getApplicationSettings().setPageExpiredErrorPage(ExpiryPage.class);
		this.getApplicationSettings().setInternalErrorPage(ErrorPage.class);

		this.getExceptionSettings().setUnexpectedExceptionDisplay(
				IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);

		this.getComponentPreOnBeforeRenderListeners().add(
				new JQComponentOnBeforeRenderListener(config));

	}

	@Override
	protected IConverterLocator newConverterLocator() {
		final ConverterLocator converterLocator = new ConverterLocator();
		converterLocator.set(Property.class, new PropertyConverter(this.repo));
		return converterLocator;
	}
}
