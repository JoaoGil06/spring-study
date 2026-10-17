package com.joaogil.springcore.common;

public class SwimCoach implements Coach{


    public SwimCoach() {
        System.out.printf("In Constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Wim 1000 meters as warmup";
    }
}
