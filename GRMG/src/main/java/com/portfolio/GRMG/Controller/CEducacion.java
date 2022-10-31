package com.portfolio.GRMG.Controller;


import com.portfolio.GRMG.Dto.DtoEducacion;
import com.portfolio.GRMG.Entity.Educacion;
import com.portfolio.GRMG.Security.Controller.Mensaje;
import com.portfolio.GRMG.Service.SEducacion;
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
@RequestMapping("/edu")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://grmgfront.web.app")

public class CEducacion {
    @Autowired
    SEducacion sEducacion;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
       if(!sEducacion.existsById(id))
           return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.NOT_FOUND);
       Educacion educacion = sEducacion.getOne(id).get();
       return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoedu) {
        if (StringUtils.isBlank(dtoedu.getNombreEd())) {
            return new ResponseEntity(new Mensaje("Es obligatorio colocar una instancia de educación"), HttpStatus.BAD_REQUEST);
        }
        if (sEducacion.existsByNombreEd(dtoedu.getNombreEd())) {
            return new ResponseEntity(new Mensaje("Instancia de educación existente"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion(dtoedu.getNombreEd(), dtoedu.getDescripcionEd());
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Instancia de educación agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducacion dtoedu) {
        //se valida si existe id
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.BAD_REQUEST);
        }
        //compara nombre de instancias de educacion
        if (sEducacion.existsByNombreEd(dtoedu.getNombreEd()) && sEducacion.getByNombreEd(dtoedu.getNombreEd()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Instancia de educación existente"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtoedu.getNombreEd())) {
            return new ResponseEntity(new Mensaje("Es obligatorio colocar una instancia de educación"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = sEducacion.getOne(id).get();
        educacion.setNombreEd(dtoedu.getNombreEd());
        educacion.setDescripcionEd((dtoedu.getDescripcionEd()));

        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Instancia de educación actualizada"), HttpStatus.OK);

    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //se valida si existe id
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.NOT_FOUND);
        }

        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Instancia de educación eliminada"), HttpStatus.OK);

    }
    
}
