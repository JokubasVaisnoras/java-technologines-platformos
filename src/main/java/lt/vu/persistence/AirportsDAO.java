package lt.vu.persistence;

import lt.vu.entities.Airports;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
@ApplicationScoped
public class AirportsDAO {
    @Inject
    private EntityManager em;

    public List<Airports> loadAll() {
        return em.createNamedQuery("Airports.findAll", Airports.class).getResultList();
    }
    public void persist(Airports airport){
        this.em.persist(airport);
    }

    public Airports findOne(Long id){
        return em.find(Airports.class, id);
    }

    public Airports update(Airports airport){
        return em.merge(airport);
    }
}

