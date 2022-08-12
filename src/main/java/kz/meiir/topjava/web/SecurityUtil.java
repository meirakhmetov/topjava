package kz.meiir.topjava.web;

import static kz.meiir.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

/**
 * @author Meiir Akhmetov on 08.08.2022
 */
public class SecurityUtil {

    private static int id =1;

    public static int authUserId(){
        return 1;
    }
    public static int authUserCaloriesPerDay(){
        return DEFAULT_CALORIES_PER_DAY;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }
}
