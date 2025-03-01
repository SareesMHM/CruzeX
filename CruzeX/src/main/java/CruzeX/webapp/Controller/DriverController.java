package CruzeX.webapp.Controller;

import CruzeX.webapp.Model.Driver;
import CruzeX.webapp.Service.DriverService;
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

@WebServlet("/DriverController")
public class DriverController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DriverService driverService = DriverService.getDriverServiceInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if ("specific".equals(type)) {
            getSpecificDriver(request, response);
        } else {
            getAllDrivers(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type != null) {
            switch (type) {
                case "update":
                    updateDriver(request, response);
                    break;
                case "add":
                    addDriver(request, response);
                    break;
                case "delete":
                    deleteDriver(request, response);
                    break;
                default:
                    break;
            }
        }
    }

    private void getAllDrivers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Driver> driverList;
        String message = "";

        try {
            driverList = driverService.getAllDrivers();
            session.setAttribute("driverList", driverList);
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            driverList = new ArrayList<>();
//            
//        }
//
//        request.setAttribute("message", message);
//        request.setAttribute("driverList", driverList);
//        RequestDispatcher rd = request.getRequestDispatcher("DriverDashboard.jsp");
//        rd.forward(request, response);
            session.setAttribute("message", message);
        }
        response.sendRedirect("DriverDashboard.jsp");
    }

    private void getSpecificDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String driverIdStr = request.getParameter("driverId");
        int driverId;
        Driver driver;
        String message = "";

        if (driverIdStr != null && !driverIdStr.isEmpty()) {
            try {
                driverId = Integer.parseInt(driverIdStr);
                driver = driverService.getSpecificDriver(driverId);
            } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
                message = e.getMessage();
                driver = new Driver();
            }
        } else {
            message = "Driver ID is missing or invalid.";
            driver = new Driver();
        }

        request.setAttribute("driver", driver);
        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("Search-Driver-Details.jsp");
        rd.forward(request, response);
    }

    private void updateDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String licenseNumber = request.getParameter("licenseNumber");
        String email = request.getParameter("email");

        Driver driver = new Driver(driverId, firstName, lastName, licenseNumber, email);
        String message = "";
        try {
            boolean result = driverService.editTheDriver(driver);
            message = result ? "Driver " + driverId + " has been successfully updated!" : "Failed to update the driver! Driver ID: " + driverId;
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("Search-Driver-Details.jsp");
        rd.forward(request, response);
    }

    private void addDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String licenseNumber = request.getParameter("licenseNumber");
        String email = request.getParameter("email");

        Driver driver = new Driver(firstName, lastName, licenseNumber, email);
        String message = "";
        try {
            boolean result = driverService.registerDriver(driver);
            message = result ? "Driver " + firstName + " " + lastName + " has been successfully added!" : "Failed to add the driver!";
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("Add-Driver-Details.jsp");
        rd.forward(request, response);
    }

    private void deleteDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        String message = "";

        try {
            boolean result = driverService.deleteTheDriver(driverId);
            message = result ? "Driver ID " + driverId + " has been successfully deleted!" : "Failed to delete the driver! Driver ID: " + driverId;
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        HttpSession session = request.getSession();
        session.setAttribute("message", message);

        try {
            List<Driver> driverList = driverService.getAllDrivers();
            session.setAttribute("driverList", driverList);
        } catch (ClassNotFoundException | SQLException e) {
            session.setAttribute("message", e.getMessage());
        }

        response.sendRedirect("DriverDashboard.jsp");
    }
}
