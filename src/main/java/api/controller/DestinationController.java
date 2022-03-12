package api.controller;

import Util.Mapper;
import api.models.DestinationModel;
import api.models.PopularityLevelModel;
import entity.TravelAgency.PopularityLevel;
import interfaces.IDestinationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import service.DestinationService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;


public class DestinationController {

    private final IDestinationService destinationService = new DestinationService();

    private TableView<DestinationModel> tableViewDestinations = new TableView<>();

    private Stage stage;

    private ToggleGroup ratingToggleGroup;
    private ToggleGroup popularityToggleGroup;

    @FXML
    private VBox destinationsVBox;

    @FXML
    private Button newDestinationButton;

    @FXML
    private TextField newLocation;

    @FXML
    private TextField newCountry;

    @FXML
    private RadioButton highLevel;

    @FXML
    private RadioButton mediumLevel;

    @FXML
    private RadioButton lowLevel;

    @FXML
    private RadioButton oneStar;

    @FXML
    private RadioButton twoStars;

    @FXML
    private RadioButton threeStars;

    @FXML
    private RadioButton fourStars;

    @FXML
    private RadioButton fiveStars;

    @FXML
    private Label locationWarning;

    @FXML
    private Label countryWarning;

    @FXML
    private Label ratingWarning;

    @FXML
    private Label popularityWarning;


    @FXML
    void viewPackagesClick(ActionEvent event) throws IOException {
        PageManagementController.setPackagesController(stage);
    }

    @FXML
    private Button viewPackagesButton;

    @FXML
    void newDestinationClick(ActionEvent event) {

        if(!newLocation.getText().equals("")){
            if(!newLocation.getText().equals("")){
                if(ratingToggleGroup.getSelectedToggle() != null){
                    if(popularityToggleGroup.getSelectedToggle() != null){
                        RadioButton rating = (RadioButton) ratingToggleGroup.getSelectedToggle();
                        destinationService.addDestination(newLocation.getText(),newCountry.getText(),
                                Integer.parseInt(rating.getText()),
                                Mapper.popularityLevelModelToEntityPopularityLevelModel(getSelectedPopularityLevel()));
                    }
                }
            }
        }

        setWarning(locationWarning,newLocation);
        setWarning(countryWarning,newCountry);
        displayTableDestinations();
    }

    private PopularityLevelModel getSelectedPopularityLevel(){

        RadioButton popularity = (RadioButton) popularityToggleGroup.getSelectedToggle();
        if(popularity.getText().equals("HIGH"))
            return PopularityLevelModel.HIGH;
        if(popularity.getText().equals("LOW"))
            return PopularityLevelModel.LOW;
        return PopularityLevelModel.MEDIUM;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setToggleGroups(){
        ratingToggleGroup = new ToggleGroup();
        popularityToggleGroup = new ToggleGroup();

        oneStar.setToggleGroup(ratingToggleGroup);
        twoStars.setToggleGroup(ratingToggleGroup);
        threeStars.setToggleGroup(ratingToggleGroup);
        fourStars.setToggleGroup(ratingToggleGroup);
        fiveStars.setToggleGroup(ratingToggleGroup);

        lowLevel.setToggleGroup(popularityToggleGroup);
        mediumLevel.setToggleGroup(popularityToggleGroup);
        highLevel.setToggleGroup(popularityToggleGroup);

    }

    public void selectRatingToggle(int destinationRating){
        if(destinationRating == 1)
            ratingToggleGroup.selectToggle(oneStar);
        else
            if(destinationRating == 2)
                ratingToggleGroup.selectToggle(twoStars);
            else
                if(destinationRating == 3)
                    ratingToggleGroup.selectToggle(threeStars);
                else
                    if(destinationRating == 4)
                        ratingToggleGroup.selectToggle(fourStars);
                    else
                        ratingToggleGroup.selectToggle(fiveStars);
    }

    public void selectPopularityToggle(PopularityLevel popularityLevel){
        if(popularityLevel == PopularityLevel.LOW)
            popularityToggleGroup.selectToggle(lowLevel);
        else
             if(popularityLevel == PopularityLevel.HIGH)
                 popularityToggleGroup.selectToggle(highLevel);
            else
                 popularityToggleGroup.selectToggle(mediumLevel);
    }

    public void displayTableDestinations(){
        destinationsVBox.getChildren().remove(tableViewDestinations);
        ObservableList<DestinationModel> observableList = FXCollections.observableList(destinationService.getDestinations());
        tableViewDestinations.setItems(observableList);
        tableViewDestinations.setOnMouseClicked((MouseEvent event) -> {
            visualizeDestination();
        });
        destinationsVBox.getChildren().add(tableViewDestinations);
    }

    public void visualizeDestination(){
        if (tableViewDestinations.getSelectionModel().getSelectedItem() != null) {
            DestinationModel selectedDestination = tableViewDestinations.getSelectionModel().getSelectedItem();
            newLocation.setText(selectedDestination.getLocation());
            newCountry.setText(selectedDestination.getCountry());
            selectRatingToggle(selectedDestination.getRatingStars());
            selectPopularityToggle(selectedDestination.getPopularityLevel());

            selectedDestination.getDelete().setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    destinationService.deleteDestination(selectedDestination.getId());
                    displayTableDestinations();
                }
            });

            selectedDestination.getAddPackage().setOnAction(new EventHandler() {
                @SneakyThrows
                @Override
                public void handle(Event event) {
                    URL url = new File("src/main/resources/Views/newPackage.fxml").toURI().toURL();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(url);
                    Parent root = loader.load();
                    NewPackageController newPackageController = loader.getController();
                    newPackageController.setDestinationModel(selectedDestination);
                    newPackageController.setDestinationDetails();
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("New package");
                    primaryStage.setScene(new Scene(root, 815, 437));
                    primaryStage.show();
                }
            });
        }
    }

    public void setTableViewDestinations(){
        TableColumn<DestinationModel,String> locationColumn = new TableColumn<>("location");
        TableColumn<DestinationModel,String > countryColumn = new TableColumn<>("country");
        TableColumn<DestinationModel,Integer> ratingStarsColumn = new TableColumn<>("rating stars");
        TableColumn<DestinationModel, PopularityLevelModel> popularityLevelColumn = new TableColumn<>("popularityLevel");
        TableColumn<DestinationModel,Button> deleteDestinationColumn= new TableColumn<>("delete");
        TableColumn<DestinationModel,Button> addPackageColumn= new TableColumn<>("Add new package");

        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        ratingStarsColumn.setCellValueFactory(new PropertyValueFactory<>("ratingStars"));
        popularityLevelColumn.setCellValueFactory(new PropertyValueFactory<>("popularityLevel"));
        deleteDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));
        addPackageColumn.setCellValueFactory(new PropertyValueFactory<>("addPackage"));

        tableViewDestinations.getColumns().add(locationColumn);
        tableViewDestinations.getColumns().add(countryColumn);
        tableViewDestinations.getColumns().add(ratingStarsColumn);
        tableViewDestinations.getColumns().add(popularityLevelColumn);
        tableViewDestinations.getColumns().add(deleteDestinationColumn);
        tableViewDestinations.getColumns().add(addPackageColumn);
        destinationsVBox.getChildren().add(tableViewDestinations);

        ObservableList<DestinationModel> observableList = FXCollections.observableList(destinationService.getDestinations());
        tableViewDestinations.setItems(observableList);
        tableViewDestinations.setOnMouseClicked((MouseEvent event) -> {
            visualizeDestination();
        });
    }

    void setWarning(Label warningLabel, TextField textField){
        warningLabel.setVisible(textField.getText().equals(""));
    }

}
