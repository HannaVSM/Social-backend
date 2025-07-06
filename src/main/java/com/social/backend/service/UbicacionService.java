package com.social.backend.service;

import com.social.backend.model.Ubicacion;
import com.social.backend.repository.UbicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    public UbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public List<Ubicacion> obtenerPorUbiCodUbica(String ubiCodUbica) {
        return ubicacionRepository.findByUbiCodUbica(ubiCodUbica);
    }
}
