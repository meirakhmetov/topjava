package kz.meiir.topjava.model;

import javafx.print.Collation;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

import static kz.meiir.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

/**
 * @author Meiir Akhmetov on 08.08.2022
 */
public class User extends AbstractNamedEntity{
    private String email;
    private String password;
    private boolean enabled = true;
    private Date registered = new Date();
    private Set<Role> roles;
    private int caloriesPerDay = DEFAULT_CALORIES_PER_DAY;

    public User(){}

    public User(User u){
        this(u.getId(),u.getName(), u.getEmail(), u.getPassword(), u.getCaloriesPerDay(), u.isEnabled(),u.getRegistered(), u.getRoles());
    }

    public User(Integer id, String name, String email, String password, Role role, Role...roles){
        this(id, name, email, password, DEFAULT_CALORIES_PER_DAY, true, new Date(), EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, int caloriesPerDay, boolean enabled, Date registered, Collection<Role>  roles){
        super(id, name);
        this.email=email;
        this.password=password;
        this.caloriesPerDay=caloriesPerDay;
        this.enabled=enabled;
        this.registered = registered;
        setRoles(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setRoles(Collection<Role> roles){
        this.roles = CollectionUtils.isEmpty(roles)?EnumSet.noneOf(Role.class):EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User(" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", caloriesPerDay=" + caloriesPerDay +
                ')';
    }
}
