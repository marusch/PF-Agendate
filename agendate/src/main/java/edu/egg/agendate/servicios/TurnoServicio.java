package edu.egg.agendate.servicios;

import edu.egg.agendate.entidades.Cliente;
import edu.egg.agendate.entidades.Turno;
import edu.egg.agendate.repositorios.TurnoRepositorio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurnoServicio {

    @Autowired
    private TurnoRepositorio turnoRepositorio;

    @Autowired
    private JavaMailSender mailSender;

    public boolean validacionTurno(Turno turno) {

        if (turnoRepositorio.existsByHora(turno.getHora()) && turnoRepositorio.existsByFecha(turno.getFecha())) {

            return true;
        }
        return false;
    }

    @Transactional
    public Turno guardarTurno(Turno turnoDto, HttpSession session) {

        Turno turno = new Turno();
        turno.setFecha(turnoDto.getFecha());
        turno.setHora(turnoDto.getHora());
        Cliente cliente = (Cliente) session.getAttribute("usuariosession");
        String nombre = cliente.getNombre();
        turno.setNombreUsuario(nombre);
        String apellido = cliente.getApellido();
        turno.setApellidoUsuario(apellido);
        String email = cliente.getEmail();
        turno.setEmailUsuario(email);
        Long telefono = cliente.getTelefono();
        turno.setTelefonoUsuario(telefono);

        /*
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom("sistemadeturnosegg@gmail.com");
        mensaje.setTo(email);
        mensaje.setSubject("Confirmación de turno");
        mensaje.setText("Hola " + nombre + " su turno ha sido reservado para la fecha " + turnoDto.getFecha() + " ,hora " + turnoDto.getHora() );
        mailSender.send(mensaje);
         */
        return turnoRepositorio.save(turno);

    }

    @Transactional
    public void enviarMensaje(Turno turnoDto, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuariosession");
        String email = cliente.getEmail();
        String nombre = cliente.getNombre();
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom("sistemadeturnosegg@gmail.com");
        mensaje.setTo(email);
        mensaje.setSubject("Confirmación de turno");
        mensaje.setText("Hola " + nombre + " su turno ha sido reservado para la fecha " + turnoDto.getFecha() + " " + turnoDto.getHora());
        mailSender.send(mensaje);
    }

    @Transactional
    public Turno ModificarTurno(Long id, Turno turnoDto) {
        Turno turno = turnoRepositorio.findById(turnoDto.getId()).get();

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

    @Transactional
    public Turno actualizarTurno(Turno turno) {
        return turnoRepositorio.save(turno);
    }
}
