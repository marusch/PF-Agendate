package edu.egg.agendate.repositorios;

import edu.egg.agendate.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    public Usuario findByEmail(String email);
    
    boolean existsByEmail(String email);
    
}
