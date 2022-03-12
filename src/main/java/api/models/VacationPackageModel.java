package api.models;

import java.util.Date;
import lombok.*;
@Getter
@Setter


public class VacationPackageModel {

    private String id;

    private String name;

    private double price;

    private Date startDate;

    private Date endDate;

    private String extraSpecifications;

    private int peopleCapacity;

    public VacationPackageModel(String id, String name, double price, Date startDate, Date endDate, String extraSpecifications, int peopleCapacity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extraSpecifications = extraSpecifications;
        this.peopleCapacity = peopleCapacity;
    }
}
