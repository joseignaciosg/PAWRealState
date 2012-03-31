package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Los usuarios deben poder contactar al dueño de una propiedad para hacerle consultas.
 * Para esto deben disponer de una página en donde puedan ingresar su usuario, mail, 
 * telefono y descripcion opcional y le devuelva la información del usuario una vez 
 * realizada la consulta
 * 
 *  @author Nico
 */

@SuppressWarnings("serial")
public class ContactRequestPage extends HttpServlet {

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
