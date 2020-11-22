package Classes;

import java.util.List;
import java.sql.*;

public class DbConection implements IDbConnection
{
    private Connection conn = null;
    private final String DB_URL = "jdbc:sqlserver://;servername=DESKTOP-JCRR398\\PROJECTSSERVER;databaseName=ChatAppDb;integratedSecurity=true;";

    @Override
    public String TestServerConnection()
    {
        try
        {
            conn = DriverManager.getConnection(DB_URL);
            if (conn != null) {
                return "Db connection successful";
            }

        }
        catch (SQLException ex)
        {
            return "Db connection error: "+ ex.getMessage();
        }
        return null;
    }

    @Override
    public boolean IsUserLoggedIn(int Id)
    {
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT LoggedIn FROM Users WHERE Id = "+Id);
            while (rs.next()) {
                String lastName = rs.getString("Lname");
                System.out.println(lastName);
            }
        }
        catch (SQLException ex)
        {
            return false;
        }
        return false;
    }

    @Override
    public User LogIn(String email, String password) {
        return null;
    }

    @Override
    public User Register(User user) {
        return null;
    }

    @Override
    public void AddFriend(int userId, int friendId) {

    }

    @Override
    public void RemoveFriend(int userId, int friendId) {

    }

    @Override
    public List<User> GetFriends(int userId) {
        return null;
    }

    @Override
    public List<User> SearchUsers(String name) {
        return null;
    }
}
