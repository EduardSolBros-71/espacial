package com.eduardsol.espacial.services.implementations;

import com.eduardsol.espacial.entity.SeriePelicula;
import com.eduardsol.espacial.repositories.SeriePeliculaRepository;
import com.eduardsol.espacial.services.SeriePeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeriePeliculaServiceImpl implements SeriePeliculaService {

    @Autowired
    private SeriePeliculaRepository seriePeliculaRepository;

    // Obtener SeriePelicula por ID (caché)
    @Override
    @Cacheable(value = "seriesPeliculas", key = "#id")
    public Optional<SeriePelicula> getById(Long id) {
        return seriePeliculaRepository.findById(id);
    }

    // Obtener todas las series o películas con paginación
    @Override
    public Page<SeriePelicula> findAll(Pageable pageable) {
        return seriePeliculaRepository.findAll(pageable);
    }

    // Buscar Series o Películas por nombre (paginación y caché)
    @Override
    @Cacheable(value = "seriesPeliculas", key = "#nombre")
    public Page<SeriePelicula> getByName(String nombre, Pageable pageable) {
        return seriePeliculaRepository.findByNombre(nombre, pageable);
    }

    // Guardar una Serie o Película y actualizar el caché
    @Override
    @CachePut(value = "seriesPeliculas", key = "#seriePelicula.id")
    public SeriePelicula save(SeriePelicula seriePelicula) {
        return seriePeliculaRepository.save(seriePelicula);
    }

    // Eliminar una Serie o Película (evict del caché)
    @Override
    @CacheEvict(value = "seriesPeliculas", key = "#id")
    public Optional<SeriePelicula> delete(Long id) {
        Optional<SeriePelicula> seriePelicula = seriePeliculaRepository.findById(id);

        if (seriePelicula.isPresent()) {
            seriePeliculaRepository.deleteById(id);
        }

        return seriePelicula;
    }

    // Actualizar una Serie o Película y actualizar el caché
    @Override
    @CachePut(value = "seriesPeliculas", key = "#seriePelicula.id")
    public Optional<SeriePelicula> update(SeriePelicula seriePelicula) {
        Optional<SeriePelicula> serieExistente = seriePeliculaRepository.findById(seriePelicula.getId());

        if (serieExistente.isPresent()) {
            seriePeliculaRepository.save(seriePelicula);
        }

        return serieExistente;
    }
}