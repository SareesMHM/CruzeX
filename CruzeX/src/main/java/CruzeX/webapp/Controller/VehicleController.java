package CruzeX.webapp.Controller;

import CruzeX.webapp.Model.Vehicle;
import CruzeX.webapp.Service.VehicleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/VehicleController")
public class VehicleController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VehicleService vehicleService = VehicleService.getVehicleServiceInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if ("specific".equals(type)) {
            getSpecificVehicle(request, response);
        } else {
            getAllVehicles(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if (null != type) switch (type) {
            case "update":
                updateVehicle(request, response);
                break;
            case "add":
                addVehicle(request, response);
                break;
            case "delete":
                deleteVehicle(request, response);
                break;
            default:
                break;
        }
    }

   
//    private void getAllVehicles(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        HttpSession session = request.getSession();
//        List<Vehicle> vehicleList;
//        String message = "";
//
//        try {
//            vehicleList = vehicleService.getAllVehicles();
//            session.setAttribute("vehicleList", vehicleList);
//        } catch (ClassNotFoundException | SQLException e) {
//            message = "Error fetching vehicles: " + e.getMessage();
//            session.setAttribute("message", message);
//        }
//        session.setAttribute("vehiclesLoaded", true);
//
//        //response.sendRedirect("VehicleDashboard.jsp"); // Redirect instead of forwarding
//        response.sendRedirect("VehicleDashboard.jsp");
//
//    }
    
    private void getAllVehicles(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession();
    List<Vehicle> vehicleList;
    String message = "";

    try {
        vehicleList = vehicleService.getAllVehicles();
        session.setAttribute("vehicleList", vehicleList);
    } catch (ClassNotFoundException | SQLException e) {
        message = "Error fetching vehicles: " + e.getMessage();
        session.setAttribute("message", message);
    }
    response.sendRedirect("VehicleDashboard.jsp");
}

    private void getSpecificVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleId = request.getParameter("vehicleId");
        String message = "";
        Vehicle vehicle = null;

        if (vehicleId != null && !vehicleId.trim().isEmpty()) {
            try {
                vehicle = vehicleService.getSpecificVehicle(vehicleId);
            } catch (ClassNotFoundException | SQLException e) {
                message = "Error fetching vehicle: " + e.getMessage();
            }
        } else {
            message = "Vehicle ID is missing or invalid.";
        }

        request.setAttribute("vehicle", vehicle);
        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("Search-Vehicle-Details.jsp");
        rd.forward(request, response);
    }

    private void updateVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleId = request.getParameter("vehicleId");
        String vehicleName = request.getParameter("vehicleName");
        String image = request.getParameter("image");
        String category = request.getParameter("category");
        int monthFee = 0;
        String message = "";

        try {
            monthFee = Integer.parseInt(request.getParameter("monthFee"));
        } catch (NumberFormatException e) {
            message = "Invalid month fee. Please enter a valid number.";
        }

        if (vehicleId == null || vehicleId.trim().isEmpty() || vehicleName == null || category == null) {
            message = "All fields are required.";
        } else {
            try {
                Vehicle vehicle = new Vehicle(vehicleId, vehicleName, image, category, monthFee);
                boolean result = vehicleService.editVehicle(vehicle);
                message = result ? "Vehicle " + vehicleId + " updated successfully!" : "Failed to update vehicle.";
            } catch (ClassNotFoundException | SQLException e) {
                message = "Error updating vehicle: " + e.getMessage();
            }
        }

        request.setAttribute("message", message);
        response.sendRedirect("Search-Vehicle-Details.jsp");
    }

    private void addVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleID = request.getParameter("vehicleID");
        String vehicleName = request.getParameter("vehicleName");
        String image = request.getParameter("image");
        String category = request.getParameter("category");
        int monthFee = 0;
        String message = "";

        try {
            monthFee = Integer.parseInt(request.getParameter("monthFee"));
        } catch (NumberFormatException e) {
            message = "Invalid month fee. Please enter a valid number.";
        }

        if (vehicleID == null || vehicleID.trim().isEmpty() || vehicleName == null || category == null) {
            message = "All fields are required.";
        } else {
            try {
                Vehicle vehicle = new Vehicle(vehicleID, vehicleName, image, category, monthFee);
                boolean result = vehicleService.registerVehicle(vehicle);
                message = result ? "Vehicle " + vehicleName + " added successfully!" : "Failed to add vehicle.";
            } catch (ClassNotFoundException | SQLException e) {
                message = "Error adding vehicle: " + e.getMessage();
            }
        }

        request.setAttribute("message", message);
        response.sendRedirect("Add-Vehicle-Details.jsp");
    }

    private void deleteVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleId = request.getParameter("vehicleId");
        String message = "";

        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            message = "Vehicle ID is required for deletion.";
        } else {
            try {
                boolean result = vehicleService.deleteVehicle(vehicleId);
                message = result ? "Vehicle ID " + vehicleId + " deleted successfully!" : "Failed to delete vehicle.";
            } catch (ClassNotFoundException | SQLException e) {
                message = "Error deleting vehicle: " + e.getMessage();
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        response.sendRedirect("VehicleDashboard.jsp");
    }
}
