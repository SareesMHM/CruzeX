package CruzeX.webapp.Service;

import CruzeX.webapp.Dao.QueryDAO;
import CruzeX.webapp.Model.Query;

import java.util.List;

public class QueryService {
    private final QueryDAO dao = new QueryDAO();

    public boolean submitQuery(Query query) {
        return dao.saveQuery(query);
    }

    public List<Query> fetchAllQueries() {
        return dao.getAllQueries();
    }

    public boolean deleteQueryByEmail(String email) {
        return dao.deleteQueryByEmail(email);
    }

    public boolean updateQueryStatus(String email, String status) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}