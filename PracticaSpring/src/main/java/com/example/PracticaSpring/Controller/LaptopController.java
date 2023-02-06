package com.example.PracticaSpring.Controller;

import com.example.PracticaSpring.Entity.Laptop;
import com.example.PracticaSpring.Repositories.LaptopRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    //Atributos
    private  LaptopRepository laptopRepository;
    private final Logger log = LoggerFactory.getLogger(LaptopController.class); // se debe crear el objeto para log

    //contructores

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    //CRUD

    //Obtener todas las laptops
    //@PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/laptops")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    //Obtener un solo laptop por id
    @GetMapping("/laptops/{id}")
    public ResponseEntity<Laptop> findById(@PathVariable Long id){
        Optional<Laptop> obtLaptop = laptopRepository.findById(id);
        if(obtLaptop.isPresent())
            return ResponseEntity.ok(obtLaptop.get());
        else
            return ResponseEntity.notFound().build();


    }


    //Crear una laptop
    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        //@RequestBody indica que la petidicion se dara ne el contenido del objeto, es decir se insertara dentro de la abse de datos
        System.out.println(headers.get("User-Agent"));// esta linea muestra de donde se hace la peticion
        //ahora se crea un control para saber si existe  el id del libro que se desea guardar
        if(laptop.getId() != null){
            log.warn("se a intentado crear un libro con id, cuando se hace automatico");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(laptop);
    }

    //Actualizar una laptop
    @PutMapping("api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        //en esta seccion se invierten los papeles de guardar ya que en esta se va a actualizar y sera
        // necesario que exista un id
        if(laptop.getId()== null){
            log.warn("No existe id para actualizar");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    //eliminar una sola laptop
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> DeleteOne(@PathVariable Long id){
        //se debe mirar si existe el id para eliminar
        if(!laptopRepository.existsById(id)){
            log.warn("No existe id para eliminar");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    //Eliminar cada laptop
    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        log.warn("Se ha eliminado todos los laptops");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
