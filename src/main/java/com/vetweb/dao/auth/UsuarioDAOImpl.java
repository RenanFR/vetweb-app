package com.vetweb.dao.auth;
// @author 11151504898

import com.vetweb.model.auth.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImpl implements UserDetailsService {
    
	@PersistenceContext
    private EntityManager entityManager;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Usuario> usuarios = entityManager.createQuery("select u from Usuario u where u.username = :username", Usuario.class) 
                .setParameter("username", username).getResultList();
        if(usuarios.isEmpty())  throw new UsernameNotFoundException("Usuário " + username + " não encontrado.   ");
        return usuarios.get(0);
    }
    
}
