package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Passengers;
import lt.vu.entities.Planes;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PassengersDAO;
import lt.vu.persistence.PlanesDAO;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class PassengersUC {
    @Inject
    private PassengersDAO passengersDAO;
    @Inject
    private PlanesDAO planesDAO;

    @Getter @Setter
    private Passengers passengerToCreate = new Passengers();

    @Getter
    private List<Passengers> allPassengers;
    @Getter@Setter
    private Planes plane;


    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long planeId = Long.parseLong(requestParameters.get("planeId"));
        this.plane = planesDAO.findOne(planeId);
    }

    @Transactional
    @LoggedInvocation
    public void createPassenger() {
        passengerToCreate.setPlanes(this.plane);
        passengersDAO.persist(passengerToCreate);
    }

}