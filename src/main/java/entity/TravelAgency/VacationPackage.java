package entity.TravelAgency;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter

@Entity
@Table(name = "vacation_package")
public class VacationPackage {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private double price;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private String extraSpecifications;

    @Column
    private int peopleCapacity;


    public VacationPackage(){}


    public VacationPackage(String id, String name, double price, Date startDate, Date endDate, String extraSpecifications, int peopleCapacity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extraSpecifications = extraSpecifications;
        this.peopleCapacity = peopleCapacity;
    }
}
