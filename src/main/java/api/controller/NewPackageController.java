package api.controller;

import Util.ExceptionHandler.OperationStatus;
import Util.Mapper;
import api.models.DestinationModel;
import interfaces.IVacationPackageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.ToString;
import service.VacationPackageService;

public class NewPackageController {

    private DestinationModel destinationModel;
    private final IVacationPackageService vacationPackageService = new VacationPackageService();

    @FXML
    private TextField locationField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField ratingField;

    @FXML
    private TextField popularityField;

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
    private Button createButton;

    @FXML
    private Label nameWarning;

    @FXML
    private Label priceWarning;

    @FXML
    private Label dateWarning;

    @FXML
    private Label capacityWarning;

    @FXML
    private TextField opStatus;

    @FXML
    void createClick(ActionEvent event) {
        if(!packageNameField.getText().equals("")){
            if(!priceField.getText().equals("")){
                if(startDatePicker.getValue() != null && endDatePicker.getValue()!=null){
                    if(!capacityField.getText().equals("")){

                      OperationStatus operationStatus = vacationPackageService.addVacationPackage(packageNameField.getText(),Double.parseDouble(priceField.getText()),
                                startDatePicker.getValue(),endDatePicker.getValue(),extraDetailsField.getText(),Integer.parseInt(capacityField.getText()), 0,Mapper.destinationModelToEntityModel(destinationModel));
                        //System.out.println("OK");
                        opStatus.setText(operationStatus.getDescription());

                    }
                }
            }
        }

        setWarning(nameWarning,packageNameField);
        setWarning(priceWarning,priceField);
        setWarning(capacityWarning,capacityField);
        setDateWarning();
    }

    public void setDestinationModel(DestinationModel destinationModel){
        this.destinationModel = destinationModel;
    }

    public void setDestinationDetails(){
        locationField.setText(destinationModel.getLocation());
        countryField.setText(destinationModel.getCountry());
        ratingField.setText(String.valueOf(destinationModel.getRatingStars()));
        popularityField.setText(destinationModel.getPopularityLevel().toString());
    }

    void setWarning(Label warningLabel, TextField textField){
        warningLabel.setVisible(textField.getText().equals(""));
    }

    void setDateWarning(){
        dateWarning.setVisible(startDatePicker.getValue() == null || endDatePicker.getValue() == null);
    }

}
