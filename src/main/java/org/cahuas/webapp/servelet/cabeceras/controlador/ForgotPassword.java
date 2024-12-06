package org.cahuas.webapp.servelet.cabeceras.controlador;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet que maneja la recuperación de contraseñas mediante el envío de un código de recuperación (OTP)
 * al correo electrónico del usuario.
 *
 * @author Team Shalom
 * @version 1.5
 */
@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {

	/**
	 * Maneja la solicitud POST para recuperar la contraseña del usuario.
	 *
	 * Este método recibe el correo electrónico del usuario, genera un código OTP (One Time Password),
	 * y envía un correo con el código de recuperación. El correo es enviado utilizando el servicio SMTP
	 * de Gmail.
	 *
	 * @param request La solicitud HTTP recibida por el servlet.
	 * @param response La respuesta HTTP que el servlet enviará al cliente.
	 * @throws ServletException Si hay un error al procesar la solicitud.
	 * @throws IOException Si hay un error de entrada/salida.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtiene el correo electrónico de la solicitud
		String email = request.getParameter("email");
		RequestDispatcher dispatcher = null;
		int otpvalue = 0; // Valor del código OTP generado
		HttpSession mySession = request.getSession(); // Sesión HTTP para almacenar el OTP y el correo

		// Verifica que el correo no sea nulo ni vacío
		if(email != null && !email.equals("")) {
			// Genera un código OTP aleatorio
			Random rand = new Random();
			otpvalue = rand.nextInt(1255650);

			String to = email; // Dirección de correo del destinatario
			// Configuración de propiedades para la conexión SMTP
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// Configura la sesión de correo con la autenticación necesaria
			Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("shalomoficial12@gmail.com", "vvyhyxukcysumxcd");
				}
			});

			// Crea y envía el mensaje de correo electrónico con el código OTP
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(email));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("Codigo de recuperación de tu cuenta - Shalom");

				// Mensaje en formato HTML con el código OTP
				String htmlMessage = "<!DOCTYPE html>"
						+ "<html>"
						+ "<head>"
						+ "<style>"
						+ "body { font-family: Arial, sans-serif; line-height: 1.6; }"
						+ ".header { font-size: 24px; color: #333; }"
						+ ".code { font-size: 20px; color: #0056b3; font-weight: bold; }"
						+ ".footer { font-size: 14px; color: #777; margin-top: 20px; }"
						+ "</style>"
						+ "</head>"
						+ "<body>"
						+ "<p class='header'>Hola,</p>"
						+ "<p>Recibimos una solicitud para recuperar la contraseña de tu cuenta asociada al correo: <strong>" + email + "</strong>.</p>"
						+ "<p>Por favor, utiliza el siguiente código para continuar con el proceso de recuperación:</p>"
						+ "<p class='code'>" + otpvalue + "</p>"
						+ "<p>Si no solicitaste este cambio, ignora este mensaje. Tu cuenta está segura.</p>"
						+ "<p class='footer'>Atentamente,<br>El equipo de Shalom</p>"
						+ "</body>"
						+ "</html>";

				// Establece el contenido del mensaje como HTML
				message.setContent(htmlMessage, "text/html; charset=utf-8");

				// Envía el mensaje
				Transport.send(message);
				System.out.println("Correo enviado con éxito");
			} catch (MessagingException e) {
				// Maneja cualquier excepción de envío de correo
				throw new RuntimeException(e);
			}

			// Redirige al usuario a la página para ingresar el OTP
			dispatcher = request.getRequestDispatcher("/usuario/EnterOTP.jsp");
			request.setAttribute("message", "OTP is sent to your email id");
			mySession.setAttribute("otp", otpvalue); // Almacena el OTP en la sesión
			mySession.setAttribute("email", email); // Almacena el correo en la sesión
			dispatcher.forward(request, response); // Redirige a la página correspondiente
		}
	}
}