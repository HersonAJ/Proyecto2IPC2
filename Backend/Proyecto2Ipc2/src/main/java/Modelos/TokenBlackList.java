/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author herson
 */
import java.util.HashSet;
import java.util.Set;

public class TokenBlackList {
    private static Set<String> blacklist = new HashSet<>();

    public static void add(String token) {
        blacklist.add(token);
        System.out.println("Token añadido a la lista negra: " + token); // Log para verificar que se agrega el token
    }

    public static boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}

