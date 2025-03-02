package CruzeX.webapp.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CruzeX.webapp.Model.Booking;
import CruzeX.webapp.Service.BookingService;

@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingService bookingService = BookingService.getBookingServiceInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");

        if ("specific".equals(type)) {
            getSpecificBooking(request, response);
        } else {
            getAllBookings(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");

        if (type != null) {
            switch (type) {
                case "update":
                    updateBooking(request, response);
                    break;
                case "add":
                    addBooking(request, response);
                    break;
                case "delete":
                    deleteBooking(request, response);
                    break;
                default:
                    response.sendRedirect("BookingDashboard.jsp");
                    break;
            }
        }
    }

    private void getAllBookings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Booking> bookingList;
        String message = "";

        try {
            bookingList = bookingService.getAllBookings();
        } catch (Exception e) {
            message = e.getMessage();
            bookingList = new ArrayList<>();
        }

        request.setAttribute("message", message);
        request.setAttribute("bookingList", bookingList);

        RequestDispatcher rd = request.getRequestDispatcher("BookingDashboard.jsp");
        rd.forward(request, response);
    }

    private void getSpecificBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingIdStr = request.getParameter("bookingID");
        int bookingID;
        Booking booking;
        String message = "";

        if (bookingIdStr != null && !bookingIdStr.isEmpty()) {
            try {
                bookingID = Integer.parseInt(bookingIdStr);
                booking = bookingService.getSpecificBooking(bookingID);
            } catch (NumberFormatException e) {
                message = e.getMessage();
                booking = new Booking();
            }
        } else {
            message = "Booking ID is missing or invalid.";
            booking = new Booking();
        }

        request.setAttribute("booking", booking);
        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("Search-Booking.jsp");
        rd.forward(request, response);
    }

    private void updateBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int bookingID = Integer.parseInt(request.getParameter("bookingID"));
            int customerID = Integer.parseInt(request.getParameter("customerID"));
            String vehicleID = request.getParameter("vehicleID");
            int driverID = Integer.parseInt(request.getParameter("driverID"));
            String bookingDate = request.getParameter("bookingDate");
            String bookingTime = request.getParameter("bookingTime");
            String pickupLocation = request.getParameter("pickupLocation");
            String dropLocation = request.getParameter("dropLocation");

            double distance = Double.parseDouble(request.getParameter("distance"));
            double monthFee = Double.parseDouble(request.getParameter("monthFee"));
            double fare = distance * monthFee;  // Total cost calculation

            Booking booking = new Booking(bookingID, customerID, vehicleID, driverID, bookingDate, bookingTime, pickupLocation, dropLocation, distance, fare);

            boolean result = bookingService.editTheBooking(booking);
            String message = result ? "Booking " + bookingID + " successfully updated!" : "Failed to update the booking!";

            request.setAttribute("message", message);
            response.sendRedirect("Search-Booking.jsp");

        } catch (Exception e) {
            request.setAttribute("message", "Error updating booking: " + e.getMessage());
            response.sendRedirect("Search-Booking.jsp");
        }
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int customerID = Integer.parseInt(request.getParameter("customerID"));
            String vehicleID = request.getParameter("vehicleID");
            int driverID = Integer.parseInt(request.getParameter("driverID"));
            String bookingDate = request.getParameter("bookingDate");
            String bookingTime = request.getParameter("bookingTime");
            String pickupLocation = request.getParameter("pickupLocation");
            String dropLocation = request.getParameter("dropLocation");

            double distance = Double.parseDouble(request.getParameter("distance"));
            double monthFee = Double.parseDouble(request.getParameter("monthFee"));
            double fare = distance * monthFee;  // Total cost calculation

            Booking booking = new Booking(customerID, vehicleID, driverID, bookingDate, bookingTime, pickupLocation, dropLocation, distance, fare);
            int bookingID = bookingService.registerBooking(booking);

            String message = (bookingID > 0) ? "Booking successfully created!" : "Failed to add booking!";
            request.setAttribute("message", message);

            // Redirect to payment page with booking details
            response.sendRedirect("Add-Payment.jsp?bookingID=" + bookingID + "&customerID=" + customerID + "&amount=" + fare);

        } catch (Exception e) {
            request.setAttribute("message", "Error adding booking: " + e.getMessage());
            response.sendRedirect("Add-Booking.jsp");
        }
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int bookingID = Integer.parseInt(request.getParameter("bookingID"));
            boolean result = bookingService.deleteTheBooking(bookingID);

            String message = result ? "Booking with ID " + bookingID + " successfully deleted!" : "Failed to delete the booking!";
            HttpSession session = request.getSession();
            session.setAttribute("message", message);

            List<Booking> bookingList = bookingService.getAllBookings();
            session.setAttribute("bookingList", bookingList);

            response.sendRedirect("BookingDashboard.jsp");

        } catch (Exception e) {
            request.setAttribute("message", "Error deleting booking: " + e.getMessage());
            response.sendRedirect("BookingDashboard.jsp");
        }
    }
}