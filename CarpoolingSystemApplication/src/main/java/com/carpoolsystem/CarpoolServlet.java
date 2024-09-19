package com.carpoolsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementation of Servlet class CarpoolServlet
 * Handles Carpool bookings done using form
 */
public class CarpoolServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    // Declaration of List variable
    private List<String> listOfAvailableRides;
    private static final Logger logging = Logger.getLogger(CarpoolServlet.class.getName());

    /**
     * Default constructor
     */
    public CarpoolServlet() {
        super();
    }

    /**
     * Initialize available carpool rides
     */
    @Override
    public void init() throws ServletException {
        // Initialize a list of available carpool rides
    	listOfAvailableRides = new ArrayList<>();
        logging.info("CarpoolServlet initialized. Available rides: " + listOfAvailableRides);
    }

    /**
     * Log the requests done by the user
     */
    @Override
    protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        // Logging incoming requests, including the request type and URL
        String reqType = servletRequest.getMethod();
        String reqURL = servletRequest.getRequestURL().toString();
        logging.info("Received Requests. Type: " + reqType + ", URL: " + reqURL);
        super.service(servletRequest, servletResponse);
    }

    /**
     * Handles GET requests
     */
    @Override
    protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        // Display Welcome message and list of rides available
        servletResponse.setContentType("text/html");
        servletResponse.getWriter().println("Welcome to Carpool Booking System <br>");
        servletResponse.getWriter().println("List of Ride Available Now : <br>");
        for (String ridesList : listOfAvailableRides) {
            servletResponse.getWriter().println(ridesList + "<br>");
        }
    }

    /**
     * Handles POST requests
     */
    @Override
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        // doGet method call to display content of POST requests
        doGet(servletRequest, servletResponse);
    }

    /**
     * Log message indication servlet termination.
     */
    @Override
    public void destroy() {
        // Logging a message indicating that the servlet is being terminated
    	logging.info("Carpool Servlet is being terminated.");
    }
}
