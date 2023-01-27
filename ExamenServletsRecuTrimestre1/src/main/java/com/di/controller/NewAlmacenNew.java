package com.di.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.di.entities.AlmacenNew;
import com.di.service.Almacen_new_service;

@WebServlet ("/InsertNewAlmacenNew")
public class NewAlmacenNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String nombre = request.getParameter("nombreAlmacen");
		AlmacenNew almacenNew = new AlmacenNew();
		almacenNew.setNombre(nombre);

		int status = Almacen_new_service.save(almacenNew);
		if (status > 0) {
			out.print("<p>Almacen guardado con exito!</p>");
			request.getRequestDispatcher("index.html").include(request, response);
		} else {
			out.println("No se pudo guardar el almacen!");
		}
		out.close();
	}
}