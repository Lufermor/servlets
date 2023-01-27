package com.di.controller;

/**
 * 
 */

/**
 * @author Luis Fernando Moreno
 *
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ToHomePage")
public class ToHomePage extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToHomePage() {
        super();
    }
  
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/index.html").forward(request, response);
//    	response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("<h1>Mensaje desde método doGet desde DemoServlet</h1>");
//        doPost(request, response);
    }
  
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Test.jsp").forward(request, response);
    }
}
