package com.eduardsol.espacial.controllers;


import com.eduardsol.espacial.entity.SeriePelicula;
import com.eduardsol.espacial.services.SeriePeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/series-peliculas")
public class SeriePeliculaController {

    @Autowired
    private SeriePeliculaService seriePeliculaService;

    @GetMapping("/{id}")
    public ResponseEntity<SeriePelicula> getById(@PathVariable Long id) {
        Optional<SeriePelicula> seriePelicula = seriePeliculaService.getById(id);
        return seriePelicula.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<SeriePelicula>> findAll(Pageable pageable) {
        Page<SeriePelicula> seriesPeliculas = seriePeliculaService.findAll(pageable);
        return new ResponseEntity<>(seriesPeliculas, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<SeriePelicula>> getByName(
            @RequestParam("nombre") String nombre, Pageable pageable) {
        Page<SeriePelicula> seriesPeliculas = seriePeliculaService.getByName(nombre, pageable);
        return new ResponseEntity<>(seriesPeliculas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SeriePelicula> createSeriePelicula(@RequestBody SeriePelicula seriePelicula) {
        SeriePelicula savedSeriePelicula = seriePeliculaService.save(seriePelicula);
        return new ResponseEntity<>(savedSeriePelicula, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeriePelicula(@PathVariable Long id) {
        Optional<SeriePelicula> deletedSeriePelicula = seriePeliculaService.delete(id);
        if (deletedSeriePelicula.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeriePelicula> updateSeriePelicula(
            @PathVariable Long id, @RequestBody SeriePelicula updatedSeriePelicula) {
        Optional<SeriePelicula> updatedSerie = seriePeliculaService.update(updatedSeriePelicula);
        return updatedSerie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
