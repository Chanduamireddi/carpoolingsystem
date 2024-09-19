package com.carpoolsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Implementation of Servlet class CarpoolServlet
 * Handles Carpool bookings submitted through HTML form
 */

public class CarpoolServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarpoolServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
	 */
	protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		// TODO Auto-generated method stub
		servletResponse.setContentType("text/html");
		servletResponse.getWriter().println("Welcome To Carpool Booking System");
	}

	/**
	 * @see HttpServlet#doPost((HttpServletRequest servletRequest, HttpServletResponse servletResponse)
	 */
	protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(servletRequest, servletResponse);
	}

}
