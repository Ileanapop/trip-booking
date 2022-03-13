package Util;

import api.models.DestinationModel;
import api.models.PopularityLevelModel;
import api.models.UserModel;
import api.models.VacationPackageModel;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import entity.TravelAgency.Destination;
import entity.TravelAgency.PopularityLevel;
import entity.TravelAgency.VacationPackage;
import entity.Users.User;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static UserModel entityUserToModelUser(User user){
        return new UserModel(user.getId(), user.getFirstName(),
                user.getLastName(), user.getPassword(), user.getEmail(),
                user.getUsername(), packageEntityListToPackageModelList(user.getPackages()));
    }

    public static DestinationModel entityDstToModelDst(Destination destination){
        return new DestinationModel(destination.getId(),
                destination.getLocation(),
                destination.getCountry(),
                destination.getRatingStars(),
                destination.getPopularityLevel());
    }

    public static VacationPackageModel entityVacationPackageToModelVacationPackage(VacationPackage vacationPackage)
    {
        return new VacationPackageModel(vacationPackage.getId(),
                vacationPackage.getName(),vacationPackage.getPrice(),
                vacationPackage.getStartDate(),vacationPackage.getEndDate(),
                vacationPackage.getExtraSpecifications(),
                vacationPackage.getPeopleCapacity(), vacationPackage.getBookings(),entityDstToModelDst(vacationPackage.getDestination()));
    }

    public static List<DestinationModel> dstListEntitiesToDstModelList(List<Destination> destinationList){
        List<DestinationModel> destinationModels = new ArrayList<>();

        for(Destination destination: destinationList){
            destinationModels.add(entityDstToModelDst(destination));
        }
        return destinationModels;
    }

    public static PopularityLevel popularityLevelModelToEntityPopularityLevelModel(PopularityLevelModel popularityLevelModel){
        if(popularityLevelModel == PopularityLevelModel.HIGH)
            return PopularityLevel.HIGH;
        if(popularityLevelModel == PopularityLevelModel.LOW)
            return PopularityLevel.LOW;
        return PopularityLevel.MEDIUM;
    }

    public static Destination destinationModelToEntityModel(DestinationModel destinationModel){
        return new Destination(destinationModel.getId(),
                destinationModel.getLocation(),
                destinationModel.getCountry(),
                destinationModel.getRatingStars(),
                destinationModel.getPopularityLevel());
    }

    public static List<VacationPackageModel> packageEntityListToPackageModelList(List<VacationPackage> vacationPackageList){
        List<VacationPackageModel> vacationPackageModels = new ArrayList<>();

        for(VacationPackage vacationPackage:vacationPackageList){
            vacationPackageModels.add(entityVacationPackageToModelVacationPackage(vacationPackage));
        }
        return vacationPackageModels;
    }


}
