package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ValidateOtpTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private ValidateOtp validateOtp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testService_CorrectOtp() throws Exception {
        // Configurar mock para OTP correcto
        when(request.getParameter("otp")).thenReturn("1234");
        when(session.getAttribute("otp")).thenReturn(1234);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/usuario/newPassword.jsp")).thenReturn(dispatcher);

        // Ejecutar el método
        validateOtp.service(request, response);

        // Verificar comportamiento
        verify(request).setAttribute("email", null); // Asegúrate de configurar el email si es necesario
        verify(request).setAttribute("status", "success");
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testService_WrongOtp() throws Exception {
        // Configurar mock para OTP incorrecto
        when(request.getParameter("otp")).thenReturn("1234");
        when(session.getAttribute("otp")).thenReturn(5678);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/usuario/EnterOTP.jsp")).thenReturn(dispatcher);

        // Ejecutar el método
        validateOtp.service(request, response);

        // Verificar comportamiento
        verify(request).setAttribute("message", "wrong otp");
        verify(dispatcher).forward(request, response);
    }
}
