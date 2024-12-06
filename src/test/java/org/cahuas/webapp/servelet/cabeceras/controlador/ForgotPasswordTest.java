package org.cahuas.webapp.servelet.cabeceras.controlador;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.mail.Address;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;

import static org.mockito.Mockito.*;

class ForgotPasswordTest {

    private ForgotPassword forgotPasswordServlet;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        forgotPasswordServlet = new ForgotPassword();
    }

    @Test
    void testDoPost_SuccessfullySendsEmail() throws Exception {
        String email = "testuser@example.com";
        when(request.getParameter("email")).thenReturn(email);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/usuario/EnterOTP.jsp")).thenReturn(dispatcher);

        // Capturar la llamada al método Transport.send()
        ArgumentCaptor<MimeMessage> messageCaptor = ArgumentCaptor.forClass(MimeMessage.class);

        forgotPasswordServlet.doPost(request, response);

        // Verificar que el mensaje de correo electrónico fue enviado
        MimeMessage message = messageCaptor.getValue();
        verify(message).setFrom((Address) Mockito.any());
        verify(message).addRecipient(eq(Message.RecipientType.TO), Mockito.any());
        verify(message).setSubject("Codigo de recuperación de tu cuenta - Shalom");

        // Verificar que el contenido del correo electrónico contiene el OTP
        String content = message.getContent().toString();
        assertTrue(content.contains("Codigo de recuperación de tu cuenta"));
        assertTrue(content.contains("OTP"));

        // Verificar redirección al JSP de ingresar OTP
        verify(dispatcher).forward(request, response);

        // Verificar que el OTP y el email se almacenaron en la sesión
        verify(session).setAttribute("otp", Mockito.anyInt());
        verify(session).setAttribute("email", email);
    }

    @Test
    void testDoPost_InvalidEmail() throws Exception {
        String invalidEmail = "";
        when(request.getParameter("email")).thenReturn(invalidEmail);

        forgotPasswordServlet.doPost(request, response);

        // Verificar que no se envíe ningún correo ni se haga la redirección
        verify(response, never()).setContentType(anyString());
        verify(response, never()).setHeader(anyString(), anyString());
    }
}
