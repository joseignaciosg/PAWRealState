package ar.edu.itba.it.paw.web.servlets;

import javax.servlet.http.HttpServlet;

/**
 * Los usuarios registrados deben poder publicar avisos de venta o alquiler de
 * propiedades. Para realizar la publicación se debe indicar la información
 * básica del aviso: el tipo de inmueble (departamento o casa), el tipo de
 * operación (venta o alquiler), la dirección, el barrio, el precio, la cantidad
 * de ambientes, la superficie cubierta, la superficie descubierta, la
 * antigüedad, los servicios que dispone (cable, teléfono, pileta, salón, cancha
 * de paddle, quincho) y una descripción (único campo optativo). Los servicios
 * que tiene una propiedad no se deben registrar como un texto libre sino que se
 * debe poder indicar si dispone o no de cada uno de ellos.
 * 
 * @author cris
 * 
 */
public class PublishPage extends HttpServlet {

}
