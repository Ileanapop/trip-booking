package service.validators.TravelAgencyData;

import java.time.LocalDate;

public class DateValidator {

    public boolean validateDate(LocalDate startDate, LocalDate endDate){
        return startDate.isBefore(endDate);
    }

}
