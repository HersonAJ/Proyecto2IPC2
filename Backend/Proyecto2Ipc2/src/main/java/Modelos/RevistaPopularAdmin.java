/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author herson
 */
public class RevistaPopularAdmin {
    private int idRevista;
    private String titulo;
    private int totalSuscripciones;
    private String usuariosSuscritos;

    public RevistaPopularAdmin(int idRevista, String titulo, int totalSuscripciones, String usuariosSuscritos) {
        this.idRevista = idRevista;
        this.titulo = titulo;
        this.totalSuscripciones = totalSuscripciones;
        this.usuariosSuscritos = usuariosSuscritos;
    }

    // Getters y Setters

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

    public int getTotalSuscripciones() {
        return totalSuscripciones;
    }

    public void setTotalSuscripciones(int totalSuscripciones) {
        this.totalSuscripciones = totalSuscripciones;
    }

    public String getUsuariosSuscritos() {
        return usuariosSuscritos;
    }

    public void setUsuariosSuscritos(String usuariosSuscritos) {
        this.usuariosSuscritos = usuariosSuscritos;
    }
}

