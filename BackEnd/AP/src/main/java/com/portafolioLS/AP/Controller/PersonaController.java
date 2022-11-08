package com.portafolioLS.AP.Controller;

import com.portafolioLS.AP.Entity.Persona;
import com.portafolioLS.AP.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

    @Autowired IPersonaService iPersonaService;

    //La url le dice al frontend que tipo de método utilizar
    @GetMapping("/personas/traer")
    public List<Persona> getPersona() {
        return iPersonaService.getPersona();
    }

    //Para decirle al front qué debe guardar en la base de datos
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona) {

        iPersonaService.savePersona(persona);
        return "La persona fue creada";
    }

    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id) {

        iPersonaService.deletePersona(id);

        return "La persona fue eliminada";
    }

    //URL:PUERTO/personas/editar/4/nombre & apellido & img
    //editar persona
    @PutMapping("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
            @RequestParam("nombre") String nuevoNombre,
            @RequestParam("apellido") String nuevoApellido,
            @RequestParam("img") String nuevoImg) {

        Persona persona = iPersonaService.findPersona(id);

        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevoImg);

        iPersonaService.savePersona(persona);

        return persona;

    }

    
    @GetMapping("/personas/traer/perfil")
    public Persona findpersona(){
        return iPersonaService.findPersona((long)1);
    }

}
