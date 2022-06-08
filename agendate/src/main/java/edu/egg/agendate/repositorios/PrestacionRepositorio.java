
package edu.egg.agendate.repositorios;

import edu.egg.agendate.entidades.Prestacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestacionRepositorio extends JpaRepository<Prestacion, Long>{
    
        @Query("SELECT p FROM Prestacion p WHERE p.nombre LIKE :nombre")
        Optional<Prestacion> searchByNameParam(@Param("nombre") String nombre);
        
        @Query("SELECT p FROM Prestacion p WHERE p.nombre LIKE ?1")
        Optional<Prestacion> searchByName(String name);
        
        boolean existsByName(String nombre);
    
}
