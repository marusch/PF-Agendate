package edu.egg.agendate.controladores;

import edu.egg.agendate.entidades.Profesional;
import edu.egg.agendate.servicios.ProfesionalServicio;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/profesional")
public class ProfesionalControlador {
    
    @Autowired
    private ProfesionalServicio profServicio;
    
    
   //tabla para mostrar todos
    @GetMapping
    public ModelAndView mostrarProfesionales(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("profesionales", profServicio.mostrarTodos());
        return mav;
    }

    // /form para crear Profesional
    @GetMapping("/formulario_profesional")
    public ModelAndView obtenerForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("registro-prof-form");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("profesional", inputFlashMap.get("profesional"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        } else {
            mav.addObject("profesional", new Profesional());
        }

        mav.addObject("action", "crear");
        return mav;
    }

    // /form/id para modificar Profesional
    @GetMapping("/formulario_profesional/{id}")
    public ModelAndView obtenerForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("registro-prof-form");
        mav.addObject("profesional", profServicio.mostrarPorId(id));
        mav.addObject("action", "modificar");
        return mav;
    }

    // /crear
    @PostMapping("/crear")
    public RedirectView crear(Profesional profDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/login");

        try {
            profServicio.crear(profDto);
            attributes.addFlashAttribute("success", "El registro se ha realizado con éxito.");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("profesional", profDto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/profesional/formulario_profesional");
        }

        return redirect;
    }

    // /modificar
    @PostMapping("/modificar")
    public RedirectView modificar(Profesional profDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/profesional");
        profServicio.modificar(profDto);
        attributes.addFlashAttribute("success", "operación realizada con éxito");
        return redirect;
    }

    // /borrar/id
    @PostMapping("/borrar/{id}")
    public RedirectView borrar(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/profesional");
        profServicio.borrarPorId(id);
        return redirect;
    }
    
}
