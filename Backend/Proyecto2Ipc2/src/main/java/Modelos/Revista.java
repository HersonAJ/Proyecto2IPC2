/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.Date;
import java.util.List;

/**
 *
 * @author herson
 */

public class Revista {
    
    private int idRevista;
    private String titulo;
    private String descripcion;
    private String categoria;
    private int idUsuario;
    private String estado;
    private double costoPorDia;
    private List<Tag> tags;
    private boolean permiteComentarios; // Nuevo atributo permiteComentarios
    private boolean permiteMegusta; // Nuevo atributo permiteMegusta
    private boolean permiteSuscripcion; // Nuevo atributo permiteSuscripcion
    private Date fechaCreacion;

    public Revista() {
    }

    public Revista(int idRevista, String titulo, String descripcion, String categoria, int idUsuario, String estado, double costoPorDia, boolean permiteComentarios, boolean permiteMegusta, boolean permiteSuscripcion, Date fechaCreacion) {
        this.idRevista = idRevista;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.costoPorDia = costoPorDia;
        this.permiteComentarios = permiteComentarios;
        this.permiteMegusta = permiteMegusta;
        this.permiteSuscripcion = permiteSuscripcion;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getCostoPorDia() {
        return costoPorDia;
    }

    public void setCostoPorDia(double costoPorDia) {
        this.costoPorDia = costoPorDia;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public boolean isPermiteComentarios() {
        return permiteComentarios;
    }

    public void setPermiteComentarios(boolean permiteComentarios) {
        this.permiteComentarios = permiteComentarios;
    }

    public boolean isPermiteMegusta() {
        return permiteMegusta;
    }

    public void setPermiteMegusta(boolean permiteMegusta) {
        this.permiteMegusta = permiteMegusta;
    }

    public boolean isPermiteSuscripcion() {
        return permiteSuscripcion;
    }

    public void setPermiteSuscripcion(boolean permiteSuscripcion) {
        this.permiteSuscripcion = permiteSuscripcion;
    }
    
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Revista{" +
                "idRevista=" + idRevista +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", idUsuario=" + idUsuario +
                ", estado='" + estado + '\'' +
                ", costoPorDia=" + costoPorDia +
                ", permiteComentarios=" + permiteComentarios +
                ", permiteMegusta=" + permiteMegusta +
                ", permiteSuscripcion=" + permiteSuscripcion +
                ", tags=" + tags +
                '}';
    }
}