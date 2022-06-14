package edu.egg.agendate.servicios;

import edu.egg.agendate.entidades.Cliente;
import edu.egg.agendate.entidades.Turno;
import edu.egg.agendate.repositorios.TurnoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurnoServicio {

    @Autowired
    private TurnoRepositorio turnoRepositorio;

    @Transactional
    public Turno guardarTurno(Turno turno, Cliente cliente) { 
        turno.setCliente(cliente);
       
        return turnoRepositorio.save(turno);

    }

    @Transactional
    public Turno ModificarTurno(Long id, Turno turnoDto, Cliente cliente) {
        Turno turno = turnoRepositorio.findById(turnoDto.getId()).get();
        turno.setCliente(cliente);
       

        return turnoRepositorio.save(turno);
    }

    public void EliminarTurno(Long id) {
        turnoRepositorio.deleteById(id);
    }

    @Transactional
    public List<Turno> listaTurno() {
        return turnoRepositorio.findAll();
    }

    @Transactional
    public Turno obtenerTurnoPorId(Long id) {
        return turnoRepositorio.findById(id).get();
    }
}
