package ar.edu.itba.it.paw.web.common;

import org.apache.wicket.request.*;
import org.apache.wicket.request.cycle.*;
import org.hibernate.*;
import org.hibernate.classic.Session;
import org.hibernate.context.*;
import org.springframework.util.*;

public class HibernateRequestCycleListener extends AbstractRequestCycleListener {

	private final SessionFactory sessionFactory;
	private boolean error;

	public HibernateRequestCycleListener(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void onBeginRequest(final RequestCycle cycle) {
		Assert.state(!ManagedSessionContext.hasBind(this.sessionFactory),
				"Session already bound to this thread");
		final Session session = this.sessionFactory.openSession();
		ManagedSessionContext.bind(session);
		session.beginTransaction();
	}

	@Override
	public void onEndRequest(final RequestCycle cycle) {
		if (!this.error) {
			this.commit();
		} else {
			this.rollback();
			this.error = false;
		}
	}

	@Override
	public IRequestHandler onException(final RequestCycle cycle,
			final Exception ex) {
		this.rollback();
		this.error = true;
		return null;
	}

	private void commit() {
		final Session session = this.sessionFactory.getCurrentSession();
		Assert.state(session.isOpen(), "Can't commit a closed session!");
		try {
			final Transaction tx = session.getTransaction();
			if (tx.isActive()) {
				session.flush();
				tx.commit();
				System.out.println("Commit!");
			}
		} finally {
			this.close(session);
		}
	}

	private void rollback() {
		final Session session = this.sessionFactory.getCurrentSession();
		Assert.state(session.isOpen(), "Can't rollback a closed session!");
		try {
			final Transaction tx = session.getTransaction();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			this.close(session);
		}
	}

	private void close(final Session session) {
		ManagedSessionContext.unbind(this.sessionFactory);
		session.close();
	}
}
