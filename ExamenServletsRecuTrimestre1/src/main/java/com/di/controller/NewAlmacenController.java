package com.di.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.di.entities.AlmacenNew;
import com.di.entities.AlmacenOld;
import com.di.service.Almacen_new_service;
import com.di.service.Almacen_old_service;

@WebServlet ("/FormNewAlmacen")
public class NewAlmacenController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.getRequestDispatcher("FormularioNuevoAlmacen.html").include(request, response);
//		PrintWriter out = response.getWriter();
//		out.println("<a href='FormNewAlmacenOld.html'>Insertar almacen old</a>");
//		out.println("<a href='FormNewAlmacenNew.html'>Insertar almacen new</a>");
//		out.println("<a href='FormularioNuevoAlmacen.html'>Insertar nuevo almacen con metodo 3</a>");
//		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//Se obtienen los datos del formulario:
		String nombre = request.getParameter("nombreAlmacen");
		int fechaCreac = Integer.parseInt(request.getParameter("fechaCreacion"));
		int status = 0;
		if(fechaCreac>= 2010) {
			AlmacenNew almacenNew = new AlmacenNew();
			almacenNew.setNombre(nombre);
			status = Almacen_new_service.save(almacenNew);
		}
		else if(fechaCreac< 2010) {
			AlmacenOld almacenOld = new AlmacenOld();
			almacenOld.setNombre(nombre);
			status = Almacen_old_service.save(almacenOld);
		}

		//Se notifica al usuario del resultado de la operación.
		if (status > 0) {
			out.print("<p>Almacen guardado con exito!</p>");
			request.getRequestDispatcher("index.html").include(request, response);
		} else {
			out.println("No se pudo guardar el almacen!");
		}
		
		out.close();
	}

}
