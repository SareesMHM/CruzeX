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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        VehicleService vehicleService = VehicleService.getVehicleServiceInstance();

        if (type != null && type.equals("specific")) {
            getSpecificVehicle(request, response, vehicleService);
        } else {
            getAllVehicles(request, response, vehicleService);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        VehicleService vehicleService = VehicleService.getVehicleServiceInstance();

        if (type != null && type.equals("update")) {
            updateVehicle(request, response, vehicleService);
        } else if (type != null && type.equals("add")) {
            addVehicle(request, response, vehicleService);
        } else if (type != null && type.equals("delete")) {
            deleteVehicle(request, response, vehicleService);
        }
    }

    private void getAllVehicles(HttpServletRequest request, HttpServletResponse response, VehicleService service) throws ServletException, IOException {
        String message = "";
        List<Vehicle> vehicleList;

        try {
            vehicleList = service.getAllVehicles();
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            vehicleList = new ArrayList<>();
        }

        request.setAttribute("message", message);
        request.setAttribute("vehicleList", vehicleList);

        RequestDispatcher rd = request.getRequestDispatcher("VehicleDashboard.jsp");
        rd.forward(request, response);
    }

    private void getSpecificVehicle(HttpServletRequest request, HttpServletResponse response, VehicleService service) throws ServletException, IOException {
        String vehicleIdStr = request.getParameter("vehicleId");
        int vehicleId;
        Vehicle vehicle;
        String message = "";

        if (vehicleIdStr != null && !vehicleIdStr.isEmpty()) {
            try {
                vehicleId = Integer.parseInt(vehicleIdStr);
                vehicle = service.getSpecificVehicle(vehicleId);
            } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
                message = e.getMessage();
                vehicle = new Vehicle(0, "", "", "");
            }
        } else {
            message = "Vehicle ID is missing or invalid.";
            vehicle = new Vehicle(0, "", "", "");
        }

        request.setAttribute("vehicle", vehicle);
        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("Search-Vehicle-Details.jsp");
        rd.forward(request, response);
    }

    private void updateVehicle(HttpServletRequest request, HttpServletResponse response, VehicleService service) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String vehicleName = request.getParameter("vehicleName");
        String image = request.getParameter("image");
        String category = request.getParameter("category");

        Vehicle vehicle = new Vehicle(vehicleId, vehicleName, image, category);

        boolean result;
        String message = "";
        try {
            result = service.editVehicle(vehicle);
            if (result) {
                message = "Vehicle " + vehicleId + " has been successfully updated!";
            } else {
                message = "Failed to update the vehicle! Vehicle ID: " + vehicleId;
            }
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("Search-Vehicle-Details.jsp");
        rd.forward(request, response);
    }

    private void addVehicle(HttpServletRequest request, HttpServletResponse response, VehicleService service) throws ServletException, IOException {
        String vehicleName = request.getParameter("vehicleName");
        String image = request.getParameter("image");
        String category = request.getParameter("category");

        Vehicle vehicle = new Vehicle(0, vehicleName, image, category);

        boolean result;
        String message = "";
        try {
            result = service.registerVehicle(vehicle);
            if (result) {
                message = "Vehicle " + vehicleName + " has been successfully added!";
            } else {
                message = "Failed to add the vehicle! Vehicle: " + vehicleName;
            }
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("Add-Vehicle-Details.jsp");
        rd.forward(request, response);
    }

    private void deleteVehicle(HttpServletRequest request, HttpServletResponse response, VehicleService service) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));

        boolean result;
        String message = "";
        try {
            result = service.deleteVehicle(vehicleId);
            if (result) {
                message = "Vehicle ID " + vehicleId + " has been successfully deleted!";
            } else {
                message = "Failed to delete the vehicle! Vehicle ID: " + vehicleId;
            }
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        HttpSession session = request.getSession();

        try {
            List<Vehicle> vehicleList = service.getAllVehicles();
            session.setAttribute("vehicleList", vehicleList);
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        session.setAttribute("message", message);
        response.sendRedirect("VehicleDashboard.jsp");
    }
}
