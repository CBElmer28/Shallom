package org.cahuas.webapp.servelet.cabeceras.models.modelo;
import java.util.Date;
import java.util.List;

public class Venta {
    private int id;
    private int idUsuario; // ID del usuario que hizo la venta
    private Date fecha; // Fecha de la venta
    private String estado; // Estado de la venta (por ejemplo, 'Entregado')
    private double total; // Total de la venta

    // Lista de detalles de la venta
    private List<DetalleVenta> detalles;

    // Constructor vacío
    public Venta() {}

    // Constructor con parámetros
    public Venta(int id, int idUsuario, Date fecha, String estado, double total) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    // Método toString() para depuración
    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                ", total=" + total +
                '}';
    }
}
