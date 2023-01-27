package com.di.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.di.entities.Emp;
import com.di.services.EmpDBHandler;

@WebServlet("/updateservlet2") 
public class UpdateServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");
		Emp e = new Emp();
		e.setId(id);
		e.setName(name);
		e.setDept(dept);

		int status = EmpDBHandler.update(e);
		if (status > 0) {
			out.println("Record updated succesfully...");
			response.sendRedirect("viewservlet");
		} else {
			out.println("Sorry! unable to update record");
		}
		out.close();
	}
}