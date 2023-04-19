package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;

import lt.vu.persistence.PlanesDAO;
import lt.vu.entities.Planes;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PlanesUC {
    @Inject
    private PlanesDAO planesDAO;

    @Getter @Setter
    private Planes planeToCreate = new Planes();
    @Getter @Setter
    private String planeToSearchFor = "";

    @Getter
    private List<Planes> allPlanes;

    @Getter
    private List<Planes> searchedPlanes;


    @PostConstruct
    public void init(){
        loadAllPlanes();
    }

    @Transactional
    public void createPlane(){
        this.planesDAO.persist(planeToCreate);
    }

    private void loadAllPlanes(){
        this.allPlanes = planesDAO.loadAll();
    }
    @Transactional
    public void loadSearchedPlane(){
        this.searchedPlanes = planesDAO.loadSearch(planeToSearchFor);
    }


}
