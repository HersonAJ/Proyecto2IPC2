/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.Date;

/**
 *
 * @author herson
 */

public class Edicion {
    
    private int idEdicion;
    private int idRevista;
    private String tituloEdicion;
    private Date fechaCreacion;
    private String estado; // Cambiado a String
    private byte[] archivoPdf;

    public Edicion() {
    }

    public Edicion(int idEdicion, int idRevista, String tituloEdicion, Date fechaCreacion, String estado, byte[] archivoPdf) {
        this.idEdicion = idEdicion;
        this.idRevista = idRevista;
        this.tituloEdicion = tituloEdicion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.archivoPdf = archivoPdf;
    }

    public int getIdEdicion() {
        return idEdicion;
    }

    public void setIdEdicion(int idEdicion) {
        this.idEdicion = idEdicion;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getTituloEdicion() {
        return tituloEdicion;
    }

    public void setTituloEdicion(String tituloEdicion) {
        this.tituloEdicion = tituloEdicion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getArchivoPdf() {
        return archivoPdf;
    }

    public void setArchivoPdf(byte[] archivoPdf) {
        this.archivoPdf = archivoPdf;
    }
}

