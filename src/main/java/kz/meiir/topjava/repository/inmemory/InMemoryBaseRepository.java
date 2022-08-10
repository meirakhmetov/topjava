package kz.meiir.topjava.repository.inmemory;

import kz.meiir.topjava.model.AbstractBaseEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Meiir Akhmetov on 09.08.2022
 */
@Repository
public class InMemoryBaseRepository<T extends AbstractBaseEntity> {

    private static AtomicInteger counter = new AtomicInteger(0);

    private Map<Integer,T> map = new ConcurrentHashMap<>();

    public T save(T entry){
        if(entry.isNew()){
            entry.setId(counter.incrementAndGet());
            map.put(entry.getId(),entry);
            return entry;
        }
        return map.computeIfPresent(entry.getId(), (id,oldT) -> entry);
    }

    public boolean delete(int id){
        return map.remove(id) !=null;
    }

    public T get(int id) {
        return map.get(id);
    }

    Collection<T> getCollection(){
        return map.values();
    }
}
