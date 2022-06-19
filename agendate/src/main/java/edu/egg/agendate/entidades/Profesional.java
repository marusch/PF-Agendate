package edu.egg.agendate.entidades;

import edu.egg.agendate.roles.Rol;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Profesional extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profesional_id")
    private Long id;

    @Column
    @NotBlank(message = "Debe ingresar un nombre")
    private String nombre;

    @Column
    @NotBlank(message = "Debe ingresar un apellido")
    private String apellido;

    @OneToMany
    private List<Prestacion> prestacion;

    public Profesional() {
        super();
    }

    public Profesional(Long id, String nombre, String apellido, List<Prestacion> prestacion, String email, String contraseña, String confirmarContraseña, Rol rol) {
        super(id, email, contraseña, confirmarContraseña, rol);
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.prestacion = prestacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Prestacion> getPrestacion() {
        return prestacion;
    }

    public void setPrestacion(List<Prestacion> prestacion) {
        this.prestacion = prestacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
