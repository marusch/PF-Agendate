package edu.egg.agendate.entidades;

import edu.egg.agendate.roles.Rol;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column
    @NotBlank(message = "Debe ingresar un email")
    @Email
    private String email;

    @Column
    @NotBlank(message = "Debe ingresar una contraseña")
    private String contraseña;

    @Transient
    @NotBlank(message = "Debe confirmar su contraseña")
    private String confirmarContraseña;

    @Enumerated(EnumType.STRING)
    protected Rol rol;

    public Usuario() {
    }

    public Usuario(Long id, String email, String contraseña, String confirmarContraseña, Rol rol) {
        this.id = id;
        this.email = email;
        this.contraseña = contraseña;
        this.confirmarContraseña = confirmarContraseña;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getConfirmarContraseña() {
        return confirmarContraseña;
    }

    public void setConfirmarContraseña(String confirmarContraseña) {
        this.confirmarContraseña = confirmarContraseña;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
}
