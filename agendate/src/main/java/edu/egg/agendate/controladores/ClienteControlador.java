package edu.egg.agendate.controladores;

import edu.egg.agendate.entidades.Cliente;
import edu.egg.agendate.servicios.ClienteServicio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
@RequestMapping("/seccion-clientes") //por ejemplo
public class ClienteControlador {
    
    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping
    public ModelAndView mostrarClientes(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("tabla-de-clientes");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("persons", clienteServicio.obtenerTodos());
        return mav;
    }

    @GetMapping("/formulario-creacion-clientes")
    public ModelAndView obtenerFormulario(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("clientes-form");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("cliente", inputFlashMap.get("cliente"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        } else {
            mav.addObject("cliente", new Cliente());
        }

        mav.addObject("action", "crear");
        return mav;
    }

    @GetMapping("/form/{id}")
    public ModelAndView obtenerForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("clientes-form");
        mav.addObject("person", clienteServicio.obtenerPorId(id));
        mav.addObject("action", "actualizar");
        return mav;
    }

    @PostMapping("/crear")
    public RedirectView create(Cliente clienteDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/clientes");

        try {
            clienteServicio.crear(clienteDto);
            attributes.addFlashAttribute("success", "La operacion se ha realizado exitosamente");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("cliente", clienteDto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/clientes/form");
        }

        return redirect;
    }

    @PostMapping("/actualizar-clientes")
    public RedirectView actualizar(Cliente clienteDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/clientes");
        clienteServicio.modificar(clienteDto);
        attributes.addFlashAttribute("success", "La operacion se ha realizado exitosamente");
        return redirect;
    }

    @PostMapping("/borrar/{id}")
    public RedirectView borrar(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/clientes");
        clienteServicio.borrarPorId(id);
        return redirect;
    }
}
