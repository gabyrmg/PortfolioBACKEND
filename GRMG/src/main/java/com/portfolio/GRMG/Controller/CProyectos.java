package com.portfolio.GRMG.Controller;

import com.portfolio.GRMG.Dto.DtoProyectos;
import com.portfolio.GRMG.Entity.Proyectos;
import com.portfolio.GRMG.Security.Controller.Mensaje;
import com.portfolio.GRMG.Service.SProyectos;
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
@RequestMapping("proyectos")
@CrossOrigin(origins = {"http://localhost:4200"/*,"https:grmgfrontend.web.app"*/})
public class CProyectos {

    @Autowired
    SProyectos sProyectos;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list() {
        List<Proyectos> list = sProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id) {
        if (!sProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.NOT_FOUND);
        }
        Proyectos proyectos = sProyectos.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyectos dtoproyec) {
        if (StringUtils.isBlank(dtoproyec.getNombreP())) {
            return new ResponseEntity(new Mensaje("Es obligatorio colocar un nombre de proyecto"), HttpStatus.BAD_REQUEST);
        }
        if (sProyectos.existsByNombreP(dtoproyec.getNombreP())) {
            return new ResponseEntity(new Mensaje("Proyecto existente"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyectos = new Proyectos(dtoproyec.getNombreP(), dtoproyec.getDescripcionP(), dtoproyec.getLinkP());
        sProyectos.save(proyectos);
        return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyectos dtoproyec) {
        //se valida si existe id
        if (!sProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.BAD_REQUEST);
        }
        //compara nombre de proyectos
        if (sProyectos.existsByNombreP(dtoproyec.getNombreP()) && sProyectos.getByNombreP(dtoproyec.getNombreP()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Proyecto existente"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtoproyec.getNombreP())) {
            return new ResponseEntity(new Mensaje("Es obligatorio colocar un proyecto"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyectos = sProyectos.getOne(id).get();
        proyectos.setNombreP(dtoproyec.getNombreP());
        proyectos.setDescripcionP((dtoproyec.getDescripcionP()));
        proyectos.setLinkP((dtoproyec.getLinkP()));

        sProyectos.save(proyectos);
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //se valida si existe id
        if (!sProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.NOT_FOUND);
        }

        sProyectos.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);

    }

}
