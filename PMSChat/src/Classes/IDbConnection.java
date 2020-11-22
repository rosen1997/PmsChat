package Classes;

import java.util.List;

public interface IDbConnection
{
    String TestServerConnection();
    boolean IsUserLoggedIn(int Id);
    User LogIn(String email, String password);
    User Register(User user);
    void AddFriend(int userId, int friendId);
    void RemoveFriend(int userId, int friendId);
    List<User> GetFriends(int userId);
    List<User> SearchUsers(String name);
}
