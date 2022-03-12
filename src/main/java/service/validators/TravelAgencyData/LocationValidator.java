package service.validators.TravelAgencyData;

import entity.TravelAgency.Destination;

import java.util.List;

public class LocationValidator {

    public boolean isNewLocation(List<Destination> destinationList,String location){
        for(Destination destination:destinationList){
            if(destination.getLocation().equals(location))
                return false;
        }
        return true;
    }
}
