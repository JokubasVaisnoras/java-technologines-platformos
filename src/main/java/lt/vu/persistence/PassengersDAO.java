package lt.vu.persistence;



import lt.vu.entities.Passengers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
@ApplicationScoped
public class PassengersDAO {

    @Inject
    private EntityManager em;

    public List<Passengers> loadAll() {
        return em.createNamedQuery("Passengers.findAll", Passengers.class).getResultList();
    }
    public void persist(Passengers passenger){
        this.em.persist(passenger);
    }

    public Passengers findOne(Long id){
        return em.find(Passengers.class, id);
    }

    public Passengers update(Passengers passenger){
        return em.merge(passenger);
    }
}
