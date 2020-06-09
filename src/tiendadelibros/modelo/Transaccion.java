/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendadelibros.modelo;

/**
 *
 * @author Jean
 */
public class Transaccion {
    private Tipo tipo;
    private int cantidad;
    private String fecha;

    public Transaccion() {
    }

    public Transaccion(Tipo tipo, int cantidad, String fecha) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
 
}
