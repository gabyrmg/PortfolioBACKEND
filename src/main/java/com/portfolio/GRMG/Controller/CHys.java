package com.portfolio.GRMG.Controller;

import com.portfolio.GRMG.Dto.DtoHys;
import com.portfolio.GRMG.Entity.HyS;
import com.portfolio.GRMG.Security.Controller.Mensaje;
import com.portfolio.GRMG.Service.SHyS;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://grmgfront.web.app")
@CrossOrigin(origins = {"https://grmgfront.web.app","http://localhost:4200"})

public class CHys {

    @Autowired
    SHyS sHys;

    @GetMapping("/lista")
    public ResponseEntity<List<HyS>> list() {
        List<HyS> list = sHys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<HyS> getById(@PathVariable("id") int id) {
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.NOT_FOUND);
        }
        HyS hys = sHys.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHys dtohys) {
        if (StringUtils.isBlank(dtohys.getNombreH())) {
            return new ResponseEntity(new Mensaje("Es obligatorio colocar una habilidad"), HttpStatus.BAD_REQUEST);
        }
        if (sHys.existsByNombreH(dtohys.getNombreH())) {
            return new ResponseEntity(new Mensaje("Habilidad existente"), HttpStatus.BAD_REQUEST);
        }

        HyS hys = new HyS(dtohys.getNombreH(), dtohys.getPorcentaje());
        sHys.save(hys);
        return new ResponseEntity(new Mensaje("Habilidad agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHys dtohys) {
        //se valida si existe id
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.BAD_REQUEST);
        }
        //compara nombre de habilidades
        if (sHys.existsByNombreH(dtohys.getNombreH()) && sHys.getByNombreH(dtohys.getNombreH()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Habilidad existente"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtohys.getNombreH())) {
            return new ResponseEntity(new Mensaje("Es obligatorio colocar habilidad"), HttpStatus.BAD_REQUEST);
        }
        

        /*  if (StringUtils.isBlank(dtohys.getPorcentaje())) {
            return new ResponseEntity(new Mensaje("Es obligatorio colocar habilidad"), HttpStatus.BAD_REQUEST);
        }*/
        HyS hys = sHys.getOne(id).get();
        hys.setNombreH(dtohys.getNombreH());
        hys.setPorcentaje((dtohys.getPorcentaje()));

        sHys.save(hys);
        return new ResponseEntity(new Mensaje("Habilidad actualizada"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //se valida si existe id
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.NOT_FOUND);
        }

        sHys.delete(id);
        return new ResponseEntity(new Mensaje("Habilidad eliminada"), HttpStatus.OK);

    }

}
