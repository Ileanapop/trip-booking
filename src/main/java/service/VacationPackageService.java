package service;

import Util.ExceptionHandler.OperationStatus;

import Util.Mapper;
import api.models.VacationPackageModel;
import entity.TravelAgency.Destination;
import entity.TravelAgency.VacationPackage;
import interfaces.IVacationPackageRepository;
import interfaces.IVacationPackageService;
import repository.VacationPackageRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VacationPackageService implements IVacationPackageService {

    private final IVacationPackageRepository vacationPackageRepository = new VacationPackageRepository();

    @Override
    public OperationStatus addVacationPackage(String name, Double price, LocalDate startDate, LocalDate endDate, String extraSpecifications, int capacity, int bookings,Destination destination) {
        return  vacationPackageRepository.addVacationPackage(IDGeneratorService.generateUniqueID(),name,
                price,startDate,endDate,extraSpecifications,capacity,bookings, destination);

    }

    @Override
    public OperationStatus editVacationPackage(String id,String name, Double price,LocalDate startDate, LocalDate endDate, String extraSpecifications, int capacity) {
        return vacationPackageRepository.editVacationPackage(id,name,price,startDate,endDate,extraSpecifications,capacity);
    }

    @Override
    public List<VacationPackageModel> selectVacationPackages() {
        return Mapper.packageEntityListToPackageModelList(vacationPackageRepository.selectVacationPackages());
    }

    @Override
    public OperationStatus deleteVacationPackage(String id) {
        return vacationPackageRepository.deleteVacationPackage(id);
    }

    @Override
    public List<VacationPackageModel> getAvailablePackages() {
        List<VacationPackage> vacationPackageList = vacationPackageRepository.selectVacationPackages();
        List<VacationPackageModel> availableVacationPackages = new ArrayList<>();

        for(VacationPackage vacationPackage:vacationPackageList){
            if(vacationPackage.getBookings()<vacationPackage.getPeopleCapacity()){
                availableVacationPackages.add(Mapper.entityVacationPackageToModelVacationPackage(vacationPackage));
            }
        }
        return availableVacationPackages;
    }

    @Override
    public List<VacationPackageModel> filterPackagesByDestination(String location) {

        List<VacationPackageModel> filteredPackages = new ArrayList<>();
        List<VacationPackageModel> vacationPackageModels = Mapper.packageEntityListToPackageModelList(vacationPackageRepository.selectVacationPackages());

        for(VacationPackageModel vacationPackageModel:vacationPackageModels){
            if(vacationPackageModel.getDestinationModel().getLocation().equals(location))
                filteredPackages.add(vacationPackageModel);
        }
        return filteredPackages;
    }

    @Override
    public List<VacationPackageModel> filterPackagesByDPrice(Double price) {
        List<VacationPackageModel> filteredPackages = new ArrayList<>();
        List<VacationPackageModel> vacationPackageModels = Mapper.packageEntityListToPackageModelList(vacationPackageRepository.selectVacationPackages());

        for(VacationPackageModel vacationPackageModel:vacationPackageModels){
            if(vacationPackageModel.getPrice() < price)
                filteredPackages.add(vacationPackageModel);
        }
        return filteredPackages;
    }

    @Override
    public List<VacationPackageModel> filterPackagesByPeriod(LocalDate startDate, LocalDate endDate) {
        List<VacationPackageModel> filteredPackages = new ArrayList<>();
        List<VacationPackageModel> vacationPackageModels = Mapper.packageEntityListToPackageModelList(vacationPackageRepository.selectVacationPackages());

        for(VacationPackageModel vacationPackageModel:vacationPackageModels){
            if(vacationPackageModel.getStartDate().isAfter(startDate) && vacationPackageModel.getEndDate().isBefore(endDate))
                filteredPackages.add(vacationPackageModel);
        }
        return filteredPackages;
    }
}
