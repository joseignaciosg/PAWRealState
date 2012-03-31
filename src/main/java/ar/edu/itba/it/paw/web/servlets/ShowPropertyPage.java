package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Todos los usuarios, incluyendo los anónimos, deben poder 
 * desde el detalle de un aviso consultar los datos de contacto 
 * del publicador. Para ello, deben indicar su nombre, email, 
 * teléfono y un comentario opcional. Al completar estos datos 
 * se le debe mostrar al usuario el teléfono y el email del 
 * publicador.
 * 
 * @author Nico
 * 
 */

@SuppressWarnings("serial")
public class ShowPropertyPage extends HttpServlet {

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
}
