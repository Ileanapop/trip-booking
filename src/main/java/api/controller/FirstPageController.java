package api.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPageController {

    private Stage stage;

    @FXML
    private Button employeeButton;

    @FXML
    private Button adminButton;

    @FXML
    void employeeClick(ActionEvent event) throws IOException {
        PageManagementController.setDestinationsPage(stage);
    }

    @FXML
    void userClick(ActionEvent event) throws IOException {
        PageManagementController.setLogInPage(stage);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
