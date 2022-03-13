package api.controller;

import api.models.DestinationModel;
import api.models.PackageStatusModel;
import api.models.UserModel;
import api.models.VacationPackageModel;
import entity.TravelAgency.VacationPackage;
import interfaces.IDestinationService;
import interfaces.IUserService;
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
import service.UserService;
import service.VacationPackageService;

import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserPageController {

    private Stage stage;
    private UserModel userModel;
    private final IDestinationService destinationService = new DestinationService();
    private final IVacationPackageService vacationPackageService = new VacationPackageService();
    private final IUserService userService = new UserService();
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
    private Button myBookingsButton;

    @FXML
    private Button filterByDestinationButton;

    @FXML
    private Button filterByPriceButton;

    @FXML
    private Button filterByDateButton;

    @FXML
    private ComboBox<String > destinationComboBox;

    @FXML
    private Button availableBookingsButton;

    @FXML
    void availableBookingsClick() {
        List<VacationPackageModel> vacationPackageModels = vacationPackageService.getAvailablePackages();
        ObservableList<VacationPackageModel> observableList = FXCollections.observableList(vacationPackageModels);
        disableAlreadyBookedPackages(vacationPackageModels);
        tableViewPackages.setItems(observableList);
    }

    private void disableAlreadyBookedPackages(List<VacationPackageModel> vacationPackageModels){
        List<VacationPackageModel> alreadyBookedPackages = userService.getUserBookings(userModel.getId());

        for(VacationPackageModel vacationPackageModel:vacationPackageModels){
            for(VacationPackageModel alreadyBookedPackage: alreadyBookedPackages){
                if(alreadyBookedPackage.getId().equals(vacationPackageModel.getId())){
                    vacationPackageModel.getBook().setDisable(true);
                }
            }
        }
    }


    @FXML
    void filterByDateClick(ActionEvent event) {
        if(startDatePicker.getValue()!=null && endDatePicker.getValue()!=null){
            List<VacationPackageModel> filteredPackages = vacationPackageService.filterPackagesByPeriod(startDatePicker.getValue(),endDatePicker.getValue());
            ObservableList<VacationPackageModel> observableList = FXCollections.observableList(filteredPackages);
            disableAlreadyBookedPackages(filteredPackages);
            tableViewPackages.setItems(observableList);
        }
    }

    @FXML
    void filterByDestinationClick(ActionEvent event) {
        if(destinationComboBox.getValue() != null){
            List<VacationPackageModel> filteredPackages = vacationPackageService.filterPackagesByDestination(destinationComboBox.getValue());
            ObservableList<VacationPackageModel> observableList = FXCollections.observableList(filteredPackages);
            disableAlreadyBookedPackages(filteredPackages);
            tableViewPackages.setItems(observableList);
        }
    }

    @FXML
    void filterByPriceClick(ActionEvent event) {
        if(!priceFilter.getText().equals("")){
            List<VacationPackageModel> filteredPackages = vacationPackageService.filterPackagesByDPrice(Double.parseDouble(priceFilter.getText()));
            ObservableList<VacationPackageModel> observableList = FXCollections.observableList(filteredPackages);
            disableAlreadyBookedPackages(filteredPackages);
            tableViewPackages.setItems(observableList);
        }
    }

    @FXML
    void myBookingsClick(ActionEvent event) {
        packagesVBox.getChildren().remove(tableViewPackages);
        List<VacationPackageModel> bookings = userService.getUserBookings(userModel.getId());
        ObservableList<VacationPackageModel> observableList = FXCollections.observableList(bookings);
        for(VacationPackageModel vacationPackageModel:bookings){
            vacationPackageModel.getBook().setDisable(true);
        }
        tableViewPackages.setItems(observableList);
        packagesVBox.getChildren().add(tableViewPackages);
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
                    userService.createBooking(userModel,vacationPackageModel);
                    //userModel.getPackages().add(vacationPackageModel);
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
