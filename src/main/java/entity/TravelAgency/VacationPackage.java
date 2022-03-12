package entity.TravelAgency;


import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private String extraSpecifications;

    @Column
    private int peopleCapacity;

    @Column
    private int bookings;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;


    public VacationPackage(){}


    public VacationPackage(String id, String name, double price, LocalDate startDate, LocalDate endDate, String extraSpecifications, int peopleCapacity, int bookings, Destination destination) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extraSpecifications = extraSpecifications;
        this.peopleCapacity = peopleCapacity;
        this.bookings = bookings;
        this.destination = destination;
    }
}
