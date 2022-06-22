package edu.egg.agendate.servicios;

import edu.egg.agendate.entidades.Profesional;
import edu.egg.agendate.repositorios.ProfesionalRepositorio;
import edu.egg.agendate.roles.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfesionalServicio {

    @Autowired
    private ProfesionalRepositorio profesionalRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    

    public boolean validarContraseña(Profesional profesional) {

        if (!profesional.getContraseña().equals(profesional.getConfirmarContraseña())) {
            return true;
        }
        return false;
    }

    @Transactional
    public void crear(Profesional profDto) {
        if (profesionalRepo.existsByEmail(profDto.getEmail())) {
            throw new IllegalArgumentException("Ya existe un profesional registrado con ese email.");
        }

        if (validarContraseña(profDto)) {
            throw new IllegalArgumentException("Las contraseñas deben coincidir, vuelva a intentarlo.");
        }

        Profesional prof = new Profesional();

        //prof.setId(profDto.getId());
        prof.setEmail(profDto.getEmail());
        prof.setContraseña(passwordEncoder.encode(profDto.getContraseña()));
        prof.setConfirmarContraseña(profDto.getConfirmarContraseña());
        prof.setRol(Rol.USER);
        prof.setNombre(profDto.getNombre());
        prof.setApellido(profDto.getApellido());
        prof.setPrestacion(profDto.getPrestacion());

        profesionalRepo.save(prof);
    }

    @Transactional
    public void modificar(Profesional profDto) {
        Profesional prof = profesionalRepo.findById(profDto.getId()).get();

        prof.setNombre(profDto.getNombre());
        prof.setApellido(profDto.getApellido());
        prof.setPrestacion(profDto.getPrestacion());

        profesionalRepo.save(prof);

    }

    @Transactional(readOnly = true)
    public Profesional mostrarPorId(Long id) {
        return profesionalRepo.findById(id).get();

    }

    @Transactional(readOnly = true)
    public List<Profesional> mostrarTodos() {
        return profesionalRepo.findAll();
    }

    @Transactional
    public void borrarPorId(Long id) {
        profesionalRepo.deleteById(id);
    }

}
