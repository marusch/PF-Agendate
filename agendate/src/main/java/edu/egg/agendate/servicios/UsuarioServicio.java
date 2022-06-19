package edu.egg.agendate.servicios;

import edu.egg.agendate.entidades.Profesional;
import edu.egg.agendate.entidades.Usuario;
import edu.egg.agendate.repositorios.ProfesionalRepositorio;
import edu.egg.agendate.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import static java.util.Collections.singletonList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;

    /*
    @Transactional
    public void create(Usuario dto) {
        if (usuarioRepositorio.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("There is already a user associated with the email entered");
        }

        Usuario usuario = new Usuario();

        /*
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(encoder.encode(dto.getPassword()));

        if (dto.getRole() == null)
            user.setRole(roleRepository.findByName("USER").orElseThrow(() -> new IllegalArgumentException("Error")));
        
        usuarioRepositorio.save(usuario);

    }
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(email);
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());
            permisos.add(auth);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);

            /*
            session.setAttribute("id", usuario.getId());
            session.setAttribute("email", usuario.getEmail());
            session.setAttribute("rol", usuario.getRol());
            
            return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getContraseña(), singletonList(auth));
             */
            User user = new User(usuario.getEmail(), usuario.getContraseña(), permisos);

            return user;
        } else {
            return null;

        }
    }

}
