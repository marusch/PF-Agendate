package edu.egg.agendate.entidades;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turno_id")
    private Long id;

    @NotBlank(message = "Debe ingresar su nombre")
    private String nombre;

    @NotEmpty(message = "Debe ingresar su email")
    @Email
    private String email;

    @NotBlank(message = "Debe ingresar su celular")
    private String celular;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future
    @NotNull(message = "Debe ingresar su fecha de turno")
    private LocalDate fecha;

    @Column
    private LocalTime horaInicio;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Prestacion prestacion;

    public Turno() {
    }

    public Turno(Long id, String nombre, String email, String celular, LocalDate fecha, LocalTime horaInicio, Cliente cliente, Prestacion prestacion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.celular = celular;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
     
        this.cliente = cliente;
        this.prestacion = prestacion;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Prestacion getPrestacion() {
        return prestacion;
    }

    public void setPrestacion(Prestacion prestacion) {
        this.prestacion = prestacion;
    }
    
    

}
