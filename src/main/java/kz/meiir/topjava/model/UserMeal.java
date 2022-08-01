package kz.meiir.topjava.model;

import java.time.LocalDateTime;

/**
 * @author Meiir Akhmetov on 01.08.2022
 */
public class UserMeal {
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;

    public UserMeal(LocalDateTime dateTime, String description, int calories){
        this.dateTime=dateTime;
        this.description=description;
        this.calories=calories;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }

    public String getDescription(){
        return description;
    }

    public int getCalories(){
        return calories;
    }

}
