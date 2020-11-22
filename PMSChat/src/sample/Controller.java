package sample;

import Classes.DbConection;
import Classes.IDbConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.cert.PolicyNode;

public class Controller {

    public void open_reg(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent fxml = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(fxml));
        stage.show();

    }
}
