package edu.egg.agendate.entidades;

import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turno_id")
    private Long id;

    @Column
    private LocalDateTime fechaYHora;

    @Column
    private LocalTime horaInicio;

    @Column
    private LocalTime horaFin;
    
    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Prestacion prestacion;

    public Turno(Long id, LocalDateTime fecha, LocalTime fechaInicio, LocalTime fechaFin, Cliente cliente, Prestacion prestacion) {
        this.id = id;
        this.fechaYHora = fecha;
        this.horaInicio = fechaInicio;
        this.horaFin = fechaFin;
        this.cliente = cliente;
        this.prestacion = prestacion;
    }

    public Turno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fechaYHora;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fechaYHora = fechaYHora;
    }

    public LocalTime getFechaInicio() {
        return horaInicio;
    }

    public void setFechaInicio(LocalTime fechaInicio) {
        this.horaInicio = fechaInicio;
    }

    public LocalTime getFechaFin() {
        return horaFin;
    }

    public void setFechaFin(LocalTime fechaFin) {
        this.horaFin = fechaFin;
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
