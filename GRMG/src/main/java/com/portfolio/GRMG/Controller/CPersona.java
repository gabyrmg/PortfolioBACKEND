package com.portfolio.GRMG.Controller;

import com.portfolio.GRMG.Dto.DtoPersona;
import com.portfolio.GRMG.Entity.Persona;
import com.portfolio.GRMG.Security.Controller.Mensaje;
import com.portfolio.GRMG.Service.SPersona;
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
@RequestMapping("persona")
@CrossOrigin(origins = {"http://localhost:4200"/*,"https:grmgfrontend.web.app"*/})
public class CPersona {
    @Autowired
    SPersona sPersona;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = sPersona.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id) {
       if(!sPersona.existsById(id))
           return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.NOT_FOUND);
       Persona persona = sPersona.getOne(id).get();
       return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    

  /* @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoPersona dtoperso) {
        if ((StringUtils.isBlank(dtoperso.getNombre())) || (StringUtils.isBlank(dtoperso.getApellido())) ) {
            return new ResponseEntity(new Mensaje("Es obligatorio colocar un nombre y un apellido"), HttpStatus.BAD_REQUEST);
        }
        if (sPersona.existsByNombre(dtoperso.getNombre())) {
            return new ResponseEntity(new Mensaje("Nombre existente"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = new Persona(dtoperso.getNombre(),dtoperso.getApellido(),dtoperso.getTitulo(), dtoperso.getDescripcion(),dtoperso.getImg());
        sPersona.save(persona);
        return new ResponseEntity(new Mensaje("Persona agregada"), HttpStatus.OK);
    }*/

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoPersona dtoperso) {
        //se valida si existe id
        if (!sPersona.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.BAD_REQUEST);
        }
        //compara nombre de persona
        if (sPersona.existsByNombre(dtoperso.getNombre()) && sPersona.getByNombre(dtoperso.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Nombre existente"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtoperso.getNombre())) {
            return new ResponseEntity(new Mensaje("Es obligatorio colocar un nombre"), HttpStatus.BAD_REQUEST);
        }
        
        //compara apellido de persona
        if (sPersona.existsByNombre(dtoperso.getApellido()) && sPersona.getByNombre(dtoperso.getApellido()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Apellido existente"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtoperso.getApellido())) {
            return new ResponseEntity(new Mensaje("Es obligatorio colocar un apellido"), HttpStatus.BAD_REQUEST);
        }
        
        //compara descripcion
        if (sPersona.existsByNombre(dtoperso.getDescripcion()) && sPersona.getByNombre(dtoperso.getDescripcion()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Descripción existente"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtoperso.getDescripcion())) {
            return new ResponseEntity(new Mensaje("Es obligatorio colocar una descripción"), HttpStatus.BAD_REQUEST);
        }
        
         
        //compara nombre titulo
        if (sPersona.existsByNombre(dtoperso.getTitulo()) && sPersona.getByNombre(dtoperso.getTitulo()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Título existente"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtoperso.getTitulo())) {
            return new ResponseEntity(new Mensaje("Es obligatorio colocar un título"), HttpStatus.BAD_REQUEST);
        }
        
        //compara nombre de imagen
        if (sPersona.existsByNombre(dtoperso.getImg()) && sPersona.getByNombre(dtoperso.getImg()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Imagen existente"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtoperso.getImg())) {
            return new ResponseEntity(new Mensaje("Es obligatorio colocar una imagen"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = sPersona.getOne(id).get();
        persona.setNombre(dtoperso.getNombre());
        persona.setApellido(dtoperso.getApellido());
        persona.setTitulo(dtoperso.getTitulo());
        persona.setDescripcion((dtoperso.getDescripcion()));
        persona.setImg(dtoperso.getImg());

        sPersona.save(persona);
        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);

    }
    
   /* @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //se valida si existe id
        if (!sPersona.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.NOT_FOUND);
        }

        sPersona.delete(id);
        return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);

    }*/
    
    
}
