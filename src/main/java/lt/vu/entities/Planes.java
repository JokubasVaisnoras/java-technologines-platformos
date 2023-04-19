package lt.vu.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Planes.findAll", query = "select p from Planes as p")
})
@Table(name = "PLANES")
public class Planes {
    @Id
    @GeneratedValue
    private Long id;
    public void setPlaneId(Long id) {
        this.id = id;
    }

    public Long getPlaneId() {
        return id;
    }
    @Basic
    private String model;


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    private String airline;

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    @OneToMany(mappedBy = "planes")
    private List<Passengers> passengers;

    public List<Passengers> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passengers> passengers) {
        this.passengers = passengers;
    }

    @ManyToMany
    private List<Airports> airports;

    public List<Airports> getAirports() {
        return airports;
    }

    public void setAirports(List<Airports> airports) {
        this.airports = airports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planes planes = (Planes) o;
        return Objects.equals(id, planes.id) && Objects.equals(model, planes.model) && Objects.equals(airline, planes.airline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, airline);
    }
}
