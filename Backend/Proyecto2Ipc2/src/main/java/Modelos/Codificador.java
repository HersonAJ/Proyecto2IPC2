/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.Base64;

/**
 *
 * @author herson
 */
public class Codificador {
    
    public static String codificar(String contraseña){
        return Base64.getEncoder().encodeToString(contraseña.getBytes());
    }
    
    public static String decodificar(String contraseñaCodificada){
        byte[] bytesDecodificados = Base64.getDecoder().decode(contraseñaCodificada);
        return new String(bytesDecodificados);
    }
    
}
