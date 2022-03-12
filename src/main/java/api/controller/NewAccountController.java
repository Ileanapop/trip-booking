package api.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;

import java.io.IOException;

public class NewAccountController {

    private Stage stage;

    @FXML
    private TextField usernameField;

    @FXML
    private Button registerButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button backButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private Label firstNameWarning;

    @FXML
    private Label lastNameWarning;

    @FXML
    private Label emailWarning;

    @FXML
    private Label usernameWarning;

    @FXML
    private Label passwordWarning;

    @FXML
    void backClick(ActionEvent event) throws IOException {
        PageManagementController.setLogInPage(stage);
    }

    @FXML
    void registerClick(ActionEvent event) {

        if(!firstNameField.getText().equals("")){
            firstNameWarning.setVisible(false);
            if(!lastNameField.getText().equals("")) {
                lastNameWarning.setVisible(false);
                if(!passwordField.getText().equals("")) {
                    passwordWarning.setVisible(false);
                    if(!usernameField.getText().equals("")){
                        usernameWarning.setVisible(false);
                        if(!emailField.getText().equals("")){
                            emailWarning.setVisible(false);
                            UserService userService = new UserService();
                            userService.insertUser(firstNameField.getText(),lastNameField.getText(),emailField.getText(),usernameField.getText(),passwordField.getText());
                        }
                    }
                }
            }
        }

        setWarning(firstNameWarning,firstNameField);
        setWarning(lastNameWarning,lastNameField);
        setWarning(passwordWarning,passwordField);
        setWarning(usernameWarning,usernameField);
        setWarning(emailWarning,emailField);

    }

    void setWarning(Label warningLabel, TextField textField){
        warningLabel.setVisible(textField.getText().equals(""));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
