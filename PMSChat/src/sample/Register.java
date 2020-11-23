package sample;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Register {
    public TextField first_name;
    public TextField last_name;
    public TextField email_reg;
    public TextField password_reg;
    public PasswordField confirm_pass_reg;
    
    public Label label_fn;
    public Label label_ln;
    public Label label_email_reg;
    public Label label_pass;
    public Label label_cofirmpass;

    public void user_registration(MouseEvent mouseEvent) {
        if(first_name.getText().isEmpty() && last_name.getText().isEmpty() &&
            email_reg.getText().isEmpty() && password_reg.getText().isEmpty() &&
                confirm_pass_reg.getText().isEmpty()

        )
        {
            label_fn.setVisible(true);
            label_fn.setText("The field is empty");
            label_ln.setVisible(true);
            label_ln.setText("The filed is empty");
            label_email_reg.setVisible(true);
            label_email_reg.setText("The field is empty");
            label_pass.setVisible(true);
            label_pass.setText("The field is empty");
            label_cofirmpass.setVisible(true);
            label_cofirmpass.setText("The field is empty");
        }
    }
}
