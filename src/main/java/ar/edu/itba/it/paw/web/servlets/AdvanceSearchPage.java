package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * El usuario debe disponer de busquedas avanzadas para poder 
 * realizar búsquedas con varios tipos de parametros. Para esto
 * necesita una página en donde puede ingresar todos los datos y 
 * recibir una respuesta.
 * 
 * @author Nico
 * 
 */

@SuppressWarnings("serial")
public class AdvanceSearchPage extends HttpServlet {

	@Override
	protected void doGet(final javax.servlet.http.HttpServletRequest req,
			final javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
	};

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
