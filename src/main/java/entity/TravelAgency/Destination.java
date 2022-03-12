package entity.TravelAgency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
@Getter
@Setter

@Entity
@Table(name = "destination")
public class Destination {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String location;

    @Column(nullable = false)
    private String country;

    @Column
    private int ratingStars;  //constraint - between 0 and 5 stars

    @Column
    private PopularityLevel popularityLevel;

    public Destination(){}

    public Destination(String id, String location, String country, int ratingStars, PopularityLevel popularityLevel) {
        this.id = id;
        this.location = location;
        this.country = country;
        this.ratingStars = ratingStars;
        this.popularityLevel = popularityLevel;
    }
}
