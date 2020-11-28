package sample;

import Classes.DbConnection;
import Classes.IDbConnection;
import Classes.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {


    public TextField emailField;
    public PasswordField passwordField;
    public Label email_label;
    public Label password_label;

    public void open_reg(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent fxml = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(fxml));
        stage.show();
    }

    public void open_logedIn(MouseEvent mouseEvent) throws IOException{
        if(emailField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            email_label.setVisible(true);
            email_label.setText("The field is empty");
            password_label.setVisible(true);
            password_label.setText("The field is empty");
        }
        else {
            IDbConnection connection = null;
            try
            {
                connection = DbConnection.getInstance();
            }
            catch (Exception e)
            {
                //show message
                return;
            }

            User user = connection.LogIn(emailField.getText(), passwordField.getText());
            if(user==null)
            {
                //Show message incorrect email or password
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logedin.fxml"));
            Parent fxml = fxmlLoader.load();
            LogedIn logedInController= fxmlLoader.<LogedIn>getController();
            logedInController.user=user;
            logedInController.connection=connection;
            Stage stage = new Stage();
            stage.setScene(new Scene(fxml));
            stage.show();
        }

    }
}
