package CruzeX.webapp.Controller;

import CruzeX.webapp.Model.Driver;
import CruzeX.webapp.Model.Vehicle;
import CruzeX.webapp.Service.DriverService;
import CruzeX.webapp.Service.VehicleService;
import java.io.File;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@WebServlet("/VehicleController")

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB before writing to disk
    maxFileSize = 1024 * 1024 * 10,       // 10MB max file size
    maxRequestSize = 1024 * 1024 * 50     // 50MB max request size
)
   
public class VehicleController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final VehicleService vehicleService = VehicleService.getVehicleServiceObj();
    private final DriverService driverService = DriverService.getDriverServiceInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if ("customer".equals(type)) {
            getAllVehicles(request, response, "CustomerViewVehicle.jsp");
        } else if ("loadDrivers".equals(type)) {
            loadDrivers(request, response);
        } 
        else if ("getSpecificVehicle".equals(type)) {
            getSpecificVehicle(request, response);
        }
         else if ("updateVehicle".equals(type)) {
            updateVehicle(request, response);
        }
         
          else if ("deleteVehicle".equals(type)) {
            deleteVehicle(request, response);
        }else {
            getAllVehicles(request, response, "VehicleDashboard.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if (type != null) {
            switch (type) {
                case "updateVehicle":
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
    }

    private void getAllVehicles(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
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

        response.sendRedirect(page);
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

//    private void updateVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String vehicleId = request.getParameter("vehicleId");
//        String vehicleName = request.getParameter("vehicleName");
//        String image = request.getParameter("image");
//        String category = request.getParameter("category");
//        int monthFee = 0;
//        int driverID = 0;
//        String message = "";
//
//        try {
//            monthFee = Integer.parseInt(request.getParameter("monthFee"));
//            driverID = Integer.parseInt(request.getParameter("driverID"));
//        } catch (NumberFormatException e) {
//            message = "Invalid input for month fee or driver ID.";
//        }
//
//        if (vehicleId == null || vehicleId.trim().isEmpty() || vehicleName == null || category == null) {
//            message = "All fields are required.";
//        } else {
//            try {
//                Vehicle vehicle = new Vehicle(vehicleId, vehicleName, image, category, monthFee, driverID);
//                boolean result = vehicleService.editVehicle(vehicle);
//                message = result ? "Vehicle " + vehicleId + " updated successfully!" : "Failed to update vehicle.";
//            } catch (ClassNotFoundException | SQLException e) {
//                message = "Error updating vehicle: " + e.getMessage();
//            }
//        }
//
//        request.setAttribute("message", message);
////        response.sendRedirect("Search-Vehicle-Details.jsp");
//        RequestDispatcher rd = request.getRequestDispatcher("Search-Vehicle-Details.jsp");
//        rd.forward(request, response);
//    }

    

 
private void updateVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String vehicleId = request.getParameter("vehicleId");
    String vehicleName = request.getParameter("vehicleName");
    String category = request.getParameter("category");
    int monthFee = 0, driverID = 0;
    String message = "";
    String imageName = ""; // Image filename

    try {
        monthFee = Integer.parseInt(request.getParameter("monthFee"));
        driverID = Integer.parseInt(request.getParameter("driverID"));
    } catch (NumberFormatException e) {
        message = "Invalid input for month fee or driver ID.";
    }

    // Validate required fields
    if (vehicleId == null || vehicleId.trim().isEmpty() || vehicleName == null || category == null) {
        message = "All fields are required.";
    } else {
        try {
            // Handling file upload
            Part filePart = request.getPart("image"); // Get file part
            if (filePart != null && filePart.getSize() > 0) {
                imageName = filePart.getSubmittedFileName();
                
                // Define upload path
                String uploadPath = getServletContext().getRealPath("/img/car"); // Get server path
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir(); // Create directory if not exists
                
                // Write file to the server
                filePart.write(uploadPath + File.separator + imageName);
                // Create Vehicle object and update
                Vehicle vehicle = new Vehicle(vehicleId, vehicleName, imageName, category, monthFee, driverID);
                boolean result = vehicleService.editVehicle(vehicle);
                message = result ? "Vehicle updated successfully!" : "Failed to update vehicle.";
                } else {
                    message = "No file uploaded.";
                }

            
            
        } catch (Exception e) {
            message = "Error updating vehicle: " + e.getMessage();
        }
    }

    // Set message attribute and forward request
    request.setAttribute("message", message);
    request.getRequestDispatcher("Search-Vehicle-Details.jsp").forward(request, response);
}


    private void addVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleID = request.getParameter("vehicleID");
        String vehicleName = request.getParameter("vehicleName");
        String image = request.getParameter("image");
        String category = request.getParameter("category");
        int monthFee = 0;
        int driverID = 0;
        String message = "";

        try {
            monthFee = Integer.parseInt(request.getParameter("monthFee"));
            driverID = Integer.parseInt(request.getParameter("driverID"));
        } catch (NumberFormatException e) {
            message = "Invalid input for month fee or driver ID.";
        }

        if (vehicleID == null || vehicleID.trim().isEmpty() || vehicleName == null || category == null) {
            message = "All fields are required.";
        } else {
            try {
                Vehicle vehicle = new Vehicle(vehicleID, vehicleName, image, category, monthFee, driverID);
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

    private void loadDrivers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            List<Driver> driverList = driverService.getAllDrivers();
            session.setAttribute("driverList", driverList);
            response.sendRedirect("Add-Vehicle-Details.jsp");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("Add-Vehicle-Details.jsp");
//            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            request.setAttribute("message", "Error fetching drivers: " + e.getMessage());
            response.sendRedirect("Add-Vehicle-Details.jsp");
        }
    }
}