package Classes;

import java.util.List;

public interface IDbConnection
{
    boolean IsUserLoggedIn(int Id);
    User LogIn(String email, String password);
    void Register(User user);
    void SignOut(int Id);
    void AddFriend(int userId, int friendId);
    void RemoveFriend(int userId, int friendId);
    List<User> GetFriends(int userId);
    List<User> SearchUsers(String name);
}
