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

@WebServlet("/bookingController")
public class BookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        BookingService bookingService = BookingService.getBookingServiceInstance();

        if (type != null && type.equals("specific")) {
            getSpecificBooking(request, response, bookingService);
        } else {
            getAllBookings(request, response, bookingService);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        BookingService bookingService = BookingService.getBookingServiceInstance();

        if (type != null && type.equals("update")) {
            updateBooking(request, response, bookingService);
        } else if (type != null && type.equals("add")) {
            addBooking(request, response, bookingService);
        } else if (type != null && type.equals("delete")) {
            deleteBooking(request, response, bookingService);
        }
    }

    private void getAllBookings(HttpServletRequest request, HttpServletResponse response,
            BookingService service) throws ServletException, IOException {

        String message = "";
        List<Booking> bookingList;

        try {
            bookingList = service.getAllBookings();
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            bookingList = new ArrayList<Booking>();
        }

        request.setAttribute("message", message);
        request.setAttribute("bookingList", bookingList);

        RequestDispatcher rd = request.getRequestDispatcher("BookingDashboard.jsp");
        rd.forward(request, response);
    }

    private void getSpecificBooking(HttpServletRequest request, HttpServletResponse response,
            BookingService service) throws ServletException, IOException {
        String bookingIdStr = request.getParameter("bookingID");
        int bookingID;
        Booking booking;
        String message = "";

        if (bookingIdStr != null && !bookingIdStr.isEmpty()) {
            try {
                bookingID = Integer.parseInt(bookingIdStr);
                booking = service.getSpecificBooking(bookingID);
            } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
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

    private void updateBooking(HttpServletRequest request, HttpServletResponse response,
            BookingService service) throws ServletException, IOException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        String bookingDate = request.getParameter("bookingDate");
        String bookingTime = request.getParameter("bookingTime");
        String driverName = request.getParameter("driverName");
        String address = request.getParameter("address");
        String destination = request.getParameter("destination");

        Booking booking = new Booking(bookingID, customerID, bookingDate, bookingTime, driverName, address, destination);

        boolean result;
        String message = "";
        try {
            result = service.editTheBooking(booking);
            if (result) {
                message = "Booking " + bookingID + " has been successfully updated!";
            } else {
                message = "Failed to update the booking! Booking ID: " + bookingID;
            }
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("Search-Booking.jsp");
        rd.forward(request, response);
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response,
            BookingService service) throws ServletException, IOException {

        int customerID = Integer.parseInt(request.getParameter("customerID"));
        String bookingDate = request.getParameter("bookingDate");
        String bookingTime = request.getParameter("bookingTime");
        String driverName = request.getParameter("driverName");
        String address = request.getParameter("address");
        String destination = request.getParameter("destination");

        Booking booking = new Booking(customerID, bookingDate, bookingTime, driverName, address, destination);

        boolean result;
        String message = "";
        try {
            result = service.registerBooking(booking);
            if (result) {
                message = "Booking for Customer ID " + customerID + " has been successfully added!";
            } else {
                message = "Failed to add the booking for Customer ID " + customerID;
            }
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("Add-Booking.jsp");
        rd.forward(request, response);
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response,
            BookingService service) throws ServletException, IOException {

        int bookingID = Integer.parseInt(request.getParameter("bookingID"));

        boolean result;
        String message = "";
        try {
            result = service.deleteTheBooking(bookingID);
            if (result) {
                message = "Booking with ID " + bookingID + " has been successfully deleted!";
            } else {
                message = "Failed to delete the booking with ID " + bookingID;
            }
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        HttpSession session = request.getSession();

        try {
            List<Booking> bookingList = service.getAllBookings();
            session.setAttribute("bookingList", bookingList);
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        session.setAttribute("message", message);
        response.sendRedirect("BookingDashboard.jsp");
    }
}
