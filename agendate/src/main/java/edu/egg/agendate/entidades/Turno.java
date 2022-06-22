package edu.egg.agendate.entidades;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turno_id")
    private Long id;

    @Column
    private String nombreUsuario;

    @Column
    private String apellidoUsuario;

    @Column
    private String emailUsuario;

    @Column
    private Long telefonoUsuario;

    @Column
    @NotNull(message = "Debe ingresar la fecha de turno")
    @DateTimeFormat(pattern="yyyy-MM-dd") 
    @Future
    private LocalDate fecha;

    @NotNull(message = "Debe ingresar la hora de turno")
    @Column
    private String hora;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Usuario usuario;

    /*
    @ManyToOne
    @Transient
    private Prestacion prestacion;
     */
    public Turno() {
    }

    public Turno(Long id, String nombreUsuario, String apellidoUsuario, String emailUsuario, Long telefonoUsuario, LocalDate fecha, String hora, Cliente cliente, Usuario usuario) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.emailUsuario = emailUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.cliente = cliente;
        this.usuario = usuario;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCliente(String a) {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public Long getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(Long telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

}
