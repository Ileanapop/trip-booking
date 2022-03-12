package service;

import java.util.UUID;

public class IDGeneratorService {

    public static String generateUniqueID(){
        return UUID.randomUUID().toString();
    }

}
