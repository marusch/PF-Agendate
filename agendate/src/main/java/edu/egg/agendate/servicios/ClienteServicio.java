package edu.egg.agendate.servicios;

import edu.egg.agendate.entidades.Cliente;
import edu.egg.agendate.repositorios.ClienteRepositorio;
import edu.egg.agendate.roles.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServicio {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public boolean validarContraseña(Cliente cliente) {

        if (!cliente.getContraseña().equals(cliente.getConfirmarContraseña())) {
            return true;
        }
        return false;
    }

    @Transactional
    public void crear(Cliente clienteDto) {
        if (clienteRepositorio.existsByEmail(clienteDto.getEmail())) {
            throw new IllegalArgumentException("Ya existe un cliente registrado con ese email.");
        }

        if (validarContraseña(clienteDto)) {
            throw new IllegalArgumentException("Las contraseñas deben coincidir, vuelva a intentarlo.");
        }

        Cliente cliente = new Cliente();

        cliente.setId(clienteDto.getId());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setContraseña(passwordEncoder.encode(clienteDto.getContraseña()));
        cliente.setConfirmarContraseña(clienteDto.getConfirmarContraseña());
        cliente.setRol(Rol.USER);
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellido(clienteDto.getApellido());
        cliente.setTelefono(clienteDto.getTelefono());

        clienteRepositorio.save(cliente);
    }

    @Transactional
    public void modificar(Cliente clienteDto) {
        Cliente cliente = clienteRepositorio.findById(clienteDto.getId()).get();

        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellido(clienteDto.getApellido());

        clienteRepositorio.save(cliente);
    }

    @Transactional(readOnly = true)
    public Cliente obtenerPorId(Long id) {
        return clienteRepositorio.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Cliente> obtenerTodos() {
        return clienteRepositorio.findAll();
    }

    @Transactional
    public void borrarPorId(Long id) {
        clienteRepositorio.deleteById(id);
    }

}
