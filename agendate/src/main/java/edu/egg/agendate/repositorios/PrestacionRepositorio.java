
package edu.egg.agendate.repositorios;

import edu.egg.agendate.entidades.Prestacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestacionRepositorio extends JpaRepository<Prestacion, Long>{
    
    
    
}
