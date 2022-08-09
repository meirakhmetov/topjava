package kz.meiir.topjava.util;

import kz.meiir.topjava.model.AbstractBaseEntity;
import kz.meiir.topjava.util.exception.NotFoundException;

/**
 * @author Meiir Akhmetov on 08.08.2022
 */
public class ValidationUtil {

    public static <T> T checkNotFoundWithId(T object, int id){
        return checkNotFound(object, "id=" +id);
    }

    public static void checkNotFoundWithId(boolean found, int id){
        checkNotFound(found, "id=" +id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object!= null, msg);
        return object;
    }
    public static void checkNotFound(boolean found, String msg){
        if(!found){
            throw new NotFoundException("Not found entity with "+msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity){
        if(!entity.isNew()){
            throw new IllegalArgumentException(entity+" must be new (id==null");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id){
        // conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728266/548473)
        if(entity.isNew()){
            entity.setId(id);
        }else if(entity.getId() != id){
            throw new IllegalArgumentException(entity+" must be with id="+id);
        }
    }

}
