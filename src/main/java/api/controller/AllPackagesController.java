package api.controller;

import api.models.PackageStatusModel;
import api.models.VacationPackageModel;
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

import service.VacationPackageService;
import java.io.IOException;


public class AllPackagesController {

    private Stage stage = new Stage();

    private TableView<VacationPackageModel> tableViewPackages = new TableView<>();

    private final IVacationPackageService vacationPackageService = new VacationPackageService();

    @FXML
    private VBox packagesVBox;

    @FXML
    private Button backButton;

    @FXML
    private TextField packageNameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField extraDetailsField;

    @FXML
    private TextField capacityField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button editPackageButton;

    @FXML
    void editClick(ActionEvent event) {
        if (tableViewPackages.getSelectionModel().getSelectedItem() != null){
            VacationPackageModel vacationPackage = tableViewPackages.getSelectionModel().getSelectedItem();
            vacationPackageService.editVacationPackage(vacationPackage.getId(),packageNameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    startDatePicker.getValue(),endDatePicker.getValue(),
                    extraDetailsField.getText(),Integer.parseInt(capacityField.getText()));
            displayTableVacationPackages();
        }
    }

    @FXML
    void backClick(ActionEvent event) throws IOException {
        PageManagementController.setDestinationsPage(stage);
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTableViewDestinations(){
        TableColumn<VacationPackageModel,String> nameColumn = new TableColumn<>("name");
        TableColumn<VacationPackageModel,Integer > capacityColumn = new TableColumn<>("capacity");
        TableColumn<VacationPackageModel,Integer > bookingsColumn = new TableColumn<>("bookings");
        TableColumn<VacationPackageModel, PackageStatusModel> statusColumn = new TableColumn<>("status");
        TableColumn<VacationPackageModel,Button> deleteDestinationColumn= new TableColumn<>("delete");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("peopleCapacity"));
        bookingsColumn.setCellValueFactory(new PropertyValueFactory<>("bookings"));
        deleteDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableViewPackages.getColumns().add(nameColumn);
        tableViewPackages.getColumns().add(capacityColumn);
        tableViewPackages.getColumns().add(bookingsColumn);
        tableViewPackages.getColumns().add(statusColumn);
        tableViewPackages.getColumns().add(deleteDestinationColumn);
        packagesVBox.getChildren().add(tableViewPackages);

        ObservableList<VacationPackageModel> observableList = FXCollections.observableList(vacationPackageService.selectVacationPackages());
        tableViewPackages.setItems(observableList);
        tableViewPackages.setOnMouseClicked((MouseEvent event) -> {
            visualizePackage();
        });
    }

    public void displayTableVacationPackages(){
        packagesVBox.getChildren().remove(tableViewPackages);
        ObservableList<VacationPackageModel> observableList = FXCollections.observableList(vacationPackageService.selectVacationPackages());
        tableViewPackages.setItems(observableList);
        tableViewPackages.setOnMouseClicked((MouseEvent event) -> {
            visualizePackage();
        });
        packagesVBox.getChildren().add(tableViewPackages);
    }

    private void visualizePackage() {
        if (tableViewPackages.getSelectionModel().getSelectedItem() != null) {
            VacationPackageModel vacationPackageModel= tableViewPackages.getSelectionModel().getSelectedItem();
            packageNameField.setText(vacationPackageModel.getName());
            priceField.setText(String.valueOf(vacationPackageModel.getPrice()));
            extraDetailsField.setText(vacationPackageModel.getExtraSpecifications());
            capacityField.setText(String.valueOf(vacationPackageModel.getPeopleCapacity()));
            startDatePicker.setValue(vacationPackageModel.getStartDate());
            endDatePicker.setValue(vacationPackageModel.getEndDate());

            vacationPackageModel.getDelete().setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    vacationPackageService.deleteVacationPackage(vacationPackageModel.getId());
                    displayTableVacationPackages();
                }
            });

        }
    }

}
