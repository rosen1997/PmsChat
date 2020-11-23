package Classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DbConection implements IDbConnection
{
    private Connection conn = null;
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //private final String DB_URL = "jdbc:sqlserver://;servername=DESKTOP-JCRR398\\PROJECTSSERVER;databaseName=ChatAppDb;integratedSecurity=true;authenticationScheme=NativeAuthentication";
    private final String DB_URL = "jdbc:sqlserver://;servername=LAPTOP-UCG8FSAC\\NZSQLSERVER;databaseName=PMSChat;integratedSecurity=true;authenticationScheme=NativeAuthentication";

    @Override
    public String TestServerConnection()
    {
        try
        {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            conn = DriverManager.getConnection(DB_URL);

            if (conn != null) {
                return "Db connection successful";
            }

        }
        catch (SQLException ex)
        {
            return "Db connection error: "+ ex.getMessage();
        }
        return "Error";
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
                boolean loggedIn = rs.getBoolean("LoggedIn");
                return loggedIn;
            }
        }
        catch (SQLException ex)
        {

        }
        return false;
    }

    @Override
    public User LogIn(String email, String password)
    {
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs;

            String passwordHashMd5 = this.HashMd5(password);
            if(passwordHashMd5==null)
            {
                //throw exception
            }

            rs = stmt.executeQuery("SELECT * FROM Users WHERE Email = '"+email+"' and Password = '"+passwordHashMd5+"'");
            while (rs.next()) {
                User user = new User(rs.getInt("Id"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Email"),true);
                stmt.executeUpdate("UPDATE Users SET LoggedIn="+1+" WHERE Id="+user.Id);
                return user;
            }
        }
        catch (SQLException ex)
        {

        }
        return null;
    }

    @Override
    public void SignOut(int Id)
    {
        try
        {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("UPDATE Users SET LoggedIn="+0+" WHERE Id="+Id);
            conn.close();
        }
        catch (SQLException ex)
        {

        }
    }

    @Override
    public void Register(User user)
    {
        try
        {
            Statement stmt = conn.createStatement();

            String passwordHashMd5 = this.HashMd5(user.Password);
            if(passwordHashMd5==null)
            {
                //throw exception
            }

            stmt.execute("INSERT INTO Users VALUES('"+user.FirstName+"','"+user.LastName+"','"+user.Email+"',"+user.LoggedIn+",'"+passwordHashMd5+ "')");
        }
        catch (SQLException ex)
        {

        }
    }

    @Override
    public void AddFriend(int userId, int friendId)
    {
        try
        {
            Statement stmt = conn.createStatement();

            stmt.execute("INSERT INTO FriendsList VALUES("+userId+","+friendId+ ")");
        }
        catch (SQLException ex)
        {

        }
    }

    @Override
    public void RemoveFriend(int userId, int friendId)
    {
        try
        {
            Statement stmt = conn.createStatement();

            stmt.execute("DELETE FROM FriendsList WHERE UserId="+userId+" AND "+"FriendId= "+friendId);
        }
        catch (SQLException ex)
        {

        }
    }

    @Override
    public List<User> GetFriends(int userId)
    {
        List<User> friends= new ArrayList<User>();

        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT Users.Id, Users.FirstName, Users.LastName, Users.Email, Users.LoggedIn FROM FriendsList JOIN Users ON FriendsList.FriendId=Users.Id WHERE FriendsList.UserId="+userId);
            while (rs.next()) {
                friends.add(new User(rs.getInt("Id"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Email"),rs.getBoolean("LoggedIn")));
            }
            return friends;
        }
        catch (SQLException ex)
        {

        }
        return null;
    }

    @Override
    public List<User> SearchUsers(String name)
    {
        List<User> friends= new ArrayList<User>();

        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM USERS WHERE FirstName='"+name+"'");
            while (rs.next()) {
                friends.add(new User(rs.getInt("Id"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Email"),rs.getBoolean("LoggedIn")));
            }
            return friends;
        }
        catch (SQLException ex)
        {

        }
        return null;
    }

    private String HashMd5(String password)
    {
        try {
            String generatedPassword = null;
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
            return generatedPassword;
        }
        catch (NoSuchAlgorithmException e)
        {
            //rethrow
        }
        return null;
    }
}
