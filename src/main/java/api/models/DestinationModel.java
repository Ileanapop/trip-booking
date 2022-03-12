package api.models;

import entity.TravelAgency.PopularityLevel;
import javafx.scene.control.Button;
import lombok.*;


@Getter
@Setter
public class DestinationModel {

    private String id;
    private String location;
    private String country;
    private int ratingStars;  //constraint - between 0 and 5 stars
    private PopularityLevel popularityLevel;
    private Button delete;
    private Button addPackage;


    public DestinationModel(){}

    public DestinationModel(String id, String location, String country, int ratingStars, PopularityLevel popularityLevel) {
        this.id = id;
        this.location = location;
        this.country = country;
        this.ratingStars = ratingStars;
        this.popularityLevel = popularityLevel;
        delete = new Button("-");
        addPackage = new Button("new package");
    }

}
