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

@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		RequestDispatcher dispatcher = null;
		int otpvalue = 0;
		HttpSession mySession = request.getSession();
		
		if(email!=null || !email.equals("")) {
			// sending otp
			Random rand = new Random();
			otpvalue = rand.nextInt(1255650);

			String to = email;// change accordingly
			// Get the session object
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("shalomoficial12@gmail.com", "vvyhyxukcysumxcd");

				}
			});
			// compose message
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(email));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("Codigo de recuperación de tu cuenta - Shalom");
				// Mensaje en formato HTML para mayor personalización
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
						+ "<p class='footer'>Atentamente,<br>El equipo de Tu Empresa</p>"
						+ "</body>"
						+ "</html>";

				message.setContent(htmlMessage, "text/html; charset=utf-8");

				// send message
				Transport.send(message);
				System.out.println("Correo enviado con éxito");
			}

			catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			dispatcher = request.getRequestDispatcher("/usuario/EnterOTP.jsp");
			request.setAttribute("message","OTP is sent to your email id");
			//request.setAttribute("connection", conn);
			mySession.setAttribute("otp",otpvalue); 
			mySession.setAttribute("email",email); 
			dispatcher.forward(request, response);
			//request.setAttribute("status", "success");
		}
		
	}

}
