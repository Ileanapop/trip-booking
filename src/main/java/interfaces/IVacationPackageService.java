package interfaces;

import Util.ExceptionHandler.OperationStatus;
import api.models.VacationPackageModel;
import entity.TravelAgency.Destination;
import entity.TravelAgency.VacationPackage;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IVacationPackageService {

    OperationStatus addVacationPackage(String name, Double price, LocalDate startDate, LocalDate endDate, String extraSpecifications, int capacity, int bookings,Destination destination);
    OperationStatus editVacationPackage(String id,String name, Double price, LocalDate startDate, LocalDate endDate, String extraSpecifications, int capacity);
    List<VacationPackageModel> selectVacationPackages();
    OperationStatus deleteVacationPackage(String id);
    List<VacationPackageModel> getAvailablePackages();
    List<VacationPackageModel> filterPackagesByDestination(String location);
    List<VacationPackageModel> filterPackagesByDPrice(Double price);
    List<VacationPackageModel> filterPackagesByPeriod(LocalDate startDate, LocalDate endDate);
}
