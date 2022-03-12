import api.controller.FirstPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new File("src/main/resources/Views/view.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        Parent root = loader.load();

        FirstPageController firstPageController = loader.getController();
        firstPageController.setStage(primaryStage);

        primaryStage.setTitle("First Page");
        primaryStage.setScene(new Scene(root, 610, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}