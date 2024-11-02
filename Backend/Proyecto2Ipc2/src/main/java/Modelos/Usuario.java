/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.List;

/**
 *
 * @author herson
 */
public class Usuario {
    
    private int idUsuario;
    private String nombre;
    private String email;
    private String contraseña;
    private byte[] fotoPerfil;
    private String descripcion;
    private List<String> hobbies;
    private List<String> temasInteres;
    private String tipoUsuario;
    private double saldoCartera;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String email, String contraseña, byte[] fotoPerfil, String descripcion, List<String> hobbies, List<String> temasInteres, String tipoUsuario, double saldoCartera) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.fotoPerfil = fotoPerfil;
        this.descripcion = descripcion;
        this.hobbies = hobbies;
        this.temasInteres = temasInteres;
        this.tipoUsuario = tipoUsuario;
        this.saldoCartera = saldoCartera;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public List<String> getTemasInteres() {
        return temasInteres;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public double getSaldoCartera() {
        return saldoCartera;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public void setTemasInteres(List<String> temasInteres) {
        this.temasInteres = temasInteres;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setSaldoCartera(double saldoCartera) {
        this.saldoCartera = saldoCartera;
    }
    
    void actualizarPerfil (String nombre, String descripcion, List<String> hobbies, List<String> temasInteres){
        
    }
    
    void recargarCartera(double monto){
        
    }
    
    boolean esAdmin(){
      return true;   
    }
     
    boolean esEditor(){
      return true;   
    }
    
    boolean esSuscriptor(){
      return true;   
    }    
}
