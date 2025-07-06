package com.social.backend.controller;

import com.social.backend.model.Propiedad;
import com.social.backend.repository.PropiedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/propiedades")
public class PropiedadController {

    @Autowired
    private PropiedadRepository propiedadRepository;

    @GetMapping
    public List<Propiedad> getAll() {
        System.out.println(propiedadRepository.findAll());
        return propiedadRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Propiedad> getById(@PathVariable String id) {
        return propiedadRepository.findById(id);
    }

    @PostMapping
    public Propiedad create(@RequestBody Propiedad propiedad) {
        return propiedadRepository.save(propiedad);
    }

    @PutMapping("/{id}")
    public Propiedad update(@PathVariable String id, @RequestBody Propiedad propiedad) {
        propiedad.setIdPropiedad(id);
        return propiedadRepository.save(propiedad);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        propiedadRepository.deleteById(id);
    }
}

