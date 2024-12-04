/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cahuas.webapp.servelet.cabeceras.models.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Carro;


@WebListener
public class AplicacionListener implements  HttpSessionListener {
    private ServletContext servletContext;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro", carro);
    }
}
