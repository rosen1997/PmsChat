package sample;

import Classes.IDbConnection;
import Classes.User;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class LogedIn {

    public User user;
    public IDbConnection connection;

    public AnchorPane show_contacts;
    public ListView friendsList;

    public void open_contacts(MouseEvent mouseEvent) {
        
        show_contacts.setVisible(true);

    }

    public void show_friends(MouseEvent mouseEvent) {
        friendsList.getItems().clear();
        List<User> friends = connection.GetFriends(user.Id);
        for (User friend: friends)
        {
            friendsList.getItems().add(friend.FirstName.toString()+" "+friend.LastName.toString());
        }
    }
}
