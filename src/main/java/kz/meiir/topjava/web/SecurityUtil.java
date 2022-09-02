package kz.meiir.topjava.web;

import kz.meiir.topjava.model.AbstractBaseEntity;

import static kz.meiir.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

/**
 * @author Meiir Akhmetov on 08.08.2022
 */
public class SecurityUtil {

    private static int id = AbstractBaseEntity.START_SEQ;

    public static int authUserId(){
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static int authUserCaloriesPerDay(){
        return DEFAULT_CALORIES_PER_DAY;
    }
}
