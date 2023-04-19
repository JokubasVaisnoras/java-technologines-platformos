package lt.vu.persistence;

import lt.vu.entities.Planes;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PlanesDAO {

    @Inject
    private EntityManager em;

    public List<Planes> loadAll() {
        return em.createNamedQuery("Planes.findAll", Planes.class).getResultList();
    }
    public void persist(Planes plane){
        this.em.persist(plane);
    }

    public Planes findOne(Long id){
        return em.find(Planes.class, id);
    }

    public Planes update(Planes plane){
        return em.merge(plane);
    }
    public List loadSearch(String s) {

        return em.createQuery(
                        "select p " +
                                "from Planes as p " +
                                "where p.airline like :airlines")
                .setParameter("airlines", s == null ? "" : "%" + s + "%").
                getResultList();
    }
}
