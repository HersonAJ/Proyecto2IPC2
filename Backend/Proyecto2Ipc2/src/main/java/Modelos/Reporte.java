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

public class Reporte {
    private int idReporte;
    private String tipoReporte;
    private String intervaloTiempo;
    private int idUsuario;
    private Date fechaReporte;

    // Constructor
    public Reporte(int idReporte, String tipoReporte, String intervaloTiempo, int idUsuario, Date fechaReporte) {
        this.idReporte = idReporte;
        this.tipoReporte = tipoReporte;
        this.intervaloTiempo = intervaloTiempo;
        this.idUsuario = idUsuario;
        this.fechaReporte = fechaReporte;
    }

    // Getters y Setters
    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getIntervaloTiempo() {
        return intervaloTiempo;
    }

    public void setIntervaloTiempo(String intervaloTiempo) {
        this.intervaloTiempo = intervaloTiempo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }
}

