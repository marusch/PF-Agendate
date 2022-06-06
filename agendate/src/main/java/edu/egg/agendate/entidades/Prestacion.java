package edu.egg.agendate.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Prestacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prestacion_id")
    private Long id;

    @Column
    @NotBlank(message = "Debe ingresar un nombre")
    private String nombre;

    @Column
    @NotNull(message = "Debe completar este campo")
    private Long tiempoDuracion;
    
    
    private boolean isDisponible;
    
    @Column
    private Long precio;

    @ManyToOne
    private Profesional profesional;

    public Prestacion() {
    }

    public Prestacion(Long id, String nombre, Long tiempoDuracion, Long precio, Profesional profesional) {
        this.id = id;
        this.nombre = nombre;
        this.tiempoDuracion = tiempoDuracion;
        this.precio = precio;
        this.profesional = profesional;
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

    public Long getTiempoDuracion() {
        return tiempoDuracion;
    }

    public void setTiempoDuracion(Long tiempoDuracion) {
        this.tiempoDuracion = tiempoDuracion;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

}
