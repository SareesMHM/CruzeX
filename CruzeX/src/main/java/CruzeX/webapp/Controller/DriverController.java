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

@WebServlet("/driverController")
public class DriverController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        DriverService driverService = DriverService.getDriverServiceInstance();

        if (type != null && type.equals("specific")) {
            getSpecificDriver(request, response, driverService);
        } else {
            getAllDrivers(request, response, driverService);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        DriverService driverService = DriverService.getDriverServiceInstance();

        if (type != null && type.equals("update")) {
            updateDriver(request, response, driverService);
        } else if (type != null && type.equals("add")) {
            addDriver(request, response, driverService);
        } else if (type != null && type.equals("delete")) {
            deleteDriver(request, response, driverService);
        }
    }

    private void getAllDrivers(HttpServletRequest request, HttpServletResponse response, DriverService service) throws ServletException, IOException {
        String message = "";
        List<Driver> driverList;

        try {
            driverList = service.getAllDrivers();
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
            driverList = new ArrayList<>();
        }

        request.setAttribute("message", message);
        request.setAttribute("driverList", driverList);

        RequestDispatcher rd = request.getRequestDispatcher("DriverDashboard.jsp");
        rd.forward(request, response);
    }

    private void getSpecificDriver(HttpServletRequest request, HttpServletResponse response, DriverService service) throws ServletException, IOException {
        String driverIdStr = request.getParameter("driverId");
        int driverId;
        Driver driver;
        String message = "";

        if (driverIdStr != null && !driverIdStr.isEmpty()) {
            try {
                driverId = Integer.parseInt(driverIdStr);
                driver = service.getSpecificDriver(driverId);
            } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
                message = e.getMessage();
                driver = new Driver(0, "", "", "", "");  // Default values if exception
            }
        } else {
            message = "Driver ID is missing or invalid.";
            driver = new Driver(0, "", "", "", "");  // Default values if invalid
        }

        request.setAttribute("driver", driver);
        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("Search-Driver-Details.jsp");
        rd.forward(request, response);
    }

    private void updateDriver(HttpServletRequest request, HttpServletResponse response, DriverService service) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String licenseNumber = request.getParameter("licenseNumber");
        String email = request.getParameter("email");

        Driver driver = new Driver(driverId, firstName, lastName, licenseNumber, email);

        boolean result;
        String message = "";
        try {
            result = service.editTheDriver(driver);
            if (result) {
                message = "Driver " + driverId + " has been successfully updated!";
            } else {
                message = "Failed to update the driver! Driver ID: " + driverId;
            }
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("Search-Driver-Details.jsp");
        rd.forward(request, response);
    }

    private void addDriver(HttpServletRequest request, HttpServletResponse response, DriverService service) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String licenseNumber = request.getParameter("licenseNumber");
        String email = request.getParameter("email");

        Driver driver = new Driver(firstName, lastName, licenseNumber, email);

        boolean result;
        String message = "";
        try {
            result = service.registerDriver(driver);
            if (result) {
                message = "Driver " + firstName + " " + lastName + " has been successfully added!";
            } else {
                message = "Failed to add the driver! Driver: " + firstName + " " + lastName;
            }
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("Add-Driver-Details.jsp");
        rd.forward(request, response);
    }

    private void deleteDriver(HttpServletRequest request, HttpServletResponse response, DriverService service) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("driverId"));

        boolean result;
        String message = "";
        try {
            result = service.deleteTheDriver(driverId);
            if (result) {
                message = "Driver ID " + driverId + " has been successfully deleted!";
            } else {
                message = "Failed to delete the driver! Driver ID: " + driverId;
            }
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        HttpSession session = request.getSession();

        try {
            List<Driver> driverList = service.getAllDrivers();
            session.setAttribute("driverList", driverList);
        } catch (ClassNotFoundException | SQLException e) {
            message = e.getMessage();
        }

        session.setAttribute("message", message);
        response.sendRedirect("DriverDashboard.jsp");
    }
}
