package ar.edu.itba.it.paw.web;

import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernatePropertyRepository;
import ar.edu.itba.it.paw.web.common.HibernateRequestCycleListener;
import ar.edu.itba.it.paw.web.converters.PropertyConverter;
import ar.edu.itba.it.paw.web.photos.ImageResourceReference;

import com.google.code.jqwicket.JQComponentOnBeforeRenderListener;
import com.google.code.jqwicket.JQContributionConfig;

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

		// final JQContributionConfig config = new JQContributionConfig(
		// new JavaScriptResourceReference(this.getClass(), "jquery.js"))
		// .withJQueryUiJs(
		// new JavaScriptResourceReference(this.getClass(),
		// "jquery-ui.js")).withJQueryUiCss(
		// new CssResourceReference(this.getClass(),
		// "jquery-ui.css"));

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
