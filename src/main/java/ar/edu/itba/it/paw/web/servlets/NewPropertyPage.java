package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.web.utils.HTMLUtils;

@SuppressWarnings("serial")
public class NewPropertyPage extends HttpServlet {
	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		HTMLUtils.render("myproperties/newproperty.jsp", req, resp);
	}

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		this.doGet(req, resp);
	}
}
