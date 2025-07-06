package com.social.backend.service;

import com.social.backend.model.Amigo;
import com.social.backend.repository.AmigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class AmigoService {

    @Autowired
    private AmigoRepository amigoRepository;

    public List<Amigo> obtenerAmigosDe(String consecUser) {
        return amigoRepository.buscarAmigosDe(consecUser);
    }

    @Transactional
    public void crearAmigo(String consecUser, String consecUser2) {
        amigoRepository.insertarAmigo(consecUser, consecUser2);
    }
}

