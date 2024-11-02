/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

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
    private String estado; // Nuevo atributo estado

    public Revista() {
    }

    public Revista(int idRevista, String titulo, String descripcion, String categoria, int idUsuario, String estado) {
        this.idRevista = idRevista;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.idUsuario = idUsuario;
        this.estado = estado;
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

    @Override
    public String toString() {
        return "Revista{" +
                "idRevista=" + idRevista +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", idUsuario=" + idUsuario +
                ", estado='" + estado + '\'' +
                '}';
    }
}
