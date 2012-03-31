package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Los usuarios registrados deben poder cargar y eliminar fotos asociadas a una
 * publicación. En la página de detalle de una publicación se deben poder ver
 * las fotos relacionadas a la misma
 * 
 * @author cris
 * 
 */

@SuppressWarnings("serial")
public class PhotoAddPage extends HttpServlet {

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
