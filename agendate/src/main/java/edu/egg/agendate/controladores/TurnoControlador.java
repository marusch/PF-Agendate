package edu.egg.agendate.controladores;

import edu.egg.agendate.entidades.Cliente;
import edu.egg.agendate.entidades.Turno;
import edu.egg.agendate.servicios.ClienteServicio;
import edu.egg.agendate.servicios.TurnoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/turno")
public class TurnoControlador {

    @Autowired
    private TurnoServicio turnoServicio;

  

    @GetMapping("/turnos")
    public String listarTurnos(Model modelo) {
        List<Turno> turnos = turnoServicio.listaTurno();
        modelo.addAttribute("turnos", turnos);
        return "turnos";
    }

    @GetMapping("/turnos/nuevo")
    public String crearTurnoFormulario(Model modelo) {
        Turno turno = new Turno();
         modelo.addAttribute("turno", turno);
       
        return "crear_turno";
    }

    @PostMapping("/turnos")
    public String guardarTurno(@Validated Turno turno, Cliente cliente) { 
        turnoServicio.guardarTurno(turno, cliente); 
        return "redirect:/turnos";
    }

    @GetMapping("/turnos/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("turno", turnoServicio.obtenerTurnoPorId(id));
        return "editar_turno";
    }

    @PostMapping("/turnos/{id}")
    public String actualizarTurno(@PathVariable Long id, @ModelAttribute("turno") Turno turno, Cliente cliente,
        Model modelo) {
        Turno turnoExistente = turnoServicio.obtenerTurnoPorId(id);

        turnoExistente.setFecha(turno.getFecha());
        turnoExistente.setHoraInicio(turno.getHoraInicio());
        turnoExistente.setCliente(turno.getCliente());
      

        return "redirect:/turnos";
    }

    @GetMapping("/turnos/{id}")
    public String eliminarTurno(@PathVariable Long id, RedirectAttributes redirect) {
        turnoServicio.EliminarTurno(id);
        
        redirect.addFlashAttribute("msgExito", "El contacto ha sido eliminado correctamente");
        return "redirect:/turnos";
    }

}

