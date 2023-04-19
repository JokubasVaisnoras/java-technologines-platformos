package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Airports;
import lt.vu.entities.Planes;
import lt.vu.persistence.AirportsDAO;
import lt.vu.persistence.PlanesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
@Model
public class AirportUC {

    @Inject
    private AirportsDAO airportsDAO;

    @Getter
    private List<Airports> allAirports;

    @PostConstruct
    public void init(){
        loadAllAirports();
    }

    private void loadAllAirports() {this.allAirports = airportsDAO.loadAll();}

}
