package interfaces;

import entity.TravelAgency.Destination;
import entity.TravelAgency.PopularityLevel;

import java.util.List;

public interface IDestinationRepository {

    List<Destination> getDestinations();
    boolean deleteDestination(String id);
    void addDestination(String id, String location, String country, int ratingStars, PopularityLevel popularityLevel);

}
