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
public class MeGusta {
    private int idMeGusta;
    private int idUsuario;
    private int idRevista;
    private Date fechaMeGusta;

    // Getters y Setters

    public int getIdMeGusta() {
        return idMeGusta;
    }

    public void setIdMeGusta(int idMeGusta) {
        this.idMeGusta = idMeGusta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public Date getFechaMeGusta() {
        return fechaMeGusta;
    }

    public void setFechaMeGusta(Date fechaMeGusta) {
        this.fechaMeGusta = fechaMeGusta;
    }
}

