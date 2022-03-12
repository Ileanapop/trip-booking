package service.validators.TravelAgencyData;

public class RatingValidator {

    public boolean isValidNumber(int n){
        return (n>=0 && n<=5);
    }
}
