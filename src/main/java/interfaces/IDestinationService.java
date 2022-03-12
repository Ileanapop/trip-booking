package interfaces;

import api.models.DestinationModel;
import entity.TravelAgency.Destination;
import entity.TravelAgency.PopularityLevel;

import java.util.List;

public interface IDestinationService {

    List<DestinationModel> getDestinations();
    boolean deleteDestination(String id);
    void addDestination(String location, String country, int ratingStars, PopularityLevel popularityLevel);

}
