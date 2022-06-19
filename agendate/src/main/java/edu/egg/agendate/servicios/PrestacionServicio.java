
package edu.egg.agendate.servicios;

import edu.egg.agendate.entidades.Prestacion;
import edu.egg.agendate.repositorios.PrestacionRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PrestacionServicio {
    @Autowired
    private PrestacionRepositorio prestacionRepo;
    
   
    
    @Transactional(readOnly = true)
    public Prestacion mostrarPorId(Long id){
        return prestacionRepo.findById(id).get();
        
    }
    
    
    @Transactional(readOnly = true)
    public List<Prestacion> mostrarTodos() {
        return prestacionRepo.findAll();
    }

}
 