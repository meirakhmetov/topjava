package kz.meiir.topjava.model;

/**
 * @author Meiir Akhmetov on 08.08.2022
 */
public class AbstractNamedEntity extends AbstractBaseEntity{

    protected String name;

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name=name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString()+'('+name+')';
    }
}
