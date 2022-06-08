/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.agendate.servicios;

import edu.egg.agendate.entidades.Prestacion;
import edu.egg.agendate.repositorios.PrestacionRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rosi-PC
 */

@Service
public class PrestacionServicio {
    @Autowired
    private PrestacionRepositorio prestacionRepo;
    
    @Transactional
    public void crearPrestacion(Prestacion prestacionDto){
     if (prestacionRepo.existsByName(prestacionDto.getNombre()))
                throw new IllegalArgumentException("El email ingresado ya se encuentra registrado");
        
        Prestacion prestacion = new Prestacion();
        
        prestacion.setId(prestacionDto.getId());
        prestacion.setNombre(prestacionDto.getNombre());
        prestacion.setPrecio(prestacionDto.getPrecio());
        prestacion.setProfesional(prestacionDto.getProfesional());
        prestacion.setTiempoDuracion(prestacionDto.getTiempoDuracion());
        prestacionRepo.save(prestacion);
        
      
        
    }
    
        @Transactional
    public void modificar(Prestacion prestacionDto){
        Prestacion prestacion = prestacionRepo.findById(prestacionDto.getId()).get();
        
        
        prestacion.setNombre(prestacionDto.getNombre());
        prestacion.setPrecio(prestacionDto.getPrecio());
        prestacion.setProfesional(prestacionDto.getProfesional());
        prestacion.setTiempoDuracion(prestacionDto.getTiempoDuracion());
        prestacionRepo.save(prestacion);
        
    }
    
    @Transactional(readOnly = true)
    public Prestacion mostrarPorId(Long id){
        return prestacionRepo.findById(id).get();
        
    }
    
    
    @Transactional(readOnly = true)
    public List<Prestacion> mostrarTodos() {
        return prestacionRepo.findAll();
    }

}
