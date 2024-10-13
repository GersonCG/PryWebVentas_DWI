package modelo;

import java.util.Date;

/**
 *
 * @author Cesar
 */
public class Ventas {

    private Date cab_fecha;
    private int cab_id;
    private String clapellido;
    private String cldireccion;
    private String nombre;
    private double precio;
    private int det_cantidad;
    private double det_precio;
    private double det_total;
    private int det_id;

    public Ventas() {
    }

    public Ventas(Date cab_fecha, int cab_id, String clapellido, String cldireccion, String nombre, double precio, int det_cantidad, double det_precio, double det_total, int det_id) {
        this.cab_fecha = cab_fecha;
        this.cab_id = cab_id;
        this.clapellido = clapellido;
        this.cldireccion = cldireccion;
        this.nombre = nombre;
        this.precio = precio;
        this.det_cantidad = det_cantidad;
        this.det_precio = det_precio;
        this.det_total = det_total;
        this.det_id = det_id;
    }

    public Date getCab_fecha() {
        return cab_fecha;
    }

    public void setCab_fecha(Date cab_fecha) {
        this.cab_fecha = cab_fecha;
    }

    public int getCab_id() {
        return cab_id;
    }

    public void setCab_id(int cab_id) {
        this.cab_id = cab_id;
    }

    public String getClapellido() {
        return clapellido;
    }

    public void setClapellido(String clapellido) {
        this.clapellido = clapellido;
    }

    public String getCldireccion() {
        return cldireccion;
    }

    public void setCldireccion(String cldireccion) {
        this.cldireccion = cldireccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDet_cantidad() {
        return det_cantidad;
    }

    public void setDet_cantidad(int det_cantidad) {
        this.det_cantidad = det_cantidad;
    }

    public double getDet_precio() {
        return det_precio;
    }

    public void setDet_precio(double det_precio) {
        this.det_precio = det_precio;
    }

    public double getDet_total() {
        return det_total;
    }

    public void setDet_total(double det_total) {
        this.det_total = det_total;
    }

    public int getDet_id() {
        return det_id;
    }

    public void setDet_id(int det_id) {
        this.det_id = det_id;
    }

    

    

    
    
}
