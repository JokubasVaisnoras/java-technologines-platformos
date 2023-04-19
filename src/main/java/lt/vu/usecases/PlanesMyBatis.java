package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.PlanesMapper;
import lt.vu.mybatis.model.Planes;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PlanesMyBatis {
    @Inject
    private PlanesMapper planesMapper;

    @Getter
    private List<Planes> allPlanes;

    @Getter @Setter
    private Planes planeToCreate = new Planes();

    @PostConstruct
    public void init() {
        this.loadAllPlanes();
    }

    private void loadAllPlanes() {
        this.allPlanes = planesMapper.selectAll();
    }

    @Transactional
    public String createPlanes() {
        planesMapper.insert(planeToCreate);
        return "/myBatis/planes?faces-redirect=true";
    }
}