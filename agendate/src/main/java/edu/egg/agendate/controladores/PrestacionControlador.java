/*
package edu.egg.agendate.controladores;

import edu.egg.agendate.servicios.PrestacionServicio;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;


@Controller
@RequestMapping("")
public class PrestacionControlador {
    
  @Autowired
    private PrestacionServicio prestacionServicio;
  
     //tabla para mostrar todas las prestaciones
    @GetMapping
    public ModelAndView mostrarPrestaciones(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("prestaciones", prestacionServicio.mostrarTodos());
        return mav;
    }

    
}
*/