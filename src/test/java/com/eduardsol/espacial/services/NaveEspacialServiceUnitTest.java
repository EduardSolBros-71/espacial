package com.eduardsol.espacial.services;

import com.eduardsol.espacial.entity.NaveEspacial;
import com.eduardsol.espacial.repositories.NaveEspacialRepository;
import com.eduardsol.espacial.services.implementations.NaveEspecialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NaveEspacialServiceUnitTest {

    @Mock
    private NaveEspacialRepository naveEspacialRepository;

    @InjectMocks
    private NaveEspecialServiceImpl naveEspacialService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks
    }

    @Test
    public void testGetByName() {
        Pageable pageable = PageRequest.of(0, 5);
        // Lista simulada de Naves Espaciales
        List<NaveEspacial> naves = Arrays.asList(
                new NaveEspacial(1L, "X-Wing", 6, Collections.emptyList()),
                new NaveEspacial(2L, "Y-Wing", 2, Collections.emptyList())
        );
        Page<NaveEspacial> page = new PageImpl<>(naves, pageable, naves.size());

        when(naveEspacialRepository.findByNombreContaining("Wing", pageable)).thenReturn(page);

        Page<NaveEspacial> result = naveEspacialService.getByName("Wing", pageable);

        verify(naveEspacialRepository, times(1)).findByNombreContaining("Wing", pageable);

        assertEquals(2, result.getTotalElements());
    }
}
