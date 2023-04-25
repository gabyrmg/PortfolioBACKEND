package com.portfolio.GRMG.Service;

import com.portfolio.GRMG.Entity.HyS;
import com.portfolio.GRMG.Repository.RHyS;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SHyS {

    @Autowired
    RHyS rHys;

    public List<HyS> list() {
        return rHys.findAll();

    }

    public Optional<HyS> getOne(int id) {
        return rHys.findById(id);
    }

    public Optional<HyS> getByNombreH(String nombreH) {
        return rHys.findByNombreH(nombreH);
    }

    public void save(HyS hys) {
        rHys.save(hys);
    }

    public void delete(int id) {
        rHys.deleteById(id);
    }

    public boolean existsById(int id) {
        return rHys.existsById(id);

    }

    public boolean existsByNombreH(String nombreH) {
        return rHys.existsByNombreH(nombreH);

    }

}
