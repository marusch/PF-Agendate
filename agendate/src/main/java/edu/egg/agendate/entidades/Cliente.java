package edu.egg.agendate.entidades;

import edu.egg.agendate.roles.Rol;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Cliente extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;

    @Column
    @NotBlank(message = "Debe ingresar un nombre")
    private String nombre;

    @Column
    @NotBlank(message = "Debe ingresar un apellido")
    private String apellido;

    @Column
    @NotNull(message = "Debe ingresar un telefono")
    private Long telefono;

    public Cliente() {
        super();
    }

    public Cliente(Long id, String nombre, String apellido, Long telefono, String email, String contraseña, String confirmarContraseña, Rol rol) {
        super(id, email, contraseña, confirmarContraseña, rol);
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
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

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
