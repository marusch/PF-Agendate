package edu.egg.agendate.controladores;

import edu.egg.agendate.entidades.Turno;
import edu.egg.agendate.servicios.ClienteServicio;
import edu.egg.agendate.servicios.TurnoServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TurnoControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private TurnoServicio turnoServicio;

    @GetMapping("/lista-turnos")
    public String listarTurnos(Model modelo) {
        List<Turno> turnos = turnoServicio.listaTurno();
        modelo.addAttribute("turnos", turnos);
        return "lista-turnos";
    }

    @GetMapping("/registro-turnos")
    public String crearTurnoFormulario(Model modelo) {
        modelo.addAttribute("turno", new Turno());

        return "registro-turnos";
    }

    @PostMapping("/registro-turnos")
    public String guardarTurno(@Validated Turno turno, BindingResult bindingResult, HttpSession session, RedirectAttributes redirect, Model modelo) {

        if (turnoServicio.validacionTurno(turno)) {

            redirect.addFlashAttribute("msgError", "Fecha y hora no disponibles");
            return "redirect:registro-turnos";

        } else if (bindingResult.hasErrors()) {

            modelo.addAttribute("turno", turno);
            return "registro-turnos";

        } else {

            turnoServicio.guardarTurno(turno, session);
            redirect.addFlashAttribute("msgExito", "El turno ha sido agregado con exito");
            return "redirect:lista-turnos";
        }
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
        Turno turno = turnoServicio.obtenerTurnoPorId(id);
        modelo.addAttribute("turno", turno);

        return "registro-turnos";
    }

    @PostMapping("/{id}/editar")
    public String actualizarTurno(@PathVariable Long id, @Validated Turno turno,
            BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {

        Turno turnoExistente = turnoServicio.obtenerTurnoPorId(id);

        if (turnoServicio.validacionTurno(turno)) {
            redirect.addFlashAttribute("msgError", "Fecha y hora no disponibles");
            return "redirect:";

        } else if (bindingResult.hasErrors()) {
            modelo.addAttribute("turno", turno);
            return "registro-turnos";
        } else {

            turnoExistente.setFecha(turno.getFecha());
            turnoExistente.setHora(turno.getHora());
            turnoExistente.getNombreUsuario();
            turnoExistente.getApellidoUsuario();
            turnoExistente.getEmailUsuario();
            turnoExistente.getTelefonoUsuario();

            turnoServicio.actualizarTurno(turnoExistente);
            redirect.addFlashAttribute("msgExito", "El turno ha sido actualizado correctamente");
            return "redirect:/lista-turnos";
        }
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarTurno(@PathVariable Long id, RedirectAttributes redirect) {
        turnoServicio.EliminarTurno(id);

        redirect.addFlashAttribute("msgExito", "El turno ha sido eliminado correctamente");
        return "redirect:/lista-turnos";
    }

}
