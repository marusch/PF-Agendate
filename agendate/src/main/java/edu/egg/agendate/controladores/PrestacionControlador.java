
package edu.egg.agendate.controladores;

import edu.egg.agendate.entidades.Prestacion;
import edu.egg.agendate.servicios.PrestacionServicio;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;


@Controller
@RequestMapping("/prestaciones")
public class PrestacionControlador {
   
  @Autowired
    private PrestacionServicio prestacionServicio;
  
     //tabla para mostrar todas las prestaciones

    @GetMapping("/lista-prestaciones")
    public String listarTurnos(Model modelo) {
        List<Prestacion> prestacion = prestacionServicio.mostrarTodos();
        modelo.addAttribute("prestacion", prestacion);
        return "lista-prestaciones";
    }
    
}
