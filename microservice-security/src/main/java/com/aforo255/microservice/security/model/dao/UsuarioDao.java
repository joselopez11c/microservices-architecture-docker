package com.aforo255.microservice.security.model.dao;

import com.aforo255.microservice.security.model.entity.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

}
