package com.projectsvadim.project3restapi.util;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String error){
        super(error);
    }
}
