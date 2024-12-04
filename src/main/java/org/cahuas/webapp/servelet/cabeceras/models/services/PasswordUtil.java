package org.cahuas.webapp.servelet.cabeceras.models.services;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // Generar un hash de la contraseña
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Verificar una contraseña con el hash almacenado
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
