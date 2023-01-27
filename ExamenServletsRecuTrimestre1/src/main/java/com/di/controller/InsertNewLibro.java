package com.di.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.di.entities.Libro;
import com.di.service.Libro_service;

@WebServlet ("/InsertNewLibro")
public class InsertNewLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String isbn = request.getParameter("isbn");
		String fechaEdit = request.getParameter("fechaEdicion");
//		int editYear = Integer.parseInt(fechaEdit.split("-")[0]);
		Date fechaEdicion = new Date();
		try {
			fechaEdicion = new SimpleDateFormat("yyyy-MM-dd").parse(fechaEdit);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fechaEdicion);
		System.out.println(fechaEdicion.getYear());
		System.out.println(fechaEdicion.getYear() + 1900);
		Libro libro = new Libro();
		libro.setIsbn(isbn);
		libro.setFechaEdicion(fechaEdicion);
		/*
		 * NOTA!: Para dejar esto bien hecho, hay que averiguar la forma 
		 * de tener un desplegable condicionado en el formulario del libro
		 * ese desplegable debe mostrar los nombres de los almacenes nuevos 
		 * o de los viejos según corresponda, es decir que habría que hacer una 
		 * consulta a la base de datos, bien a la tabla de almacenes nuevos,
		 * o a la de viejos, en función de la fecha de edición del libro.
		 */
		//Se pone 110 porque Date cuenta los años desde 1900
		if(fechaEdicion.getYear()  < 110) libro.setAlmacenOld(1);
		else libro.setAlmacenNew(1);

		int status = Libro_service.save(libro);
		if (status > 0) {
			out.print("<p>Almacen guardado con exito!</p>");
			request.getRequestDispatcher("index.html").include(request, response);
		} else {
			out.println("No se pudo guardar el almacen!");
		}
		out.close();
	}
}