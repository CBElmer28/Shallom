package org.cahuas.webapp.servelet.cabeceras.models.services;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utilidad para manejar operaciones relacionadas con contraseñas, como encriptación y verificación.
 * <p>
 * Utiliza la biblioteca BCrypt para garantizar un alto nivel de seguridad en las contraseñas.
 * </p>
 * 
 * @author Team Shalom
 * @version 1.5
 */
public class PasswordUtil {

    /**
     * Genera un hash seguro para la contraseña proporcionada.
     * 
     * @param password La contraseña en texto plano que se desea encriptar.
     * @return Un hash encriptado de la contraseña.
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Verifica si una contraseña en texto plano coincide con su hash almacenado.
     * 
     * @param plainPassword La contraseña en texto plano a verificar.
     * @param hashedPassword El hash almacenado contra el cual se verificará.
     * @return {@code true} si la contraseña coincide con el hash; de lo contrario, {@code false}.
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
