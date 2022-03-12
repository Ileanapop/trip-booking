package api.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PageManagementController {

    public static void setLogInPage(Stage stage) throws IOException {
        URL url = new File("src/main/resources/Views/login.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        Parent root = loader.load();

        LogInController logInController = loader.getController();

        Stage primaryStage = new Stage();
        logInController.setStage(primaryStage);
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root, 610, 400));
        stage.close();
        primaryStage.show();
    }

    public static void setFirstPage(Stage stage) throws IOException {
        URL url = new File("src/main/resources/Views/view.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        Parent root = loader.load();

        FirstPageController firstPageController = loader.getController();

        Stage primaryStage = new Stage();
        firstPageController.setStage(primaryStage);
        primaryStage.setTitle("First Page");
        primaryStage.setScene(new Scene(root, 610, 400));
        stage.close();
        primaryStage.show();
    }

    public static void setNewAccountPage(Stage stage) throws IOException {
        URL url = new File("src/main/resources/Views/newAccount.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        Parent root = loader.load();

        NewAccountController newAccountController = loader.getController();

        Stage primaryStage = new Stage();
        newAccountController.setStage(primaryStage);
        primaryStage.setTitle("New user");
        primaryStage.setScene(new Scene(root, 610, 400));
        stage.close();
        primaryStage.show();
    }

    public static void setDestinationsPage(Stage stage) throws IOException {
        URL url = new File("src/main/resources/Views/destinations.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        Parent root = loader.load();

        DestinationController destinationController = loader.getController();
        destinationController.setTableViewDestinations();
        destinationController.setToggleGroups();

        Stage primaryStage = new Stage();
        destinationController.setStage(primaryStage);
        primaryStage.setTitle("Vacation destinations");
        primaryStage.setScene(new Scene(root, 920, 627));
        stage.close();
        primaryStage.show();
    }

}
