package api.models;

import java.time.LocalDate;

import javafx.scene.control.Button;
import lombok.*;
@Getter
@Setter


public class VacationPackageModel {

    private String id;

    private String name;

    private double price;

    private LocalDate startDate;

    private LocalDate endDate;

    private String extraSpecifications;

    private int peopleCapacity;

    private int bookings;

    private Button delete;
    private Button book;

    public VacationPackageModel(String id, String name, double price, LocalDate startDate, LocalDate endDate, String extraSpecifications, int peopleCapacity, int bookings) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extraSpecifications = extraSpecifications;
        this.peopleCapacity = peopleCapacity;
        this.bookings = bookings;
        this.delete = new Button("-");
        this.book = new Button("+");
    }

    public PackageStatusModel getStatus(){
        if(bookings == 0)
            return PackageStatusModel.NOT_BOOKED;
        else
            if(bookings == peopleCapacity)
                return PackageStatusModel.BOOKED;
            else
                return PackageStatusModel.IN_PROGRESS;
    }
}
