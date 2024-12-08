package org.cahuas.webapp.servelet.cabeceras.models.modelo;

import java.util.Date;
import java.util.List;

/**
 * Clase que representa una venta en el sistema. Una venta está asociada a un usuario,
 * contiene detalles sobre la venta, su estado, fecha y total.
 * 
 * @author Team Shalom
 * @version 1.7
 */
public class Venta {

    // Identificador único de la venta
    private int id;
    
    // ID del usuario que realizó la venta
    private int idUsuario;
    
    // Fecha en que se realizó la venta
    private Date fecha;
    
    // Estado de la venta (por ejemplo, 'Entregado')
    private String estado;
    
    // Total de la venta
    private double total;

    // Lista de detalles asociados a la venta
    private List<DetalleVenta> detalles;

    /**
     * Constructor vacío de la clase Venta.
     */
    public Venta() {}

    /**
     * Constructor con parámetros para crear una venta con los atributos principales.
     *
     * @param id El identificador único de la venta.
     * @param idUsuario El ID del usuario que realizó la venta.
     * @param fecha La fecha de la venta.
     * @param estado El estado de la venta (por ejemplo, 'Entregado').
     * @param total El total de la venta.
     */
    public Venta(int id, int idUsuario, Date fecha, String estado, double total) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }

    /**
     * Obtiene el identificador de la venta.
     *
     * @return El identificador de la venta.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la venta.
     *
     * @param id El identificador de la venta.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el ID del usuario que realizó la venta.
     *
     * @return El ID del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario que realizó la venta.
     *
     * @param idUsuario El ID del usuario que realizó la venta.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene la fecha en que se realizó la venta.
     *
     * @return La fecha de la venta.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la venta.
     *
     * @param fecha La fecha de la venta.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el estado de la venta (por ejemplo, 'Entregado').
     *
     * @return El estado de la venta.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la venta (por ejemplo, 'Entregado').
     *
     * @param estado El estado de la venta.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el total de la venta.
     *
     * @return El total de la venta.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Establece el total de la venta.
     *
     * @param total El total de la venta.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Obtiene la lista de detalles asociados a la venta.
     *
     * @return La lista de detalles de la venta.
     */
    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    /**
     * Establece la lista de detalles de la venta.
     *
     * @param detalles La lista de detalles de la venta.
     */
    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    /**
     * Método toString() para representar la venta como una cadena.
     *
     * @return Una representación en cadena de la venta.
     */
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
