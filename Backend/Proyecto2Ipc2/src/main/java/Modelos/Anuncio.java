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
    private byte[] imagen;  // Ahora almacenará la imagen en formato binario
    private String imagenBase64;
    private String video;   // URL del video (para anuncios de video)
    private int idUsuario;
    private Date fechaInicio; // Fecha de inicio de vigencia del anuncio (manual)
    private Date fechaFin; // Fecha de fin de vigencia del anuncio (manual)
    private String estado; // Estado del anuncio (Activo, Inactivo)

    // Constructor vacío
    public Anuncio() {
    }

    // Constructor con parámetros
    public Anuncio(int idAnuncio, String tipo, String contenido, byte[] imagen, String video, int idUsuario, Date fechaInicio, Date fechaFin, String estado) {
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
    
    public String getImagenBase64(){
        return imagenBase64;
    }
    
    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }

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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
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

    // Método para obtener la duración del anuncio
    public String getDuracion() {
        long diffInMillies = Math.abs(fechaFin.getTime() - fechaInicio.getTime());
        long diff = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies, java.util.concurrent.TimeUnit.MILLISECONDS);

        if (diff == 1) return "1 día";
        else if (diff == 3) return "3 días";
        else if (diff == 7) return "1 semana";
        else if (diff == 14) return "2 semanas";
        else return "Duración no válida";
    }
}


