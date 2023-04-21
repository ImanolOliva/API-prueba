package com.example.challenge.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String nombre;

    @NotNull
    @NotBlank
    private String apellido;

    @NotNull
    @NotBlank
    private String fechaNacimiento;
    @NotNull
    @NotBlank
    private String direccion;


    private Integer telefono;
    @NotNull
    @NotBlank
    private String pais;

    public Persona(){

    }
    public Persona(Long id, String nombre, String apellido, String fechaNacimiento, String direccion, Integer telefono, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.pais = pais;
    }

    public Long getId() {
        return id;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return
                 "nombre='" + nombre + '\n' +
                ", apellido='" + apellido + '\n' +
                ", fechaNacimiento='" + fechaNacimiento + '\n' +
                ", direccion='" + direccion + '\n' +
                ", telefono=" + telefono + '\n' +
                ", pais='" + pais + '\n';
    }
}
