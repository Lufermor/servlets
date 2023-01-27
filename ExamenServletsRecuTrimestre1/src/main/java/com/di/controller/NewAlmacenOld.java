package com.di.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.di.entities.AlmacenOld;
import com.di.service.Almacen_old_service;

@WebServlet ("/InsertNewAlmacenOld")
public class NewAlmacenOld extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String nombre = request.getParameter("nombreAlmacen");
		System.out.println(nombre);
		AlmacenOld almacenOld = new AlmacenOld();
		almacenOld.setNombre(nombre);
		System.out.println(almacenOld.getNombre());

		int status = Almacen_old_service.save(almacenOld);
		if (status > 0) {
			out.print("<p>Almacen guardado con exito!</p>");
			request.getRequestDispatcher("index.html").include(request, response);
		} else {
			out.println("No se pudo guardar el almacen!");
		}
		out.close();
	}
}