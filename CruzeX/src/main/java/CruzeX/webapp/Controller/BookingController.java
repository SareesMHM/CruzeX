package CruzeX.webapp.Controller;

import CruzeX.webapp.Model.Booking;
import CruzeX.webapp.Service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@WebServlet("/BookingController")
public class BookingController extends HttpServlet {

    private final BookingService bookingService = BookingService.getBookingServiceInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if ("add".equals(type)) {
            addBooking(request, response);
        } else if ("update".equals(type)) {
            updateBooking(request, response);
        } else if ("delete".equals(type)) {
            deleteBooking(request, response);
        } else {
            response.sendRedirect("error.jsp?message=Invalid Request Type");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if ("specific".equals(type)) {
            getSpecificBooking(request, response);
        } else {
            getAllBookings(request, response);
        }
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int customerID = Integer.parseInt(request.getParameter("customerID"));
            String vehicleID = request.getParameter("vehicleID");
            int driverID = Integer.parseInt(request.getParameter("driverID"));
            String bookingDate = request.getParameter("bookingDate");
            String bookingTime = request.getParameter("bookingTime");
            String pickupLocation = request.getParameter("pickupLocation");
            String dropLocation = request.getParameter("dropLocation");
            double distance = Double.parseDouble(request.getParameter("distance"));
            double baseFare = Double.parseDouble(request.getParameter("fare"));
            double tax = Double.parseDouble(request.getParameter("tax"));
            double discount = Double.parseDouble(request.getParameter("discount"));
            double totalFare = Double.parseDouble(request.getParameter("totalFare"));
            

            // Apply 7% tax and 7% discount
//            double tax = baseFare * 0.07;
//            double discount = baseFare * 0.07;
//            double totalFare = baseFare + tax - discount;

            // Create Booking object
            Booking booking = new Booking(customerID, vehicleID, driverID, bookingDate, bookingTime, pickupLocation, dropLocation, distance, baseFare, tax, discount, totalFare);

            // Save to database
            int bookingID = bookingService.registerBooking(booking);

            if (bookingID > 0) {
//                response.sendRedirect("BookingDashboard.jsp?message=Booking Added Successfully&bookingID=" + bookingID);
                   response.sendRedirect("Add-Payment.jsp?bookingID=" + bookingID + "&customerID=" + customerID + "&amount=" + totalFare);
            } else {
                response.sendRedirect("error.jsp?message=Booking Failed");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?message=Invalid Number Format");
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=An Unexpected Error Occurred");
        }
    }

    private void updateBooking(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            double baseFare = Double.parseDouble(request.getParameter("fare"));

            // Apply 7% tax and 7% discount again
            double tax = baseFare * 0.07;
            double discount = baseFare * 0.07;
            double totalFare = baseFare + tax - discount;

            // Create Booking object
            Booking booking = new Booking(bookingID, customerID, vehicleID, driverID, bookingDate, bookingTime, pickupLocation, dropLocation, distance, baseFare, tax, discount, totalFare);

            // Update booking in the database
            boolean success = bookingService.editTheBooking(booking);

            if (success) {
                response.sendRedirect("BookingDashboard.jsp?message=Booking Updated Successfully");
            } else {
                response.sendRedirect("error.jsp?message=Booking Update Failed");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?message=Invalid Number Format");
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=An Unexpected Error Occurred");
        }
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int bookingID = Integer.parseInt(request.getParameter("bookingID"));

            boolean success = bookingService.deleteTheBooking(bookingID);

            if (success) {
                response.sendRedirect("BookingDashboard.jsp?message=Booking Deleted Successfully");
            } else {
                response.sendRedirect("error.jsp?message=Booking Deletion Failed");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?message=Invalid Booking ID");
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=An Unexpected Error Occurred");
        }
    }

//    private void getSpecificBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            int bookingID = Integer.parseInt(request.getParameter("bookingID"));
//
//            Booking booking = bookingService.getSpecificBooking(bookingID);
//
//            if (booking != null && booking.getBookingID() > 0) {
//                request.setAttribute("booking", booking);
//                request.getRequestDispatcher("Search-Booking.jsp").forward(request, response);
//            } else {
//                response.sendRedirect("error.jsp?message=Booking Not Found");
//            }
//        } catch (NumberFormatException e) {
//            response.sendRedirect("error.jsp?message=Invalid Booking ID");
//        } catch (Exception e) {
//            response.sendRedirect("error.jsp?message=An Unexpected Error Occurred");
//        }
//    }
   private void getSpecificBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String bookingIdParam = request.getParameter("bookingID");
    String message = "";
    Booking booking = null;

    if (bookingIdParam != null && !bookingIdParam.trim().isEmpty()) {
        try {
            int bookingID = Integer.parseInt(bookingIdParam);
            booking = bookingService.getSpecificBooking(bookingID);
        } catch (NumberFormatException e) {
            message = "Invalid booking ID format." + e.getMessage();
        }
    } else {
        message = "Booking ID is missing or invalid.";
    }

    request.setAttribute("booking", booking);
    request.setAttribute("message", message);
    RequestDispatcher rd = request.getRequestDispatcher("Search-Booking.jsp");
    rd.forward(request, response);
}

    private void getAllBookings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession();
       List<Booking> bookingList;
       String message ="";
        try {
             bookingList = bookingService.getAllBookings();
            session.setAttribute("bookingList", bookingList);
            
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=An Unexpected Error Occurred");
            message = "Error fetching booking:"+e.getMessage();
            session.setAttribute("message", e);
        }
        response.sendRedirect("BookingDeshboard.jsp");
    }
}