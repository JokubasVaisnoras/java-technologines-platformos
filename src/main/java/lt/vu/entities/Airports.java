package lt.vu.entities;

import javax.lang.model.element.Name;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Airports.findAll", query = "select a from Airports as a")
})
@Table(name = "Airports")
public class Airports {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
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
    private List<Planes> planes;

    @ManyToMany(mappedBy = "airports")
    public List<Planes> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Planes> planes) {
        this.planes = planes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airports airports = (Airports) o;
        return Objects.equals(id, airports.id) && Objects.equals(Name, airports.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name);
    }
}
