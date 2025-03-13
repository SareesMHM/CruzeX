package CruzeX.webapp.Dao;

import CruzeX.webapp.Model.DBConnection;
import CruzeX.webapp.Model.Query;

import java.sql.*;
import java.util.*;

public class QueryDAO {
    
     public DbConnector getDbConnector() {
        DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl();
        return factory.getDbConnector();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        DbConnector connector = getDbConnector();
        return connector.getDbConnection();
    }
    public boolean saveQuery(Query query) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO queries (name, email, message) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, query.getName());
            stmt.setString(2, query.getEmail());
            stmt.setString(3, query.getMessage());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Query> getAllQueries() {
        List<Query> queryList = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM queries ORDER BY id DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Query q = new Query();
                q.setName(rs.getString("name"));
                q.setEmail(rs.getString("email"));
                q.setMessage(rs.getString("message"));
                queryList.add(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryList;
    }

    public boolean deleteQueryByEmail(String email) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM queries WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}