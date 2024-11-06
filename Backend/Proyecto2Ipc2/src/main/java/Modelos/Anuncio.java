/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author herson
 */
import java.util.Date;

public class Anuncio {
    private int idAnuncio;
    private String tipo;
    private String contenido;
    private String imagen;  // URL o ruta de la imagen (para anuncios de texto e imagen)
    private String video;   // URL del video (para anuncios de video)
    private int idUsuario;
    private Date fechaInicio; // Fecha de inicio de vigencia del anuncio (manual)
    private Date fechaFin; // Fecha de fin de vigencia del anuncio (manual)
    private String estado; // Estado del anuncio (Activo, Inactivo)

    // Constructor vacío
    public Anuncio() {
    }

    // Constructor con parámetros
    public Anuncio(int idAnuncio, String tipo, String contenido, String imagen, String video, int idUsuario, Date fechaInicio, Date fechaFin, String estado) {
        this.idAnuncio = idAnuncio;
        this.tipo = tipo;
        this.contenido = contenido;
        this.imagen = imagen;
        this.video = video;
        this.idUsuario = idUsuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    // Getters y Setters

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

