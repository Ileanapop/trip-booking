package entity.TravelAgency;

import javax.persistence.*;

import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VacationPackage> vacationPackageList;

    public Destination(){}

    public Destination(String id, String location, String country, int ratingStars, PopularityLevel popularityLevel) {
        this.id = id;
        this.location = location;
        this.country = country;
        this.ratingStars = ratingStars;
        this.popularityLevel = popularityLevel;
    }
}
