
package edu.egg.agendate.repositorios;

import edu.egg.agendate.entidades.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalRepositorio extends JpaRepository<Profesional, Long> {
    
    public Profesional findByEmail(String email);
    
    boolean existsByEmail(String email);
}
