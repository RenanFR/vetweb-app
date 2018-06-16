package com.vetweb.dao.auth;

 import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.vetweb.model.auth.Usuario;


@Repository 
public class UsuarioDAO	{
	
    @PersistenceContext
    private EntityManager entityManager;

    public void salvar(Usuario usuario) {
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword())); 
        entityManager.persist(usuario);
    }
    
}
