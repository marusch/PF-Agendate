package edu.egg.agendate.repositorios;

import edu.egg.agendate.entidades.Turno;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepositorio extends JpaRepository<Turno, Long> {
    
    /*
    @Query("Select r from turno r where r.hora_inicio = :horaInicio")
     Optional<Turno> findByHoraInicio(@Param("horaInicio") String horaInicio);
     */
    
    boolean existsByHora(String hora);
    boolean existsByFecha(LocalDate fecha);
}

