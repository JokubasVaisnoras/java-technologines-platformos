package lt.vu.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Passengers.findAll", query = "select p from Passengers as p")
})
public class Passengers {
    private Long id;

    public void setPassengerId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getPassengerId() {
        return id;
    }

    private String Name;

    @Basic
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private Integer age;

    @Basic
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String last_name;

    @Basic
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    private Planes planes;

    @ManyToOne
    public Planes getPlanes() {
        return planes;
    }

    public void setPlanes(Planes planes) {
        this.planes = planes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passengers that = (Passengers) o;
        return Objects.equals(id, that.id) && Objects.equals(Name, that.Name) && Objects.equals(age, that.age) && Objects.equals(last_name, that.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name, age, last_name);
    }
}
