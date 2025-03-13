package CruzeX.webapp.Controller;

import CruzeX.webapp.Model.Query;
import CruzeX.webapp.Service.QueryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/QueryController")
public class QueryController extends HttpServlet {

    private final QueryService service = new QueryService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");

        if (type == null) {
            response.sendRedirect("error.jsp?message=Missing+request+type");
            return;
        }

        switch (type) {
            case "add":
                addQuery(request, response);
                break;
            case "delete":
                deleteQuery(request, response);
                break;
            case "getAllQueries":
                getAllQueries(request, response);
                break;
            default:
                response.sendRedirect("error.jsp?message=Invalid+Request+Type");
        }
    }
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String type = request.getParameter("type");

    if ("getAllQueries".equals(type)) {
        getAllQueries(request, response);
    } else {
        response.sendRedirect("error.jsp?message=Invalid+GET+Request");
    }
}

    // Submit new query from user
    private void addQuery(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("userName");
        String email = request.getParameter("userEmail");
        String messageContent = request.getParameter("userMessage");
        String message;

        if (name == null || name.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            messageContent == null || messageContent.trim().isEmpty()) {
            message = "All fields are required.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("QueryForm.jsp").forward(request, response);
            return;
        }

        Query query = new Query(name, email, messageContent);
        boolean result = service.submitQuery(query);

        if (result) {
            response.sendRedirect("thank-you.jsp");
        } else {
            message = "Failed to submit query.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("QueryForm.jsp").forward(request, response);
        }
    }

    // Load all queries (for admin)
    private void getAllQueries(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Query> queryList;

        try {
            queryList = service.fetchAllQueries();
            session.setAttribute("queryList", queryList);
            response.sendRedirect("admin-view-queries.jsp");
        } catch (Exception e) {
            session.setAttribute("message", "Error fetching queries: " + e.getMessage());
            response.sendRedirect("error.jsp?message=An+Unexpected+Error+Occurred");
        }
    }

    // Delete query by email (admin)
    private void deleteQuery(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = request.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            response.sendRedirect("error.jsp?message=Invalid+Email");
            return;
        }

        try {
            boolean success = service.deleteQueryByEmail(email);
            if (success) {
                response.sendRedirect("QueryController?type=getAllQueries&message=Query+Deleted+Successfully");
            } else {
                response.sendRedirect("error.jsp?message=Query+Deletion+Failed");
            }
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=An+Unexpected+Error+Occurred");
        }
    }
}