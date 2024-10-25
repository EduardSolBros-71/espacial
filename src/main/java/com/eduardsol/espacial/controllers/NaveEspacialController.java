package com.eduardsol.espacial.controllers;

import com.eduardsol.espacial.entity.NaveEspacial;
import com.eduardsol.espacial.services.NaveEspacialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@RestController
@RequestMapping("/api/naves")
public class NaveEspacialController {

    @Autowired
    private NaveEspacialService naveEspacialService;

    @GetMapping("/{id}")
    public ResponseEntity<NaveEspacial> getById(@PathVariable Long id) {
        Optional<NaveEspacial> nave = naveEspacialService.getById(id);
        return nave.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<NaveEspacial>> findAll(Pageable pageable) {
        Page<NaveEspacial> naves = naveEspacialService.findAll(pageable);
        return new ResponseEntity<>(naves, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<NaveEspacial>> getByName(
            @RequestParam("nombre") String nombre, Pageable pageable) {
        Page<NaveEspacial> naves = naveEspacialService.getByName(nombre, pageable);
        return new ResponseEntity<>(naves, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NaveEspacial> createNave(@RequestBody NaveEspacial naveEspacial) {
        NaveEspacial savedNave = naveEspacialService.save(naveEspacial);
        return new ResponseEntity<>(savedNave, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNave(@PathVariable Long id) {
        Optional<NaveEspacial> deletedNave = naveEspacialService.delete(id);
        if (deletedNave.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<NaveEspacial> updateNave(
            @PathVariable Long id, @RequestBody NaveEspacial updatedNaveEspacial) {
        Optional<NaveEspacial> updatedNave = naveEspacialService.update(updatedNaveEspacial);
        return updatedNave.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
