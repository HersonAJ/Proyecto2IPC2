/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author herson
 */
public class Cobro {
    private int idCobro;
    private Date fechaCobro;
    private BigDecimal montoTotal;
    private String detalle;

    // Constructores, getters y setters
    public Cobro() {}

    public Cobro(int idCobro, Date fechaCobro, BigDecimal montoTotal, String detalle) {
        this.idCobro = idCobro;
        this.fechaCobro = fechaCobro;
        this.montoTotal = montoTotal;
        this.detalle = detalle;
    }

    public int getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(int idCobro) {
        this.idCobro = idCobro;
    }

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}

