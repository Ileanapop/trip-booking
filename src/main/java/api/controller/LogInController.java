package api.controller;

import Util.Mapper;
import api.models.UserModel;
import interfaces.IUserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;

import java.io.IOException;

public class LogInController {

    private final IUserService userService = new UserService();
    private Stage stage;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button createAccount;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button backButton;

    @FXML
    private Label usernameWarning;

    @FXML
    private Label passwordWarning;

    @FXML
    void backClick(ActionEvent event) throws IOException {
        PageManagementController.setFirstPage(stage);
    }

    @FXML
    void createAccountClick(ActionEvent event) throws IOException {
        PageManagementController.setNewAccountPage(stage);
    }

    @FXML
    void loginClick(ActionEvent event) throws IOException {

        if(!usernameTextField.getText().equals("")){
            if(!passwordField.getText().equals("")){
                UserModel userModel = userService.authenticateUser(usernameTextField.getText(), passwordField.getText());
                if(userModel!=null){
                    PageManagementController.setUserPage(stage,userModel);
                }
            }
        }

        setWarning(usernameWarning,usernameTextField);
        setWarning(passwordWarning,passwordField);
    }

    void setWarning(Label warningLabel, TextField textField){
        warningLabel.setVisible(textField.getText().equals(""));
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
