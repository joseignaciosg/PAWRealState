package ar.edu.itba.it.paw.web.base;

import ar.edu.itba.it.paw.web.*;
import ar.edu.itba.it.paw.web.registration.*;

@SuppressWarnings("serial")
public abstract class SecuredPage extends BasePage {
	public SecuredPage() {
		final RealStateSession session = (RealStateSession) this.getSession();
		if (!session.isSignedIn()) {
			this.redirectToInterceptPage(new RegistrationPage());
		}
	}
}
