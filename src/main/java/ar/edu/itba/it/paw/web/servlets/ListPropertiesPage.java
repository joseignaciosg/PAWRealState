package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Todos los usuarios, incluyendo los anónimos, deben poder acceder a una página 
 * que muestre todos los datos de un aviso, incluyendo las fotos y el 
 * formulario de contacto (que se describe a continuación).
 * 
 * @author Nico
 * 
 */

@SuppressWarnings("serial")
public class ListPropertiesPage extends HttpServlet {

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
}
