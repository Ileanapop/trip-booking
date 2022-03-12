package service;

import Util.Mapper;
import api.models.DestinationModel;
import entity.TravelAgency.Destination;
import entity.TravelAgency.PopularityLevel;
import interfaces.IDestinationRepository;
import interfaces.IDestinationService;
import repository.DestinationRepository;
import service.validators.TravelAgencyData.LocationValidator;
import service.validators.TravelAgencyData.RatingValidator;

import java.util.List;

public class DestinationService implements IDestinationService {

    private final IDestinationRepository destinationRepository = new DestinationRepository();
    @Override
    public List<DestinationModel> getDestinations() {
        return Mapper.dstListEntitiesToDstModelList(destinationRepository.getDestinations());
    }

    @Override
    public boolean deleteDestination(String id) {

        if(getDestinationById(id) == null){
            return false;
        }
        return destinationRepository.deleteDestination(id);
    }

    @Override
    public void addDestination(String location, String country, int ratingStars, PopularityLevel popularityLevel) {
        LocationValidator locationVlidator = new LocationValidator();
        RatingValidator positiveNumberValidator = new RatingValidator();

        if(locationVlidator.isNewLocation(destinationRepository.getDestinations(),location) && positiveNumberValidator.isValidNumber(ratingStars)){
            destinationRepository.addDestination(IDGeneratorService.generateUniqueID(), location,country,ratingStars,popularityLevel);
        }
    }

    public Destination getDestinationById(String id){
        List<Destination> destinationList = destinationRepository.getDestinations();
        for(Destination destination: destinationList)
            if(destination.getId().equals(id))
                return destination;
        return null;
    }
}
