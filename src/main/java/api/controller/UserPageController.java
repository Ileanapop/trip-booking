package api.controller;

import api.models.DestinationModel;
import api.models.PackageStatusModel;
import api.models.UserModel;
import api.models.VacationPackageModel;
import interfaces.IDestinationService;
import interfaces.IVacationPackageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.DestinationService;
import service.VacationPackageService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserPageController {

    private Stage stage;
    private UserModel userModel;
    private final IDestinationService destinationService = new DestinationService();
    private final IVacationPackageService vacationPackageService = new VacationPackageService();
    private TableView<VacationPackageModel> tableViewPackages = new TableView<>();

    @FXML
    private VBox packagesVBox;

    @FXML
    private TextField priceFilter;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button filterButton;

    @FXML
    private Button myBookingsButton;

    @FXML
    private ComboBox<String > destinationComboBox;

    @FXML
    private Button availableBookingsButton;

    @FXML
    void availableBookingsClick() {
        List<VacationPackageModel> vacationPackageModels = vacationPackageService.getAvailablePackages();
        ObservableList<VacationPackageModel> observableList = FXCollections.observableList(vacationPackageModels);
        tableViewPackages.setItems(observableList);
    }

    @FXML
    void filterClick(ActionEvent event) {

    }

    @FXML
    void myBookingsClick(ActionEvent event) {

    }

    public void setDestinationComboBox(){
        List<DestinationModel>  destinationModels = destinationService.getDestinations();
        List<String> allLocations = new ArrayList<>();
        for(DestinationModel destinationModel: destinationModels){
            allLocations.add(destinationModel.getLocation());
        }
        ObservableList<String> options = FXCollections.observableArrayList(allLocations);
        destinationComboBox.setItems(options);
    }

    public void setPackageTable(){
        TableColumn<VacationPackageModel,String> nameColumn = new TableColumn<>("name");
        TableColumn<VacationPackageModel,Integer > capacityColumn = new TableColumn<>("capacity");
        TableColumn<VacationPackageModel,Integer > bookingsColumn = new TableColumn<>("bookings");
        TableColumn<VacationPackageModel,Button> bookPackageColumn = new TableColumn<>("book");
        TableColumn<VacationPackageModel, LocalDate> startDateColumn = new TableColumn<>("start date");
        TableColumn<VacationPackageModel, LocalDate> endDateColumn = new TableColumn<>("end date");


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("peopleCapacity"));
        bookingsColumn.setCellValueFactory(new PropertyValueFactory<>("bookings"));
        bookPackageColumn.setCellValueFactory(new PropertyValueFactory<>("book"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        tableViewPackages.getColumns().add(nameColumn);
        tableViewPackages.getColumns().add(capacityColumn);
        tableViewPackages.getColumns().add(bookingsColumn);
        tableViewPackages.getColumns().add(startDateColumn);
        tableViewPackages.getColumns().add(endDateColumn);
        tableViewPackages.getColumns().add(bookPackageColumn);

        packagesVBox.getChildren().add(tableViewPackages);

        tableViewPackages.setOnMouseClicked((MouseEvent event) -> {
            selectPackage();
        });

    }

    public void displayTableVacationPackages(){
        packagesVBox.getChildren().remove(tableViewPackages);
        ObservableList<VacationPackageModel> observableList = FXCollections.observableList(vacationPackageService.selectVacationPackages());
        tableViewPackages.setItems(observableList);
        packagesVBox.getChildren().add(tableViewPackages);
    }


    private void selectPackage() {
        if (tableViewPackages.getSelectionModel().getSelectedItem() != null) {
            VacationPackageModel vacationPackageModel= tableViewPackages.getSelectionModel().getSelectedItem();
            vacationPackageModel.getBook().setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    //vacationPackageService.deleteVacationPackage(vacationPackageModel.getId());
                    availableBookingsClick();
                }
            });

        }
    }

    public void setUserModel(UserModel userModel){
        this.userModel = userModel;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
