package interfaces;

import Util.ExceptionHandler.OperationStatus;
import api.models.VacationPackageModel;
import entity.TravelAgency.Destination;
import entity.TravelAgency.VacationPackage;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IVacationPackageRepository {
    OperationStatus addVacationPackage(String id, String name, Double price, LocalDate startDate, LocalDate endDate, String extraSpecifications, int capacity, int bookings,Destination destination);
    OperationStatus editVacationPackage(String id, String name, Double price, LocalDate startDate, LocalDate endDate, String extraSpecifications, int capacity);
    List<VacationPackage> selectVacationPackages();
    OperationStatus deleteVacationPackage(String id);

}
