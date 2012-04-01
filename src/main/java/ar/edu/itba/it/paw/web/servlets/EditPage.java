package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Los usuarios registrados deben poder editar los avisos publicados, pudiendo
 * modificar toda la inforrmación básica del punto anterior. Para esto deben
 * disponer de una página en donde se listen sus propiedades publicadas.
 * 
 * @author cris
 * 
 */

@SuppressWarnings("serial")
public class EditPage extends HttpServlet {

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
