package com.carpoolsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
     * Handles GET requests
     */
    @Override
    protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");

        // Read HTML file
        StringBuilder responseHtml = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(getServletContext().getRealPath("/carpoolBookingSystem.html")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                responseHtml.append(line).append("\n");
            }
        }

        // Insert available rides into HTML
        String updatedHtml = responseHtml.toString().replace("<!-- Body to add rides -->", getRidesHtml());

        // HTML response
        PrintWriter writer = servletResponse.getWriter();
        writer.println(updatedHtml);
    }

    /**
     * Display Rides By Generating New HTML
     */
    private String getRidesHtml() {
        StringBuilder ridesHtml = new StringBuilder();
        if (listOfAvailableRides.isEmpty()) {
            ridesHtml.append("<tr><td colspan='3'>Ohh No... No Rides Available at this time.</td></tr>");
        } else {
            for (String ride : listOfAvailableRides) {
                ridesHtml.append("<tr>");
                String[] rideDetails = ride.split(",");
                for (String detail : rideDetails) {
                    ridesHtml.append("<td>").append(detail).append("</td>");
                }
                ridesHtml.append("</tr>");
            }
        }
        return ridesHtml.toString();
    }

    /**
     * Handles POST requests
     */
    @Override
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        // Retrieve form parameters
        String startingLocation = servletRequest.getParameter("startingLocation");
        String destination = servletRequest.getParameter("destination");
        String seatsAvailable = servletRequest.getParameter("seatsAvailable");

        // New Ride Details Format
        String newRide = String.format("%s,%s,%s", startingLocation, destination, seatsAvailable);

        // Appending new ride details to existing list
        listOfAvailableRides.add(newRide);

        // Log the addition of a new ride
        logging.info("New ride added: " + newRide);

        // Display updated list by sending back response to get method
        servletResponse.sendRedirect(servletRequest.getContextPath() + "/carpool");
    }

    /**
     * Log message indicating servlet termination.
     */
    @Override
    public void destroy() {
        // Logging a message indicating that the servlet is being terminated
        logging.info("Carpool Servlet is being terminated.");
    }
}
