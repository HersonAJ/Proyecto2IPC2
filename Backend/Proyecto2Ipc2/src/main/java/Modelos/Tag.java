/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author herson
 */
public class Tag {
    private int idTag;
    private String nombre;

    public Tag() {
    }

    public Tag(int idTag, String nombre) {
        this.idTag = idTag;
        this.nombre = nombre;
    }

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "idTag=" + idTag +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

